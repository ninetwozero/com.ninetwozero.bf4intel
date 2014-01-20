package com.ninetwozero.bf4intel.ui.news;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.datatypes.HooahToggleRequest;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.news.HooahInformation;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.json.news.NewsArticleComment;
import com.ninetwozero.bf4intel.json.news.NewsArticleRequest;
import com.ninetwozero.bf4intel.network.BaseSimpleRequest;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.network.SimplePostRequest;
import com.ninetwozero.bf4intel.resources.maps.WebsiteErrorMessageMap;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;
import com.squareup.otto.Subscribe;

import java.util.List;

public class NewsArticleFragment extends BaseLoadingListFragment {
    public static final String TAG = "NewsArticleFragment";
    public static final String ID = "articleId";

    private static final int ID_LOADER_REFRESH_ARTICLE = 4200;
    private final int ID_LOADER_REFRESH_COMMENTS = 4300;
    private final int ID_LOADER_POST_COMMENT = 4400;
    private final int ID_LOADER_HOOAH = 4500;

    private String articleId;

    public NewsArticleFragment() {
    }

    public static NewsArticleFragment newInstance(final Bundle data) {
        final NewsArticleFragment fragment = new NewsArticleFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.fragment_news_article, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected void startLoadingData() {
        final Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalStateException("NULL bundle passed to " + TAG);
        }

        articleId = arguments.getString("articleId", "");
        getLoaderManager().restartLoader(ID_LOADER_REFRESH_ARTICLE, arguments, this);
    }

    @Override
    public Loader<Result> onCreateLoader(int loaderId, Bundle bundle) {
        if (loaderId == ID_LOADER_REFRESH_ARTICLE) {
            showLoadingState(true);
            return new IntelLoader(
                getActivity(),
                new SimpleGetRequest(
                    UrlFactory.buildNewsArticleURL(bundle.getString(ID)),
                    BaseSimpleRequest.RequestType.FROM_NAVIGATION
                )
            );
        } else if (loaderId == ID_LOADER_HOOAH) {
            return new IntelLoader(
                getActivity(),
                new SimplePostRequest(
                    UrlFactory.buildNewsArticleHooahURL(articleId),
                    bundle
                )
            );
        } else if (loaderId == ID_LOADER_POST_COMMENT) {
            return new IntelLoader(
                getActivity(),
                new SimplePostRequest(
                    UrlFactory.buildNewsArticlePostCommentURL(articleId),
                    bundle
                )
            );
        }
        throw new IllegalArgumentException("No loader matching " + loaderId);
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        if (loader.getId() == ID_LOADER_REFRESH_ARTICLE) {
            final Gson gson = new Gson();
            final JsonParser parser = new JsonParser();
            final JsonElement rootObject = parser.parse(resultMessage).getAsJsonObject().get("context");
            final NewsArticleRequest container = gson.fromJson(rootObject, NewsArticleRequest.class);
            final NewsArticle article = container.getArticle();

            displayArticle(article);
        } else if (loader.getId() == ID_LOADER_HOOAH) {
            final Gson gson = new Gson();
            final JsonParser parser = new JsonParser();
            final JsonElement infoObject = parser.parse(resultMessage).getAsJsonObject().get("info");
            final HooahInformation information = gson.fromJson(infoObject, HooahInformation.class);

            View cardParent = getListView().findViewById(R.id.card_root);
            if (cardParent == null) {
                cardParent = getActivity().findViewById(R.id.card_root);
            }

            ((ImageView) cardParent.findViewById(R.id.button_hooah)).setImageResource(
                information.isVoted() ? R.drawable.ic_menu_hooah_ok : R.drawable.ic_menu_hooah
            );
            ((TextView) cardParent.findViewById(R.id.num_hooahs)).setText(
                String.valueOf(information.getVoteCount())
            );
        } else if (loader.getId() == ID_LOADER_POST_COMMENT) {
            final View parent = getView();
            if (parent == null) {
                return;
            }

            final EditText input = (EditText) parent.findViewById(R.id.input_content);
            input.setText("");
            input.clearFocus();

            toggleButton(parent, true);
            showToast(R.string.msg_comment_ok);
        }
        showLoadingState(false);
    }

    @Override
    protected void onLoadFailure(final Loader loader, final String resultMessage) {
        // TODO: Display error message in bar below ActionBar
        showToast(WebsiteErrorMessageMap.get(resultMessage));
    }

    @Subscribe
    public void onUserPressedHooah(final HooahToggleRequest request) {
        final Bundle data = new Bundle();
        data.putString("post-check-sum", "0xCAFEBABE");
        data.putString("articleId", request.getId());

        getLoaderManager().restartLoader(ID_LOADER_HOOAH, data, this);
    }

    private void initialize(final View view) {
        setupActionBar();
        setupListView(view);
        setupForm(view);
    }

    private void setupActionBar() {
        final ActionBar actionBar = getActivity().getActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setTitle(R.string.navigationdrawer_news);
    }

    private void setupListView(final View view) {
        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void setupForm(final View view) {
        view.findViewById(R.id.button_send).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    doSendComment();
                }
            }
        );
    }

    private void doSendComment() {
        final View container = getView();
        if( container == null ) {
            return;
        }

        final EditText input = (EditText) container.findViewById(R.id.input_content);
        final String comment = input.getText().toString();

        if( "".equals(comment) ) {
            input.setError(getString(R.string.msg_enter_comment));
            return;
        }

        toggleButton(container, false);
        input.setError(null);

        final Bundle data = new Bundle();
        data.putString("articleId", articleId);
        getLoaderManager().restartLoader(ID_LOADER_POST_COMMENT, data, this);
    }

    private void displayArticle(final NewsArticle article) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final View header = getHeaderView(view, article);
        populateHeaderView(header, article);
        sendCommentsToListView(article.getComments());
    }

    /* I hate this shit - header views re the worst implemented thing ever */
    private View getHeaderView(final View view, final NewsArticle article) {
        final ListView listView = getListView();
        if (article.hasComments()) {
            View header = listView.findViewById(R.id.card_root);
            if (header == null) {
                header = layoutInflater.inflate(R.layout.list_header_news_article, null);
                listView.addHeaderView(header, null, false);
            }
            return header;
        } else {
            return view.findViewById(R.id.header_when_empty);
        }
    }

    private void populateHeaderView(final View header, final NewsArticle article) {
        new NewsArticleLayout(getActivity(), header).populate(article, true);
    }

    private void sendCommentsToListView(final List<NewsArticleComment> comments) {
        ArticleCommentListAdapter adapter = (ArticleCommentListAdapter) getListAdapter();
        if (adapter == null) {
            adapter = new ArticleCommentListAdapter(getActivity());
            setListAdapter(adapter);
        }
        adapter.setItems(comments);
    }

    private void toggleButton(final View parent, final boolean enable) {
        final View button = parent.findViewById(R.id.button_send);
        button.setAlpha(enable ? 0.8f : 0.3f);
        button.setEnabled(enable);
    }
}
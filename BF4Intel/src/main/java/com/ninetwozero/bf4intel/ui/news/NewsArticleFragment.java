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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.json.news.NewsArticleComment;
import com.ninetwozero.bf4intel.json.news.NewsArticleRequest;
import com.ninetwozero.bf4intel.network.BaseSimpleRequest;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.List;

public class NewsArticleFragment extends BaseLoadingListFragment {
    public static final String TAG = "NewsArticleFragment";
    public static final String ID = "articleId";

    private static final int ID_LOADER_REFRESH_ARTICLE = 4200;
    private final int ID_LOADER_REFRESH_COMMENTS = 4300;
    private final int ID_LOADER_POST_COMMENT = 4400;
    private final int ID_LOADER_HOOAH = 4500;

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
        getLoaderManager().restartLoader(ID_LOADER_REFRESH_ARTICLE, getArguments(), this);
    }

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        return new IntelLoader(
            getActivity(),
            new SimpleGetRequest(
                UrlFactory.buildNewsArticleURL(bundle.getString(ID)),
                BaseSimpleRequest.RequestType.FROM_NAVIGATION
            )
        );
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        final Gson gson = new Gson();
        final JsonParser parser = new JsonParser();
        final JsonElement rootObject = parser.parse(resultMessage).getAsJsonObject().get("context");
        final NewsArticleRequest container = gson.fromJson(rootObject, NewsArticleRequest.class);
        final NewsArticle article = container.getArticle();

        displayArticle(article);
    }

    @Override
    protected void onLoadFailure(final Loader loader, final String resultMessage) {

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

        final ImageView button = (ImageView) container.findViewById(R.id.button_send);
        final EditText input = (EditText) container.findViewById(R.id.input_content);
        final String comment = input.getText().toString();

        if( "".equals(comment) ) {
            input.setError(getString(R.string.msg_enter_comment));
            return;
        }

        button.setEnabled(false);

        // TODO: Trigger loader for POST submit in Comment fragment

        button.setEnabled(true);
        showToast(R.string.msg_comment_ok);
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
        if (article.hasComments()) {
            final ListView listView = getListView();
            final View header = layoutInflater.inflate(R.layout.list_header_news_article, null);
            if (listView.getHeaderViewsCount() > 0) {
                listView.removeHeaderView(header);
            }
            listView.addHeaderView(header, null, false);
            return header;
        } else {
            return view.findViewById(R.id.header_when_empty);
        }
    }

    private void populateHeaderView(final View header, final NewsArticle article) {
        NewsArticleLayout.populate(getActivity(), header, article, true);
    }

    private void sendCommentsToListView(final List<NewsArticleComment> comments) {
        ArticleCommentListAdapter adapter = (ArticleCommentListAdapter) getListAdapter();
        if (adapter == null) {
            adapter = new ArticleCommentListAdapter(getActivity());
            setListAdapter(adapter);
        }
        adapter.setItems(comments);
    }
}

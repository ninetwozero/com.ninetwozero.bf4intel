package com.ninetwozero.bf4intel.ui.news;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
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
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.resources.maps.WebsiteErrorMessageMap;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;
import com.squareup.otto.Subscribe;

import java.util.List;
import java.util.Map;

public class NewsArticleFragment extends BaseLoadingFragment implements ActionMode.Callback {
    public static final String TAG = "NewsArticleFragment";
    public static final String ID = "articleId";
    public static final String COMMENT_ID = "commentId";

    private static final int ID_LOADER_REFRESH_ARTICLE = 4200;
    private final int ID_LOADER_REFRESH_COMMENTS = 4300;
    private final int ID_LOADER_POST_COMMENT = 4400;
    private final int ID_LOADER_HOOAH = 4500;
    private final int ID_LOADER_COMMENT_UPVOTE = 4600;
    private final int ID_LOADER_COMMENT_DOWNVOTE = 4700;

    private ActionMode actionMode;
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

        articleId = arguments.getString(ID, "");
        getLoaderManager().restartLoader(ID_LOADER_REFRESH_ARTICLE, arguments, this);
    }

    @Override
    public Loader<Result> onCreateLoader(final int loaderId, final Bundle bundle) {
        BaseSimpleRequest request = null;
        switch (loaderId) {
            case ID_LOADER_REFRESH_ARTICLE:
                showLoadingState(true);
                request = new SimpleGetRequest(
                    UrlFactory.buildNewsArticleURL(bundle.getString(ID)),
                    BaseSimpleRequest.RequestType.FROM_NAVIGATION
                );
                break;
            case ID_LOADER_HOOAH:
                request = new SimplePostRequest(
                    UrlFactory.buildNewsArticleHooahURL(articleId),
                    bundle
                );
                break;
            case ID_LOADER_POST_COMMENT:
                request = new SimplePostRequest(
                    UrlFactory.buildNewsArticlePostCommentURL(articleId),
                    bundle
                );
                break;
            case ID_LOADER_COMMENT_UPVOTE:
                request = new SimplePostRequest(
                    UrlFactory.buildNewsArticleCommentUpvoteURL(bundle.getString(COMMENT_ID)),
                    bundle
                );
                break;
            case ID_LOADER_COMMENT_DOWNVOTE:
                request = new SimplePostRequest(
                    UrlFactory.buildNewsArticleCommentDownvoteURL(bundle.getString(COMMENT_ID)),
                    bundle
                );
                break;
            default:
                throw new IllegalArgumentException("No loader matching " + loaderId);
        }
        return new IntelLoader(getActivity(), request);
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        if (loader.getId() == ID_LOADER_REFRESH_ARTICLE) {
            onArticleRefreshed(resultMessage);
        } else if (loader.getId() == ID_LOADER_HOOAH) {
            onArticleHooah(resultMessage);
        } else if (loader.getId() == ID_LOADER_POST_COMMENT) {
            onCommentPosted();
        } else if (loader.getId() == ID_LOADER_COMMENT_UPVOTE || loader.getId() == ID_LOADER_COMMENT_DOWNVOTE) {
            onCommentVote(resultMessage);
        }
        showLoadingState(false);
    }

    @Override
    protected void onLoadFailure(final Loader loader, final String resultMessage) {
        // TODO: Display error message in bar below ActionBar
        showToast(WebsiteErrorMessageMap.get(resultMessage));
    }

    @Override
    public boolean onCreateActionMode(final ActionMode mode, final Menu menu) {
        actionMode = mode;
        mode.getMenuInflater().inflate(R.menu.news_article_comment_cab, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(final ActionMode mode, final Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(final ActionMode mode, final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ab_action_upvote:
                doToggleHooahForComment(true);
                mode.finish();
                return true;
            case R.id.ab_action_downvote:
                doToggleHooahForComment(false);
                mode.finish();
                return true;
            case R.id.ab_action_report:
                showToast("TODO: Report a bad commment");
                return true;
            default:
                return false;
        }
    }

    @Subscribe
    public void onUserPressedHooah(final HooahToggleRequest request) {
        final Bundle data = new Bundle();
        data.putString(Keys.CHECKSUM, SessionStore.getChecksum());
        data.putString(ID, request.getId());

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
        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemLongClickListener(
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final long packedPosition = listView.getExpandableListPosition(position);
                    final int positionType = ExpandableListView.getPackedPositionType(packedPosition);
                    if (positionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                        if (actionMode == null) {
                            getActivity().startActionMode(NewsArticleFragment.this);
                        }
                        listView.setItemChecked(position, true);
                    }
                    return true;
                }
            }
        );
        listView.setOnGroupClickListener(
            new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    if (actionMode == null) {
                        return false;
                    }

                    parent.setItemChecked(parent.getCheckedItemPosition(), false);
                    parent.setItemChecked(
                        parent.getFlatListPosition(
                            ExpandableListView.getPackedPositionForGroup(groupPosition)
                        ),
                        true
                    );
                    return true;
                }
            }
        );
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
        if (container == null) {
            return;
        }

        final EditText input = (EditText) container.findViewById(R.id.input_content);
        final String comment = input.getText().toString();

        if ("".equals(comment)) {
            input.setError(getString(R.string.msg_enter_comment));
            return;
        }

        toggleButton(container, false);
        input.setError(null);

        final Bundle data = new Bundle();
        data.putString(ID, articleId);
        getLoaderManager().restartLoader(ID_LOADER_POST_COMMENT, data, this);
    }

    private void displayArticle(final NewsArticleRequest newsRequest) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final NewsArticle article = newsRequest.getArticle();
        final View header = getHeaderView(view, article);
        populateHeaderView(header, article);
        sendCommentsToListView(article.getComments(), newsRequest.getHooahStatus());
    }

    private View getHeaderView(final View view, final NewsArticle article) {
        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
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

    private void sendCommentsToListView(final List<NewsArticleComment> comments, final Map<String, Boolean> hooahMap) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        final View emptyView = view.findViewById(android.R.id.empty);

        ArticleCommentListAdapter adapter = (ArticleCommentListAdapter) listView.getExpandableListAdapter();
        if (adapter == null) {
            adapter = new ArticleCommentListAdapter(getActivity());
            listView.setAdapter(adapter);
        }

        adapter.setItems(comments, hooahMap);
        emptyView.setVisibility(comments.size() > 0 ? View.GONE : View.VISIBLE);
    }

    private void toggleButton(final View parent, final boolean enable) {
        final View button = parent.findViewById(R.id.button_send);
        button.setAlpha(enable ? 0.8f : 0.3f);
        button.setEnabled(enable);
    }

    private void doToggleHooahForComment(final boolean isUpvote) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        final int checkedItemPosition = listView.getCheckedItemPosition();
        final ArticleCommentListAdapter adapter = (ArticleCommentListAdapter) listView.getExpandableListAdapter();
        final NewsArticleComment comment = adapter.getGroup(checkedItemPosition);

        final Bundle data = new Bundle();
        data.putString(ID, articleId);
        data.putString(COMMENT_ID, comment.getId());
        data.putString(Keys.CHECKSUM, SessionStore.getChecksum());

        getLoaderManager().restartLoader(
            isUpvote ? ID_LOADER_COMMENT_UPVOTE : ID_LOADER_COMMENT_DOWNVOTE,
            data,
            this
        );
    }

    private void onArticleRefreshed(final String resultMessage) {
        final Gson gson = new Gson();
        final JsonParser parser = new JsonParser();
        final JsonElement rootObject = parser.parse(resultMessage).getAsJsonObject().get("context");
        final NewsArticleRequest articleRequest = gson.fromJson(rootObject, NewsArticleRequest.class);

        displayArticle(articleRequest);
    }

    private void onArticleHooah(final String resultMessage) {
        final Gson gson = new Gson();
        final JsonParser parser = new JsonParser();
        final JsonElement dataObject = parser.parse(resultMessage).getAsJsonObject().get("data");
        final JsonElement infoObject = dataObject.getAsJsonObject().get("info").getAsJsonObject();
        final HooahInformation information = gson.fromJson(infoObject, HooahInformation.class);

        final ExpandableListView listView = (ExpandableListView) getView().findViewById(android.R.id.list);
        View cardParent = listView.findViewById(R.id.card_root);
        if (cardParent == null) {
            cardParent = getActivity().findViewById(R.id.card_root);
        }

        new NewsArticleLayout(getActivity(), cardParent).updateHooahForArticle(
            information.getVoteCount(),
            information.isVoted()
        );
    }

    private void onCommentPosted() {
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


    private void onCommentVote(final String resultMessage) {
        // {"type":"success","message":"Comment upvote created","data":{"id":"2955063418463155072"}}
        // ^ or v instead of star in actionbar?
        Log.d(TAG, "output => " + resultMessage);
    }


    @Override
    public void onDestroyActionMode(final ActionMode mode) {
        actionMode = null;

        final View view = getView();
        if (view == null) {
            return;
        }

        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        listView.setItemChecked(listView.getCheckedItemPosition(), false);
    }
}

package com.ninetwozero.bf4intel.ui.news;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.JsonElement;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.events.HooahToggleRequest;
import com.ninetwozero.bf4intel.events.news.NewsArticleRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.news.HooahInformation;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.json.news.NewsArticleComment;
import com.ninetwozero.bf4intel.json.news.NewsArticleRequest;
import com.ninetwozero.bf4intel.network.SimplePostRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.resources.maps.WebsiteErrorMessageMap;
import com.ninetwozero.bf4intel.services.news.NewsArticleService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class NewsArticleFragment extends BaseLoadingFragment implements ActionMode.Callback {
    public static final String TAG = "NewsArticleFragment";
    public static final String ID = "articleId";
    public static final String COMMENT_ID = "commentId";

    private static final int ID_REQUEST_REFRESH_ARTICLE = 4200;
    private final int ID_REQUEST_POST_COMMENT = 4400;
    private final int ID_REQUEST_HOOAH = 4500;
    private final int ID_REQUEST_COMMENT_UPVOTE = 4600;
    private final int ID_REQUEST_COMMENT_DOWNVOTE = 4700;

    private final String FLAG_SHOW_LOADING = "showLoading";

    private ActionMode actionMode;
    private String articleId;
    private URL articleUrl;

    public NewsArticleFragment() {
    }

    public static NewsArticleFragment newInstance(final Bundle data) {
        final NewsArticleFragment fragment = new NewsArticleFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle icicle) {
        articleId = getArguments().getString(ID, "");
        articleUrl = UrlFactory.buildNewsArticleURL(articleId);

        super.onCreate(icicle);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.fragment_news_article, parent, false);
        initialize(view);
        return view;
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        final Bundle arguments = getArguments();
        if (isReloading || !Bf4Intel.isConnectedToNetwork()) {
            return;
        }

        showLoadingState(arguments.getBoolean(FLAG_SHOW_LOADING, true));
        isReloading = true;

        final Intent intent = new Intent(getActivity(), NewsArticleService.class);
        intent.putExtra(NewsArticleService.INTENT_ARTICLE_ID, articleId);
        getActivity().startService(intent);
    }

    private void doRequest(final int id, final Bundle bundle) {
        switch (id) {
            case ID_REQUEST_HOOAH:
                Bf4Intel.getRequestQueue().add(fetchRequestForHooah(bundle));
                break;

            case ID_REQUEST_POST_COMMENT:
                Bf4Intel.getRequestQueue().add(fetchRequestForPostComment(bundle));
                break;

            case ID_REQUEST_COMMENT_UPVOTE:
                Bf4Intel.getRequestQueue().add(fetchRequestForCommentUpvote(bundle));
                break;

            case ID_REQUEST_COMMENT_DOWNVOTE:
                Bf4Intel.getRequestQueue().add(fetchRequestForCommentDownvote(bundle));
                break;

            default:
                Log.w(getClass().getSimpleName(), "No request matching " + id);
        }
    }

    @Subscribe
    public void onArticleRefreshed(NewsArticleRefreshedEvent response) {
        displayArticle(response.getRequest());
        showLoadingState(false);
        isReloading = false;
    }

    private Request<HooahInformation> fetchRequestForHooah(Bundle bundle) {
        return new SimplePostRequest<HooahInformation>(
            UrlFactory.buildNewsArticleHooahURL(articleId),
            bundle,
            this
        ) {
            @Override
            protected HooahInformation doParse(String json) {
                final JsonElement dataObject = parser.parse(json).getAsJsonObject().get("data");
                final JsonElement infoObject = dataObject.getAsJsonObject().get("info").getAsJsonObject();
                final HooahInformation information = gson.fromJson(infoObject, HooahInformation.class);
                return information;
            }

            @Override
            protected void deliverResponse(HooahInformation response) {
                final ExpandableListView listView = (ExpandableListView) getView().findViewById(android.R.id.list);
                View cardParent = listView.findViewById(R.id.card_root);
                if (cardParent == null) {
                    cardParent = getActivity().findViewById(R.id.card_root);
                }

                new NewsArticleLayout(getActivity(), cardParent).updateHooahForArticle(
                    response.getVoteCount(),
                    response.isVoted()
                );
            }
        };
    }

    private Request<Object> fetchRequestForPostComment(Bundle bundle) {
        return new SimplePostRequest<Object>(
            UrlFactory.buildNewsArticleHooahURL(articleId),
            bundle,
            this
        ) {
            @Override
            protected Object doParse(String json) {
                return json;
            }

            @Override
            protected void deliverResponse(Object response) {
                final View parent = getView();
                final EditText input = (EditText) parent.findViewById(R.id.input_content);
                input.setText("");
                input.clearFocus();

                toggleButton(parent, true);
                showToast(R.string.msg_comment_ok);
            }
        };
    }

    private Request<Object> fetchRequestForCommentUpvote(Bundle bundle) {
        return new SimplePostRequest<Object>(
            UrlFactory.buildNewsArticleCommentUpvoteURL(bundle.getString(COMMENT_ID)),
            bundle,
            this
        ) {
            @Override
            protected Object doParse(String json) {
                return json;
            }

            @Override
            protected void deliverResponse(Object response) {
                final Bundle data = getArguments();
                data.putBoolean(FLAG_SHOW_LOADING, false);
                doRequest(ID_REQUEST_REFRESH_ARTICLE, data);
            }
        };
    }

    private Request<Object> fetchRequestForCommentDownvote(Bundle bundle) {
        return new SimplePostRequest<Object>(
            UrlFactory.buildNewsArticleCommentDownvoteURL(bundle.getString(COMMENT_ID)),
            bundle,
            this
        ) {
            @Override
            protected Object doParse(String json) {
                return json;
            }

            @Override
            protected void deliverResponse(Object response) {
                final Bundle data = getArguments();
                data.putBoolean(FLAG_SHOW_LOADING, false);
                doRequest(ID_REQUEST_REFRESH_ARTICLE, data);
            }
        };
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);

        String errorKey = error.getMessage();
        if (errorKey.contains("upvote")) {
            errorKey = WebsiteErrorMessageMap.ALREADY_UPVOTED;
        }
        showToast(WebsiteErrorMessageMap.get(errorKey));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_news_article, menu);

        final MenuItem shareItem = menu.findItem(R.id.ab_action_share);
        if (shareItem != null) {
            MenuItemCompat.setActionProvider(shareItem, new ShareActionProvider(getActivity()));
            final Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, articleUrl.toString());
            intent.setType("text/plain");
            ((ShareActionProvider) MenuItemCompat.getActionProvider(shareItem)).setShareIntent(intent);
        }
    }

    @Override
    public boolean onCreateActionMode(final ActionMode mode, final Menu menu) {
        actionMode = mode;
        mode.getMenuInflater().inflate(R.menu.news_article_comment_cab, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(final ActionMode mode, final Menu menu) {
        menu.setGroupVisible(R.id.ab_action_voting, SessionStore.isLoggedIn());
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
            default:
                return false;
        }
    }

    @Subscribe
    public void onUserPressedHooah(final HooahToggleRequest request) {
        if (!SessionStore.isLoggedIn()) {
            showToast(R.string.toast_please_log_in);
            return;
        }

        final Bundle data = new Bundle();
        data.putString(Keys.CHECKSUM, SessionStore.getChecksum());
        data.putString(ID, request.getId());

        doRequest(ID_REQUEST_HOOAH, data);
    }

    private void initialize(final View view) {
        setupErrorMessage(view);
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

                    final int oldPosition = parent.getCheckedItemPosition();
                    final int newPosition = parent.getFlatListPosition(
                        ExpandableListView.getPackedPositionForGroup(groupPosition)
                    );

                    if (oldPosition == newPosition) {
                        actionMode.finish();
                        return true;
                    }

                    parent.setItemChecked(oldPosition, false);
                    parent.setItemChecked(newPosition, true);
                    return true;
                }
            }
        );
    }

    private void setupForm(final View view) {
        if (!SessionStore.isLoggedIn()) {
            view.findViewById(R.id.wrap_form).setVisibility(View.GONE);
            return;
        }

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

        if (!SessionStore.isLoggedIn()) {
            showToast(R.string.toast_please_log_in);
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
        doRequest(ID_REQUEST_POST_COMMENT, data);
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
        button.setAlpha(enable ? ALPHA_ENABLED : ALPHA_DISABLED);
        button.setEnabled(enable);
    }

    private void doToggleHooahForComment(final boolean isUpvote) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        final int checkedItemPosition = listView.getCheckedItemPosition() - listView.getHeaderViewsCount();
        final ArticleCommentListAdapter adapter = (ArticleCommentListAdapter) listView.getExpandableListAdapter();
        final NewsArticleComment comment = adapter.getGroup(checkedItemPosition);

        final Bundle data = new Bundle();
        data.putString(ID, articleId);
        data.putString(COMMENT_ID, comment.getId());
        data.putString(Keys.CHECKSUM, SessionStore.getChecksum());

        doRequest(isUpvote ? ID_REQUEST_COMMENT_UPVOTE : ID_REQUEST_COMMENT_DOWNVOTE, data);
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

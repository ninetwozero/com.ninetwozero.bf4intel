package com.ninetwozero.bf4intel.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.events.news.NewsArticleRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.json.news.NewsArticleComment;
import com.ninetwozero.bf4intel.json.news.NewsArticleRequest;
import com.ninetwozero.bf4intel.resources.maps.WebsiteErrorMessageMap;
import com.ninetwozero.bf4intel.services.news.NewsArticleService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class NewsArticleFragment extends BaseLoadingFragment {
    public static final String ID = "articleId";
    private final String FLAG_SHOW_LOADING = "showLoading";

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
        startLoadingData(getArguments().getBoolean(FLAG_SHOW_LOADING, true));
    }

    @Override
    protected void startLoadingData(boolean showLoading) {
        if (isReloading || !Bf4Intel.isConnectedToNetwork()) {
            return;
        }

        showLoadingState(showLoading);
        isReloading = true;

        final Intent intent = new Intent(getActivity(), NewsArticleService.class);
        intent.putExtra(NewsArticleService.INTENT_ARTICLE_ID, articleId);
        getActivity().startService(intent);
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onArticleRefreshed(NewsArticleRefreshedEvent response) {
        displayArticle(response.getRequest());
        showLoadingState(false);
        isReloading = false;
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

    private void initialize(final View view) {
        setupErrorMessage(view);
        setupActionBar();
        setupSwipeRefreshLayout(view);
        setupListView(view);
    }

    private void setupActionBar() {
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setTitle(R.string.navigationdrawer_news);
    }

    private void setupListView(final View view) {
        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        setCustomEmptyText(view, R.string.empty_text_news);
    }

    private void displayArticle(final NewsArticleRequest newsRequest) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final NewsArticle article = newsRequest.getArticle();
        final View header = getHeaderView(view);
        populateHeaderView(header, article);

        sendCommentsToListView(prepareCommentsForArticle(article.getComments()), newsRequest.getHooahStatus());
    }

    private List<NewsArticleComment> prepareCommentsForArticle(final List<NewsArticleComment> comments) {
        if (comments.size() == 0) {
            comments.add(new NewsArticleComment(ArticleCommentListAdapter.ID_NO_REAL_COMMENTS));
        }
        return comments;
    }

    private View getHeaderView(final View view) {
        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        View header = listView.findViewById(R.id.card_root);
        if (header == null) {
            header = layoutInflater.inflate(R.layout.list_header_news_article, null);
            listView.addHeaderView(header, null, false);
        }
        return header;
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
}

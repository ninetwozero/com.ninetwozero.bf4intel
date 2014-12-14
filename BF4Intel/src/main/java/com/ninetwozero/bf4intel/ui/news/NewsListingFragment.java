package com.ninetwozero.bf4intel.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.events.news.NewsListingRefreshedEvent;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.resources.maps.WebsiteErrorMessageMap;
import com.ninetwozero.bf4intel.services.news.NewsListingService;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

public class NewsListingFragment extends BaseLoadingListFragment {
    public static final String ID = "articleId";

    private static final int pageId = 1;

    public NewsListingFragment() {
    }

    public static NewsListingFragment newInstance(final Bundle data) {
        final NewsListingFragment fragment = new NewsListingFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.fragment_news_listing, parent, false);
        initialize(view);
        return view;
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final Bundle data = new Bundle();
        data.putString(NewsArticleFragment.ID, String.valueOf(id));

        final Intent intent = new Intent(getActivity(), SingleFragmentActivity.class);
        intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_TYPE, FragmentFactory.Type.NEWS_ITEM.ordinal());
        intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, data);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData(true);
    }

    @Override
    protected void startLoadingData(boolean showLoading) {
        if (isReloading || !Bf4Intel.isConnectedToNetwork()) {
            return;
        }

        showLoadingState(showLoading);
        isReloading = true;

        final Intent intent = new Intent(getActivity(), NewsListingService.class);
        intent.putExtra(NewsListingService.INTENT_PAGE_ID, pageId);
        getActivity().startService(intent);
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onNewsRefreshed(NewsListingRefreshedEvent response) {
        sendItemsToListView(response.getRequest().getArticles());
        showLoadingState(false);
        isReloading = false;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        showToast(WebsiteErrorMessageMap.get(error.getMessage()));
    }

    private void initialize(final View view) {
        setupErrorMessage(view);
        setupSwipeRefreshLayout(view);
        setupListView(view);
    }

    private void setupListView(final View view) {
        setCustomEmptyText(view, R.string.empty_text_news);
    }

    private void sendItemsToListView(final List<NewsArticle> items) {
        final View parent = getView();
        if (parent == null) {
            return;
        }

        NewsListAdapter adapter = (NewsListAdapter) getListAdapter();
        if (adapter == null) {
            adapter = new NewsListAdapter(getActivity());
            setListAdapter(adapter);
        }
        adapter.setItems(items);
    }
}

package com.ninetwozero.bf4intel.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.abstractions.BaseListFragment;
import com.ninetwozero.bf4intel.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.adapters.FeedAdapter;
import com.ninetwozero.bf4intel.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public class NewsListingFragment extends BaseListFragment {
    public NewsListingFragment() {
    }

    public static NewsListingFragment newInstance() {
        final NewsListingFragment fragment = new NewsListingFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static NewsListingFragment newInstance(final Bundle data) {
        final NewsListingFragment fragment = new NewsListingFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = mInflater.inflate(R.layout.fragment_news_listing, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setupListView(view);
        updateActionBar(getActivity(), "NEWS", R.drawable.ic_actionbar_news);
    }

    private void setupListView(final View view) {
        if (view == null) {
            return;
        }

        final FeedAdapter feedAdapter = new FeedAdapter(getActivity(), getDummyItems());
        setListAdapter(feedAdapter);
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final Fragment itemFragment = mFragmentManager.findFragmentByTag("NewsArticleFragment");

        if (itemFragment != null && itemFragment instanceof NewsArticleFragment) {
            ((NewsArticleFragment) itemFragment).loadArticle(id);
        } else {
            final Bundle data = new Bundle();
            data.putLong(NewsArticleFragment.ID, id);

            final Intent intent = new Intent(getActivity(), SingleFragmentActivity.class)
                .putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, data)
                .putExtra(SingleFragmentActivity.INTENT_FRAGMENT_TYPE, FragmentFactory.Type.NEWS_ITEM.ordinal());
            startActivity(intent);
        }
    }

    private List<Integer> getDummyItems() {
        List<Integer> items = new ArrayList<Integer>(10);
        items.add(R.layout.list_item_news);
        items.add(R.layout.list_item_news);
        items.add(R.layout.list_item_news);
        items.add(R.layout.list_item_news);
        return items;
    }
}
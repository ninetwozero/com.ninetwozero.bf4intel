package com.ninetwozero.battlelog.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractListFragment;
import com.ninetwozero.battlelog.adapters.FeedAdapter;
import com.ninetwozero.battlelog.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public class ForumListingFragment extends AbstractListFragment {
    public ForumListingFragment() {}

    public static ForumListingFragment newInstance() {
        final ForumListingFragment fragment = new ForumListingFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = mInflater.inflate(R.layout.fragment_forum_listing, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setupListView(view);
        updateActionBar(getActivity(), "FORUMS", R.drawable.ic_actionbar_forums);
    }

    private void setupListView(final View view) {
        if( view == null ) {
            return;
        }

        final FeedAdapter feedAdapter = new FeedAdapter(getActivity(), getDummyItems());
        setListAdapter(feedAdapter);
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final Fragment itemFragment = mFragmentManager.findFragmentByTag("ThreadListingFragment");

        /* FIXME: Get actual id's */
        final Bundle data = new Bundle();
        data.putLong(ThreadListingFragment.ID, id);

        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.activity_root, FragmentFactory.get(FragmentFactory.Type.THREAD_LISTING), "ThreadListingFragment");
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        showToast("Opening forum ID: " + id);
    }

    private List<Integer> getDummyItems() {
        List<Integer> items = new ArrayList<Integer>(10);
        items.add(R.layout.list_item_forum);
        items.add(R.layout.list_item_forum);
        items.add(R.layout.list_item_forum);
        items.add(R.layout.list_item_forum);
        items.add(R.layout.list_item_forum);
        items.add(R.layout.list_item_forum);
        items.add(R.layout.list_item_forum);
        items.add(R.layout.list_item_forum);
        items.add(R.layout.list_item_forum);
        items.add(R.layout.list_item_forum);
        return items;
    }
}

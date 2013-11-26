package com.ninetwozero.bf4intel.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.adapters.FeedAdapter;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;
import com.ninetwozero.bf4intel.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public class ForumListingFragment extends BaseListFragment {
    public ForumListingFragment() {
    }

    public static ForumListingFragment newInstance() {
        final ForumListingFragment fragment = new ForumListingFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static ForumListingFragment newInstance(final Bundle data) {
        final ForumListingFragment fragment = new ForumListingFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = this.layoutInflater.inflate(R.layout.fragment_forum_listing, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setupListView(view);
        updateActionBar(getActivity(), "FORUMS", R.drawable.ic_actionbar_forums);
    }

    private void setupListView(final View view) {
        if (view == null) {
            return;
        }

        final DummyAdapter feedAdapter = new DummyAdapter(getActivity(), getDummyItems());
        setListAdapter(feedAdapter);
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        /* FIXME: Get actual id's */
        final Bundle data = new Bundle();
        data.putLong(ThreadListingFragment.FORUM_ID, id);

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_root, FragmentFactory.get(FragmentFactory.Type.THREAD_LISTING), "ThreadListingFragment");
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        showToast("Opening forum FORUM_ID: " + id);
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

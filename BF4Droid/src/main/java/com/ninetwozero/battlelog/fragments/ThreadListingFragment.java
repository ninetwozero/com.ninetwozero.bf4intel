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

public class ThreadListingFragment extends AbstractListFragment {
    public static final String FORUM_ID = "forumId";

    public ThreadListingFragment() {
    }

    public static ThreadListingFragment newInstance() {
        final ThreadListingFragment fragment = new ThreadListingFragment();
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
        updateActionBar(getActivity(), "Dummy Forum (Uncategorized)", R.drawable.ic_actionbar_forums);
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
        final Fragment itemFragment = mFragmentManager.findFragmentByTag("PostListingFragment");

        /* FIXME: Get actual id's */
        final Bundle data = new Bundle();
        data.putLong(PostListingFragment.THREAD_ID, id);

        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.activity_root, FragmentFactory.get(FragmentFactory.Type.POST_LISTING), "PostListingFragment");
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        showToast("Opening thread THREAD_ID: " + id);
    }

    private List<Integer> getDummyItems() {
        List<Integer> items = new ArrayList<Integer>(10);
        items.add(R.layout.list_item_thread);
        items.add(R.layout.list_item_thread);
        items.add(R.layout.list_item_thread);
        items.add(R.layout.list_item_thread);
        items.add(R.layout.list_item_thread);
        items.add(R.layout.list_item_thread);
        items.add(R.layout.list_item_thread);
        items.add(R.layout.list_item_thread);
        items.add(R.layout.list_item_thread);
        items.add(R.layout.list_item_thread);
        return items;
    }
}

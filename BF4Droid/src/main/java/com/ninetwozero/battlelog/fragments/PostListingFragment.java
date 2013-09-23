package com.ninetwozero.battlelog.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractListFragment;
import com.ninetwozero.battlelog.adapters.FeedAdapter;

import java.util.ArrayList;
import java.util.List;

public class PostListingFragment extends AbstractListFragment {
    public static final String THREAD_ID = "threadId";

    public PostListingFragment() {}

    public static PostListingFragment newInstance() {
        final PostListingFragment fragment = new PostListingFragment();
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
        updateActionBar(getActivity(), "Dummy Thread #0001", R.drawable.ic_actionbar_forums);
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
        /* TODO: Quote or something? */
        showToast("Clicked on POST ID: " + id);
    }

    private List<Integer> getDummyItems() {
        List<Integer> items = new ArrayList<Integer>(10);
        items.add(R.layout.list_item_thread_post);
        return items;
    }
}

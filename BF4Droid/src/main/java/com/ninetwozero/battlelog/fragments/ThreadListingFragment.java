package com.ninetwozero.battlelog.fragments;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.BaseListFragment;
import com.ninetwozero.battlelog.activities.SingleFragmentActivity;
import com.ninetwozero.battlelog.adapters.FeedAdapter;
import com.ninetwozero.battlelog.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public class ThreadListingFragment extends BaseListFragment {
    public static final String FORUM_ID = "forumId";
    private long mForumId;

    public ThreadListingFragment() {
    }

    public static ThreadListingFragment newInstance() {
        final ThreadListingFragment fragment = new ThreadListingFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static ThreadListingFragment newInstance(final Bundle data) {
        final ThreadListingFragment fragment = new ThreadListingFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = mInflater.inflate(R.layout.fragment_forum_listing, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final Bundle data = new Bundle();
        data.putLong(PostListingFragment.THREAD_ID, id);

        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.activity_root, FragmentFactory.get(FragmentFactory.Type.POST_LISTING), "PostListingFragment");
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        showToast("Opening thread THREAD_ID: " + id);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_feed, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.ab_action_new) {
            Intent intent = new Intent(getActivity(), SingleFragmentActivity.class);
            intent = intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, getDataForThreadCreation());
            intent = intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_TYPE, FragmentFactory.Type.THREAD_CREATING.ordinal());
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    private Bundle getDataForThreadCreation() {
        final Bundle data = new Bundle();
        data.putLong(PostListingFragment.THREAD_ID, mForumId);
        return data;
    }
}

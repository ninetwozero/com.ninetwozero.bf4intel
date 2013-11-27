package com.ninetwozero.bf4intel.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ListView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.adapters.FeedAdapter;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class PostListingFragment extends BaseListFragment {
    public static final String THREAD_ID = "threadId";
    private long mThreadId;

    public PostListingFragment() {
    }

    public static PostListingFragment newInstance() {
        final PostListingFragment fragment = new PostListingFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static PostListingFragment newInstance(final Bundle data) {
        final PostListingFragment fragment = new PostListingFragment();
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

        final View view = this.layoutInflater.inflate(R.layout.fragment_forum_listing, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        /* TODO: Quote or something? */
        showToast("Clicked on POST ID: " + id);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_feed, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ab_action_new) {
            Intent intent = new Intent(getActivity(), SingleFragmentActivity.class);
            intent = intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, getDataForPostCreation());
            intent = intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_TYPE, FragmentFactory.Type.POST_CREATING.ordinal());
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize(final View view) {
        setupListView(view);
        updateActionBar(getActivity(), "Dummy Thread #0001", R.drawable.ic_actionbar_forums);
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
        items.add(R.layout.list_item_thread_post);
        return items;
    }

    private Bundle getDataForPostCreation() {
        final Bundle data = new Bundle();
        data.putLong(PostListingFragment.THREAD_ID, mThreadId);
        return data;
    }
}

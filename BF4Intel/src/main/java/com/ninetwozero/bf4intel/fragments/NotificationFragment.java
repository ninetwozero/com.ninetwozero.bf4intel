package com.ninetwozero.bf4intel.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.BaseListFragment;
import com.ninetwozero.bf4intel.adapters.FeedAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends BaseListFragment {
    public static final String FORUM_ID = "forumId";

    public NotificationFragment() {
    }

    public static NotificationFragment newInstance() {
        final NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static NotificationFragment newInstance(final Bundle data) {
        final NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = mInflater.inflate(R.layout.generic_list, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setupListView(view);
        updateActionBar(getActivity(), "NOTIFICATIONS", R.drawable.ic_actionbar_logo);
    }

    private void setupListView(final View view) {
        if (view == null) {
            return;
        }

        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        final FeedAdapter feedAdapter = new FeedAdapter(getActivity(), getDummyItems());
        setListAdapter(feedAdapter);
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        showToast("Clicked on notification: " + id);
    }

    private List<Integer> getDummyItems() {
        List<Integer> items = new ArrayList<Integer>(10);
        items.add(R.layout.list_item_notification);
        items.add(R.layout.list_item_notification);
        items.add(R.layout.list_item_notification);
        items.add(R.layout.list_item_notification);
        items.add(R.layout.list_item_notification);
        items.add(R.layout.list_item_notification);
        items.add(R.layout.list_item_notification);
        items.add(R.layout.list_item_notification);
        items.add(R.layout.list_item_notification);
        items.add(R.layout.list_item_notification);
        return items;
    }
}

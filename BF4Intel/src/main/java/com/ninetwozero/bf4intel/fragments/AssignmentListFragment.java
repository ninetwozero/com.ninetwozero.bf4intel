package com.ninetwozero.bf4intel.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.ListView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.abstractions.BaseListFragment;
import com.ninetwozero.bf4intel.adapters.FeedAdapter;

import java.util.ArrayList;
import java.util.List;

public class AssignmentListFragment extends BaseListFragment {
    public AssignmentListFragment() {
    }

    public static AssignmentListFragment newInstance() {
        final AssignmentListFragment fragment = new AssignmentListFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static AssignmentListFragment newInstance(final Bundle data) {
        final AssignmentListFragment fragment = new AssignmentListFragment();
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

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        showToast("TODO: Open up SingleFragmentActivity that lists more details!");
    }

    private void initialize(final View view) {
        // Todo: Appropriate ICON for assignments
        updateActionBar(getActivity(), R.string.assignments, R.drawable.ic_actionbar_logo);
        setupListView(view);
    }

    private void setupListView(final View view) {
        if (view == null) {
            return;
        }

        // TODO: Setup ListView here

        final FeedAdapter feedAdapter = new FeedAdapter(getActivity(), getDummyItems());
        setListAdapter(feedAdapter);
    }

    private List<Integer> getDummyItems() {
        List<Integer> items = new ArrayList<Integer>(10);
        items.add(R.layout.list_item_assignments);
        items.add(R.layout.list_item_assignments);
        items.add(R.layout.list_item_assignments);
        items.add(R.layout.list_item_assignments);
        items.add(R.layout.list_item_assignments);
        items.add(R.layout.list_item_assignments);
        items.add(R.layout.list_item_assignments);
        items.add(R.layout.list_item_assignments);
        items.add(R.layout.list_item_assignments);
        items.add(R.layout.list_item_assignments);
        return items;
    }
}

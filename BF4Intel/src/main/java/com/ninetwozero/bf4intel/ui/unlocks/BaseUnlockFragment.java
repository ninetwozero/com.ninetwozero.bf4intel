package com.ninetwozero.bf4intel.ui.unlocks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseExpandableIntelAdapter;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;

public abstract class BaseUnlockFragment extends BaseLoadingListFragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.fragment_unlocks, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingState(false);
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        requestQueue.add(fetchRequest(getArguments()));
    }
    private void initialize(final View view) {
        setupListView(view);
    }

    private void setupListView(final View view) {
        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        final TextView emptyTextView = (TextView) view.findViewById(android.R.id.empty);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
        emptyTextView.setText(R.string.msg_no_unlocks);
    }

    protected void toggleAllRows(final boolean expand) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }
        final BaseExpandableIntelAdapter adapter = (BaseExpandableIntelAdapter) listView.getExpandableListAdapter();
        for (int i = 0, max = adapter.getGroupCount(); i < max; i++) {
            if (expand) {
                listView.expandGroup(i);
            } else {
                listView.collapseGroup(i);
            }
        }
    }

    protected abstract Request<?> fetchRequest(Bundle bundle);
}

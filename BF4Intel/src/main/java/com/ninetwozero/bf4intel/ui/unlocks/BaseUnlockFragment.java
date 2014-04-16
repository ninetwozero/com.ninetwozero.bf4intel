package com.ninetwozero.bf4intel.ui.unlocks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;

public abstract class BaseUnlockFragment extends BaseLoadingFragment {

    protected GridView gridView;
    protected TextView emptyTextView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);
        return layoutInflater.inflate(R.layout.fragment_unlocks, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);
    }

    @Override
    public void onResume() {
        showLoadingState(false);
        super.onResume();
    }

    @Override
    protected void startLoadingData() {

    }

    private void initialize(final View view) {
        setupListView(view);
    }

    private void setupListView(final View view) {
        emptyTextView = (TextView) view.findViewById(android.R.id.empty);
        emptyTextView.setText(R.string.msg_no_unlocks);

        gridView = (GridView) view.findViewById(android.R.id.list);
        gridView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
        gridView.setEmptyView(emptyTextView);
    }
}

package com.ninetwozero.bf4intel.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.BaseFragment;

public class BattleReportFragment extends BaseFragment {
    public static final String ID = "reportId";

    private long mId;

    public BattleReportFragment() {
    }

    public static BattleReportFragment newInstance() {
        final BattleReportFragment fragment = new BattleReportFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static BattleReportFragment newInstance(final Bundle data) {
        final BattleReportFragment fragment = new BattleReportFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = mInflater.inflate(R.layout.fragment_battle_report, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadReport(mId);
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "Loading...", R.drawable.ic_actionbar_feed);
    }

    public void loadReport(final long id) {
        mId = id;
        if (mId > 0) {
            /* TODO: Do actual loading */
        }
    }
}

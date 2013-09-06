package com.ninetwozero.battlelog.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.adapters.ListRowAdapter;
import com.ninetwozero.battlelog.datatypes.ListRow;
import com.ninetwozero.battlelog.datatypes.ListRowType;
import com.ninetwozero.battlelog.factories.ListRowFactory;

import java.util.ArrayList;
import java.util.List;

public class BattleFeedFragment extends ListFragment {
    private LayoutInflater mInflater;

    public BattleFeedFragment() {}

    public static BattleFeedFragment newInstance() {
        final BattleFeedFragment fragment = new BattleFeedFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        mInflater = inflater;

        final View view = mInflater.inflate(R.layout.fragment_feed, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        //setListAdapter(mFeedAdapter);
    }
}

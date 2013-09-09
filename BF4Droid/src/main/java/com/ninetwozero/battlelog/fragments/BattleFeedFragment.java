package com.ninetwozero.battlelog.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.adapters.FeedAdapter;
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
        setupActionBar();
        setupListView(view);
    }

    private void setupActionBar() {
        final Activity activity = getActivity();
        if( activity == null ) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle("BATTLE FEED");
        actionBar.setIcon(R.drawable.test_rank31); //TODO: Feed logo or something
    }

    private void setupListView(final View view) {
        if( view == null ) {
            return;
        }

        // TODO: Setup ListView here

        final FeedAdapter feedAdapter = new FeedAdapter(getActivity(), getDummyItems());
        setListAdapter(feedAdapter);
    }

    private List<Integer> getDummyItems() {
        List<Integer> items = new ArrayList<Integer>(10);
        items.add(R.layout.list_feed_status);
        items.add(R.layout.list_feed_wallpost);
        items.add(R.layout.list_feed_favorite_server);
        items.add(R.layout.list_feed_gameaccess);
        items.add(R.layout.list_feed_battlereport);
        return items;
    }
}

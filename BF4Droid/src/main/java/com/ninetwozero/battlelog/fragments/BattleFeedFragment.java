package com.ninetwozero.battlelog.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractListFragment;
import com.ninetwozero.battlelog.adapters.FeedAdapter;
import com.ninetwozero.battlelog.adapters.ListRowAdapter;
import com.ninetwozero.battlelog.datatypes.ListRow;
import com.ninetwozero.battlelog.datatypes.ListRowType;
import com.ninetwozero.battlelog.factories.FragmentFactory;
import com.ninetwozero.battlelog.factories.ListRowFactory;

import java.util.ArrayList;
import java.util.List;

public class BattleFeedFragment extends AbstractListFragment {
    public BattleFeedFragment() {}

    public static BattleFeedFragment newInstance() {
        final BattleFeedFragment fragment = new BattleFeedFragment();
        fragment.setArguments(new Bundle());
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

        final View view = mInflater.inflate(R.layout.fragment_feed, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_feed, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.action_new ) {
            final FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.activity_root, FragmentFactory.get(FragmentFactory.Type.BATTLE_FEED_POSTING));
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "BATTLE FEED", R.drawable.ic_actionbar_feed);
        setupListView(view);
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

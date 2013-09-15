package com.ninetwozero.battlelog.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractListFragment;
import com.ninetwozero.battlelog.activities.SlidingMenuAccessInterface;
import com.ninetwozero.battlelog.adapters.ListRowAdapter;
import com.ninetwozero.battlelog.datatypes.ListRow;
import com.ninetwozero.battlelog.datatypes.ListRowType;
import com.ninetwozero.battlelog.factories.FragmentFactory;
import com.ninetwozero.battlelog.factories.ListRowFactory;
import com.ninetwozero.battlelog.utils.ExternalAppLauncher;

import java.util.ArrayList;
import java.util.List;

public class SlidingMenuFragment extends AbstractListFragment {
    public SlidingMenuFragment() {}

    public static SlidingMenuFragment newInstance() {
        final SlidingMenuFragment fragment = new SlidingMenuFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = inflater.inflate(R.layout.fragment_slidingmenu, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final ListRow item = ((ListRowAdapter) getListAdapter()).getItem(position);
        if( item.hasIntent() ) {
            startActivity(item.getIntent());
        } else if( item.hasFragmentType() ) {
            final FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.activity_root, FragmentFactory.get(item.getFragmentType()));
            transaction.commit();
        }
        ((SlidingMenuAccessInterface) getActivity()).toggle();
    }

    private void initialize(final View view) {
        final ListRowAdapter slidingMenuAdapter = new ListRowAdapter(getActivity(), getItemsForMenu());
        setListAdapter(slidingMenuAdapter);
    }

    private List<ListRow> getItemsForMenu() {
        final List<ListRow> items = new ArrayList<ListRow>();

        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "LOGGED IN AS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_ACCOUNT, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "SELECTED SOLDIER"));
        items.add(ListRowFactory.create(ListRowType.SIDE_SOLDIER, new Bundle()));
        // TODO: These should be displayed in the "sidebar" while viewing "profile"?
        //items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "WEAPONS"));
        //items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "UNLOCKS"));
        //items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "ASSIGNMENTS"));
        //items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "SETTINGS"));


        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "SELECTED PLATOON"));
        items.add(ListRowFactory.create(ListRowType.SIDE_PLATOON, new Bundle()));
        // TODO: These should be displayed in the "sidebar" while viewing "platoon"?
        //items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "CREATE NEW"));
        //items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "INVITES"));
        //items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "SETTINGS"));

        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "SOCIAL"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "NEWS", FragmentFactory.Type.NEWS_LISTING));
        items.add(ListRowFactory.create(ListRowType.SIDE_FEED, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "BATTLE CHAT", ExternalAppLauncher.getIntent(getActivity(), "com.ninetwozero.battlechat")));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "SERVERS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "FORUMS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "VIEW FORUMS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "SAVED THREADS"));

        return items;
    }
}

package com.ninetwozero.battlelog.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractListFragment;
import com.ninetwozero.battlelog.activities.SlidingMenuAccessInterface;
import com.ninetwozero.battlelog.adapters.ExpandableListRowAdapter;
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

    private void initialize(final View view) {
        setupListView(view);
    }

    private void setupListView(final View view) {
        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        final ExpandableListAdapter slidingMenuAdapter = new ExpandableListRowAdapter(getActivity(), getItemsForMenu());
        listView.setAdapter(slidingMenuAdapter);
        listView.setOnGroupClickListener(
                new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(final ExpandableListView listView, final View view, final int group, final long id) {
                        return onGroupItemClick(listView, view, group);
                    }
                }
        );

        listView.setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView expandableListView, View view, int group, int child, long id) {
                        return onChildItemClick(listView, group, child);
                    }
                }
        );
    }

    private boolean onGroupItemClick(final ExpandableListView listView, final View view, final int position) {
        final int positionOfGroup = listView.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(position));
        final ListRow item = ((ExpandableListRowAdapter) listView.getExpandableListAdapter()).getGroup(position);
        if( item.getType() == ListRowType.SIDE_HEADING ) {
            return true;
        }

        handleItemClick(item);
        listView.setItemChecked(positionOfGroup, true);

        if( item.getChildCount() == 0 ) {
            ((SlidingMenuAccessInterface) getActivity()).toggle();
        }
        return false;
    }

    private boolean onChildItemClick(final ExpandableListView listView, final int group, final int child) {
        final int positionOfChild = listView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(group, child));
        final ListRow item = ((ExpandableListRowAdapter) listView.getExpandableListAdapter()).getChild(group, child);

        handleItemClick(item);
        listView.setItemChecked(positionOfChild, true);

        ((SlidingMenuAccessInterface) getActivity()).toggle();
        return false;
    }


    private void handleItemClick(final ListRow item) {
        if( item.hasIntent() ) {
            startActivity(item.getIntent());
        } else if( item.hasFragmentType() ) {
            final FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.activity_root, FragmentFactory.get(item.getFragmentType()));
            transaction.commit();
        }
    }

    private List<ListRow> getItemsForMenu() {
        final List<ListRow> items = new ArrayList<ListRow>();

        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "LOGGED IN AS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_ACCOUNT, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "SELECTED SOLDIER"));
        items.add(ListRowFactory.create(ListRowType.SIDE_SOLDIER, new Bundle(), getChildrenForSoldier()));

        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "SELECTED PLATOON"));
        items.add(ListRowFactory.create(ListRowType.SIDE_PLATOON, new Bundle()));

        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "NEWS", FragmentFactory.Type.NEWS_LISTING));
        items.add(ListRowFactory.create(ListRowType.SIDE_FEED, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "BATTLE CHAT", ExternalAppLauncher.getIntent(getActivity(), "com.ninetwozero.battlechat")));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "NOTIFICATIONS", FragmentFactory.Type.NOTIFICATION));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "SERVERS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "FORUMS", getChildrenForForum()));
        return items;
    }

    private List<ListRow> getChildrenForSoldier() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "STATISTICS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "WEAPONS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "UNLOCKS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "ASSIGNMENTS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "SETTINGS"));
        return items;
    }

    private List<ListRow> getChildrenForPlatoon() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "VIEW"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "CREATE NEW"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "INVITES"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "SETTINGS"));
        return items;
    }

    private List<ListRow> getChildrenForForum() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "VIEW FORUMS", FragmentFactory.Type.FORUM_LISTING));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "SAVED THREADS"));
        return items;
    }
}

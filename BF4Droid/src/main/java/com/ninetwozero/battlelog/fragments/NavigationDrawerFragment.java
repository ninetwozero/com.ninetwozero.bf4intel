package com.ninetwozero.battlelog.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.BaseListFragment;
import com.ninetwozero.battlelog.activities.SoldierStatisticsActivity;
import com.ninetwozero.battlelog.adapters.ExpandableListRowAdapter;
import com.ninetwozero.battlelog.datatypes.ListRow;
import com.ninetwozero.battlelog.datatypes.ListRowType;
import com.ninetwozero.battlelog.factories.FragmentFactory;
import com.ninetwozero.battlelog.factories.ListRowFactory;
import com.ninetwozero.battlelog.utils.ExternalAppLauncher;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerFragment extends BaseListFragment {

    private static final String STATE_SELECTED_GROUP = "selected_navigation_drawer_group_position";
    private static final String STATE_SELECTED_CHILD = "selected_navigation_drawer_child_position";
    private static final String STATE_SELECTION_IS_GROUP = "selected_navigation_drawer_is_group";

    private ExpandableListView mListView;
    private NavigationDrawerCallbacks mCallbacks;

    private int mCurrentSelectedGroupPosition = 0;
    private int mCurrentSelectedChildPosition = 0;
    private boolean mCurrentSelectionIsGroup = true;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCurrentSelectedGroupPosition = savedInstanceState.getInt(STATE_SELECTED_GROUP);
            mCurrentSelectedChildPosition = savedInstanceState.getInt(STATE_SELECTED_CHILD);
            mCurrentSelectionIsGroup = savedInstanceState.getBoolean(STATE_SELECTION_IS_GROUP, true);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        final View baseView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        initialize(baseView);
        return baseView;
    }

    private void initialize(final View view) {
        setupRegularViews(view);
        setupListView(view);
        selectItem(mCurrentSelectedGroupPosition, mCurrentSelectedChildPosition, mCurrentSelectionIsGroup);
    }

    private void setupRegularViews(final View view) {
        // TODO: Needs to get username from session storage
        final View wrapper = view.findViewById(R.id.wrap_login_name);
        ((TextView) wrapper.findViewById(R.id.login_name)).setText("NINETWOZERO");
    }


    private void setupListView(final View view) {
        mListView = (ExpandableListView) view.findViewById(android.R.id.list);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        final ExpandableListAdapter slidingMenuAdapter = new ExpandableListRowAdapter(getActivity(), getItemsForMenu());
        mListView.setAdapter(slidingMenuAdapter);
        mListView.setOnGroupClickListener(
                new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(final ExpandableListView listView, final View view, final int group, final long id) {
                        return onGroupItemClick(listView, view, group);
                    }
                }
        );

        mListView.setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView expandableListView, View view, int group, int child, long id) {
                        return onChildItemClick(expandableListView, group, child);
                    }
                }
        );
    }

    private boolean onGroupItemClick(final ExpandableListView listView, final View view, final int position) {
        final int positionOfGroup = listView.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(position));
        final ListRow item = ((ExpandableListRowAdapter) listView.getExpandableListAdapter()).getGroup(position);
        if (item.getType() == ListRowType.SIDE_HEADING) {
            return true;
        }

        selectItem(item, positionOfGroup);

        if (item.getChildCount() == 0) {
            ((NavigationDrawerCallbacks) getActivity()).onNavigationDrawerItemSelected(positionOfGroup, item.getTitle());
        }

        storePositionState(positionOfGroup, 0, true);
        return false;
    }

    private boolean onChildItemClick(final ExpandableListView listView, final int group, final int child) {
        final int positionOfChild = listView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(group, child));
        final ListRow item = ((ExpandableListRowAdapter) listView.getExpandableListAdapter()).getChild(group, child);

        selectItem(item, positionOfChild);

        storePositionState(group, child, false);
        ((NavigationDrawerCallbacks) getActivity()).onNavigationDrawerItemSelected(positionOfChild, item.getTitle());
        return false;
    }

    private void storePositionState(final int group, final int child, final boolean isGroup) {
        mCurrentSelectedGroupPosition = group;
        mCurrentSelectedChildPosition = child;
        mCurrentSelectionIsGroup = isGroup;
    }

    private List<ListRow> getItemsForMenu() {
        final List<ListRow> items = new ArrayList<ListRow>();

        //items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "LOGGED IN AS"));
        //items.add(ListRowFactory.create(ListRowType.SIDE_ACCOUNT, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "SELECTED SOLDIER"));
        items.add(ListRowFactory.create(ListRowType.SIDE_SOLDIER, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "MY SOLDIER"));
        items.addAll(getRowsForSoldier());

        //items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "SELECTED PLATOON"));
        //items.add(ListRowFactory.create(ListRowType.SIDE_PLATOON, new Bundle()));
        //items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "MY PLATOON"));
        //items.addAll(getRowsForPlatoon());

        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, "SOCIAL"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "NEWS", FragmentFactory.Type.NEWS_LISTING));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "BATTLE FEED", FragmentFactory.Type.BATTLE_FEED));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "BATTLE CHAT", ExternalAppLauncher.getIntent(getActivity(), "com.ninetwozero.battlechat")));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "NOTIFICATIONS", FragmentFactory.Type.NOTIFICATION));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "SERVERS"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "FORUMS", getRowsForForum()));
        return items;
    }

    /*
        TODO:
        Open ViewPagerActivity? Create ViewPagerFragment and display other fragments in it?

        Need to investigate pros/cons of that approach as well as how nested fragments work
    */
    private List<ListRow> getRowsForSoldier() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "OVERVIEW", FragmentFactory.Type.SOLDIER_OVERVIEW));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "STATISTICS", getSoldierStatisticsIntent(0))); // TODO: Pass real value
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "UNLOCKS", FragmentFactory.Type.SOLDIER_UNLOCKS));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, "ASSIGNMENTS", FragmentFactory.Type.SOLDIER_ASSIGNMENTS));
        return items;
    }

    private List<ListRow> getRowsForPlatoon() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "VIEW", FragmentFactory.Type.PLATOON_PROFILE));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "CREATE NEW"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "INVITES"));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "SETTINGS"));
        return items;
    }

    private List<ListRow> getRowsForForum() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "VIEW FORUMS", FragmentFactory.Type.FORUM_LISTING));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, "SAVED THREADS"));
        return items;
    }

    private Intent getSoldierStatisticsIntent(final long id) {
        Intent intent = new Intent(getActivity(), SoldierStatisticsActivity.class);
        intent = intent.putExtra(SoldierStatisticsActivity.INTENT_ID, id);
        return intent;
    }

    private void selectItem(final int group, final int child, final boolean isGroup) {
        ListRow row;
        int position;
        ExpandableListRowAdapter adapter = (ExpandableListRowAdapter) mListView.getExpandableListAdapter();

        if( isGroup ) {
            row = adapter.getGroup(group);
            position = mListView.getFlatListPosition(ExpandableListView.getPackedPositionGroup(group));
        } else {
            row = adapter.getChild(group, child);
            position = mListView.getFlatListPosition(ExpandableListView.getPackedPositionChild(child));
        }
        selectItem(row, position);
    }

    private void selectItem(final ListRow item, final int position) {
        if (mListView != null) {
            mListView.setItemChecked(position, true);
        }

        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position, item.getTitle());
        }

        startItem(item);
    }

    private void startItem(final ListRow item) {
        if (item.hasIntent()) {
            startActivity(item.getIntent());
        } else if (item.hasFragmentType()) {
            try {
                final FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(R.id.activity_root, FragmentFactory.get(item.getFragmentType()));
                transaction.commitAllowingStateLoss();
            } catch (TypeNotPresentException ex) {
                showToast(ex.getMessage());
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_GROUP, mCurrentSelectedGroupPosition);
        outState.putInt(STATE_SELECTED_CHILD, mCurrentSelectedGroupPosition);
        outState.putBoolean(STATE_SELECTION_IS_GROUP, mCurrentSelectionIsGroup);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        final NavigationDrawerCallbacks callback = ((NavigationDrawerCallbacks) getActivity());
        if (callback != null && callback.isDrawerOpen()) {
            inflater.inflate(R.menu.main, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ab_action_settings:
                Toast.makeText(getActivity(), "Settings...", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private ActionBar getActionBar() {
        return getActivity().getActionBar();
    }

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(final int position, final String title);
        boolean isDrawerOpen();
    }
}

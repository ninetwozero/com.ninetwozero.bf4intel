package com.ninetwozero.bf4intel.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.Keys;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.activities.SoldierStatisticsActivity;
import com.ninetwozero.bf4intel.adapters.ExpandableListRowAdapter;
import com.ninetwozero.bf4intel.assignments.AssignmentsActivity;
import com.ninetwozero.bf4intel.base.BaseListFragment;
import com.ninetwozero.bf4intel.datatypes.ListRow;
import com.ninetwozero.bf4intel.datatypes.ListRowType;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.factories.ListRowFactory;
import com.ninetwozero.bf4intel.utils.ExternalAppLauncher;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerFragment extends BaseListFragment {
    public static final String BATTLE_CHAT = "BATTLE CHAT";
    public static final String BATTLE_CHAT_PACKAGE = "com.ninetwozero.battlechat";

    private static final String STATE_SELECTED_GROUP = "selected_navigation_drawer_group_position";
    private static final String STATE_SELECTED_CHILD = "selected_navigation_drawer_child_position";
    private static final String STATE_SELECTION_IS_GROUP = "selected_navigation_drawer_is_group";

    private static final int INTENT_SOLDIER_STATISTICS = 1;
    private static final int INTENT_ASSIGNMENTS = 2;

    private ExpandableListView listView;
    private NavigationDrawerCallbacks callbacks;

    private int currentSelectedGroupPosition = 0;
    private int currentSelectedChildPosition = 0;
    private boolean currentSelectionIsGroup = true;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        final View baseView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        initialize(baseView, savedState);
        return baseView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_GROUP, currentSelectedGroupPosition);
        outState.putInt(STATE_SELECTED_CHILD, currentSelectedChildPosition);
        outState.putBoolean(STATE_SELECTION_IS_GROUP, currentSelectionIsGroup);
    }

    @Override
    public void onResume() {
        super.onResume();
        selectItemFromState(currentSelectedGroupPosition, currentSelectedChildPosition, currentSelectionIsGroup); // Put this where?
    }

    private void initialize(final View view, final Bundle state) {
        setupDataFromState(state);
        setupRegularViews(view);
        setupListView(view);
    }

    private void setupDataFromState(final Bundle state) {
        if (state != null) {
            currentSelectedGroupPosition = state.getInt(STATE_SELECTED_GROUP);
            currentSelectedChildPosition = state.getInt(STATE_SELECTED_CHILD);
            currentSelectionIsGroup = state.getBoolean(STATE_SELECTION_IS_GROUP, true);
        }
    }

    private void setupRegularViews(final View view) {
        // TODO: Needs to build username from session storage
        final View wrapper = view.findViewById(R.id.wrap_login_name);
        ((TextView) wrapper.findViewById(R.id.login_name)).setText("NINETWOZERO");
    }


    private void setupListView(final View view) {
        listView = (ExpandableListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        final ExpandableListAdapter slidingMenuAdapter = new ExpandableListRowAdapter(getActivity(), getItemsForMenu());
        listView.setAdapter(slidingMenuAdapter);
        listView.setOnGroupClickListener(
                new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(final ExpandableListView expandableListView, final View view, final int group, final long id) {
                        return onGroupItemClick(expandableListView, group);
                    }
                }
        );

        listView.setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView expandableListView, View view, int group, int child, long id) {
                        return onChildItemClick(expandableListView, group, child);
                    }
                }
        );
    }

    private boolean onGroupItemClick(final ExpandableListView listView, final int position) {
        final int positionOfGroup = listView.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(position));
        final ListRow item = ((ExpandableListRowAdapter) listView.getExpandableListAdapter()).getGroup(position);
        if (item.getType() == ListRowType.SIDE_HEADING) {
            return true;
        }

        selectItem(item, positionOfGroup, !item.hasChildren(), false);
        storePositionState(positionOfGroup, -1, true);
        return false;
    }

    private boolean onChildItemClick(final ExpandableListView listView, final int group, final int child) {
        final int positionOfChild = listView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(group, child));
        final ListRow item = ((ExpandableListRowAdapter) listView.getExpandableListAdapter()).getChild(group, child);

        selectItem(item, positionOfChild, true, false);
        storePositionState(group, child, false);
        return false;
    }

    private void storePositionState(final int group, final int child, final boolean isGroup) {
        currentSelectedGroupPosition = group;
        currentSelectedChildPosition = child;
        currentSelectionIsGroup = isGroup;
    }

    private List<ListRow> getItemsForMenu() {
        final List<ListRow> items = new ArrayList<ListRow>();

        items.addAll(getRowsForSoldier());
        items.addAll(getRowsForSocial());
        return items;
    }

    private List<ListRow> getRowsForSoldier() {
        final Bundle data = new Bundle();
        final List<ListRow> items = new ArrayList<ListRow>();

        // TODO: Get these from session storage somewhere -also, extract to constants somewhere
        data.putString(Keys.Soldier.NAME, "NINETWOZERO");
        data.putString(Keys.Soldier.ID, "177958806");
        data.putInt(Keys.Soldier.PLATFORM, 2);

        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_selected_soldier)));
        items.add(ListRowFactory.create(ListRowType.SIDE_SOLDIER, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_my_soldier)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_overview), data, FragmentFactory.Type.SOLDIER_OVERVIEW));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_statistics), data, intentToStart(INTENT_SOLDIER_STATISTICS)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_unlocks), data, FragmentFactory.Type.SOLDIER_UNLOCKS));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.assignments), data, intentToStart(INTENT_ASSIGNMENTS)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.battlereports), data, FragmentFactory.Type.BATTLE_REPORT_LISTING));
        return items;
    }

    private List<ListRow> getRowsForSocial() {
        // FIXME: Separate bundles per fragment type
        final Bundle data = new Bundle();
        final List<ListRow> items = new ArrayList<ListRow>();

        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_social)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_news), data, FragmentFactory.Type.NEWS_LISTING));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_battle_feed), data, FragmentFactory.Type.BATTLE_FEED));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, BATTLE_CHAT, data, ExternalAppLauncher.getIntent(getActivity(), BATTLE_CHAT_PACKAGE)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_notifications), data, FragmentFactory.Type.NOTIFICATION));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_servers)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_forums), data, getRowsForForum()));
        return items;
    }

    private List<ListRow> getRowsForForum() {
        final Bundle data = new Bundle();
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, getString(R.string.navigationdrawer_view_forums), data, FragmentFactory.Type.FORUM_LISTING));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, getString(R.string.navigationdrawer_saved_threads)));
        return items;
    }

    private Intent intentToStart(final int intentID) {
        int soldierID = 0; //TODO will replace with real soldier id once it become available
        Intent intent = null;
        switch(intentID){
            case INTENT_SOLDIER_STATISTICS:
                intent = new Intent(getActivity(), SoldierStatisticsActivity.class);
                intent = intent.putExtra(SoldierStatisticsActivity.INTENT_ID, soldierID);
                break;
            case INTENT_ASSIGNMENTS:
                intent = new Intent(getActivity(), AssignmentsActivity.class);
                break;
            default:
                Log.i(NavigationDrawerFragment.class.getSimpleName(), "Did not found any matching activity for intent " + intentID);
        }

        return intent;
    }

    private void selectItemFromState(final int group, final int child, final boolean isGroup) {
        ListRow row;
        int position;
        ExpandableListRowAdapter adapter = (ExpandableListRowAdapter) listView.getExpandableListAdapter();

        if( isGroup ) {
            row = adapter.getGroup(group);
            position = listView.getFlatListPosition(listView.getPackedPositionForGroup(group));
        } else {
            row = adapter.getChild(group, child);
            position = listView.getFlatListPosition(listView.getPackedPositionForChild(group, child));
        }
        selectItem(row, position, true, true);
    }

    private void selectItem(final ListRow item, final int position, final boolean closeDrawer, final boolean isOnResume) {
        if (listView != null) {
            listView.setItemChecked(position, true);
        }

        if (callbacks != null && closeDrawer) {
            callbacks.onNavigationDrawerItemSelected(position, item.getTitle());
        }

        startItem(item, isOnResume);
    }

    private void startItem(final ListRow item, final boolean isOnResume) {
        if (item.hasIntent() && !isOnResume) {
            startActivity(item.getIntent());
        } else if (item.hasFragmentType()) {
            try {
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                final String tag = item.getFragmentType().toString();
                final Fragment fragment = fragmentManager.findFragmentByTag(tag);
                if (fragment == null) {
                    transaction.replace(R.id.activity_root, FragmentFactory.get(item.getFragmentType(), item.getData()), tag);
                } else {
                    transaction.show(fragment);
                }
                transaction.commit();
            } catch (TypeNotPresentException ex) {
                showToast(ex.getMessage());
            }
        }
    }

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(final int position, final String title);
        boolean isDrawerOpen();
    }
}

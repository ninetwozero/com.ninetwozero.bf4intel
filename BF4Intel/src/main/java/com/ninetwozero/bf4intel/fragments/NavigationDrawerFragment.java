package com.ninetwozero.bf4intel.fragments;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.abstractions.BaseListFragment;
import com.ninetwozero.bf4intel.activities.SoldierStatisticsActivity;
import com.ninetwozero.bf4intel.adapters.ExpandableListRowAdapter;
import com.ninetwozero.bf4intel.assignments.AssignmentsActivity;
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
        setRetainInstance(false);
        setHasOptionsMenu(true);
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
        outState.putInt(STATE_SELECTED_CHILD, mCurrentSelectedChildPosition);
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
        final Activity activity = getActivity();
        if (activity != null) {
            switch (item.getItemId()) {
                case R.id.ab_action_settings:
                    Toast.makeText(activity, "Settings...", Toast.LENGTH_SHORT).show();
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        selectItemFromState(mCurrentSelectedGroupPosition, mCurrentSelectedChildPosition, mCurrentSelectionIsGroup); // Put this where?
    }

    private void initialize(final View view, final Bundle state) {
        setupDataFromState(state);
        setupRegularViews(view);
        setupListView(view);
    }

    private void setupDataFromState(final Bundle state) {
        if (state != null) {
            mCurrentSelectedGroupPosition = state.getInt(STATE_SELECTED_GROUP);
            mCurrentSelectedChildPosition = state.getInt(STATE_SELECTED_CHILD);
            mCurrentSelectionIsGroup = state.getBoolean(STATE_SELECTION_IS_GROUP, true);
        }
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
                    public boolean onGroupClick(final ExpandableListView expandableListView, final View view, final int group, final long id) {
                        return onGroupItemClick(expandableListView, group);
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
        mCurrentSelectedGroupPosition = group;
        mCurrentSelectedChildPosition = child;
        mCurrentSelectionIsGroup = isGroup;
    }

    private List<ListRow> getItemsForMenu() {
        final List<ListRow> items = new ArrayList<ListRow>();

        items.addAll(getRowsForSoldier());
        items.addAll(getRowsForSocial());
        return items;
    }

    private List<ListRow> getRowsForSoldier() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.selected_soldier)));
        items.add(ListRowFactory.create(ListRowType.SIDE_SOLDIER, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_my_soldier)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_overview), FragmentFactory.Type.SOLDIER_OVERVIEW));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_statistics), intentToStart(INTENT_SOLDIER_STATISTICS)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_unlocks), FragmentFactory.Type.SOLDIER_UNLOCKS));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.assignments), intentToStart(INTENT_ASSIGNMENTS)));
        return items;
    }

    private List<ListRow> getRowsForSocial() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_social)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_news), FragmentFactory.Type.NEWS_LISTING));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_battle_feed), FragmentFactory.Type.BATTLE_FEED));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, BATTLE_CHAT, ExternalAppLauncher.getIntent(getActivity(), BATTLE_CHAT_PACKAGE)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_notifications), FragmentFactory.Type.NOTIFICATION));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_servers)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_forums), getRowsForForum()));
        return items;
    }

    private List<ListRow> getRowsForForum() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR_CHILD, getString(R.string.navigationdrawer_view_forums), FragmentFactory.Type.FORUM_LISTING));
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
        ExpandableListRowAdapter adapter = (ExpandableListRowAdapter) mListView.getExpandableListAdapter();

        if( isGroup ) {
            row = adapter.getGroup(group);
            position = mListView.getFlatListPosition(mListView.getPackedPositionForGroup(group));
        } else {
            row = adapter.getChild(group, child);
            position = mListView.getFlatListPosition(mListView.getPackedPositionForChild(group, child));
        }
        selectItem(row, position, true, true);
    }

    private void selectItem(final ListRow item, final int position, final boolean closeDrawer, final boolean isOnResume) {
        if (mListView != null) {
            mListView.setItemChecked(position, true);
        }

        if (mCallbacks != null && closeDrawer) {
            mCallbacks.onNavigationDrawerItemSelected(position, item.getTitle());
        }

        startItem(item, isOnResume);
    }

    private void startItem(final ListRow item, final boolean isOnResume) {
        if (item.hasIntent() && !isOnResume) {
            startActivity(item.getIntent());
        } else if (item.hasFragmentType()) {
            try {
                final FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(R.id.activity_root, FragmentFactory.get(item.getFragmentType()));
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

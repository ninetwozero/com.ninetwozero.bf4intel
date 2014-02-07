package com.ninetwozero.bf4intel.ui.fragments;

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

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;
import com.ninetwozero.bf4intel.datatypes.ListRow;
import com.ninetwozero.bf4intel.datatypes.ListRowType;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.factories.ListRowFactory;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.adapters.ExpandableListRowAdapter;
import com.ninetwozero.bf4intel.ui.assignments.AssignmentsActivity;
import com.ninetwozero.bf4intel.ui.awards.AwardsActivity;
import com.ninetwozero.bf4intel.ui.stats.SoldierStatisticsActivity;
import com.ninetwozero.bf4intel.ui.unlocks.UnlockActivity;
import com.ninetwozero.bf4intel.utils.ExternalAppLauncher;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerFragment extends BaseListFragment {
    public static final String BATTLE_CHAT = "BATTLE CHAT";
    public static final String BATTLE_CHAT_PACKAGE = "com.ninetwozero.battlechat";

    private static final String STATE_SELECTED_GROUP = "selected_navigation_drawer_group_position";
    private static final String STATE_SELECTED_CHILD = "selected_navigation_drawer_child_position";
    private static final String STATE_SELECTION_IS_GROUP = "selected_navigation_drawer_is_group";

    private static final int DEFAULT_GROUP_POSITION_GUEST = 1;
    private static final int DEFAULT_GROUP_POSITION_TRACKING = 9;
    private static final int DEFAULT_GROUP_POSITION = 3;

    private static final int INTENT_SOLDIER_STATISTICS = 1;
    private static final int INTENT_ASSIGNMENTS = 2;
    private static final int INTENT_AWARDS = 3;
    private static final int INTENT_UNLOCKS = 4;

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
        selectItemFromState(currentSelectedGroupPosition, currentSelectedChildPosition, currentSelectionIsGroup);
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
        } else {
            currentSelectedGroupPosition = fetchStartingGroupForSessionState();
            currentSelectedChildPosition = -1;
            currentSelectionIsGroup = true;
        }
    }

    private int fetchStartingGroupForSessionState() {
        if (SessionStore.isLoggedIn()) {
            return DEFAULT_GROUP_POSITION;
        } else if (SessionStore.hasUserId()) {
            return DEFAULT_GROUP_POSITION_TRACKING;
        } else {
            return DEFAULT_GROUP_POSITION_GUEST;
        }
    }

    private void setupRegularViews(final View view) {
        final View wrapper = view.findViewById(R.id.wrap_login_name);
        final TextView loginStatusView = (TextView) wrapper.findViewById(R.id.string_login_status);
        final TextView loginUsernameView = (TextView) wrapper.findViewById(R.id.login_name);

        if (SessionStore.isLoggedIn()) {
            loginStatusView.setText(R.string.logged_in_as);
            loginUsernameView.setText(SessionStore.getUsername());
        } else if (SessionStore.hasUserId()) {
            loginStatusView.setText(R.string.tracking_user);
            loginUsernameView.setText(SessionStore.getUsername());
        } else {
            loginStatusView.setText(R.string.not_logged_in);
            loginUsernameView.setText(R.string.empty);
        }
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

        if (SessionStore.hasUserId()) {
            items.addAll(getRowsForSoldier());
        }
        items.addAll(getRowsForSocial());
        return items;
    }

    private List<ListRow> getRowsForSoldier() {
        final Bundle data = new Bundle();
        final List<ListRow> items = new ArrayList<ListRow>();

        // TODO: Get these from session storage somewhere when implemented (see BattleChat)
        data.putString(Keys.Soldier.NAME, "NINETWOZERO");
        data.putInt(Keys.Soldier.ID, 177958806);
        data.putInt(Keys.Soldier.PLATFORM, 2);

        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_selected_soldier)));
        items.add(ListRowFactory.create(ListRowType.SIDE_SOLDIER, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_my_soldier)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_overview), data, FragmentFactory.Type.SOLDIER_OVERVIEW));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_statistics), data, intentToStart(INTENT_SOLDIER_STATISTICS)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_unlocks), data, intentToStart(INTENT_UNLOCKS)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.assignments), data, intentToStart(INTENT_ASSIGNMENTS)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.awards), data, intentToStart(INTENT_AWARDS)));
        return items;
    }

    private List<ListRow> getRowsForSocial() {
        // TODO: We need to populate the bundle from Session storage (when available)
        final Bundle data = new Bundle();
        //data.putString(Keys.Profile.ID, "2832658801548551060");
        //data.putString(Keys.Profile.NAME, "Karl Lindmark");
        //data.putString(Keys.Profile.USERNAME, "NINETWOZERO");
        //data.putString(Keys.Profile.GRAVATAR_HASH, "1241459af7d1ba348ec8b258240ea145");

        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_social)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_home), data, fetchTypeForHome()));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_news), data, FragmentFactory.Type.NEWS_LISTING));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, BATTLE_CHAT, data, ExternalAppLauncher.getIntent(getActivity(), BATTLE_CHAT_PACKAGE)));
        return items;
    }

    private FragmentFactory.Type fetchTypeForHome() {
        return SessionStore.isLoggedIn() ? FragmentFactory.Type.BATTLE_FEED : FragmentFactory.Type.HOME;
    }

    private Intent intentToStart(final int intentID) {
        final Bundle profileBundle = fetchProfileBundle();
        Intent intent = null;
        switch(intentID){
            case INTENT_SOLDIER_STATISTICS:
                intent = new Intent(getActivity(), SoldierStatisticsActivity.class);
                break;
            case INTENT_ASSIGNMENTS:
                intent = new Intent(getActivity(), AssignmentsActivity.class);
                break;
            case INTENT_AWARDS:
                intent = new Intent(getActivity(), AwardsActivity.class);
                break;
            case INTENT_UNLOCKS:
                intent = new Intent(getActivity(), UnlockActivity.class);
                break;
            default:
                intent = new Intent();
                Log.i(NavigationDrawerFragment.class.getSimpleName(), "Did not found any matching activity for intent " + intentID);
        }
        return intent.putExtra("profile", profileBundle);
    }

    private Bundle fetchProfileBundle() {
        final Bundle bundle = new Bundle();
        bundle.putString(Keys.Profile.ID, "2832658801548551060");
        bundle.putString(Keys.Profile.NAME, "Karl Lindmark");
        bundle.putString(Keys.Profile.USERNAME, "NINETWOZERO");
        bundle.putString(Keys.Profile.GRAVATAR_HASH, "1241459af7d1ba348ec8b258240ea145");
        bundle.putInt(Keys.Soldier.ID, 177958806);
        bundle.putString(Keys.Soldier.NAME, "NINETWOZERO");
        bundle.putInt(Keys.Soldier.PLATFORM, 2);
        return bundle;
    }

    private void selectItemFromState(final int group, final int child, final boolean isGroup) {
        final ExpandableListRowAdapter adapter = (ExpandableListRowAdapter) listView.getExpandableListAdapter();
        final ListRow row = isGroup? adapter.getGroup(group) : adapter.getChild(group, child);
        final int position = listView.getFlatListPosition(
            isGroup? listView.getPackedPositionForGroup(group) : listView.getPackedPositionForChild(group, child)
        );
        selectItem(row, position, true, true);
    }

    private void selectItem(final ListRow item, final int position, final boolean closeDrawer, final boolean isOnResume) {
        final boolean isFragment = !item.hasIntent();
        if (listView != null && isFragment) {
            listView.setItemChecked(position, true);
        }

        if (callbacks != null && closeDrawer) {
            callbacks.onNavigationDrawerItemSelected(position, isFragment ? item.getTitle() : null);
        }

        startItem(item, isOnResume);
    }

    private void startItem(final ListRow item, final boolean isOnResume) {
        if (item.hasIntent() && !isOnResume) {
            startActivityForResult(item.getIntent(), 12345);
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

    public void reload() {
        final View view = getView();
        if (view == null) {
            return;
        }

        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        final ExpandableListRowAdapter adapter = ((ExpandableListRowAdapter) listView.getExpandableListAdapter());
        adapter.setItems(getItemsForMenu());
    }

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(final int position, final String title);
        boolean isDrawerOpen();
    }
}

package com.ninetwozero.bf4intel.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;
import com.ninetwozero.bf4intel.datatypes.TrackingNewProfileEvent;
import com.ninetwozero.bf4intel.factories.BundleFactory;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.factories.ListRowFactory;
import com.ninetwozero.bf4intel.interfaces.ListRowElement;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;
import com.ninetwozero.bf4intel.menu.ListRowType;
import com.ninetwozero.bf4intel.menu.NormalRow;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.adapters.ListRowAdapter;
import com.ninetwozero.bf4intel.ui.assignments.AssignmentsActivity;
import com.ninetwozero.bf4intel.ui.awards.AwardsActivity;
import com.ninetwozero.bf4intel.ui.stats.SoldierStatisticsActivity;
import com.ninetwozero.bf4intel.ui.unlocks.UnlockActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.ExternalAppLauncher;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.DatabaseCompartment;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class NavigationDrawerFragment extends BaseListFragment {
    public static final String BATTLE_CHAT = "BATTLE CHAT";
    public static final String BATTLE_CHAT_PACKAGE = "com.ninetwozero.battlechat";

    private final String STATE_SELECTED_POSITIION = "selected_navigation_drawer_group_position";

    private final int POSITION_SOLDIER_HEADING = 0;
    private final int POSITION_SOLDIER_SPINNER = 1;

    private final int DEFAULT_POSITION_GUEST = 1;
    private final int DEFAULT_POSITION_TRACKING = 9;
    private final int DEFAULT_POSITION = 3;

    private final int INTENT_SOLDIER_STATISTICS = 1;
    private final int INTENT_ASSIGNMENTS = 2;
    private final int INTENT_AWARDS = 3;
    private final int INTENT_UNLOCKS = 4;

    private ListView listView;
    private NavigationDrawerCallbacks callbacks;

    private int currentSelectedPosition = 0;

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
        outState.putInt(STATE_SELECTED_POSITIION, currentSelectedPosition);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        selectItemFromState(currentSelectedPosition);
    }

    @Override
    public void onPause() {
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        final ListRowElement item = ((ListRowAdapter) getListAdapter()).getItem(position);
        if (item instanceof NormalRow) {
            selectItem((NormalRow) item, position, true, false);
            storePositionState(position);
        }
    }

    @Subscribe
    public void onStartedTrackingNewProfile(final TrackingNewProfileEvent event) {
        final View view = getView();
        if (view == null) {
            return;
        }
        setupRegularViews(view);
        ((ListRowAdapter) getListAdapter()).setItems(getItemsForMenu());
    }

    private void initialize(final View view, final Bundle state) {
        setupDataFromState(state);
        setupRegularViews(view);
        setupListView(view);
    }

    private void setupDataFromState(final Bundle state) {
        if (state != null) {
            currentSelectedPosition = state.getInt(STATE_SELECTED_POSITIION);
        } else {
            currentSelectedPosition = fetchStartingPositionForSessionState();
        }
    }

    private int fetchStartingPositionForSessionState() {
        if (SessionStore.isLoggedIn()) {
            return DEFAULT_POSITION;
        } else if (SessionStore.hasUserId()) {
            return DEFAULT_POSITION_TRACKING;
        } else {
            return DEFAULT_POSITION_GUEST;
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
        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ListRowAdapter(getActivity(), getItemsForMenu()));
    }

    private void storePositionState(final int position) {
        currentSelectedPosition = position;
    }

    private List<ListRowElement> getItemsForMenu() {
        final List<ListRowElement> items = new ArrayList<ListRowElement>();

        if (SessionStore.hasUserId()) {
            items.addAll(getRowsForSoldier());
        }
        items.addAll(getRowsForSocial());
        return items;
    }

    private List<ListRowElement> getRowsForSoldier() {
        final List<SummarizedSoldierStats> stats = fetchSoldiersForMenu();
        final Bundle data = buildBundleForSoldier(stats);
        final List<ListRowElement> items = new ArrayList<ListRowElement>();

        items.add(POSITION_SOLDIER_HEADING, ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_selected_soldier)));
        items.add(POSITION_SOLDIER_SPINNER, ListRowFactory.createSoldierRow(stats));
        items.add(ListRowFactory.create(ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_my_soldier)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_overview), data, FragmentFactory.Type.SOLDIER_OVERVIEW));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_statistics), data, intentToStart(INTENT_SOLDIER_STATISTICS)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.navigationdrawer_unlocks), data, intentToStart(INTENT_UNLOCKS)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.assignments), data, intentToStart(INTENT_ASSIGNMENTS)));
        items.add(ListRowFactory.create(ListRowType.SIDE_REGULAR, getString(R.string.awards), data, intentToStart(INTENT_AWARDS)));
        return items;
    }

    private List<SummarizedSoldierStats> fetchSoldiersForMenu() {
        final BaseIntelActivity activity = (BaseIntelActivity) getActivity();
        final List<SummarizedSoldierStats> soldiers = new ArrayList<SummarizedSoldierStats>();
        final DatabaseCompartment database = cupboard().withDatabase(activity.getReadableDatabase());
        final Cursor results = database.query(SummarizedSoldierStats.class).withSelection(
            "userId = ?", SessionStore.getUserId()
        ).getCursor();

        for (SummarizedSoldierStats result : cupboard().withCursor(results).iterate(SummarizedSoldierStats.class)) {
            soldiers.add(result);
        }

        return soldiers;
    }

    private Bundle buildBundleForSoldier(final List<SummarizedSoldierStats> listOfStats) {
        for (SummarizedSoldierStats soldierStats : listOfStats) {
            if (soldierStats.getId() == sharedPreferences.getLong(Keys.Menu.LATEST_PERSONA, -1)) {
                return BundleFactory.createForStats(soldierStats);
            }
        }
        return new Bundle();
    }

    private List<ListRowElement> getRowsForSocial() {
        // TODO: We need to populate the bundle from Session storage (when available)
        final Bundle data = new Bundle();
        //data.putString(Keys.Profile.ID, "2832658801548551060");
        //data.putString(Keys.Profile.NAME, "Karl Lindmark");
        //data.putString(Keys.Profile.USERNAME, "NINETWOZERO");
        //data.putString(Keys.Profile.GRAVATAR_HASH, "1241459af7d1ba348ec8b258240ea145");

        final List<ListRowElement> items = new ArrayList<ListRowElement>();
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
        Intent intent;
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

    private void selectItemFromState(final int position) {
        final ListRowAdapter adapter = (ListRowAdapter) getListAdapter();
        final ListRowElement row = adapter.getItem(position);

        if (row instanceof NormalRow) {
            selectItem((NormalRow) row, position, true, true);
        }
    }

    private void selectItem(final NormalRow item, final int position, final boolean closeDrawer, final boolean isOnResume) {
        final boolean isFragment = !item.hasIntent();
        if (listView != null && isFragment) {
            listView.setItemChecked(position, true);
        }

        if (callbacks != null && closeDrawer) {
            callbacks.onNavigationDrawerItemSelected(position, isFragment ? item.getTitle() : null);
        }

        startItem(item, isOnResume);
    }

    private void startItem(final NormalRow item, final boolean isOnResume) {
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

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(final int position, final String title);
        boolean isDrawerOpen();
    }
}

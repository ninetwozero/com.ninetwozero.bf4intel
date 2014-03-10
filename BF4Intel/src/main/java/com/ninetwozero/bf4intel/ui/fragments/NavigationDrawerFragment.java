package com.ninetwozero.bf4intel.ui.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;
import com.ninetwozero.bf4intel.datatypes.TrackingNewProfileEvent;
import com.ninetwozero.bf4intel.factories.BundleFactory;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.factories.ListRowFactory;
import com.ninetwozero.bf4intel.interfaces.ListRowElement;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;
import com.ninetwozero.bf4intel.menu.ListRowType;
import com.ninetwozero.bf4intel.menu.SimpleListRow;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.adapters.NavigationDrawerListAdapter;
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

    private final String STATE_SELECTED_POSITION = "selected_navigation_group_position";
    private final String STATE_SELECTED_POSITION_TRACKING = "selected_navigation_group_position_tracking";

    private static final int DEFAULT_POSITION_GUEST = 1;
    private static final int DEFAULT_POSITION_TRACKING = 9;
    private static final int DEFAULT_POSITION = 3;

    private ListView listView;
    private NavigationDrawerCallbacks callbacks;

    private int currentSelectedPosition;
    private Bundle soldierBundleForMenu;

    public NavigationDrawerFragment() {
        if (getArguments() == null) {
            final Bundle data = new Bundle();
            data.putBoolean(BaseFragment.CALLED_FROM_VIEWPAGER, true);
            setArguments(data);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        final View baseView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        initialize(baseView);
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
        final ListRowElement item = ((NavigationDrawerListAdapter) getListAdapter()).getItem(position);
        if (item instanceof SimpleListRow) {
            selectItem((SimpleListRow) item, position, true, false);
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
        ((NavigationDrawerListAdapter) getListAdapter()).setItems(getItemsForMenu());
    }

    private void initialize(final View view) {
        setupDataFromState();
        setupRegularViews(view);
        setupListView(view);
    }

    private void setupDataFromState() {
        currentSelectedPosition = fetchStartingPositionForSessionState();
    }

    private int fetchStartingPositionForSessionState() {
        if (SessionStore.isLoggedIn()) {
            return sharedPreferences.getInt(STATE_SELECTED_POSITION, DEFAULT_POSITION);
        } else if (SessionStore.hasUserId()) {
            return sharedPreferences.getInt(STATE_SELECTED_POSITION_TRACKING, DEFAULT_POSITION_TRACKING);
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
        setListAdapter(new NavigationDrawerListAdapter(getActivity(), getItemsForMenu()));
    }

    private void storePositionState(final int position) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        if (SessionStore.isLoggedIn()) {
            editor.putInt(STATE_SELECTED_POSITION, position).commit();
        } else if (SessionStore.hasUserId()) {
            editor.putInt(STATE_SELECTED_POSITION_TRACKING, position).commit();
        }

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
        final List<ListRowElement> items = new ArrayList<ListRowElement>();
        soldierBundleForMenu = buildBundleForSoldier(stats);

        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_HEADING,
                getString(R.string.navigationdrawer_selected_soldier)
            )
        );
        items.add(ListRowFactory.createSoldierRow(stats));
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_my_soldier)
            )
        );
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_REGULAR,
                getString(R.string.navigationdrawer_overview),
                soldierBundleForMenu,
                FragmentFactory.Type.SOLDIER_OVERVIEW
            )
        );
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_REGULAR,
                getString(R.string.navigationdrawer_statistics),
                soldierBundleForMenu,
                FragmentFactory.Type.SOLDIER_STATS
            )
        );
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_REGULAR,
                getString(R.string.navigationdrawer_unlocks),
                soldierBundleForMenu,
                FragmentFactory.Type.SOLDIER_UNLOCKS
            )
        );
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_REGULAR,
                getString(R.string.assignments),
                soldierBundleForMenu,
                FragmentFactory.Type.SOLDIER_ASSIGNMENTS
            )
        );
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_REGULAR,
                getString(R.string.awards),
                soldierBundleForMenu,
                FragmentFactory.Type.SOLDIER_AWARDS
            )
        );
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

        results.close();
        return soldiers;
    }

    private Bundle buildBundleForSoldier(final List<SummarizedSoldierStats> listOfStats) {
        for (SummarizedSoldierStats soldierStats : listOfStats) {
            if (soldierStats.getId() == sharedPreferences.getLong(Keys.Menu.LATEST_PERSONA, -1)) {
                final Bundle bundle = BundleFactory.createForStats(soldierStats);
                bundle.putString(Keys.Profile.ID, SessionStore.getUserId());
                bundle.putString(Keys.Profile.USERNAME, SessionStore.getUsername());
                bundle.putString(Keys.Profile.GRAVATAR_HASH, SessionStore.getGravatarHash());
                return bundle;
            }
        }
        return new Bundle();
    }

    private List<ListRowElement> getRowsForSocial() {
        final Bundle data = new Bundle();
        final List<ListRowElement> items = new ArrayList<ListRowElement>();
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_HEADING, getString(R.string.navigationdrawer_social)
            )
        );
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_REGULAR,
                getString(R.string.navigationdrawer_home),
                data,
                fetchTypeForHome()
            )
        );
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_REGULAR,
                getString(R.string.navigationdrawer_news),
                data,
                FragmentFactory.Type.NEWS_LISTING
            )
        );
        items.add(
            ListRowFactory.create(
                ListRowType.SIDE_REGULAR,
                BATTLE_CHAT,
                data,
                ExternalAppLauncher.getIntent(getActivity(), BATTLE_CHAT_PACKAGE)
            )
        );
        return items;
    }

    private FragmentFactory.Type fetchTypeForHome() {
        return SessionStore.isLoggedIn() ? FragmentFactory.Type.BATTLE_FEED : FragmentFactory.Type.HOME;
    }

    private void selectItemFromState(final int position) {
        final NavigationDrawerListAdapter adapter = (NavigationDrawerListAdapter) getListAdapter();
        if (position >= adapter.getCount()) {
            selectItemFromState(fetchStartingPositionForSessionState());
        }

        final ListRowElement row = adapter.getItem(position);
        if (row instanceof SimpleListRow) {
            selectItem((SimpleListRow) row, position, true, true);
        }
    }

    private void selectItem(final SimpleListRow item, final int position, final boolean closeDrawer, final boolean isOnResume) {
        final boolean isFragment = !item.hasIntent();
        if (listView != null && isFragment) {
            listView.setItemChecked(position, true);
        }

        if (callbacks != null && closeDrawer) {
            callbacks.onNavigationDrawerItemSelected(position, isFragment ? item.getTitle() : null);
        }

        startItem(item, isOnResume);
    }

    private void startItem(final SimpleListRow item, final boolean isOnResume) {
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

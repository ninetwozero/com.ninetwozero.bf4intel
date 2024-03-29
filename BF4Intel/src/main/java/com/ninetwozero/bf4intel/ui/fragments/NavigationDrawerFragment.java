package com.ninetwozero.bf4intel.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.FragmentTransaction;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.database.dao.login.SoldierAccessComparator;
import com.ninetwozero.bf4intel.database.dao.login.SummarizedSoldierStatsDAO;
import com.ninetwozero.bf4intel.events.ActiveSoldierChangedEvent;
import com.ninetwozero.bf4intel.events.SoldierInformationUpdatedEvent;
import com.ninetwozero.bf4intel.events.TrackingNewSoldierEvent;
import com.ninetwozero.bf4intel.factories.BundleFactory;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.menu.NavigationDrawerItem;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.resources.maps.profile.SoldierImageMap;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankImageMap;
import com.ninetwozero.bf4intel.ui.about.AppInfoActivity;
import com.ninetwozero.bf4intel.ui.login.LoginActivity;
import com.ninetwozero.bf4intel.ui.settings.SettingsActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.ExternalAppLauncher;
import com.ninetwozero.bf4intel.utils.PersonaUtils;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se.emilsjolander.sprinkles.Query;

public class NavigationDrawerFragment extends BaseFragment {
    public static final String BATTLE_CHAT_PACKAGE = "com.ninetwozero.battlechat";

    private static final String STATE_SELECTED_POSITION_TRACKING = "selected_navigation_group_position_tracking";
    private static final int INVALID_POSITION = -1;
    private static final int DEFAULT_POSITION_GUEST = 0;
    private static final int DEFAULT_POSITION_TRACKING = 0;

    private boolean isRecreated;
    private NavigationDrawerCallbacks callbacks;
    private ViewGroup navigationDrawerItemContainer;
    private View[] navigationDrawerItemViews;

    private int currentMenuSelection = INVALID_POSITION;
    private List<NavigationDrawerItem> navigationDrawerItems = new ArrayList<>();
    private List<SummarizedSoldierStatsDAO> soldiers = new ArrayList<>();
    private boolean shouldShowTheSoldiers = false;

    private String selectedSoldierId;
    private int selectedSoldierPlatform;
    private SummarizedSoldierStatsDAO selectedSoldier;

    public NavigationDrawerFragment() {
        if (getArguments() == null) {
            final Bundle data = new Bundle();
            data.putBoolean(BaseFragment.FLAG_DISABLE_AUTOMATIC_ANALYTICS, true);
            data.putBoolean(BaseFragment.FLAG_DISABLE_RETAIN_STATE, true);
            setArguments(data);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        super.onCreateView(inflater, container, savedState);

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
        if (!isRecreated) {
            isRecreated = true;
            selectItemFromState(currentMenuSelection);
        }
    }

    @Override
    public void onPause() {
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onStartedTrackingNewProfile(final TrackingNewSoldierEvent event) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final int currentMenuSelection = this.currentMenuSelection;
        initialize(view);
        selectItemFromState(currentMenuSelection);
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onSoldierInformationUpdated(final SoldierInformationUpdatedEvent event) {
        final View view = getView();
        if (view == null) {
            return;
        }

        setupRegularViews(view);
        setupSoldierBox(view);
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onActiveSoldierChanged(final ActiveSoldierChangedEvent event) {
        final View view = getView();
        if (view == null) {
            return;
        }

        // Since we're only changing the selected soldier in the dropdown, no need to refresh via DB
        final int currentMenuSelection = this.currentMenuSelection;
        setupRegularViews(view);
        Collections.sort(soldiers, new SoldierAccessComparator());
        populateSoldierList(view);
        selectItemFromState(currentMenuSelection);
    }

    private void initialize(final View view) {
        setupDataFromPreferences();
        loadSoldiersFromDatabaseIfWeHaveAny();
        setupRegularViews(view);
        populateNavigationDrawer(view);
        populateSoldierList(view);
    }

    private void loadSoldiersFromDatabaseIfWeHaveAny() {
        soldiers = fetchSoldiersForMenu();
        for (SummarizedSoldierStatsDAO soldier : soldiers) {
            if (isSelectedSoldier(soldier)) {
                selectedSoldier = soldier;
                return;
            }
        }

        if (!soldiers.isEmpty()) {
            selectedSoldier = soldiers.get(0);
            selectedSoldierId = selectedSoldier.getSoldierId();
            selectedSoldierPlatform = selectedSoldier.getPlatformId();
        }
    }

    private void setupDataFromPreferences() {
        currentMenuSelection = fetchStartingPositionForSessionState();
        selectedSoldierId = sharedPreferences.getString(Keys.Menu.LATEST_PERSONA, "");
        selectedSoldierPlatform = sharedPreferences.getInt(Keys.Menu.LATEST_PERSONA_PLATFORM, 0);
    }

    public int fetchDefaultPosition() {
        if (hasSoldier()) {
            return DEFAULT_POSITION_TRACKING;
        } else {
            return DEFAULT_POSITION_GUEST;
        }
    }

    private boolean hasSoldier() {
        return !"".equals(sharedPreferences.getString(Keys.Menu.LATEST_PERSONA, ""));
    }

    private int fetchStartingPositionForSessionState() {
        if (hasSoldier()) {
            return sharedPreferences.getInt(STATE_SELECTED_POSITION_TRACKING, DEFAULT_POSITION_TRACKING);
        } else {
            return DEFAULT_POSITION_GUEST;
        }
    }

    private void setupRegularViews(final View view) {
        final View wrapper = view.findViewById(R.id.wrap_login_info);
        if (hasSoldier()) {
            setupSoldierBox(view);
            wrapper.setVisibility(View.VISIBLE);
        } else {
            wrapper.setVisibility(View.GONE);
        }
    }

    private void setupSoldierBox(final View view) {
        final View soldierItemWrapper = view.findViewById(R.id.wrap_login_info);
        final View soldierItemView = soldierItemWrapper.findViewById(R.id.selected_soldier_dropdown);
        populateSoldierItemView(soldierItemView, selectedSoldier);

        if (soldiers.size() > 1) {
            soldierItemWrapper.setEnabled(true);
            soldierItemWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    onSoldierBoxClick(v);
                }
            });
            setVisibility(soldierItemView, R.id.soldier_dropdown_arrow, View.VISIBLE);
        } else {
            soldierItemWrapper.setOnClickListener(null);
            soldierItemWrapper.setEnabled(false);
            setVisibility(soldierItemView, R.id.soldier_dropdown_arrow, View.GONE);
        }
    }

    private void populateSoldierItemView(final View soldierItemView, final SummarizedSoldierStatsDAO soldier) {
        setImage(soldierItemView, R.id.soldier_image, SoldierImageMap.get(soldier.getPicture()));
        setText(soldierItemView, R.id.soldier_name, soldier.getSoldierName());
        setText(soldierItemView, R.id.soldier_platform, PersonaUtils.getPlatformNameFromPlatformId(soldier.getPlatformId()));
        setImage(soldierItemView, R.id.soldier_rank, RankImageMap.get(soldier.getRank()));
    }

    private void onSoldierBoxClick(final View view) {
        setVisibilityOfSoldiersInDrawer(view, !shouldShowTheSoldiers);
    }

    private void setVisibilityOfSoldiersInDrawer(final View view, boolean shouldShowTheSoldiers) {
        this.shouldShowTheSoldiers = shouldShowTheSoldiers;

        final View rootView = getView();
        if (rootView == null) {
            return;
        }

        final ImageView indicator = (ImageView) view.findViewById(R.id.soldier_dropdown_arrow);
        if (this.shouldShowTheSoldiers) {
            indicator.setImageResource(R.drawable.ic_menu_arrow_up_light);
            rootView.findViewById(R.id.navigation_drawer_account_list).setVisibility(View.VISIBLE);
            rootView.findViewById(R.id.navigation_drawer_item_container).setVisibility(View.GONE);
        } else {
            indicator.setImageResource(R.drawable.ic_menu_arrow_down_light);
            rootView.findViewById(R.id.navigation_drawer_account_list).setVisibility(View.GONE);
            rootView.findViewById(R.id.navigation_drawer_item_container).setVisibility(View.VISIBLE);
        }
    }

    private void createNavigationDrawerItemsInLayout(final View view) {
        navigationDrawerItemContainer = (ViewGroup) view.findViewById(R.id.navigation_drawer_item_container);
        if (navigationDrawerItemContainer == null) {
            return;
        }

        navigationDrawerItemViews = new View[navigationDrawerItems.size()];
        navigationDrawerItemContainer.removeAllViews();
        int i = 0;
        for (NavigationDrawerItem item : navigationDrawerItems) {
            navigationDrawerItemViews[i] = createNavigationDrawerItem(item, i, navigationDrawerItemContainer);
            navigationDrawerItemContainer.addView(navigationDrawerItemViews[i]);
            ++i;
        }
    }

    private View createNavigationDrawerItem(final NavigationDrawerItem item, final int itemPosition, ViewGroup container) {
        final int layoutToInflate = fetchLayoutToInflateForItem(item.getType());
        final View view = layoutInflater.inflate(layoutToInflate, container, false);
        if (item.getType() == NavigationDrawerItem.Type.SEPARATOR) {
            return view;
        }

        setText(view, R.id.text, item.getTitle());
        if (item.getIcon() != NavigationDrawerItem.NO_ICON) {
            setImage(view, R.id.icon, item.getIcon());
        }

        if (item.getType() != NavigationDrawerItem.Type.HEADING) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                selectItem(item, itemPosition, true, false);
                }
            });
        }
        return view;
    }

    private int fetchLayoutToInflateForItem(final NavigationDrawerItem.Type type) {
        switch (type) {
            case SEPARATOR:
                return R.layout.list_item_navdrawer_separator;
            case HEADING:
                return R.layout.list_item_navdrawer_heading;
            case ABOUT:
            case SETTINGS:
            case BATTLE_CHAT:
            case NEWS:
                return R.layout.list_item_navdrawer_smaller;
            default:
                return R.layout.list_item_navdrawer_normal;
        }
    }

    private void formatNavigationDrawerItem(View view, boolean selected) {
        if (selected) {
            view.setBackgroundColor(getResources().getColor(R.color.navigation_drawer_selected_bg));
        } else {
            TypedValue typedValue = new TypedValue();
            getActivity().getTheme().resolveAttribute(
                    android.R.attr.selectableItemBackground,
                    typedValue,
                    true
            );

            // Due to some weird padding reset when setting the background, this is needed.
            // SEE: https://code.google.com/p/android/issues/detail?id=17885
            final int paddingLeft = view.getPaddingLeft();
            final int paddingTop = view.getPaddingTop();
            final int paddingRight = view.getPaddingRight();
            final int paddingBottom = view.getPaddingBottom();
            view.setBackgroundResource(typedValue.resourceId);
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

    private void storePositionState(final int position) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        if (hasSoldier()) {
            editor.putInt(STATE_SELECTED_POSITION_TRACKING, position).apply();
        }
        currentMenuSelection = position;
    }

    private void populateNavigationDrawer(final View view) {
        navigationDrawerItems.clear();
        if (hasSoldier()) {
            navigationDrawerItems.addAll(getRowsForSoldier());
        }
        navigationDrawerItems.addAll(getRowsForSocial());

        createNavigationDrawerItemsInLayout(view);
    }

    private void populateSoldierList(final View view) {
        final ViewGroup container = (ViewGroup) view.findViewById(R.id.navigation_drawer_account_list);
        container.removeAllViews();

        for (final SummarizedSoldierStatsDAO soldier : soldiers) {
            if (isSelectedSoldier(soldier)) {
                continue;
            }

            View soldierItemView = layoutInflater.inflate(R.layout.list_item_navdrawer_soldier_dropdown, container, false);
            setVisibility(soldierItemView, R.id.soldier_dropdown_arrow, View.INVISIBLE);
            populateSoldierItemView(soldierItemView, soldier);
            soldierItemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        onSoldierSelectedInDropdown(soldier);
                    }
                }
            );
            container.addView(soldierItemView);
        }
    }

    private void onSoldierSelectedInDropdown(final SummarizedSoldierStatsDAO soldier) {
        if (!isSelectedSoldier(soldier)) {
            selectedSoldierId = soldier.getSoldierId();
            selectedSoldierPlatform = soldier.getPlatformId();
            selectedSoldier = soldier;

            soldier.setLastAccess(System.currentTimeMillis());
            soldier.save();

            storeSelectionInPreferences(soldier);
            BusProvider.getInstance().post(new ActiveSoldierChangedEvent(selectedSoldierId));
        }

        final View view = getView();
        if (view == null) {
            return;
        }

        setVisibilityOfSoldiersInDrawer(view, false);
        callbacks.closeDrawer();
    }

    private void storeSelectionInPreferences(SummarizedSoldierStatsDAO soldier) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Keys.Menu.LATEST_PERSONA, soldier.getSoldierId());
        editor.putInt(Keys.Menu.LATEST_PERSONA_PLATFORM, soldier.getPlatformId());
        editor.apply();
    }

    private List<NavigationDrawerItem> getRowsForSoldier() {
        final List<NavigationDrawerItem> rows = new ArrayList<>();
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.OVERVIEW, R.string.navigationdrawer_overview, R.drawable.menu_icon_overview));
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.STATISTICS, R.string.navigationdrawer_statistics, R.drawable.menu_icon_statistics));
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.UNLOCKS, R.string.navigationdrawer_unlocks, R.drawable.menu_icon_unlocks));
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.ASSIGNMENTS, R.string.assignments, R.drawable.menu_icon_assingnments));
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.AWARDS, R.string.awards, R.drawable.menu_icon_awards));
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.SEPARATOR, R.drawable.empty));
        return rows;
    }

    private List<NavigationDrawerItem> getRowsForSocial() {
        final List<NavigationDrawerItem> rows = new ArrayList<>();
        if (selectedSoldier == null) {
            rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.HOME, R.string.navigationdrawer_home, R.drawable.empty));
        } else {
            rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.SEARCH, R.string.navigationdrawer_search, R.drawable.menu_icon_search));
            rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.SEPARATOR));
        }
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.NEWS, R.string.navigationdrawer_news, R.drawable.empty));
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.BATTLE_CHAT, R.string.appname_battlechat, R.drawable.empty));
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.SETTINGS, R.string.navigationdrawer_settings, R.drawable.empty));
        rows.add(new NavigationDrawerItem(NavigationDrawerItem.Type.ABOUT, R.string.label_about, R.drawable.empty));
        return rows;
    }

    private List<SummarizedSoldierStatsDAO> fetchSoldiersForMenu() {
        return Query.many(
            SummarizedSoldierStatsDAO.class,
            "SELECT * FROM " + SummarizedSoldierStatsDAO.TABLE_NAME + " ORDER BY lastAccess DESC"
        ).get().asList();
    }

    private Bundle buildBundleForSoldier() {
        for (SummarizedSoldierStatsDAO soldier : soldiers) {
            if (isSelectedSoldier(soldier)) {
                return BundleFactory.createForStats(soldier);
            }
        }
        return new Bundle();
    }

    private boolean isSelectedSoldier(final SummarizedSoldierStatsDAO soldier) {
        return soldier.getSoldierId().equals(selectedSoldierId) && soldier.getPlatformId() == selectedSoldierPlatform;
    }

    private void selectItemFromState(final int position) {
        int actualPosition = position;
        if (position >= navigationDrawerItemViews.length || position == INVALID_POSITION) {
            actualPosition = fetchStartingPositionForSessionState();
        }
        selectItem(
            navigationDrawerItems.get(actualPosition),
            actualPosition,
            !callbacks.isDrawerOpen(),
            true
        );
    }

    private void selectItem(final NavigationDrawerItem item, final int position, final boolean shouldCloseDrawer, final boolean isOnResume) {
        final boolean isFragment = willItemDisplayInFragment(item);
        if (navigationDrawerItemContainer != null && isFragment) {
            for (int i = 0; i < navigationDrawerItems.size(); i++) {
                if (navigationDrawerItems.get(i).getType() != NavigationDrawerItem.Type.SEPARATOR) {
                    formatNavigationDrawerItem(navigationDrawerItemViews[i], i == position);
                }
            }
            storePositionState(position);
        }

        // This should ensure that the closing animation is smooth
        new Handler().postDelayed(
            new Runnable() {
                @Override
                public void run() {
                    startItem(item, isFragment, isOnResume);
                }
            }, 300
        );

        if (callbacks != null && isFragment) {
            callbacks.onNavigationDrawerItemSelected(position, shouldCloseDrawer, item.getTitle());
        }
    }

    private boolean willItemDisplayInFragment(final NavigationDrawerItem item) {
        switch (item.getType()) {
            case ABOUT:
            case SETTINGS:
            case SEARCH:
            case BATTLE_CHAT:
                return false;
            default:
                return true;
        }
    }

    private void startItem(final NavigationDrawerItem item, final boolean displayInFragment, final boolean isOnResume) {
        if (!displayInFragment) {
            if (!isOnResume) {
                getActivity().startActivityForResult(fetchIntentForItem(item), LoginActivity.REQUEST_PROFILE);
            }
            return;
        }

        FragmentFactory.Type fragmentType = fetchFragmentTypeForItem(item);
        if (fragmentType != null) {
            try {
                final Bundle dataBundle = buildBundleForSoldier();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                final String tag = fragmentType.toString();
                transaction.replace(
                    R.id.activity_root,
                    FragmentFactory.get(fragmentType, dataBundle),
                    tag
                );
                transaction.commit();
            } catch (TypeNotPresentException ex) {
                showToast(ex.getMessage());
            } catch (IllegalStateException ise) {
                Log.e(NavigationDrawerFragment.class.getSimpleName(), ise.getMessage());
            }
        }
    }

    private Intent fetchIntentForItem(final NavigationDrawerItem item) {
        switch (item.getType()) {
            case ABOUT:
                return new Intent(getActivity(), AppInfoActivity.class);
            case SETTINGS:
                return new Intent(getActivity(), SettingsActivity.class);
            case SEARCH:
                return new Intent(getActivity(), LoginActivity.class);
            case BATTLE_CHAT:
                return ExternalAppLauncher.getIntent(getActivity(), BATTLE_CHAT_PACKAGE);
            default:
                throw new IllegalArgumentException("No activity found for type: " + item.getType());
        }
    }

    private FragmentFactory.Type fetchFragmentTypeForItem(final NavigationDrawerItem item) {
        switch (item.getType()) {
            case HOME:
                return FragmentFactory.Type.HOME;
            case NEWS:
                return FragmentFactory.Type.NEWS_LISTING;
            case OVERVIEW:
                return FragmentFactory.Type.SOLDIER_OVERVIEW;
            case STATISTICS:
                return FragmentFactory.Type.SOLDIER_STATS;
            case UNLOCKS:
                return FragmentFactory.Type.SOLDIER_UNLOCKS;
            case ASSIGNMENTS:
                return FragmentFactory.Type.SOLDIER_ASSIGNMENTS;
            case AWARDS:
                return FragmentFactory.Type.SOLDIER_AWARDS;
            default:
                throw new IllegalArgumentException("No fragment found for type: " + item.getType());
        }
    }

    public int getCheckedItemPosition() {
        return currentMenuSelection;
    }

    public void checkDefaultItemPosition() {
        selectItemFromState(fetchDefaultPosition());
    }

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(final int position, final boolean shouldClose, final int titleResource);
        boolean isDrawerOpen();
        void closeDrawer();
    }
}

package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.database.dao.stats.weapons.WeaponSorter;
import com.ninetwozero.bf4intel.database.dao.stats.weapons.WeaponStatsDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.events.stats.weapons.WeaponStatsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.stats.weapons.WeaponStatsService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.List;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class WeaponStatsFragment extends BaseLoadingListFragment {
    private ListView listView;

    public static final String WEAPON_SORT_MODE = "weaponSortMode";
    private static final String WEAPONS_AB_SUBTITLE = "weapons_ab_subtitle";
    private static final String WEAPONT_SORT_MODE_CATEGORY = "weapontSortModeCategory";

    public static WeaponStatsFragment newInstance(final Bundle data) {
        final WeaponStatsFragment fragment = new WeaponStatsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_list_stats, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle arguments = getArgumentsBundle();
        Query.one(
            WeaponStatsDAO.class,
            "SELECT * " +
                "FROM " + WeaponStatsDAO.TABLE_NAME + " " +
                "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<WeaponStatsDAO>() {
                @Override
                public boolean handleResult(WeaponStatsDAO weaponstatsDAO) {
                    if (weaponstatsDAO == null) {
                        startLoadingData();
                        return true;
                    }

                    sendDataToListView(weaponstatsDAO.getWeaponStats().getWeaponsList());
                    showLoadingState(false);
                    return true;
                }
            }
        );
    }

    @Override
    protected void startLoadingData() {
        if (isReloading || !Bf4Intel.isConnectedToNetwork()) {
            return;
        }

        showLoadingState(true);
        isReloading = true;

        final Intent intent = new Intent(getActivity(), WeaponStatsService.class);
        intent.putExtra(WeaponStatsService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int i, long l) {
        final Weapon weapon = ((WeaponStatsAdapter) listView.getAdapter()).getItem(i);
        final Bundle dataToPass = getArgumentsBundle();
        dataToPass.putSerializable(WeaponDetailsFragment.INTENT_WEAPON, weapon);

       openDetailFragment(FragmentFactory.Type.WEAPON_STATS_DETAILS, dataToPass, WeaponDetailsFragment.TAG);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onWeaponsRefreshed(WeaponStatsRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void initialize(View view) {
        setupErrorMessage(view);
        setupSwipeRefreshLayout(view);
        setupListView(view);
    }

    private void setupListView(final View view) {
        if (view == null) {
            return;
        }

        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void sendDataToListView(final List<Weapon> weaponstats) {
        if (getView() == null) {
            return;
        }

        WeaponStatsAdapter adapter = (WeaponStatsAdapter) getListAdapter();
        if (adapter == null) {
            adapter = new WeaponStatsAdapter(getActivity());
            setListAdapter(adapter);
        }
        adapter.setItems(weaponstats);

        if (sharedPreferences.getInt(WEAPON_SORT_MODE, 0) == SortMode.CATEGORIZED.ordinal()) {
            adapter.getFilter().filter(sharedPreferences.getString(WEAPONT_SORT_MODE_CATEGORY, ""));
        }

        actionBarSetSubtitleFromSharedPref(WEAPONS_AB_SUBTITLE, R.string.label_sort_all);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.filter_sort, menu);

        sortTitleResources = getResourceStringArray(R.array.ab_weapons_sort_menus);
        addMenuProviderFor(R.id.ab_action_sort, menu, sortTitleResources);

        filterTitleResources = getResourceStringArray(R.array.ab_weapon_filter_menu);
        addMenuProviderFor(R.id.ab_action_filter, menu, filterTitleResources);

        sortingKeys = WeaponSorter.WEAPON_GROUP_CODE.toArray(new String[WeaponSorter.WEAPON_GROUP_CODE.size()]);
    }

    @Override
    protected void handleFilterRequest(String category, String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        final View view = getView();
        if (view == null) {
            return;
        }

        final WeaponStatsAdapter adapter = (WeaponStatsAdapter) getListView().getAdapter();
        if (adapter == null) {
            return;
        }
        adapter.getFilter().filter(category);

        sharedPreferences.edit()
            .putInt(WEAPON_SORT_MODE, SortMode.CATEGORIZED.ordinal())
            .putString(WEAPONT_SORT_MODE_CATEGORY, category)
            .putString(WEAPONS_AB_SUBTITLE, subtitleResString)
            .commit();
    }

    @Override
    protected void handleSortingRequest(SortMode sortMode, String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        sharedPreferences.edit().putInt(WEAPON_SORT_MODE, sortMode.ordinal())
            .putString(WEAPONS_AB_SUBTITLE, subtitleResString)
            .putString(WEAPONT_SORT_MODE_CATEGORY, "")
            .commit();
        BusProvider.getInstance().post(new RefreshEvent());
    }
}

package com.ninetwozero.bf4intel.ui.unlocks.weapons;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.database.dao.unlocks.weapons.WeaponUnlockDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.weapons.WeaponUnlockSorter;
import com.ninetwozero.bf4intel.events.unlocks.weapons.WeaponUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.unlocks.weapons.WeaponUnlockService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.List;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class WeaponUnlockFragment extends BaseUnlockFragment {
    private static final String SORT_MODE = "weaponUnlockSortMode";
    private static final String AB_SUBTITLE = "weapon_unlock_ab_subtitle";
    private static final String SORT_MODE_CATEGORY = "weaponUnlockSortModeCategory";
    
    public static WeaponUnlockFragment newInstance(final Bundle data) {
        final WeaponUnlockFragment fragment = new WeaponUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle arguments = getArgumentsBundle();
        Query.one(
            WeaponUnlockDAO.class,
            "SELECT * " +
                "FROM " + WeaponUnlockDAO.TABLE_NAME + " " +
                "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<WeaponUnlockDAO>() {
                @Override
                public boolean handleResult(WeaponUnlockDAO weaponUnlockDAO) {
                    if (weaponUnlockDAO == null) {
                        startLoadingData();
                        return true;
                    }

                    sendDataToListView(weaponUnlockDAO.getWeaponUnlock().getSortedUnlocks());
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

        final Intent intent = new Intent(getActivity(), WeaponUnlockService.class);
        intent.putExtra(WeaponUnlockService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.filter_sort, menu);

        sortTitleResources = getResourceStringArray(R.array.ab_sort_menus);
        addMenuProviderFor(R.id.ab_action_sort, menu, sortTitleResources);

        filterTitleResources = getResourceStringArray(R.array.ab_weapon_filter_menu);
        addMenuProviderFor(R.id.ab_action_filter, menu, filterTitleResources);

        sortingKeys = WeaponUnlockSorter.CATEGORY_ORDER;
    }

    @Override
    protected void handleFilterRequest(String category, String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        if (getView() == null) {
            return;
        }

        final WeaponUnlockAdapter adapter = (WeaponUnlockAdapter) gridView.getAdapter();
        adapter.getFilter().filter(category);
        sharedPreferences.edit()
            .putInt(SORT_MODE, SortMode.CATEGORIZED.ordinal())
            .putString(SORT_MODE_CATEGORY, category)
            .putString(AB_SUBTITLE, subtitleResString)
            .apply();
    }

    @Override
    protected void handleSortingRequest(SortMode sortMode, String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        sharedPreferences.edit()
            .putInt(SORT_MODE, sortMode.ordinal())
            .putString(AB_SUBTITLE, subtitleResString)
            .putString(SORT_MODE_CATEGORY, "")
            .apply();
        BusProvider.getInstance().post(new RefreshEvent());
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onWeaponUnlocksRefreshed(WeaponUnlocksRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void sendDataToListView(final List<WeaponUnlockContainer> unlocks) {
        WeaponUnlockAdapter adapter = (WeaponUnlockAdapter) gridView.getAdapter();
        if (adapter == null) {
            adapter = new WeaponUnlockAdapter(getActivity());
            gridView.setAdapter(adapter);
        }
        adapter.setItems(unlocks);

        if (sharedPreferences.getInt(SORT_MODE, 0) == SortMode.CATEGORIZED.ordinal()) {
            adapter.getFilter().filter(sharedPreferences.getString(SORT_MODE_CATEGORY, ""));
        }
        actionBarSetSubtitleFromSharedPref(AB_SUBTITLE, R.string.label_sort_all);
    }
}
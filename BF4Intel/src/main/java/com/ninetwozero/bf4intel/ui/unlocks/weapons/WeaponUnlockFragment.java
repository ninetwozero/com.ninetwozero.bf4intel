package com.ninetwozero.bf4intel.ui.unlocks.weapons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.dao.unlocks.weapons.WeaponUnlockDAO;
import com.ninetwozero.bf4intel.events.unlocks.weapons.WeaponUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.unlocks.weapons.WeaponUnlockService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;
import com.squareup.otto.Subscribe;

import java.util.List;
import java.util.Map;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class WeaponUnlockFragment extends BaseUnlockFragment {
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
                    final View view = getView();
                    if (view == null || weaponUnlockDAO == null) {
                        startLoadingData();
                        return true;
                    }

                    sendDataToListView(weaponUnlockDAO.getWeaponUnlock().getSortedWeaponUnlocksMap());
                    showLoadingState(false);
                    return true;
                }
            }
        );
    }

    @Override
    protected void startLoadingData() {
        if (isReloading) {
            return;
        }

        showLoadingState(true);
        isReloading = true;

        final Intent intent = new Intent(getActivity(), WeaponUnlockService.class);
        intent.putExtra(WeaponUnlockService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int i, long l) {
        // TODO: Open details
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    @Subscribe
    public void onWeaponUnlocksRefreshed(WeaponUnlocksRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void sendDataToListView(final Map<String, List<WeaponUnlockContainer>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }
        listView.setAdapter(new WeaponUnlockAdapter(getActivity(), unlockMap));
    }
}
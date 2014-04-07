package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.dao.unlocks.vehicles.VehicleUnlockDAO;
import com.ninetwozero.bf4intel.events.unlocks.vehicles.VehicleUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.unlocks.vehicles.VehicleUnlockService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;
import com.squareup.otto.Subscribe;

import java.util.List;
import java.util.Map;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class VehicleUnlockFragment extends BaseUnlockFragment {
    public static VehicleUnlockFragment newInstance(final Bundle data) {
        final VehicleUnlockFragment fragment = new VehicleUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle arguments = getArgumentsBundle();
        Query.one(
            VehicleUnlockDAO.class,
            "SELECT * " +
                "FROM " + VehicleUnlockDAO.TABLE_NAME + " " +
                "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<VehicleUnlockDAO>() {
                @Override
                public boolean handleResult(VehicleUnlockDAO vehicleUnlockDAO) {
                    final View view = getView();
                    if (view == null || vehicleUnlockDAO == null) {
                        return true;
                    }

                    sendDataToListView(vehicleUnlockDAO.getVehicleUnlocks().getSortedVehicleUnlockMap());
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

        final Intent intent = new Intent(getActivity(), VehicleUnlockService.class);
        intent.putExtra(VehicleUnlockService.SOLDIER_BUNDLE, getArgumentsBundle());
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
    public void onVehicleUnlockedRefreshed(VehicleUnlocksRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void sendDataToListView(final Map<String, List<VehicleUnlock>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }
        listView.setAdapter(new VehicleUnlockAdapter(getActivity(), unlockMap));
    }
}

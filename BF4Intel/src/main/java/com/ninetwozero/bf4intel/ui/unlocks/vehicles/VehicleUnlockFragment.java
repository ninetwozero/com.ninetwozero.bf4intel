package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.widget.ExpandableListView;

import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlocks;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleUnlockFragment extends BaseUnlockFragment {
    private static final int ID_LOADER = 3200;

    public static VehicleUnlockFragment newInstance(final Bundle data) {
        final VehicleUnlockFragment fragment = new VehicleUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        final VehicleUnlocks unlocks = fromJson(resultMessage, VehicleUnlocks.class);
        if (unlocks.getUnlockMap() == null) {
            return;
        }

        sendDataToListView(sortItemsInMap(unlocks.getUnlockMap()));
        showLoadingState(false);
    }

    @Override
    protected void startLoadingData() {
        getLoaderManager().restartLoader(ID_LOADER, getArguments(), this);
    }

    @Override
    public Loader<Result> onCreateLoader(final int i, final Bundle bundle) {
        showLoadingState(true);
        return new IntelLoader(
            getActivity(),
            new SimpleGetRequest(
                UrlFactory.buildVehicleUnlocksURL(
                    bundle.getLong(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }


    private void sendDataToListView(final Map<String, List<VehicleUnlock>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }
        listView.setAdapter(new VehicleUnlockAdapter(getActivity(), unlockMap));
    }

    private Map<String, List<VehicleUnlock>> sortItemsInMap(final Map<String, List<VehicleUnlock>> unlockMap) {
        final Map<String, List<VehicleUnlock>> map = new HashMap<String, List<VehicleUnlock>>();
        for (String key : unlockMap.keySet()) {
            final List<VehicleUnlock> unlocks = unlockMap.get(key);
            Collections.sort(unlocks);
            map.put(key, unlocks);
        }
        return map;
    }
}

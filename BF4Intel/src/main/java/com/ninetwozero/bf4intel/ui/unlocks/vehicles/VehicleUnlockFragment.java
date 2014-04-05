package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlocks;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleUnlockFragment extends BaseUnlockFragment {
    public static VehicleUnlockFragment newInstance(final Bundle data) {
        final VehicleUnlockFragment fragment = new VehicleUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }

    protected Request<Map<String, List<VehicleUnlock>>> fetchRequest(Bundle bundle) {
        return new SimpleGetRequest<Map<String, List<VehicleUnlock>>>(
            UrlFactory.buildVehicleUnlocksURL(
                bundle.getString(Keys.Soldier.ID),
                bundle.getInt(Keys.Soldier.PLATFORM)
            ),
            this
        ) {
            @Override
            protected Map<String, List<VehicleUnlock>> doParse(String json) {
                final VehicleUnlocks unlocks = fromJson(json, VehicleUnlocks.class);
                return sortItemsInMap(unlocks.getUnlockMap());
            }

            @Override
            protected void deliverResponse(Map<String, List<VehicleUnlock>> response) {
                sendDataToListView(response);
                showLoadingState(false);
            }
        };
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

    private void sendDataToListView(final Map<String, List<VehicleUnlock>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }
        listView.setAdapter(new VehicleUnlockAdapter(getActivity(), unlockMap));
    }
}

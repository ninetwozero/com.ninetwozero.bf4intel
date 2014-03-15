package com.ninetwozero.bf4intel.ui.unlocks.weapons;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlocks;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponUnlockFragment extends BaseUnlockFragment {
    private static final int ID_LOADER = 3100;

    public static WeaponUnlockFragment newInstance(final Bundle data) {
        final WeaponUnlockFragment fragment = new WeaponUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected Request<Map<String, List<WeaponUnlockContainer>>> fetchRequest(Bundle bundle) {
        return new SimpleGetRequest<Map<String, List<WeaponUnlockContainer>>>(
            UrlFactory.buildWeaponUnlocksURL(
                bundle.getLong(Keys.Soldier.ID),
                bundle.getInt(Keys.Soldier.PLATFORM)
            ),
            this
        ) {
            @Override
            protected Map<String, List<WeaponUnlockContainer>> doParse(String json) {
                final WeaponUnlocks unlocks = fromJson(json, WeaponUnlocks.class);
                return sortItemsInMap(unlocks.getUnlockMap());
            }

            @Override
            protected void deliverResponse(Map<String, List<WeaponUnlockContainer>> response) {
                sendDataToListView(response);
                showLoadingState(false);
            }
        };
    }

    private Map<String, List<WeaponUnlockContainer>> sortItemsInMap(final Map<String, List<WeaponUnlockContainer>> unlockMap) {
        final Map<String, List<WeaponUnlockContainer>> map = new HashMap<String, List<WeaponUnlockContainer>>();
        for (String key : unlockMap.keySet()) {
            final List<WeaponUnlockContainer> unlocks = unlockMap.get(key);
            Collections.sort(unlocks);
            map.put(key, unlocks);
        }
        return map;
    }

    private void sendDataToListView(final Map<String, List<WeaponUnlockContainer>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }
        listView.setAdapter(new WeaponUnlockAdapter(getActivity(), unlockMap));
    }
}
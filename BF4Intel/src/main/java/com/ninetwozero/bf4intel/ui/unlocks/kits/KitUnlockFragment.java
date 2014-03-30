package com.ninetwozero.bf4intel.ui.unlocks.kits;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.KitUnlocks;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitUnlockFragment extends BaseUnlockFragment {
    public static KitUnlockFragment newInstance(final Bundle data) {
        final KitUnlockFragment fragment = new KitUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected Request<Map<String, List<KitItemUnlockContainer>>> fetchRequest(Bundle bundle) {
        return new SimpleGetRequest<Map<String, List<KitItemUnlockContainer>>>(
            UrlFactory.buildKitUnlocksURL(
                bundle.getString(Keys.Soldier.ID),
                bundle.getString(Keys.Soldier.NAME),
                bundle.getInt(Keys.Soldier.PLATFORM)
            ),
            this
        ) {
            @Override
            protected Map<String, List<KitItemUnlockContainer>> doParse(String json) {
                final KitUnlocks unlocks = fromJson(json, KitUnlocks.class);
                return sortItemsInMap(unlocks.getUnlockMap());
            }

            @Override
            protected void deliverResponse(Map<String, List<KitItemUnlockContainer>> response) {
                sendDataToListView(response);
                showLoadingState(false);
            }
        };
    }

    private Map<String, List<KitItemUnlockContainer>> sortItemsInMap(final Map<String, List<KitItemUnlockContainer>> unlockMap) {
        final Map<String, List<KitItemUnlockContainer>> map = new HashMap<String, List<KitItemUnlockContainer>>();
        for (String key : unlockMap.keySet()) {
            final List<KitItemUnlockContainer> unlocks = unlockMap.get(key);
            Collections.sort(unlocks);
            map.put(key, unlocks);
        }
        return map;
    }

    private void sendDataToListView(final Map<String, List<KitItemUnlockContainer>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }

        listView.setAdapter(new KitUnlockAdapter(getActivity(), unlockMap));
        //TODO: Expand or collapse at start? >>> toggleAllRows(true)
    }
}

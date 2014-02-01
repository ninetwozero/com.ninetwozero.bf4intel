package com.ninetwozero.bf4intel.ui.unlocks.kits;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.widget.ExpandableListView;

import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.KitUnlocks;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitUnlockFragment extends BaseUnlockFragment {
    private static final int ID_LOADER = 3300;

    public static KitUnlockFragment newInstance(final Bundle data) {
        final KitUnlockFragment fragment = new KitUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected void onLoadSuccess(final String resultMessage) {
        final KitUnlocks unlocks = fromJson(resultMessage, KitUnlocks.class);
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
                UrlFactory.buildKitUnlocksURL(
                    bundle.getInt(Keys.Soldier.ID),
                    bundle.getString(Keys.Soldier.NAME),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    private void sendDataToListView(final Map<String, List<KitItemUnlockContainer>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }

        listView.setAdapter(new KitUnlockAdapter(getActivity(), unlockMap));
        //TODO: Expand or collapse at start? >>> toggleAllRows(true)
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
}

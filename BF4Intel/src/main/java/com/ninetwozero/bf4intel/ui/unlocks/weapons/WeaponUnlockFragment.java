package com.ninetwozero.bf4intel.ui.unlocks.weapons;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.widget.ExpandableListView;

import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlocks;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeaponUnlockFragment extends BaseUnlockFragment {
    private static final int ID_LOADER = 3100;

    public static WeaponUnlockFragment newInstance(final Bundle data) {
        final WeaponUnlockFragment fragment = new WeaponUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected void onLoadSuccess(final String resultMessage) {
        final WeaponUnlocks unlocks = fromJson(resultMessage, WeaponUnlocks.class);
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
                UrlFactory.buildWeaponUnlocksURL(
                    bundle.getInt(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    private void sendDataToListView(final Map<String, List<WeaponUnlockContainer>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }

        listView.setAdapter(new WeaponUnlockAdapter(getActivity(), unlockMap));
        //TODO: Expand or collapse at start? We'll need to figure this out before release
        // toggleAllRows(true)
    }

    private Map<String, List<WeaponUnlockContainer>> sortItemsInMap(final Map<String, List<WeaponUnlockContainer>> unlockMap) {
        final Map<String, List<WeaponUnlockContainer>> map = new HashMap<String, List<WeaponUnlockContainer>>();
        final Set<String> keySet = unlockMap.keySet();
        for (String key : keySet) {
            final List<WeaponUnlockContainer> unlocks = unlockMap.get(key);
            Collections.sort(unlocks);
            map.put(key, unlocks);
        }
        return map;
    }
}

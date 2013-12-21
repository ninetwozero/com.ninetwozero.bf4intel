package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseExpandableIntelAdapter;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlocks;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleUnlockFragment extends BaseLoadingListFragment {
    private static final int ID_LOADER = 1230192;

    public static VehicleUnlockFragment newInstance(final Bundle data) {
        final VehicleUnlockFragment fragment = new VehicleUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.fragment_unlocks, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingState(false);
        startLoadingData();
    }


    @Override
    protected void onLoadSuccess(final String resultMessage) {
        final VehicleUnlocks unlocks = fromJson(resultMessage, VehicleUnlocks.class);
        sendDataToListView(sortItemsInMap(unlocks.getUnlockMap()));
        showLoadingState(false);
    }

    private Map<String, List<VehicleUnlock>> sortItemsInMap(final Map<String, List<VehicleUnlock>> unlockMap) {
        final Map<String, List<VehicleUnlock>> map = new HashMap<String, List<VehicleUnlock>>();
        for (String key : unlockMap.keySet()) {
            final List<VehicleUnlock> unlocks = unlockMap.get(key);
            Collections.sort(unlocks);

            /*
                TODO:
                Get int from a Map<String, Integer>?
                param String would be key as seen in JSON
                param Integer would be String resource ID
             */
            map.put(key, unlocks);
        }
        return map;
    }

    @Override
    protected void onLoadFailure(final String resultMessage) {
        Log.d(getClass().getSimpleName(), "[onLoadFailure] resultMessage => " + resultMessage);
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
                    bundle.getString(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    private void initialize(final View view) {
        setupListView(view);
    }

    private void setupListView(final View view) {
        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        final TextView emptyTextView = (TextView) view.findViewById(android.R.id.empty);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
        emptyTextView.setText(R.string.msg_no_unlocks);
    }

    private void sendDataToListView(final Map<String, List<VehicleUnlock>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }

        listView.setAdapter(new VehicleUnlockAdapter(unlockMap, getActivity()));
        //TODO: Expand or collapse at start? >>> toggleAllRows(true)
    }

    protected void toggleAllRows(final boolean expand) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }
        final BaseExpandableIntelAdapter adapter = (BaseExpandableIntelAdapter) listView.getExpandableListAdapter();

        for (int i = 0, max = adapter.getGroupCount(); i < max; i++) {
            if (expand) {
                listView.expandGroup(i);
            } else {
                listView.collapseGroup(i);
            }
        }
    }
}
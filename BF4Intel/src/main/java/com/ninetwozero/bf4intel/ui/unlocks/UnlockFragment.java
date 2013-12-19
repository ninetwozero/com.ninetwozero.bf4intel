package com.ninetwozero.bf4intel.ui.unlocks;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlocks;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.unlocks.vehicles.VehicleUnlockAdapter;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UnlockFragment extends BaseLoadingListFragment {
    private static final int ID_LOADER = 1230192;

    public static UnlockFragment newInstance(final Bundle data) {
        final UnlockFragment fragment = new UnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.generic_list, parent, false);
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
        sendDataToListView(convertMapToList(unlocks.getUnlockMap()));
        showLoadingState(false);
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

    @Override
    public void onListItemClick(final ListView l, final View v, final int position, final long id) {
    }

    private void initialize(final View view) {
        setupListView(view);
    }

    private void setupListView(final View view) {
        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        final TextView emptyTextView = (TextView) view.findViewById(android.R.id.empty);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
        emptyTextView.setText(R.string.msg_no_unlocks);
    }

    private void sendDataToListView(final List<VehicleUnlock> unlocks) {
        if (getListView() == null) {
            return;
        }
        setListAdapter(new VehicleUnlockAdapter(unlocks, getContext()));
    }

    private List<VehicleUnlock> convertMapToList(final Map<String, List<VehicleUnlock>> unlockMap) {
        final List<VehicleUnlock> unlocks = new ArrayList<VehicleUnlock>();
        if (unlockMap != null) {
            for (String key : unlockMap.keySet()) {
                final List<VehicleUnlock> unlockList = unlockMap.get(key);
                unlocks.add(new VehicleUnlock(key));

                Collections.sort(unlockList);
                unlocks.addAll(unlockList);
            }
        }
        return unlocks;
    }
}

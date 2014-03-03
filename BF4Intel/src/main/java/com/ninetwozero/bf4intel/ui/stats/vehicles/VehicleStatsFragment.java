package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStats;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStatistics;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.List;

public class VehicleStatsFragment extends BaseLoadingListFragment {

    private static final int ID_LOADER = 2200;

    public static VehicleStatsFragment newInstance(final Bundle data) {
        final VehicleStatsFragment fragment = new VehicleStatsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);
        return layoutInflater.inflate(R.layout.fragment_list_stats, parent, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        getLoaderManager().initLoader(ID_LOADER, getArguments(), this);
    }

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        showLoadingState(true);
        return new IntelLoader(
            getActivity(),
            new SimpleGetRequest(
                UrlFactory.buildVehicleStatsURL(
                    bundle.getLong(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        final VehicleStatistics vs = fromJson(resultMessage, VehicleStatistics.class);
        final List<GroupedVehicleStats> vehiclesGrouped = vs.fetchGroupVehicles();

        getListView().setAdapter(new VehicleStatsAdapter(getActivity(), vehiclesGrouped));
        showLoadingState(false);
    }

    @Override
    protected void onLoadFailure(final Loader loader, String resultMessage) {
        Log.e(VehicleStatsFragment.class.getSimpleName(), resultMessage);
    }
}

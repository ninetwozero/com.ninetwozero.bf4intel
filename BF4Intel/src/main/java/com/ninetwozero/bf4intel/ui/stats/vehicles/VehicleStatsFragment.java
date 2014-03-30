package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStats;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStatistics;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

public class VehicleStatsFragment extends BaseLoadingListFragment {
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

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        showLoadingState(true);
        Bf4Intel.getRequestQueue().add(fetchRequest(getArguments()));
    }

    private Request<List<GroupedVehicleStats>> fetchRequest(Bundle bundle) {
        return new SimpleGetRequest<List<GroupedVehicleStats>>(
            UrlFactory.buildVehicleStatsURL(
                bundle.getString(Keys.Soldier.ID),
                bundle.getInt(Keys.Soldier.PLATFORM)
            ),
            this
        ) {
            @Override
            protected List<GroupedVehicleStats> doParse(String json) {
                final VehicleStatistics vs = fromJson(json, VehicleStatistics.class);
                final List<GroupedVehicleStats> vehiclesGrouped = vs.fetchGroupVehicles();
                return vehiclesGrouped;
            }

            @Override
            protected void deliverResponse(List<GroupedVehicleStats> response) {
                setListAdapter(new VehicleStatsAdapter(getActivity(), response));
                showLoadingState(false);
            }
        };
    }
}

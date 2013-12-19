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
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStatistics;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStats;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.*;

public class VehicleStatsFragment extends BaseLoadingListFragment {

    private static final int ID_LOADER = 2200;
    private ListView listView;
    private VehicleStatistics vs;
    private VehicleStatsAdapter adapter;

    public static VehicleStatsFragment newInstance(final Bundle data) {
        final VehicleStatsFragment fragment = new VehicleStatsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);
        final View view = layoutInflater.inflate(R.layout.fragment_list_stats, parent, false);
        listView = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        getActivity().getSupportLoaderManager().initLoader(ID_LOADER, null, this);
    }

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        showLoadingState(true);
        return new IntelLoader(getActivity().getApplicationContext(), new SimpleGetRequest(UrlFactory.buildVehicleStatsURL(200661244, 1)));
    }

    @Override
    public void onLoadFinished(Loader<Result> resultLoader, Result result) {
        if (result == Result.SUCCESS) {
            onLoadSuccess(result.getResultMessage());
        } else {
            onLoadFailure(result.getResultMessage());
        }
    }

    @Override
    protected void onLoadSuccess(String resultMessage) {
        vs = gson.fromJson(resultMessage, VehicleStatistics.class);
        showLoadingState(false);
        List<GroupedVehicleStats> vehiclesGrouped =  groupVehicles();
        adapter = new VehicleStatsAdapter(vehiclesGrouped, getContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<GroupedVehicleStats> groupVehicles() {
        List<VehicleStats> vehicleStatsList = vs.getVehiclesList();
        Map<String, GroupedVehicleStats> vehicleGroupsMap = new HashMap<String, GroupedVehicleStats>();
        for (VehicleStats stat : vehicleStatsList) {
            GroupedVehicleStats group;
            if (vehicleGroupsMap.containsKey(stat.getVehicleGroup())) {
                GroupedVehicleStats temp = vehicleGroupsMap.get(stat.getVehicleGroup());
                List<VehicleStats> vehicleStats = new ArrayList<VehicleStats>(temp.getVehicleList());
                vehicleStatsList.add(stat);
                group = new GroupedVehicleStats(temp.getGroupName(),
                    temp.getServiceStarsCount(),
                    temp.getServiceStarProgress(),
                    temp.getKillCount() + stat.getKillsCount(),
                    temp.getTimeInVehicle() + stat.getTimeInVehicle(),
                    vehicleStats);

            } else {
                List<VehicleStats> vehicleStats = new ArrayList<VehicleStats>();
                vehicleStats.add(stat);
                group = new GroupedVehicleStats(
                    stat.getVehicleGroup(),
                    getServiceStarsCount(stat.getVehicleGroup()),
                    stat.getServiceStarProgress(),
                    stat.getKillsCount(),
                    stat.getTimeInVehicle(),
                    vehicleStats);
            }
            vehicleGroupsMap.put(stat.getVehicleGroup(), group);
        }
        List<GroupedVehicleStats> groupedVehicleStatsList = new ArrayList<GroupedVehicleStats>(vehicleGroupsMap.values());
        Collections.sort(groupedVehicleStatsList);
        return groupedVehicleStatsList;
    }

    private int getServiceStarsCount(String vehicleGroup) {
        return vs.getUnlockProgressions().containsKey(vehicleGroup)
            ? vs.getUnlockProgressions().get(vehicleGroup).getStarsTaken()
            : 0;
    }

    @Override
    protected void onLoadFailure(String resultMessage) {
        Log.e(VehicleStatsFragment.class.getSimpleName(), resultMessage);
    }
}

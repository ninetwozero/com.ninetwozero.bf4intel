package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStats;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStats;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehiclesGroupStringMap;

import java.util.Collections;
import java.util.List;

public class VehicleStatsAdapter extends BaseIntelAdapter<GroupedVehicleStats> {

    public VehicleStatsAdapter(final Context context, final List<GroupedVehicleStats> vehicleStats) {
        super(context, vehicleStats);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final GroupedVehicleStats stats = itemsList.get(position);
        final List<VehicleStats> vehiclesList = stats.getVehicleList();
        Collections.sort(vehiclesList);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_stats_item, parent, false);
        }

        setText(view, R.id.index, String.valueOf(position + 1));
        setText(view, R.id.service_star_count, String.valueOf(stats.getServiceStarsCount()));
        setText(view, R.id.item_name, VehiclesGroupStringMap.get(stats.getGroupName()));
        setText(view, R.id.kill_count, R.string.num_kills, stats.getKillCount());

        setImage(view, R.id.item_image, VehicleImageMap.get(vehiclesList.get(0).getName()));
        setProgress(view, R.id.item_progress, stats.getServiceStarProgress());

        return view;
    }
}

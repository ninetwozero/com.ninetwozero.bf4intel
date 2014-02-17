package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStats;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStats;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehiclesGroupStringMap;

import java.util.Collections;
import java.util.List;

public class VehicleStatsAdapter extends BaseIntelAdapter<GroupedVehicleStats> {

    public VehicleStatsAdapter(List<GroupedVehicleStats> vehicles, Context context) {
        super(vehicles, context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_stats_item, parent, false);
        }

        GroupedVehicleStats stats = itemsList.get(position);
        List<VehicleStats> vehiclesList = stats.getVehicleList();
        Collections.sort(vehiclesList);

        ((TextView) view.findViewById(R.id.index)).setText(String.valueOf(position + 1));
        ImageView vehicleImg = (ImageView) view.findViewById(R.id.vehicle_image);
        vehicleImg.setImageResource(VehicleImageMap.get(vehiclesList.get(0).getName()));
        vehicleImg.setVisibility(View.VISIBLE);

        ((TextView)view.findViewById(R.id.service_star_count)).setText(String.valueOf(stats.getServiceStarsCount()));
        ((TextView) view.findViewById(R.id.item_name)).setText(VehiclesGroupStringMap.get(stats.getGroupName()));
        ((TextView) view.findViewById(R.id.item_kills)).setText(String.valueOf(stats.getKillCount()));

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.item_progress);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(stats.getServiceStarProgress());

        return view;
    }
}

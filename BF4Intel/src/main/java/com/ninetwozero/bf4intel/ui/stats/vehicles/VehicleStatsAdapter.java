package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStats;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;

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
            view = LayoutInflater.from(context).inflate(R.layout.list_item_weapon, parent, false);
        }

        GroupedVehicleStats stats = itemsList.get(position);
        List<VehicleStats> vehiclesList = stats.getVehicleList();
        Collections.sort(vehiclesList);

        ((TextView) view.findViewById(R.id.index)).setText(String.valueOf(position + 1));
        ((ImageView) view.findViewById(R.id.weapon_image)).setImageResource(VehicleImageMap.get(vehiclesList.get(0).getUniqueName()));
        ((TextView)view.findViewById(R.id.service_star_count)).setText(String.valueOf(stats.getServiceStarsCount()));
        ((TextView) view.findViewById(R.id.weapon_name)).setText(stats.getGroupName());
        ((TextView) view.findViewById(R.id.weapon_kills)).setText(String.valueOf(stats.getKillCount()));

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.weapon_progress);
        progressBar.setProgress(stats.getServiceStarProgress());

        return view;
    }
}

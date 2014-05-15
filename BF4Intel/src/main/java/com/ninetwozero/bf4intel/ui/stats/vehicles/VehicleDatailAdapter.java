package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStats;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleStringMap;

import java.util.List;

public class VehicleDatailAdapter extends BaseIntelAdapter<VehicleStats> {

    public VehicleDatailAdapter(Context context, List<VehicleStats> itemsList) {
        super(context, itemsList);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        VehicleStats stats = itemsList.get(position);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_vehicle_detail, parent, false);
        }

        setText(view, R.id.vehicle_list_item_name, VehicleStringMap.get(stats.getName()));
        setText(view, R.id.vehicle_list_item_score, String.valueOf(stats.getKillsCount()));

        return view;
    }
}

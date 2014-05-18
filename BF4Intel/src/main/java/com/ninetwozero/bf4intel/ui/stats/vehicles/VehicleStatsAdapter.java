package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStats;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehiclesGroupStringMap;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

public class VehicleStatsAdapter extends BaseIntelAdapter<GroupedVehicleStats> {

    public VehicleStatsAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final GroupedVehicleStats stats = itemsList.get(position);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_stats_item, parent, false);
        }

        setText(
            view,
            R.id.item_name,
            R.string.stat_item_title,
            position + 1,
            context.getString(VehiclesGroupStringMap.get(stats.getGroupName()))
        );
        setText(view, R.id.index, String.valueOf(position + 1));
        setText(view, R.id.service_star_count, String.valueOf(stats.getServiceStarsCount()));
        setText(view, R.id.item_name, VehiclesGroupStringMap.get(stats.getGroupName()));
        setText(view, R.id.kill_count, R.string.num_kills, NumberFormatter.format(stats.getKillCount()));

        setText(view, R.id.kill_count, R.string.num_kills, stats.getKillCount());
        setText(view, R.id.item_progress_value, stats.getServiceStarProgress() + "%");
        setText(view, R.id.service_star_count, String.valueOf(stats.getServiceStarsCount()));
        setImage(view, R.id.item_image, VehicleImageMap.get(stats.getVehicleList().get(0).getName()));
        setProgress(view, R.id.item_progress, stats.getServiceStarProgress());

        return view;
    }
}

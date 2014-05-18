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
        setText(view, R.id.kill_count, R.string.num_kills, NumberFormatter.format(stats.getKillCount()));
        setImage(view, R.id.item_image, VehicleImageMap.get(stats.getVehicleList().get(0).getName()));

        if (VehiclesGroupStringMap.hasServiceStar(stats.getGroupName())) {
            setVisibility(view, R.id.service_star_count, View.GONE);
            setVisibility(view, R.id.item_progress_value, View.GONE);
            setVisibility(view, R.id.item_progress, View.GONE);
            setVisibility(view, R.id.item_service_star, View.GONE);
        } else {
            setText(view, R.id.item_progress_value, stats.getServiceStarProgress() + "%");
            setText(view, R.id.service_star_count, String.valueOf(stats.getServiceStarsCount()));
            setProgress(view, R.id.item_progress, stats.getServiceStarProgress());
        }

        return view;
    }
}

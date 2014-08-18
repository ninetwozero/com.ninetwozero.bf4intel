package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStats;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehiclesGroupStringMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.ninetwozero.bf4intel.utils.NumberFormatter;
import com.ninetwozero.bf4intel.utils.StatsUtils;
import com.squareup.picasso.Picasso;

public class VehicleStatsAdapter extends BaseIntelAdapter<GroupedVehicleStats> {

    private Context context;

    public VehicleStatsAdapter(final Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final GroupedVehicleStats stats = itemsList.get(position);
        WeaponHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_vehicle, parent, false);
            holder = getVehicleHolder(view);

            view.setTag(holder);
        } else {
            holder = (WeaponHolder) view.getTag();
        }

        holder.itemName.setText(formattedTextFromResources(
            R.string.stat_item_title,
            position + 1,
            context.getString(VehiclesGroupStringMap.get(stats.getGroupName()))));

        holder.killCount.setText(formattedTextFromResources(R.string.num_kills, NumberFormatter.format(stats.getKillCount())));
        setImage(holder.vehicleImage, VehicleImageMap.get(stats.getVehicleList().get(0).getName()));

        if (VehiclesGroupStringMap.hasServiceStar(stats.getGroupName())) {
            holder.serviceStarCount.setVisibility(View.GONE);
            holder.itemProgressValue.setVisibility(View.GONE);
            holder.itemProgress.setVisibility(View.GONE);
            holder.itemServiceStar.setVisibility(View.GONE);
        } else {
            holder.serviceStarCount.setVisibility(View.VISIBLE);
            holder.itemProgressValue.setVisibility(View.VISIBLE);
            holder.itemProgress.setVisibility(View.VISIBLE);
            holder.itemServiceStar.setVisibility(View.VISIBLE);
            holder.itemProgressValue.setText(stats.getServiceStarProgress() + "%");
            holder.serviceStarCount.setText(String.valueOf(stats.getServiceStarsCount()));
            holder.itemProgress.setProgress(stats.getServiceStarProgress());
        }

        if (holder.killPerMinute != null && stats.getKillCount() > 0) {
            double killPerMinuteValue = StatsUtils.calculateKillsPerMinute(
                stats.getKillCount(), stats.getTimeInVehicle()
            );
            holder.killPerMinute.setText(NumberFormatter.format(killPerMinuteValue));
            holder.wrapKillPerMinute.setVisibility(View.VISIBLE);
        } else if (holder.killPerMinute != null) {
            holder.wrapKillPerMinute.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    private WeaponHolder getVehicleHolder(View view) {
        WeaponHolder holder = new WeaponHolder();
        holder.itemName = (TextView) view.findViewById(R.id.item_name);
        holder.killCount = (TextView) view.findViewById(R.id.kill_count);
        holder.vehicleImage = (ImageView) view.findViewById(R.id.vehicle_image);
        holder.serviceStarCount = (TextView) view.findViewById(R.id.service_star_count);
        holder.itemProgressValue = (TextView) view.findViewById(R.id.item_progress_value);
        holder.itemProgress = (ProgressBar) view.findViewById(R.id.item_progress);
        holder.itemServiceStar = (ImageView) view.findViewById(R.id.item_service_star);
        holder.wrapKillPerMinute = (ViewGroup) view.findViewById(R.id.wrap_kill_per_minute);
        holder.killPerMinute = (TextView) view.findViewById(R.id.kill_per_minute);
        return holder;
    }

    private static class WeaponHolder {

        public TextView itemName;
        public TextView killCount;
        public ImageView vehicleImage;
        public TextView serviceStarCount;
        public TextView itemProgressValue;
        public ProgressBar itemProgress;
        public ImageView itemServiceStar;
        public ViewGroup wrapKillPerMinute;
        public TextView killPerMinute;
    }
}

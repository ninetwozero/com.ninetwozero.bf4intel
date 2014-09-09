package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseFilterableIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.ninetwozero.bf4intel.utils.NumberFormatter;
import com.ninetwozero.bf4intel.utils.StatsUtils;

import java.util.ArrayList;
import java.util.List;

public class WeaponStatsAdapter extends BaseFilterableIntelAdapter<Weapon> {

    private Context context;

    public WeaponStatsAdapter(final Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Weapon weapon = itemsList.get(position);
        WeaponHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_weapon, parent, false);
            holder = getWeaponHolder(view);

            view.setTag(holder);
        } else {
            holder = (WeaponHolder) view.getTag();
        }
        holder.serviceStarCount.setText(String.valueOf(weapon.getServiceStarsCount()));
        holder.itemName.setText(formattedTextFromResources(R.string.stat_item_title,
            position + 1,
            context.getString(WeaponStringMap.get(weapon.getUniqueName()))));
        holder.killCount.setText(formattedTextFromResources(R.string.num_kills, NumberFormatter.format(weapon.getKills())));
        holder.itemProgressValue.setText(formattedTextFromResources(R.string.generic_x_of_y,
            weapon.getServiceStarsProgress(),
            100));
        setImage(holder.weaponImage, WeaponImageMap.get(weapon.getUniqueName()));
        holder.itemProgress.setProgress(weapon.getServiceStarsProgress());
        setupAccuracyView(weapon, holder);
        setupKillPerMinuteView(weapon, holder);

        return view;
    }

    private WeaponHolder getWeaponHolder(View view) {
        WeaponHolder holder = new WeaponHolder();
        holder.serviceStarCount = (TextView) view.findViewById(R.id.service_star_count);
        holder.itemName = (TextView) view.findViewById(R.id.item_name);
        holder.killCount = (TextView) view.findViewById(R.id.kill_count);
        holder.itemProgressValue = (TextView) view.findViewById(R.id.item_progress_value);
        holder.weaponImage = (ImageView) view.findViewById(R.id.weapon_image);
        holder.itemProgress = (ProgressBar) view.findViewById(R.id.item_progress);
        holder.itemProgress.setMax(100);
        holder.wrapAccuracy = (ViewGroup) view.findViewById(R.id.wrap_accuracy);
        holder.accuracyWithWeapon = (TextView) view.findViewById(R.id.accuracy_with_weapon);
        holder.wrapKillPerMinute = (ViewGroup) view.findViewById(R.id.wrap_kill_per_minute);
        holder.killPerMinute = (TextView) view.findViewById(R.id.kill_per_minute);
        return holder;
    }

    private void setupAccuracyView(final Weapon weapon, final WeaponHolder holder) {
        if (holder.accuracyWithWeapon != null && weapon.getShotsFired() > 0) {
            holder.accuracyWithWeapon.setText(NumberFormatter.percentageFormat(weapon.getAccuracy()));
            holder.wrapAccuracy.setVisibility(View.VISIBLE);
        } else if (holder.accuracyWithWeapon != null) {
            holder.wrapAccuracy.setVisibility(View.INVISIBLE);
        }
    }

    private void setupKillPerMinuteView(final Weapon weapon, final WeaponHolder holder) {
        if (holder.killPerMinute != null && weapon.getKills() > 0 && weapon.getTimeEquipped() > 0) {
            double killPerMinuteValue = StatsUtils.calculateKillsPerMinute(
                weapon.getKills(), weapon.getTimeEquipped()
            );
            holder.killPerMinute.setText(NumberFormatter.format(killPerMinuteValue));
            holder.wrapKillPerMinute.setVisibility(View.VISIBLE);
        } else if (holder.killPerMinute != null) {
            holder.killPerMinute.setText(R.string.empty);
            holder.wrapKillPerMinute.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected List<Weapon> filterItems(CharSequence constraint) {
        List<Weapon> filteredWeapons = new ArrayList<Weapon>();
        for (Weapon weapon : listWithAllItems) {
            if (weapon.getGroupCode().equals(constraint)) {
                filteredWeapons.add(weapon);
            }
        }
        return filteredWeapons;
    }

    private static class WeaponHolder {
        TextView serviceStarCount;
        TextView itemName;
        TextView killCount;
        TextView itemProgressValue;
        ImageView weaponImage;
        ProgressBar itemProgress;
        ViewGroup wrapAccuracy;
        TextView accuracyWithWeapon;
        ViewGroup wrapKillPerMinute;
        TextView killPerMinute;
    }
}

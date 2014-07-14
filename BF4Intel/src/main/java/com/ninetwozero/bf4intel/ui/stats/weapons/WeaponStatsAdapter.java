package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public WeaponStatsAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Weapon weapon = itemsList.get(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_weapon, parent, false);
        }

        setText(view, R.id.service_star_count, String.valueOf(weapon.getServiceStarsCount()));
        setText(
            view,
            R.id.item_name,
            R.string.stat_item_title,
            position + 1,
            context.getString(WeaponStringMap.get(weapon.getUniqueName()))
        );
        setText(view, R.id.kill_count, R.string.num_kills, NumberFormatter.format(weapon.getKills()));
        setText(view,
            R.id.item_progress_value,
            R.string.generic_x_of_y,
            weapon.getServiceStarsProgress(),
            100
        );
        setImage(view, R.id.weapon_image, WeaponImageMap.get(weapon.getUniqueName()));
        setProgress(view, R.id.item_progress, weapon.getServiceStarsProgress());

        TextView accuracy = (TextView) view.findViewById(R.id.accuracy_with_weapon);
        if (accuracy != null && weapon.getShotsFired() > 0) {
            accuracy.setText(NumberFormatter.percentageFormat(weapon.getAccuracy()));
            setVisibility(view, R.id.wrap_accuracy, View.VISIBLE);
        } else if (accuracy != null) {
            setVisibility(view, R.id.wrap_accuracy, View.INVISIBLE);
        }

        TextView killPerMinute = (TextView) view.findViewById(R.id.kill_per_minute);
        if (killPerMinute != null && weapon.getTimeEquipped()> 0) {
            double killPerMinuteValue = StatsUtils.calculateKillsPerMinute(
                weapon.getKills(), weapon.getTimeEquipped()
            );
            killPerMinute.setText(NumberFormatter.format(killPerMinuteValue));
            setVisibility(view, R.id.wrap_kill_per_minute, View.VISIBLE);
        } else if (killPerMinute != null) {
            killPerMinute.setText(R.string.empty);
            setVisibility(view, R.id.wrap_kill_per_minute, View.INVISIBLE);
        }

        return view;
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
}

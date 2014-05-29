package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

public class WeaponStatsAdapter extends BaseIntelAdapter<Weapon> {

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
        setText(
            view,
            R.id.item_progress_value,
            R.string.generic_x_of_y,
            weapon.getServiceStarsProgress(),
            100
        );
        setImage(view, R.id.weapon_image, WeaponImageMap.get(weapon.getUniqueName()));
        setProgress(view, R.id.item_progress, weapon.getServiceStarsProgress());

        TextView accuracy = (TextView) view.findViewById(R.id.accuracy_with_weapon);
        if (accuracy != null && weapon.getAccuracy() > 0) {
            accuracy.setText(NumberFormatter.percentageFormat(weapon.getAccuracy()));
            setVisibility(view, R.id.accuracy_label, View.VISIBLE);
        } else if (accuracy != null) {
            setVisibility(view, R.id.accuracy_label, View.INVISIBLE);
        }

        TextView killPerMinute = (TextView) view.findViewById(R.id.kill_per_minute);
        if (killPerMinute != null && weapon.getTimeEquipped() > 0) {
            double killPerMinValue = (weapon.getKills() * 100) / DateTimeUtils.toMinutes(weapon.getTimeEquipped());
            killPerMinute.setText(NumberFormatter.format(killPerMinValue / 100));
            setVisibility(view, R.id.kill_per_minute_label, View.VISIBLE);
        } else if (killPerMinute != null) {
            setVisibility(view, R.id.kill_per_minute_label, View.INVISIBLE);
        }

        return view;
    }
}

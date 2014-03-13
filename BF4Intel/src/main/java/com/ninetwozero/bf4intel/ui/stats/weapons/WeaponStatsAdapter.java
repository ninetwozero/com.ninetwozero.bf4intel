package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponsImageMap;

import java.util.List;

public class WeaponStatsAdapter extends BaseIntelAdapter<Weapon> {

    public WeaponStatsAdapter(final Context context, final List<Weapon> weaponStats) {
        super(context, weaponStats);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Weapon weapon = itemsList.get(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_stats_item, parent, false);
        }

        setText(view, R.id.index, String.valueOf(position + 1));
        setText(view, R.id.service_star_count, String.valueOf(weapon.getServiceStarsCount()));
        setText(view, R.id.item_name, WeaponStringMap.get(weapon.getUniqueName()));
        setText(view, R.id.kill_count, R.string.num_kills, weapon.getKills());

        setImage(view, R.id.item_image, WeaponsImageMap.get(weapon.getUniqueName()));
        setProgress(view, R.id.item_progress, weapon.getServiceStarsProgress());

        return view;
    }
}

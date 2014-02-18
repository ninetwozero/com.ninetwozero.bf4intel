package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponsImageMap;

import java.util.List;
import java.util.Locale;

public class WeaponStatsAdapter extends BaseIntelAdapter<Weapon> {

    public WeaponStatsAdapter(List<Weapon> itemsList, Context context) {
        super(itemsList, context);
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
        Weapon weapon = itemsList.get(position);

        ((TextView) view.findViewById(R.id.index)).setText(String.valueOf(position + 1));
        ImageView weaponImg = (ImageView) view.findViewById(R.id.weapon_image);
        weaponImg.setImageResource(WeaponsImageMap.get(weapon.getUniqueName()));
        weaponImg.setVisibility(View.VISIBLE);

        ((TextView) view.findViewById(R.id.service_star_count)).setText(String.valueOf(weapon.getServiceStarsCount()));
        ((TextView) view.findViewById(R.id.item_name)).setText(weapon.getName().toUpperCase(Locale.getDefault()));
        ((TextView) view.findViewById(R.id.item_kills)).setText(String.valueOf(weapon.getKills()));

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.item_progress);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(weapon.getServiceStarsProgress());

        return view;
    }
}

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
import com.ninetwozero.bf4intel.json.weaponstats.Weapon;

import java.util.List;

public class WeaponStatsAdapter extends BaseIntelAdapter<Weapon> {

    public WeaponStatsAdapter(List<Weapon> itemsList, Context context) {
        super(itemsList, context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_weapon, parent, false);
        }
        Weapon weapon = itemsList.get(position);

        ((TextView) view.findViewById(R.id.index)).setText(String.valueOf(position + 1));

        ((ImageView)view.findViewById(R.id.weapon_image)).setImageResource(WeaponsImageMap.weaponsMap.get(weapon.getUniqueName()));

        ((TextView) view.findViewById(R.id.service_star_count)).setText(String.valueOf(weapon.getServiceStarsCount()));

        ((TextView) view.findViewById(R.id.weapon_name)).setText(weapon.getName().toUpperCase());

        ((TextView)view.findViewById(R.id.weapon_kills)).setText(String.valueOf(weapon.getKills()));

        if(weapon.getServiceStarsCount() > 0) {
            view.findViewById(R.id.weapon_progress).setVisibility(View.GONE);
        } else {
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.weapon_progress);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(weapon.getServiceStarsProgress());
        }
        return view;
    }
}

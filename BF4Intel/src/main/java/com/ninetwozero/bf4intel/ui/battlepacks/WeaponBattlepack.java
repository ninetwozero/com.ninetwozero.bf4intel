package com.ninetwozero.bf4intel.ui.battlepacks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.battlepacks.WeaponPack;
import com.ninetwozero.bf4intel.ui.BaseListItem;
import com.ninetwozero.bf4intel.ui.SimpleListAdapter;
import com.squareup.picasso.Picasso;

public class WeaponBattlepack implements BaseListItem {

    private WeaponPack weaponPack;
    private Context context;

    public WeaponBattlepack(WeaponPack weaponPack, Context context) {
        this.weaponPack = weaponPack;
        this.context = context;
    }

    @Override
    public int getViewType() {
        return SimpleListAdapter.RowType.ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View view) {
        WeaponPackHolder holder;
        if(view == null) {
            view = inflater.inflate(R.layout.list_item_weapon_battlepack, null);
            holder = getWeaponPackHolder(view);
            view.setTag(holder);
        } else {
            holder = (WeaponPackHolder) view.getTag();
        }

        Picasso picasso = Picasso.with(context);
        picasso.load(weaponPack.getWeaponInfo().getUniqueId()).into(holder.weaponImage);
        return view;
    }

    private WeaponPackHolder getWeaponPackHolder(View view) {
        WeaponPackHolder holder = new WeaponPackHolder();
        holder.weaponImage = (ImageView) view.findViewById(R.id.weapon_image);
        holder.battlepackImage = (ImageView) view.findViewById(R.id.battlepack_image);
        holder.weaponName = (TextView) view.findViewById(R.id.weapon_name);
        holder.valueNeeded = (TextView) view.findViewById(R.id.value_needed);
        return holder;
    }

    private static class WeaponPackHolder {
        public ImageView weaponImage;
        public ImageView battlepackImage;
        public TextView weaponName;
        public TextView valueNeeded;
    }
}

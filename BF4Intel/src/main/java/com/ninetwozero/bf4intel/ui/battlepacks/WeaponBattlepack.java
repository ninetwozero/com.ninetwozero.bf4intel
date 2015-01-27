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
        if(view == null) {
            view = inflater.inflate(R.layout.list_item_weapon_battlepack, null);
        }

        return view;
    }

    private WeaponPackHolder getWeaponPackHolder(View view) {
        WeaponPackHolder holder = new WeaponPackHolder();
        holder.
    }

    private static class WeaponPackHolder {
        public ImageView weaponImage;
        public ImageView battlepackImage;
        public TextView weaponName;
        public TextView valueNeeded;
    }
}

package com.ninetwozero.bf4intel.ui.unlocks.weapons;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlock;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.resources.maps.unlocks.UnlockImageSlugMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringSlugMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponUnlockAdapter extends BaseUnlockAdapter<WeaponUnlockContainer> {

    public WeaponUnlockAdapter(final Context context) {
        super(context);
    }

    @Override
    protected List<WeaponUnlockContainer> filterItems(final CharSequence constraint) {
        List<WeaponUnlockContainer> filteredItems = new ArrayList<WeaponUnlockContainer>();

        for (WeaponUnlockContainer item : listWithAllItems) {
            if (item.getCategory().equalsIgnoreCase(constraint.toString())) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {
        final WeaponUnlock unlock = getItem(position).getUnlock();
        final UnlockCriteria criteria = unlock.getCriteria();
        WeaponUnlockHolder holder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_item_unlocks, parent, false);
            holder = getWeaponUnlockHolder(view);

            view.setTag(holder);
        } else {
            holder = (WeaponUnlockHolder) view.getTag();
        }

        setImage(holder.unlockImage, UnlockImageSlugMap.get(unlock.getSlug()));
        holder.unlockTitle.setText(WeaponStringSlugMap.get(unlock.getSlug()));
        displayInformationForCriteria(holder, criteria);

        return view;
    }

    private WeaponUnlockHolder getWeaponUnlockHolder(View view) {
        WeaponUnlockHolder holder = new WeaponUnlockHolder();
        holder.unlockImage = (ImageView) view.findViewById(R.id.img_unlock);
        holder.unlockTitle = (TextView) view.findViewById(R.id.unlock_title);
        holder.unlockCompletion = (ProgressBar) view.findViewById(R.id.unlock_completion);
        holder.unlockStatusIcon = (ImageView) view.findViewById(R.id.unlock_status_icon);
        return holder;
    }

    private static class WeaponUnlockHolder extends UnlockHolder {

        public ImageView unlockImage;
        public TextView unlockTitle;
    }
}
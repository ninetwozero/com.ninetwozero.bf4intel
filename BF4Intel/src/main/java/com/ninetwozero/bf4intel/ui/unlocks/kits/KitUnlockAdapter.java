package com.ninetwozero.bf4intel.ui.unlocks.kits;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlock;
import com.ninetwozero.bf4intel.resources.maps.unlocks.UnlockImageSlugMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringSlugMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.ArrayList;
import java.util.List;

public class KitUnlockAdapter extends BaseUnlockAdapter<KitItemUnlockContainer> {
    public KitUnlockAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {
        final WeaponUnlock unlock = getItem(position).getUnlock();
        final UnlockCriteria criteria = unlock.getCriteria();
        KitUnlockHolder holder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_item_unlocks, parent, false);
            holder = getKitUnlockHolder(view);
            view.setTag(holder);
        } else {
            holder = (KitUnlockHolder) view.getTag();
        }

        setImage(holder.unlockImage, UnlockImageSlugMap.get(unlock.getSlug()));
        holder.unlockTitle.setText(WeaponStringSlugMap.get(unlock.getSlug()));
        displayInformationForCriteria(holder, criteria);

        return view;
    }

    private KitUnlockHolder getKitUnlockHolder(View view) {
        KitUnlockHolder holder = new KitUnlockHolder();
        holder.unlockImage = (ImageView) view.findViewById(R.id.img_unlock);
        holder.unlockTitle = (TextView) view.findViewById(R.id.unlock_title);
        holder.unlockCompletion = (ProgressBar) view.findViewById(R.id.unlock_completion);
        holder.unlockStatusIcon = (ImageView) view.findViewById(R.id.unlock_status_icon);
        return holder;
    }

    @Override
    protected List<KitItemUnlockContainer> filterItems(CharSequence constraint) {
        List<KitItemUnlockContainer> filteredItems = new ArrayList<KitItemUnlockContainer>();

        int kitId = Integer.parseInt(constraint.toString());
        for (KitItemUnlockContainer item : listWithAllItems) {
            if (item.getKitId() == kitId) {
                filteredItems.add(item);
             }
        }
        return filteredItems;
    }

    private static class KitUnlockHolder extends UnlockHolder {
        public ImageView unlockImage;
        public TextView unlockTitle;
    }
}

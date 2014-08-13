package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.maps.unlocks.VehicleUnlockImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleUnlockStringMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.ArrayList;
import java.util.List;

public class VehicleUnlockAdapter extends BaseUnlockAdapter<VehicleUnlock> {
    public VehicleUnlockAdapter(final Context context) {
        super(context);
    }

    @Override
    protected List<VehicleUnlock> filterItems(final CharSequence constraint) {
        List<VehicleUnlock> filteredItems = new ArrayList<VehicleUnlock>();

        for (VehicleUnlock item : listWithAllItems) {
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
        final VehicleUnlock unlock = getItem(position);
        final UnlockCriteria criteria = unlock.getCriteria();
        VehicleUnlockHolder holder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_item_unlocks, parent, false);
            holder = new VehicleUnlockHolder();
            holder.unlockImage = (ImageView) view.findViewById(R.id.img_unlock);
            holder.unlockTitle = (TextView) view.findViewById(R.id.unlock_title);
            holder.unlockCompletion = (ProgressBar) view.findViewById(R.id.unlock_completion);
            holder.unlockStatusIcon = (ImageView) view.findViewById(R.id.unlock_status_icon);
            view.setTag(holder);
        } else {
            holder = (VehicleUnlockHolder) view.getTag();
        }

        setImage(holder.unlockImage, VehicleUnlockImageMap.get(unlock.getName()));
        holder.unlockTitle.setText(VehicleUnlockStringMap.get(unlock.getName()));
        displayInformationForCriteria(holder, criteria);

        return view;
    }

    private static class VehicleUnlockHolder extends UnlockHolder {
        public ImageView unlockImage;
        public TextView unlockTitle;
    }
}

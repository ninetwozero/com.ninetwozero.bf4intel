package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.maps.unlocks.VehicleUnlockImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleUnlockStringMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.List;

public class VehicleUnlockAdapter extends BaseUnlockAdapter<VehicleUnlock> {
    public VehicleUnlockAdapter(final Context context) {
        super(context);
    }

    @Override
    protected List<VehicleUnlock> filterItems(final CharSequence constraint) {
        return null; // TODO: Will be implemented correctly in new PR
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final VehicleUnlock unlock = getItem(position);
        final UnlockCriteria criteria = unlock.getCriteria();

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_unlocks, parent, false);
        }

        setImage(convertView, R.id.img_unlock, VehicleUnlockImageMap.get(unlock.getName()));
        setText(convertView, R.id.unlock_title, VehicleUnlockStringMap.get(unlock.getName()));
        displayInformationForCriteria(convertView, criteria);

        return convertView;
    }
}

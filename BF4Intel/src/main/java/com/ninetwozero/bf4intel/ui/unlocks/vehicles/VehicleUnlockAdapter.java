package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.maps.unlocks.VehicleUnlockImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleUnlockStringMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehiclesGroupStringMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.List;

public class VehicleUnlockAdapter extends BaseUnlockAdapter<VehicleUnlock> {
    public VehicleUnlockAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final VehicleUnlock unlock = getItem(position);
        final int completion = unlock.getCriteria().getCompletion();

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_unlocks, parent, false);
        }

        setImage(convertView, R.id.img_unlock, VehicleUnlockImageMap.get(unlock.getName()));
        setText(convertView, R.id.unlock_title, VehicleUnlockStringMap.get(unlock.getName()));
        setProgressText(convertView, R.id.unlock_subtitle, unlock.getCriteria());
        setProgress(convertView, R.id.unlock_completion, completion, 100);
        setAlpha(
            convertView,
            R.id.wrap_content_area,
            unlock.getCriteria().isCompleted() ? OPACITY_FADED : OPACITY_NORMAL
        );

        return convertView;
    }

    @Override
    protected int getCategoryString(final String key) {
        return VehiclesGroupStringMap.get(key);
    }
}

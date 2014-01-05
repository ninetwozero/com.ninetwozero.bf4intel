package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleUnlockStringMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehiclesGroupStringMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.List;
import java.util.Map;

public class VehicleUnlockAdapter extends BaseUnlockAdapter<VehicleUnlock> {
    public VehicleUnlockAdapter(final Map<String, List<VehicleUnlock>> itemMap, final Context context) {
        super(itemMap, context);
    }

    @Override
    public View getChildView(final int group, final int child, final boolean isLastChild, View convertView, final ViewGroup viewGroup) {
        final VehicleUnlock unlock = getChild(group, child);
        final int completion = unlock.getCriteria().getCompletion();

        while (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_unlocks, viewGroup, false);
        }

        setText(convertView, R.id.title, VehicleUnlockStringMap.get(unlock.getName()));
        setText(convertView, R.id.subtitle, resolveCriteriaLabel(unlock.getCriteria()));
        setProgress(convertView, R.id.progress, completion, 100);

        convertView.setAlpha(unlock.getCriteria().isCompleted() ? OPACITY_FADED : OPACITY_NORMAL);
        return convertView;
    }

    @Override
    protected int getCategoryString(final String key) {
        return VehiclesGroupStringMap.get(key);
    }
}

package com.ninetwozero.bf4intel.ui.unlocks.weapons;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.ScoreCriteria;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlock;
import com.ninetwozero.bf4intel.resources.maps.UnlockCriteriaStringMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.unlocks.VehicleUnlockImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.unlocks.VehicleUnlockStringMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.List;
import java.util.Map;

public class WeaponUnlockAdapter extends BaseUnlockAdapter<WeaponUnlock> {
    public WeaponUnlockAdapter(final Map<String, List<WeaponUnlock>> itemMap, final Context context) {
        super(itemMap, context);
    }

    @Override
    public View getChildView(final int group, final int child, final boolean isLastChild, View convertView, final ViewGroup viewGroup) {
        final WeaponUnlock unlock = getChild(group, child);
        while (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_unlocks, viewGroup, false);
        }
        return convertView;
    }

    @Override
    protected String resolveCriteriaLabel(final ScoreCriteria criteria) {
        final int resource = UnlockCriteriaStringMap.get(criteria.getLabel());
        return String.format(
            context.getString(resource),
            String.format("%,d", criteria.getCurrentValue()),
            String.format("%,d", criteria.getTargetValue())
        );
    }
}

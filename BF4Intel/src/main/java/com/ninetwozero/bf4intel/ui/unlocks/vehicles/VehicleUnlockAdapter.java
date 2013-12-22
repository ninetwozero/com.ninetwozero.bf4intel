package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleUnlockStringMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleUnlockAdapter extends BaseUnlockAdapter<VehicleUnlock> {
    // TODO: REmove this in favor for VehicleGroupStringMap when possible
    private static final Map<String, Integer> categoryStringMap = new HashMap<String, Integer>() {{
        put("Vehicle Air Jet Attack", R.string.category_attack_jets);
        put("Vehicle Anti Air", R.string.category_aa_vehicles);
        put("Vehicle Air Helicopter Scout", R.string.category_scout_helicopters);
        put("Vehicle Fast Attack Craft", R.string.category_fast_attack_crafts);
        put("Vehicle Air Jet Stealth", R.string.category_stealth_jets);
        put("Vehicle Infantry Fighting Vehicle", R.string.category_ifvs);
        put("Vehicle Main Battle Tanks", R.string.category_battle_tanks);
        put("Vehicle Air Helicopter Attack", R.string.category_attack_helicopters);
    }};

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
        return categoryStringMap.containsKey(key) ? categoryStringMap.get(key) : R.string.na;
    }
}

package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.maps.UnlockCriteriaStringMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.unlocks.VehicleUnlockStringMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleUnlockAdapter extends BaseUnlockAdapter<VehicleUnlock> {
    // TODO: REmove this in favor for VehicleGroupStringMap when possible
    private static final Map<String, String> categoryStringMap = new HashMap<String, String>() {{
        put("Vehicle Air Jet Attack", "Attack Jets");
        put("Vehicle Anti Air", "Anti Air Vehicles");
        put("Vehicle Air Helicopter Scout", "Scout Helicopters");
        put("Vehicle Fast Attack Craft", "Fast Attack Crafts");
        put("Vehicle Air Jet Stealth", "Stealth Jets");
        put("Vehicle Infantry Fighting Vehicle", "Infantry Fighting Vehicles");
        put("Vehicle Main Battle Tanks", "Battle Tanks");
        put("Vehicle Air Helicopter Attack", "Attack Helicopters");
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

        if (unlock.getCriteria().isCompleted()) {
            setText(convertView, R.id.completion, R.string.done);
            convertView.setAlpha(0.5f);
        } else {
            setText(convertView, R.id.completion, completion + "%");
            convertView.setAlpha(1.0f);
        }
        return convertView;
    }

    @Override
    protected String resolveCriteriaLabel(final UnlockCriteria criteria) {
        final int resource = UnlockCriteriaStringMap.get(criteria.getLabel());
        final int currentValue = criteria.getCurrentValue();
        final int targetValue = criteria.getTargetValue();
        return String.format(
            context.getString(resource),
            String.format("%,d", currentValue < targetValue ? currentValue : targetValue),
            String.format("%,d", targetValue)
        );
    }

    @Override
    protected String getCategoryString(final String key) {
        return categoryStringMap.containsKey(key) ? categoryStringMap.get(key) : key;
    }
}

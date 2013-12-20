package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.ScoreCriteria;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.maps.UnlockCriteriaStringMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.unlocks.VehicleUnlockImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.unlocks.VehicleUnlockStringMap;
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

        setImage(convertView, R.id.icon, VehicleUnlockImageMap.get(unlock.getName()));
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
    protected String resolveCriteriaLabel(final ScoreCriteria criteria) {
        final int resource = UnlockCriteriaStringMap.get(criteria.getLabel());
        return String.format(
            context.getString(resource),
            String.format("%,d", criteria.getCurrentValue()),
            String.format("%,d", criteria.getTargetValue())
        );
    }
}

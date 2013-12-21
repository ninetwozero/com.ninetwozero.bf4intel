package com.ninetwozero.bf4intel.ui.unlocks.kits;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.KitUnlock;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.resources.maps.UnlockCriteriaStringMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.List;
import java.util.Map;

public class KitUnlockAdapter extends BaseUnlockAdapter<KitUnlock> {
    public KitUnlockAdapter(final Map<String, List<KitUnlock>> itemMap, final Context context) {
        super(itemMap, context);
    }

    @Override
    public View getChildView(final int group, final int child, final boolean isLastChild, View convertView, final ViewGroup viewGroup) {
        final KitUnlock unlock = getChild(group, child);
        while (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_unlocks, viewGroup, false);
        }
        return convertView;
    }

    @Override
    protected String resolveCriteriaLabel(final UnlockCriteria criteria) {
        final int resource = UnlockCriteriaStringMap.get(criteria.getLabel());
        return String.format(
            context.getString(resource),
            String.format("%,d", criteria.getCurrentValue()),
            String.format("%,d", criteria.getTargetValue())
        );
    }

    @Override
    protected String getCategoryString(final String key) {
        //return stringMap.containsKey(key) ? stringMap.get(key) : key;
        return key;
    }
}

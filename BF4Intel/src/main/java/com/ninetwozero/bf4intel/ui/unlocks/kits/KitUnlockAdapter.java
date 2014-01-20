package com.ninetwozero.bf4intel.ui.unlocks.kits;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlock;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringSlugMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitUnlockAdapter extends BaseUnlockAdapter<KitItemUnlockContainer> {
    private final Map<String, Integer> categoryStringMap = new HashMap<String, Integer>() {{
        put("1", R.string.class_assault);
        put("2", R.string.class_engineer);
        put("8", R.string.class_recon);
        put("32", R.string.class_support);
    }};

    public KitUnlockAdapter(final Context context, final Map<String, List<KitItemUnlockContainer>> itemMap) {
        super(context, itemMap);
    }

    @Override
    public View getChildView(final int group, final int child, final boolean isLastChild, View convertView, final ViewGroup viewGroup) {
        final WeaponUnlock unlock = getChild(group, child).getUnlock();
        final int completion = unlock.getCriteria().getCompletion();
        while (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_unlocks, viewGroup, false);
        }

        setText(convertView, R.id.title, WeaponStringSlugMap.get(unlock.getSlug()));
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

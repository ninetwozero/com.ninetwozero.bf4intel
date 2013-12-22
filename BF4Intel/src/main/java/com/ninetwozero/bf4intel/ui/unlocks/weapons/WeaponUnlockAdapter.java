package com.ninetwozero.bf4intel.ui.unlocks.weapons;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlock;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringSlugMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponUnlockAdapter extends BaseUnlockAdapter<WeaponUnlockContainer> {
    private static final Map<String, Integer> categoryStringMap = new HashMap<String, Integer>() {{
        put("wL", R.string.category_lmgs);
        put("waPDW", R.string.category_pdws);
        put("wG", R.string.category_handgrenades);
        put("wH", R.string.category_handguns);
        put("wSR", R.string.category_snipers);
        put("wD", R.string.category_dmrs);
        put("wC", R.string.category_carbines);
        put("waS", R.string.category_shotguns);
        put("wA", R.string.category_assault_rifles);
        put("wSPk", R.string.category_knives);
    }};

    public WeaponUnlockAdapter(final Map<String, List<WeaponUnlockContainer>> itemMap, final Context context) {
        super(itemMap, context);
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
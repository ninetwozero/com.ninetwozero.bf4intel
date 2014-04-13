package com.ninetwozero.bf4intel.ui.unlocks.weapons;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlock;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.resources.maps.unlocks.UnlockImageSlugMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringSlugMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.HashMap;
import java.util.Map;

public class WeaponUnlockAdapter extends BaseUnlockAdapter<WeaponUnlockContainer> {
    private static final Map<String, Integer> categoryStringMap = new HashMap<String, Integer>() {{
        put("wA", R.string.category_assault_rifles);
        put("wC", R.string.category_carbines);
        put("waS", R.string.category_shotguns);
        put("wL", R.string.category_lmgs);
        put("waPDW", R.string.category_pdws);
        put("wD", R.string.category_dmrs);
        put("wSR", R.string.category_snipers);
        put("wH", R.string.category_handguns);
        put("wG", R.string.category_handgrenades);
        put("wSPk", R.string.category_knives);
        put("wX", R.string.category_gadgets);
    }};

    public WeaponUnlockAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final WeaponUnlock unlock = getItem(position).getUnlock();
        final UnlockCriteria criteria = unlock.getCriteria();

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_unlocks, parent, false);
        }

        setImage(convertView, R.id.img_unlock, UnlockImageSlugMap.get(unlock.getSlug()));
        setText(convertView, R.id.unlock_title, WeaponStringSlugMap.get(unlock.getSlug()));
        displayInformationForCriteria(convertView, criteria);

        return convertView;
    }

    @Override
    protected int getCategoryString(final String key) {
        return categoryStringMap.containsKey(key) ? categoryStringMap.get(key) : R.string.na;
    }
}
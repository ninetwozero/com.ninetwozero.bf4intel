package com.ninetwozero.bf4intel.ui.unlocks.kits;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlock;
import com.ninetwozero.bf4intel.resources.maps.unlocks.UnlockImageSlugMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringSlugMap;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockAdapter;

import java.util.HashMap;
import java.util.Map;

public class KitUnlockAdapter extends BaseUnlockAdapter<KitItemUnlockContainer> {
    private final Map<String, Integer> categoryStringMap = new HashMap<String, Integer>() {{
        put("1", R.string.class_assault);
        put("2", R.string.class_engineer);
        put("8", R.string.class_recon);
        put("32", R.string.class_support);
    }};

    public KitUnlockAdapter(final Context context) {
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

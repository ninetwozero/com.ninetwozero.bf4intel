package com.ninetwozero.bf4intel.ui.awards;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RibbonImagesMap {
    private static final Map<String, Integer> ribbonsMap = new HashMap<String, Integer>() {
        {
            put("r01", R.drawable.awards_ribbon01);
            put("r02", R.drawable.awards_ribbon02);
            put("r03", R.drawable.awards_ribbon03);
            put("r04", R.drawable.awards_ribbon04);
            put("r05", R.drawable.awards_ribbon05);
            put("r06", R.drawable.awards_ribbon06);
            put("r07", R.drawable.awards_ribbon07);
            put("r08", R.drawable.awards_ribbon08);
            put("r09", R.drawable.awards_ribbon09);
            put("r10", R.drawable.awards_ribbon10);
            put("r11", R.drawable.awards_ribbon11);
            put("r12", R.drawable.awards_ribbon12);
            put("r13", R.drawable.awards_ribbon13);
            put("r14", R.drawable.awards_ribbon14);
            put("r15", R.drawable.awards_ribbon15);
            put("r16", R.drawable.awards_ribbon16);
            put("r17", R.drawable.awards_ribbon17);
            put("r18", R.drawable.awards_ribbon18);
            put("r19", R.drawable.awards_ribbon19);
            put("r20", R.drawable.awards_ribbon20);
            put("r21", R.drawable.awards_ribbon21);
            put("r22", R.drawable.awards_ribbon22);
            put("r23", R.drawable.awards_ribbon23);
            put("r24", R.drawable.awards_ribbon24);
            put("r25", R.drawable.awards_ribbon25);
            put("r26", R.drawable.awards_ribbon26);
            put("r27", R.drawable.awards_ribbon27);
            put("r28", R.drawable.awards_ribbon28);
            put("r29", R.drawable.awards_ribbon29);
            put("r30", R.drawable.awards_ribbon30);
            put("r31", R.drawable.awards_ribbon31);
            put("r32", R.drawable.awards_ribbon32);
            put("r33", R.drawable.awards_ribbon33);
            put("r34", R.drawable.awards_ribbon34);
            put("r35", R.drawable.awards_ribbon35);
            put("r36", R.drawable.awards_ribbon36);
            put("r37", R.drawable.awards_ribbon37);
            put("r38", R.drawable.awards_ribbon38);
            put("r39", R.drawable.awards_ribbon39);
            put("r40", R.drawable.awards_ribbon40);
            put("r41", R.drawable.awards_ribbon41);
            put("r42", R.drawable.awards_ribbon42);
            put("r43", R.drawable.awards_ribbon43);
            put("r44", R.drawable.awards_ribbon44);
            put("r45", R.drawable.awards_ribbon45);
            put("xp1ras", R.drawable.awards_ribbon_xp1as);
            put("xp1rbd", R.drawable.awards_ribbon_xp1bd);
        }
    };

    public static int get(final String key) {
        return ribbonsMap.containsKey(key.toLowerCase(Locale.getDefault())) ? ribbonsMap.get(key.toLowerCase()) : R.drawable.acc_none;
    }
}

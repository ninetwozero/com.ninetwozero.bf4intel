package com.ninetwozero.bf4intel.ui.awards;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class RibbonImagesMap {
    private static final Map<String, Integer> ribbonsMap = new HashMap<String, Integer>() {
        {
            put("r01", R.drawable.awards_ribbon_r01);
            put("r02", R.drawable.awards_ribbon_r02);
            put("r03", R.drawable.awards_ribbon_r03);
            put("r04", R.drawable.awards_ribbon_r04);
            put("r05", R.drawable.awards_ribbon_r05);
            put("r06", R.drawable.awards_ribbon_r06);
            put("r07", R.drawable.awards_ribbon_r07);
            put("r08", R.drawable.awards_ribbon_r08);
            put("r09", R.drawable.awards_ribbon_r09);
            put("r10", R.drawable.awards_ribbon_r10);
            put("r11", R.drawable.awards_ribbon_r11);
            put("r12", R.drawable.awards_ribbon_r12);
            put("r13", R.drawable.awards_ribbon_r13);
            put("r14", R.drawable.awards_ribbon_r14);
            put("r15", R.drawable.awards_ribbon_r15);
            put("r16", R.drawable.awards_ribbon_r16);
            put("r17", R.drawable.awards_ribbon_r17);
            put("r18", R.drawable.awards_ribbon_r18);
            put("r19", R.drawable.awards_ribbon_r19);
            put("r20", R.drawable.awards_ribbon_r20);
            put("r21", R.drawable.awards_ribbon_r21);
            put("r22", R.drawable.awards_ribbon_r22);
            put("r23", R.drawable.awards_ribbon_r23);
            put("r24", R.drawable.awards_ribbon_r24);
            put("r25", R.drawable.awards_ribbon_r25);
            put("r26", R.drawable.awards_ribbon_r26);
            put("r27", R.drawable.awards_ribbon_r27);
            put("r28", R.drawable.awards_ribbon_r28);
            put("r29", R.drawable.awards_ribbon_r29);
            put("r30", R.drawable.awards_ribbon_r30);
            put("r31", R.drawable.awards_ribbon_r31);
            put("r32", R.drawable.awards_ribbon_r32);
            put("r33", R.drawable.awards_ribbon_r33);
            put("r34", R.drawable.awards_ribbon_r34);
            put("r35", R.drawable.awards_ribbon_r35);
            put("r36", R.drawable.awards_ribbon_r36);
            put("r37", R.drawable.awards_ribbon_r37);
            put("r38", R.drawable.awards_ribbon_r38);
            put("r39", R.drawable.awards_ribbon_r39);
            put("r40", R.drawable.awards_ribbon_r40);
            put("r41", R.drawable.awards_ribbon_r41);
            put("r42", R.drawable.awards_ribbon_r42);
            put("r43", R.drawable.awards_ribbon_r43);
            put("r44", R.drawable.awards_ribbon_r44);
            put("r45", R.drawable.awards_ribbon_r45);
            put("xp1rAS", R.drawable.awards_ribbon_xp1ras);
            put("xp1rBD", R.drawable.awards_ribbon_xp1rbd);
            put("xp0rCS", R.drawable.awards_ribbon_xp0rcs);
            put("xp0rFD", R.drawable.awards_ribbon_xp0rfd);
            put("xp2rCA", R.drawable.awards_ribbon_xp2rca);
            put("xp3rCW", R.drawable.awards_ribbon_xp3rcw);
            put("xp3rLB", R.drawable.awards_ribbon_xp3rlb);
            put("xp3rLM", R.drawable.awards_ribbon_xp3rlm);
        }
    };

    public static int get(final String key) {
        return ribbonsMap.containsKey(key) ? ribbonsMap.get(key) : R.drawable.acc_none;
    }
}

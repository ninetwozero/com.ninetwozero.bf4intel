package com.ninetwozero.bf4intel.resources.maps.awards;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class MedalImagesMap {
    private static final Map<String, Integer> medalsMap = new HashMap<String, Integer>() {
        {
            put("m01", R.drawable.awards_m01);
            put("m02", R.drawable.awards_m02);
            put("m03", R.drawable.awards_m03);
            put("m04", R.drawable.awards_m04);
            put("m05", R.drawable.awards_m05);
            put("m06", R.drawable.awards_m06);
            put("m07", R.drawable.awards_m07);
            put("m08", R.drawable.awards_m08);
            put("m09", R.drawable.awards_m09);
            put("m10", R.drawable.awards_m10);
            put("m11", R.drawable.awards_m11);
            put("m12", R.drawable.awards_m12);
            put("m13", R.drawable.awards_m13);
            put("m14", R.drawable.awards_m14);
            put("m15", R.drawable.awards_m15);
            put("m16", R.drawable.awards_m16);
            put("m17", R.drawable.awards_m17);
            put("m18", R.drawable.awards_m18);
            put("m19", R.drawable.awards_m19);
            put("m20", R.drawable.awards_m20);
            put("m21", R.drawable.awards_m21);
            put("m22", R.drawable.awards_m22);
            put("m23", R.drawable.awards_m23);
            put("m24", R.drawable.awards_m24);
            put("m25", R.drawable.awards_m25);
            put("m26", R.drawable.awards_m26);
            put("m27", R.drawable.awards_m27);
            put("m28", R.drawable.awards_m28);
            put("m29", R.drawable.awards_m29);
            put("m30", R.drawable.awards_m30);
            put("m31", R.drawable.awards_m31);
            put("m32", R.drawable.awards_m32);
            put("m33", R.drawable.awards_m33);
            put("m34", R.drawable.awards_m34);
            put("m35", R.drawable.awards_m35);
            put("m36", R.drawable.awards_m36);
            put("m37", R.drawable.awards_m37);
            put("m38", R.drawable.awards_m38);
            put("m39", R.drawable.awards_m39);
            put("m40", R.drawable.awards_m40);
            put("m41", R.drawable.awards_m41);
            put("m42", R.drawable.awards_m42);
            put("m43", R.drawable.awards_m43);
            put("m44", R.drawable.awards_m44);
            put("m45", R.drawable.awards_m45);
            put("xp1mAS", R.drawable.awards_mxp1mas);
            put("xp1mBD", R.drawable.awards_mxp1mbd);
            put("xp0mCTF", R.drawable.awards_mxp0mctf);
            put("xp0mCTFS", R.drawable.awards_mxp0mctfs);
            put("xp2mCA", R.drawable.awards_mxp2mca);
            put("xp3mCW", R.drawable.awards_mxp3mcw);
            put("xp3mLB", R.drawable.awards_mxp3mlb);
            put("xp3mLM", R.drawable.awards_mxp3mlm);
            put("mGMW", R.drawable.awards_mgmw);
        }
    };

    public static int get(final String key) {
        return medalsMap.containsKey(key) ? medalsMap.get(key) : R.drawable.acc_none;
    }
}

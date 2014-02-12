package com.ninetwozero.bf4intel.resources.maps.profile;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class SoldierImageMap {
    private static Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("default", R.drawable.soldier_image_default);
            put("ch-assault", R.drawable.soldier_image_default);
            put("ch-assault-urbanairborne", R.drawable.soldier_image_ch_assault_urbanairborne);
            put("ch-engineer", R.drawable.soldier_image_ch_engineer);
            put("ch-engineer-urbanairborne", R.drawable.soldier_image_ch_engineer_urbanairborne);
            put("ch-recon", R.drawable.soldier_image_ch_recon);
            put("ch-recon-urbanairborne", R.drawable.soldier_image_ch_recon_urbanairborne);
            put("ch-support", R.drawable.soldier_image_ch_support);
            put("ch-support-urbanairborne", R.drawable.soldier_image_ch_support_urbanairborne);
            put("ru-assault", R.drawable.soldier_image_ru_assault);
            put("ru-assault-berezka", R.drawable.soldier_image_ru_assault_berezka);
            put("ru-assault-partizan", R.drawable.soldier_image_ru_assault_partizan);
            put("ru-engineer", R.drawable.soldier_image_ru_engineer);
            put("ru-engineer-berezka", R.drawable.soldier_image_ru_engineer_berezka);
            put("ru-engineer-partizan", R.drawable.soldier_image_ru_engineer_partizan);
            put("ru-recon", R.drawable.soldier_image_ru_recon);
            put("ru-recon-berezka", R.drawable.soldier_image_ru_recon_berezka);
            put("ru-recon-partizan", R.drawable.soldier_image_ru_recon_partizan);
            put("ru-support", R.drawable.soldier_image_ru_support);
            put("ru-support-berezka", R.drawable.soldier_image_ru_support_berezka);
            put("ru-support-partizan", R.drawable.soldier_image_ru_support_partizan);
            put("us-assault", R.drawable.soldier_image_us_assault);
            put("us-assault-chocchip", R.drawable.soldier_image_us_assault_chocchip);
            put("us-assault-ucp", R.drawable.soldier_image_us_assault_ucp);
            put("us-engineer", R.drawable.soldier_image_us_engineer);
            put("us-engineer-chocchip", R.drawable.soldier_image_us_engineer_chocchip);
            put("us-engineer-ucp", R.drawable.soldier_image_us_engineer_ucp);
            put("us-recon", R.drawable.soldier_image_us_recon);
            put("us-recon-chocchip", R.drawable.soldier_image_us_recon_chocchip);
            put("us-recon-ucp", R.drawable.soldier_image_us_recon_ucp);
            put("us-support", R.drawable.soldier_image_us_support);
            put("us-support-chocchip", R.drawable.soldier_image_us_support_chocchip);
            put("us-support-ucp", R.drawable.soldier_image_us_support_ucp);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key) ? map.get(key) : R.drawable.soldier_image_default;
    }

}

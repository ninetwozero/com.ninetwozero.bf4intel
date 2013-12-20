package com.ninetwozero.bf4intel.resources.maps.vehicles.unlocks;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class VehicleUnlockImageMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("ID_P_VUNAME_AAAA", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AAARADAR", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AAARMOR", R.drawable.vuname_reactive_armor);
            put("ID_P_VUNAME_AABRASSGRAPHITE", R.drawable.vuname_gunner_incendiary);
            put("ID_P_VUNAME_AAENVG", R.drawable.vuname_gunner_irv);
            put("ID_P_VUNAME_AAFIREEX", R.drawable.vuname_fire_extinguisher);
            put("ID_P_VUNAME_AAHS", R.drawable.vuname_heatseekers);
            put("ID_P_VUNAME_AAMAINCANNON", R.drawable.vuname_30mm_cannon);
            put("ID_P_VUNAME_AAPREVENT", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AAPROX", R.drawable.vuname_proximity_scan);
            put("ID_P_VUNAME_AASMOKE", R.drawable.vuname_smokescreen_big);
            put("ID_P_VUNAME_AASTEALTH", R.drawable.vuname_stealth_coating);
            put("ID_P_VUNAME_AAVAIRBURST", R.drawable.vuname_burst_cannon);
            put("ID_P_VUNAME_AAVAR", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AAVHEATSEEKER", R.drawable.vuname_heatseekers);
            put("ID_P_VUNAME_AAVIRNV", R.drawable.vuname_irnv_optics);
            put("ID_P_VUNAME_AAVIRONFIST", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AAWPNEFF", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AAZOOM", R.drawable.vuname_gunner_zoom);
            put("ID_P_VUNAME_AHAA", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AHARADAR", R.drawable.vuname_active_radar);
            put("ID_P_VUNAME_AHECM", R.drawable.vuname_ecm_jammer);
            put("ID_P_VUNAME_AHENVG", R.drawable.vuname_irnv_optics);
            put("ID_P_VUNAME_AHFIREEX", R.drawable.vuname_fire_extinguisher);
            put("ID_P_VUNAME_AHFLARE", R.drawable.vuname_shflare);
            put("ID_P_VUNAME_AHGHELL", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AHGUIDE", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AHHYDRA", R.drawable.vuname_hydra_rockets);
            put("ID_P_VUNAME_AHIRNV", R.drawable.acc_irnv_scope);
            put("ID_P_VUNAME_AHPROXGUN", R.drawable.vuname_proximity_scan);
            put("ID_P_VUNAME_AHSTEALTH", R.drawable.vuname_stealth_coating);
            put("ID_P_VUNAME_AHTOW", R.drawable.vuname_tow_missile);
            put("ID_P_VUNAME_AHTVG", R.drawable.vuname_tvguided_missile);
            put("ID_P_VUNAME_AHWPNGUN", R.drawable.vuname_tbd);
            put("ID_P_VUNAME_AHZOOM", R.drawable.vuname_gunner_zoom);
            put("ID_P_VUNAME_AHZUNI", R.drawable.vuname_zuni_rockets);
            /* TODO: *sigh* We need to figure out which of these cryptic names are which images
            put("ID_P_VUNAME_ATKHELIAUTOPILOT", R.drawable.vuname_atkheliautopilot);
            put("ID_P_VUNAME_ATKJETAUTOPILOT", R.drawable.vuname_atkjetautopilot);
            put("ID_P_VUNAME_ATKJETCECM", R.drawable.vuname_atkjetcecm);
            put("ID_P_VUNAME_ATKJETFIREEX", R.drawable.vuname_atkjetfireex);
            put("ID_P_VUNAME_ATKJETFLARE", R.drawable.vuname_atkjetflare);
            put("ID_P_VUNAME_ATKJETHEATSEEKING", R.drawable.vuname_atkjetheatseeking);
            put("ID_P_VUNAME_ATKJETJDAM", R.drawable.vuname_atkjetjdam);
            put("ID_P_VUNAME_ATKJETMAINCANNON", R.drawable.vuname_atkjetmaincannon);
            put("ID_P_VUNAME_ATKJETMAVER", R.drawable.vuname_atkjetmaver);
            put("ID_P_VUNAME_ATKJETPROX", R.drawable.vuname_atkjetprox);
            put("ID_P_VUNAME_ATKJETROCKET", R.drawable.vuname_atkjetrocket);
            put("ID_P_VUNAME_ATKJETSTEALTH", R.drawable.vuname_atkjetstealth);
            put("ID_P_VUNAME_ATKJETTVGUIDED", R.drawable.vuname_atkjettvguided);
            put("ID_P_VUNAME_ATKJETWPNEFF", R.drawable.vuname_atkjetwpneff);
            put("ID_P_VUNAME_FACAA", R.drawable.vuname_facaa);
            put("ID_P_VUNAME_FACAIRBURST", R.drawable.vuname_facairburst);
            put("ID_P_VUNAME_FACARADAR", R.drawable.vuname_facaradar);
            put("ID_P_VUNAME_FACATGM", R.drawable.vuname_facatgm);
            put("ID_P_VUNAME_FACAUTOLOADER", R.drawable.vuname_facautoloader);
            put("ID_P_VUNAME_FACBRASSGRAPHITE", R.drawable.vuname_facbrassgraphite);
            put("ID_P_VUNAME_FACBURST", R.drawable.vuname_facburst);
            put("ID_P_VUNAME_FACCITV", R.drawable.vuname_faccitv);
            put("ID_P_VUNAME_FACENVG", R.drawable.vuname_facenvg);
            put("ID_P_VUNAME_FACFIREEX", R.drawable.vuname_facfireex);
            put("ID_P_VUNAME_FACHEAT", R.drawable.vuname_facheat);
            put("ID_P_VUNAME_FACIRNV", R.drawable.vuname_facirnv);
            put("ID_P_VUNAME_FACIRONFIST", R.drawable.vuname_facironfist);
            put("ID_P_VUNAME_FACLGMISSILE", R.drawable.vuname_faclgmissile);
            put("ID_P_VUNAME_FACMAINCANNON", R.drawable.vuname_facmaincannon);
            put("ID_P_VUNAME_FACMAINTENANCE", R.drawable.vuname_facmaintenance);
            put("ID_P_VUNAME_FACPROXIMITY", R.drawable.vuname_facproximity);
            put("ID_P_VUNAME_FACSMOKE", R.drawable.vuname_facsmoke);
            put("ID_P_VUNAME_FACTHERMAL", R.drawable.vuname_facthermal);
            put("ID_P_VUNAME_FACTVGUIDED", R.drawable.vuname_factvguided);
            put("ID_P_VUNAME_GUNNERAMMOFEED", R.drawable.vuname_gunnerammofeed);
            put("ID_P_VUNAME_IFMAINCANNON", R.drawable.vuname_ifmaincannon);
            put("ID_P_VUNAME_IFVAIRBURST", R.drawable.vuname_ifvairburst);
            put("ID_P_VUNAME_IFVAPFSDS", R.drawable.vuname_ifvapfsds);
            put("ID_P_VUNAME_IFVARMOR", R.drawable.vuname_ifvarmor);
            put("ID_P_VUNAME_IFVAT", R.drawable.vuname_ifvat);
            put("ID_P_VUNAME_IFVBRASSGRAPHITE", R.drawable.vuname_ifvbrassgraphite);
            put("ID_P_VUNAME_IFVCOAX", R.drawable.vuname_ifvcoax);
            put("ID_P_VUNAME_IFVENVG", R.drawable.vuname_ifvenvg);
            put("ID_P_VUNAME_IFVFIREEX", R.drawable.vuname_ifvfireex);
            put("ID_P_VUNAME_IFVFLECHETTE", R.drawable.vuname_ifvflechette);
            put("ID_P_VUNAME_IFVGUNNERFLIR", R.drawable.vuname_ifvgunnerflir);
            put("ID_P_VUNAME_IFVGUNNERPROX", R.drawable.vuname_ifvgunnerprox);
            put("ID_P_VUNAME_IFVGUNNERSOFLAM", R.drawable.vuname_ifvgunnersoflam);
            put("ID_P_VUNAME_IFVGUNNERWPSMOKE", R.drawable.vuname_ifvgunnerwpsmoke);
            put("ID_P_VUNAME_IFVGUNNERZOOM", R.drawable.vuname_ifvgunnerzoom);
            put("ID_P_VUNAME_IFVIRNV", R.drawable.vuname_ifvirnv);
            put("ID_P_VUNAME_IFVIRONFIST", R.drawable.vuname_ifvironfist);
            put("ID_P_VUNAME_IFVPREVENT", R.drawable.vuname_ifvprevent);
            put("ID_P_VUNAME_IFVSMOKE", R.drawable.vuname_ifvsmoke);
            put("ID_P_VUNAME_IFVSTEALTH", R.drawable.vuname_ifvstealth);
            put("ID_P_VUNAME_IFVWPNEFF", R.drawable.vuname_ifvwpneff);
            put("ID_P_VUNAME_IFVZOOM", R.drawable.vuname_ifvzoom);
            put("ID_P_VUNAME_JET25MMCANNON", R.drawable.vuname_jet25mmcannon);
            put("ID_P_VUNAME_JET30MMCANNON", R.drawable.vuname_jet30mmcannon);
            put("ID_P_VUNAME_JETAA", R.drawable.vuname_jetaa);
            put("ID_P_VUNAME_JETAR", R.drawable.vuname_jetar);
            put("ID_P_VUNAME_JETAUTOPILOT", R.drawable.vuname_jetautopilot);
            put("ID_P_VUNAME_JETECM", R.drawable.vuname_jetecm);
            put("ID_P_VUNAME_JETFIREEX", R.drawable.vuname_jetfireex);
            put("ID_P_VUNAME_JETFLARE", R.drawable.vuname_jetflare);
            put("ID_P_VUNAME_JETGUIDEDHOMING", R.drawable.vuname_jetguidedhoming);
            put("ID_P_VUNAME_JETMAINCANNON", R.drawable.vuname_jetmaincannon);
            put("ID_P_VUNAME_JETMAVER", R.drawable.vuname_jetmaver);
            put("ID_P_VUNAME_JETPROX", R.drawable.vuname_jetprox);
            put("ID_P_VUNAME_JETSTEALTH", R.drawable.vuname_jetstealth);
            put("ID_P_VUNAME_JETWPNEFF", R.drawable.vuname_jetwpneff);
            put("ID_P_VUNAME_MBTAPFSDS", R.drawable.vuname_mbtapfsds);
            put("ID_P_VUNAME_MBTARMOR", R.drawable.vuname_mbtarmor);
            put("ID_P_VUNAME_MBTATGM", R.drawable.vuname_mbtatgm);
            put("ID_P_VUNAME_MBTBRASSGRAPHITE", R.drawable.vuname_mbtbrassgraphite);
            put("ID_P_VUNAME_MBTCANISTER", R.drawable.vuname_mbtcanister);
            put("ID_P_VUNAME_MBTCOAX", R.drawable.vuname_mbtcoax);
            put("ID_P_VUNAME_MBTENVG", R.drawable.vuname_mbtenvg);
            put("ID_P_VUNAME_MBTFIREEX", R.drawable.vuname_mbtfireex);
            put("ID_P_VUNAME_MBTGUNNERAMMOFEED", R.drawable.vuname_mbtgunnerammofeed);
            put("ID_P_VUNAME_MBTGUNNERFLIR", R.drawable.vuname_mbtgunnerflir);
            put("ID_P_VUNAME_MBTGUNNERIRNV", R.drawable.vuname_mbtgunnerirnv);
            put("ID_P_VUNAME_MBTGUNNERPROX", R.drawable.vuname_mbtgunnerprox);
            put("ID_P_VUNAME_MBTGUNNERSOFLAM", R.drawable.vuname_mbtgunnersoflam);
            put("ID_P_VUNAME_MBTGUNNERWPSMOKE", R.drawable.vuname_mbtgunnerwpsmoke);
            put("ID_P_VUNAME_MBTGUNNERZOOM", R.drawable.vuname_mbtgunnerzoom);
            put("ID_P_VUNAME_MBTHESHELL", R.drawable.vuname_mbtheshell);
            put("ID_P_VUNAME_MBTHMG", R.drawable.vuname_mbthmg);
            put("ID_P_VUNAME_MBTIRNV", R.drawable.vuname_mbtirnv);
            put("ID_P_VUNAME_MBTIRONFIST", R.drawable.vuname_mbtironfist);
            put("ID_P_VUNAME_MBTMAINCANNON", R.drawable.vuname_mbtmaincannon);
            put("ID_P_VUNAME_MBTPREVENT", R.drawable.vuname_mbtprevent);
            put("ID_P_VUNAME_MBTSMOKE", R.drawable.vuname_mbtsmoke);
            put("ID_P_VUNAME_MBTSTAFF", R.drawable.vuname_mbtstaff);
            put("ID_P_VUNAME_MBTSTEALTH", R.drawable.vuname_mbtstealth);
            put("ID_P_VUNAME_MBTWPNEFF", R.drawable.vuname_mbtwpneff);
            put("ID_P_VUNAME_MBTZOOM", R.drawable.vuname_mbtzoom);
            put("ID_P_VUNAME_NOSTANCE", R.drawable.vuname_nostance);
            put("ID_P_VUNAME_SCOUTAUTOPILOT", R.drawable.vuname_scoutautopilot);
            put("ID_P_VUNAME_SHAA", R.drawable.vuname_shaa);
            put("ID_P_VUNAME_SHAC", R.drawable.vuname_shac);
            put("ID_P_VUNAME_SHARADAR", R.drawable.vuname_sharadar);
            put("ID_P_VUNAME_SHECM", R.drawable.vuname_shecm);
            put("ID_P_VUNAME_SHFIREEX", R.drawable.vuname_shfireex);
            put("ID_P_VUNAME_SHFLARE", R.drawable.vuname_shflare);
            put("ID_P_VUNAME_SHHELL", R.drawable.vuname_shhell);
            put("ID_P_VUNAME_SHMAINWEAPON", R.drawable.vuname_shmainweapon);
            put("ID_P_VUNAME_SHPROX", R.drawable.vuname_shprox);
            put("ID_P_VUNAME_SHSTEALTH", R.drawable.vuname_shstealth);
            put("ID_P_VUNAME_SHWPNEFF", R.drawable.vuname_shwpneff);*/
        }
    };

    public static int get(final String key) {
        final String realKey = key.replace("WARSAW_", "");
        return map.containsKey(realKey) ? map.get(realKey) : R.drawable.acc_none;
    }
}

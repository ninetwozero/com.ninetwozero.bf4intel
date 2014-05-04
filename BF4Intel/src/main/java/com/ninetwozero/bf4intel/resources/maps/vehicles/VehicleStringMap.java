package com.ninetwozero.bf4intel.resources.maps.vehicles;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class VehicleStringMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("WARSAW_ID_P_VNAME_50CAL", R.string.vehicle_name_50cal);
            put("WARSAW_ID_P_VNAME_9K22", R.string.vehicle_name_9k22);
            put("WARSAW_ID_P_VNAME_A10", R.string.vehicle_name_a10);
            put("WARSAW_ID_P_VNAME_AAV", R.string.vehicle_name_aav);
            put("WARSAW_ID_P_VNAME_AC130", R.string.vehicle_name_ac130);
            put("WARSAW_ID_P_VNAME_AH1Z", R.string.vehicle_name_ah1z);
            put("WARSAW_ID_P_VNAME_AH6", R.string.vehicle_name_ah6);
            put("WARSAW_ID_P_VNAME_CB90", R.string.vehicle_name_cb90);
            put("WARSAW_ID_P_VNAME_CENTURION", R.string.vehicle_name_centurion);
            put("WARSAW_ID_P_VNAME_COUGAR", R.string.vehicle_name_cougar);
            put("WARSAW_ID_P_VNAME_DV15", R.string.vehicle_name_dv15);
            put("WARSAW_ID_P_VNAME_GROWLER", R.string.vehicle_name_growler);
            put("WARSAW_ID_P_VNAME_HIMARS", R.string.vehicle_name_himars);
            put("WARSAW_ID_P_VNAME_HJ8", R.string.vehicle_name_hj8);
            put("WARSAW_ID_P_VNAME_J20", R.string.vehicle_name_j20);
            put("WARSAW_ID_P_VNAME_KA60", R.string.vehicle_name_ka60);
            put("WARSAW_ID_P_VNAME_KORNET", R.string.vehicle_name_kornet);
            put("WARSAW_ID_P_VNAME_LAV25", R.string.vehicle_name_lav25);
            put("WARSAW_ID_P_VNAME_LAVAD", R.string.vehicle_name_lavad);
            put("WARSAW_ID_P_VNAME_LD2000", R.string.vehicle_name_ld2000);
            put("WARSAW_ID_P_VNAME_LYT2021", R.string.vehicle_name_lyt2021);
            put("WARSAW_ID_P_VNAME_M1ABRAMS", R.string.vehicle_name_m1abrams);
            put("WARSAW_ID_P_VNAME_MI28", R.string.vehicle_name_mi28);
            put("WARSAW_ID_P_VNAME_PAKFA", R.string.vehicle_name_pakfa);
            put("WARSAW_ID_P_VNAME_PANTSIR", R.string.vehicle_name_pantsir);
            put("WARSAW_ID_P_VNAME_PGZ95", R.string.vehicle_name_pgz95);
            put("WARSAW_ID_P_VNAME_PWC", R.string.vehicle_name_pwc);
            put("WARSAW_ID_P_VNAME_Q5", R.string.vehicle_name_q5);
            put("WARSAW_ID_P_VNAME_QUAD", R.string.vehicle_name_quad);
            put("WARSAW_ID_P_VNAME_RIB", R.string.vehicle_name_rib);
            put("WARSAW_ID_P_VNAME_SPM3", R.string.vehicle_name_spm3);
            put("WARSAW_ID_P_VNAME_SU39", R.string.vehicle_name_su39);
            put("WARSAW_ID_P_VNAME_T90", R.string.vehicle_name_t90);
            put("WARSAW_ID_P_VNAME_T99", R.string.vehicle_name_t99);
            put("WARSAW_ID_P_VNAME_TOW", R.string.vehicle_name_tow);
            put("WARSAW_ID_P_VNAME_UH1Y", R.string.vehicle_name_uh1y);
            put("WARSAW_ID_P_VNAME_VDV", R.string.vehicle_name_vdv);
            put("WARSAW_ID_P_VNAME_Z10", R.string.vehicle_name_z10);
            put("WARSAW_ID_P_VNAME_Z11", R.string.vehicle_name_z11);
            put("WARSAW_ID_P_VNAME_Z9", R.string.vehicle_name_z9);
            put("WARSAW_ID_P_VNAME_ZBD09", R.string.vehicle_name_zbd09);
            put("WARSAW_ID_P_VNAME_ZFB05", R.string.vehicle_name_zfb05);
            put("WARSAW_ID_P_XP0_VNAME_DPV", R.string.vehicle_xp0_name_dpv);
            put("WARSAW_ID_P_XP0_VNAME_SKIDLOADER", R.string.vehicle_xp0_name_skidloader);
            put("WARSAW_ID_P_XP1_VNAME_BOMBER", R.string.vehicle_xp1_name_bomber);
            put("WARSAW_ID_P_XP1_VNAME_BOMBCRUISE", R.string.vehicle_xp1_name_bombcruise);
            put("WARSAW_ID_P_XP1_VNAME_BTR90", R.string.vehicle_xp1_name_btr90);
            put("WARSAW_ID_P_XP1_VNAME_DIRTBIKE", R.string.vehicle_xp1_name_dirtbike);
            put("WARSAW_ID_P_XP1_VNAME_F35", R.string.vehicle_xp1_name_f35);
            put("WARSAW_ID_P_XP1_VNAME_SUAV", R.string.vehicle_xp1_name_suav);
            put("WARSAW_ID_P_XP1_VNAME_UCAV", R.string.vehicle_xp1_name_ucav);
            put("WARSAW_ID_P_XP1_VNAME_UCAVAIRBURST", R.string.vehicle_xp1_name_ucav_airburst);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key)? map.get(key) : R.string.na;
    }
}
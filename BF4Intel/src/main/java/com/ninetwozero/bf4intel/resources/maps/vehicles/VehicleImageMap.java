package com.ninetwozero.bf4intel.resources.maps.vehicles;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class VehicleImageMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("WARSAW_ID_P_VNAME_50CAL", R.drawable.vehicle_gunshield);
            put("WARSAW_ID_P_VNAME_9K22", R.drawable.vehicle_tunguska);
            put("WARSAW_ID_P_VNAME_A10", R.drawable.vehicle_a10);
            put("WARSAW_ID_P_VNAME_AAV", R.drawable.vehicle_aav);
            put("WARSAW_ID_P_VNAME_AC130", R.drawable.vehicle_ac130);
            put("WARSAW_ID_P_VNAME_AH1Z", R.drawable.vehicle_ah1z);
            put("WARSAW_ID_P_VNAME_AH6", R.drawable.vehicle_ah6);
            put("WARSAW_ID_P_VNAME_CB90", R.drawable.vehicle_rcb90);
            put("WARSAW_ID_P_VNAME_CENTURION", R.drawable.vehicle_centurion);
            put("WARSAW_ID_P_VNAME_COUGAR", R.drawable.vehicle_mrap_cougar);
            put("WARSAW_ID_P_VNAME_DV15", R.drawable.vehicle_dv15);
            put("WARSAW_ID_P_VNAME_GROWLER", R.drawable.vehicle_growler);
            put("WARSAW_ID_P_VNAME_HIMARS", R.drawable.vehicle_himars);
            put("WARSAW_ID_P_VNAME_HJ8", R.drawable.vehicle_hj8);
            put("WARSAW_ID_P_VNAME_J20", R.drawable.vehicle_j20);
            put("WARSAW_ID_P_VNAME_KA60", R.drawable.vehicle_ka60);
            put("WARSAW_ID_P_VNAME_KORNET", R.drawable.vehicle_kornet);
            put("WARSAW_ID_P_VNAME_LAV25", R.drawable.vehicle_lav_25);
            put("WARSAW_ID_P_VNAME_LAVAD", R.drawable.vehicle_lav_ad);
            put("WARSAW_ID_P_VNAME_LD2000", R.drawable.vehicle_ld_2000);
            put("WARSAW_ID_P_VNAME_LYT2021", R.drawable.vehicle_lyt2021);
            put("WARSAW_ID_P_VNAME_M1ABRAMS", R.drawable.vehicle_m1a2);
            put("WARSAW_ID_P_VNAME_MI28", R.drawable.vehicle_mi28);
            put("WARSAW_ID_P_VNAME_PAKFA", R.drawable.vehicle_su50_t50pak);
            put("WARSAW_ID_P_VNAME_PANTSIR", R.drawable.vehicle_pantsir);
            put("WARSAW_ID_P_VNAME_PGZ95", R.drawable.vehicle_type95aa);
            put("WARSAW_ID_P_VNAME_PWC", R.drawable.vehicle_pwc_jetski);
            put("WARSAW_ID_P_VNAME_Q5", R.drawable.vehicle_q5);
            put("WARSAW_ID_P_VNAME_QUAD", R.drawable.vehicle_quadbike);
            put("WARSAW_ID_P_VNAME_RIB", R.drawable.vehicle_rhib);
            put("WARSAW_ID_P_VNAME_SPM3", R.drawable.vehicle_spm3);
            put("WARSAW_ID_P_VNAME_SU39", R.drawable.vehicle_su25);
            put("WARSAW_ID_P_VNAME_T90", R.drawable.vehicle_t90);
            put("WARSAW_ID_P_VNAME_T99", R.drawable.vehicle_type99mbt);
            put("WARSAW_ID_P_VNAME_TOW", R.drawable.vehicle_tow);
            put("WARSAW_ID_P_VNAME_UH1Y", R.drawable.vehicle_venom);
            put("WARSAW_ID_P_VNAME_VDV", R.drawable.vehicle_vdv);
            put("WARSAW_ID_P_VNAME_Z10", R.drawable.vehicle_z10w);
            put("WARSAW_ID_P_VNAME_Z11", R.drawable.vehicle_z11);
            put("WARSAW_ID_P_VNAME_Z9", R.drawable.vehicle_z9_haitun);
            put("WARSAW_ID_P_VNAME_ZBD09", R.drawable.vehicle_zbd09);
            put("WARSAW_ID_P_VNAME_ZFB05", R.drawable.vehicle_zfb05);
            put("WARSAW_ID_P_XP1_VNAME_BOMBER", R.drawable.vehicle_h6k);
            put("WARSAW_ID_P_XP1_VNAME_BOMBCRUISE", R.drawable.vehicle_h6k);
            put("WARSAW_ID_P_XP1_VNAME_BTR90", R.drawable.vehicle_btr90);
            put("WARSAW_ID_P_XP1_VNAME_DIRTBIKE", R.drawable.vehicle_dirtbike);
            put("WARSAW_ID_P_XP1_VNAME_F35", R.drawable.vehicle_f35);
            put("WARSAW_ID_P_XP1_VNAME_SUAV", R.drawable.vehicle_suav);
            put("WARSAW_ID_P_XP1_VNAME_UCAV", R.drawable.vehicle_ucav);
            put("WARSAW_ID_P_XP1_VNAME_UCAVAIRBURST", R.drawable.vehicle_ucav);
            put("WARSAW_ID_P_INAME_EOD", R.drawable.vehicle_eodbot);
            put("WARSAW_ID_P_INAME_MORTAR", R.drawable.vehicle_m224_mortar);
            put("WARSAW_ID_P_INAME_MAV", R.drawable.vehicle_mav);
            put("WARSAW_ID_P_XP0_VNAME_DPV", R.drawable.vehicle_dpv_fancy);
            put("WARSAW_ID_P_XP0_VNAME_SKIDLOADER", R.drawable.vehicle_skidloader_fancy);
            put("WARSAW_ID_P_XP2_INAME_AAMINE", R.drawable.w_aamine_fancy);
            put("WARSAW_ID_P_XP2_VNAME_ACV", R.drawable.vehicle_acv_fancy);
            put("WARSAW_ID_P_XP2_VNAME_OLDCANNON", R.drawable.vehicle_oldcannon_fancy);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key)? map.get(key) : R.drawable.acc_none;
    }
}
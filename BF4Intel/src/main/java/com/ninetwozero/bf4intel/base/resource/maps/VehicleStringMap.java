package com.ninetwozero.bf4intel.base.resource.maps;

import java.util.HashMap;
import java.util.Map;

public class VehicleStringMap {
    private static final Map<String, String> map = new HashMap<String, String>() {
        {
            put("WARSAW_ID_P_VNAME_50CAL", ".50 CAL");
            put("WARSAW_ID_P_VNAME_9K22", "9K22 TUNGUSKA-M");
            put("WARSAW_ID_P_VNAME_A10", "A10 WARTHOG");
            put("WARSAW_ID_P_VNAME_AAV", "AAV-7A1 AMTRAC");
            put("WARSAW_ID_P_VNAME_AC130", "AC-130 GUNSHIP");
            put("WARSAW_ID_P_VNAME_AH1Z", "AH-1Z VIPER");
            put("WARSAW_ID_P_VNAME_AH6", "AH-6J LITTLE BIRD");
            put("WARSAW_ID_P_VNAME_CB90", "RCB");
            put("WARSAW_ID_P_VNAME_CENTURION", "HJ-8 LAUNCHER");
            put("WARSAW_ID_P_VNAME_COUGAR", "MRAP");
            put("WARSAW_ID_P_VNAME_DV15", "DV-15");
            put("WARSAW_ID_P_VNAME_GROWLER", "M1161 ITV");
            put("WARSAW_ID_P_VNAME_HIMARS", "M142");
            put("WARSAW_ID_P_VNAME_HJ8", "HJ-8 LAUNCHER");
            put("WARSAW_ID_P_VNAME_J20", "J-20");
            put("WARSAW_ID_P_VNAME_KA60", "KA-60 KASATKA");
            put("WARSAW_ID_P_VNAME_KORNET", "9M133 KORNET LAUNCHER");
            put("WARSAW_ID_P_VNAME_LAV25", "LAV-25");
            put("WARSAW_ID_P_VNAME_LAVAD", "LAV-AD");
            put("WARSAW_ID_P_VNAME_LD2000", "LD-2000 AA");
            put("WARSAW_ID_P_VNAME_LYT2021", "LYT2021");
            put("WARSAW_ID_P_VNAME_M1ABRAMS", "M1 ABRAMS");
            put("WARSAW_ID_P_VNAME_MI28", "Mi-28 HAVOC");
            put("WARSAW_ID_P_VNAME_PAKFA", "SU-50");
            put("WARSAW_ID_P_VNAME_PANTSIR", "PANTSIR-S1");
            put("WARSAW_ID_P_VNAME_PGZ95", "TYPE 95 AA");
            put("WARSAW_ID_P_VNAME_PWC", "PWC");
            put("WARSAW_ID_P_VNAME_Q5", "Q-5 FANTAN");
            put("WARSAW_ID_P_VNAME_QUAD", "QUAD BIKE");
            put("WARSAW_ID_P_VNAME_RIB", "RHIB BOAT");
            put("WARSAW_ID_P_VNAME_SPM3", "SPM-3");
            put("WARSAW_ID_P_VNAME_SU39", "SU-25TM FROGFOOT");
            put("WARSAW_ID_P_VNAME_T90", "T-90A");
            put("WARSAW_ID_P_VNAME_T99", "TYPE 99 MBT");
            put("WARSAW_ID_P_VNAME_TOW", "M220 TOW LAUNCHER");
            put("WARSAW_ID_P_VNAME_UH1Y", "UH-1Y VENOM");
            put("WARSAW_ID_P_VNAME_VDV", "VDV BUGGY");
            put("WARSAW_ID_P_VNAME_Z10", "Z-10W");
            put("WARSAW_ID_P_VNAME_Z11", "Z-11W");
            put("WARSAW_ID_P_VNAME_Z9", "Z-9 HAITUN");
            put("WARSAW_ID_P_VNAME_ZBD09", "ZBD-09");
            put("WARSAW_ID_P_VNAME_ZFB05", "ZFB-05");
        }
    };

    public static String get(final String key) {
        return map.containsKey(key)? map.get(key) : key;
    }
}
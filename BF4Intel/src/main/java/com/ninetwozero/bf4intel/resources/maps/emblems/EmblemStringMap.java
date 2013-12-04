package com.ninetwozero.bf4intel.resources.maps.emblems;

import java.util.HashMap;
import java.util.Map;

public class EmblemStringMap {
    private static final Map<String, String> map = new HashMap<String, String>() {
        {
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_01", "Drone");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_02", "Gunship");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_03", "Stealthjet");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_04", "Binocular");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_05", "Cracket");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_06", "Crown 3");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_07", "Raptor");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_08", "T -rex");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_09", "Triceratops");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_10", "Eod Bot");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_11", "Eye");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_12", "Flames");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_13", "Gear");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_14", "Grenade");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_15", "Armored Jeep");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_16", "Fast Jeep");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_17", "Ifv");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_18", "Mobile Aa2");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_19", "Ground Quad");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_20", "Headshot");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_21", "Transport Heli");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_22", "Jam");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_23", "Lightning");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_24", "Lion2");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_25", "Longrange");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_26", "Mag");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_27", "Medal Frame");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_28", "Medal Ring");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_29", "Medal Ring 2");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_30", "Medal Ring 3");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_31", "Medal Shape 3");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_32", "Missile");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_33", "Reduce Fall");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_34", "Shield 9");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_35", "Shield 5");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_36", "Shield 8");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_37", "Snakestick Wings");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_38", "Specialist");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_39", "Stationary Aa 2");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_40", "Stationary Mg 2");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_41", "Stationary Tow 2");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_42", "Suppression");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_43", "Sword");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_44", "Team Play");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_45", "Timer");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_46", "Aav");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_47", "Rhib Boat");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_48", "Dmr");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_49", "Wheel 3");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_50", "Wreath 3");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_51", "Attack Jet");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_52", "Explosion");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_53", "Fleur-de-lis");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_54", "Mlr");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_55", "Tank");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_56", "Handshake");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_57", "Attack Heli");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_58", "Hooah");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_59", "Lion");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_60", "Mine");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_61", "Square Corner");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_62", "Half Circle");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_63", "Line");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_64", "Crescent Moon");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_65", "Drop");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_66", "Bent Line");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_67", "Right Triangle");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_68", "Sale!");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_69", "Angry Fist");
            put("WARSAW_ID_M_BATTLEPACK_EMBLEM_70", "Epic Goat");
        }
    };

    public static String get(final String key) {
        return map.containsKey(key)? map.get(key) : key;
    }
}

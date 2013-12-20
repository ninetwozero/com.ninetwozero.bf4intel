package com.ninetwozero.bf4intel.resources.maps.vehicles;

import com.ninetwozero.bf4intel.R;import java.util.HashMap;
import java.util.Map;

public class VehiclesGroupStringMap {

    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("Vehicle Air Helicopter Attack", R.string.vehicle_group_attack_helicopter);
            put("Vehicle Air Helicopter Scout", R.string.vehicle_group_scout_helicopter);
            put("Vehicle Air Jet Attack", R.string.vehicle_group_attack_jet);
            put("Vehicle Air Jet Stealth", R.string.vehicle_group_stealth_jet);
            put("Vehicle Anti Air", R.string.vehicle_group_anti_air);
            put("Vehicle Fast Attack Craft", R.string.vehicle_group_fast_attack_craft);
            put("Vehicle Infantry Fighting Vehicle", R.string.vehicle_group_infantry_fighting_vehicle);
            put("Vehicle Main Battle Tanks", R.string.vehicle_group_main_battle_tank);
            put("Vehicle Transport", R.string.vehicle_group_transport_vehicle);
            put("Weapon Stationary ", R.string.vehicle_group_stationary_weapon);
            put("Vehicle Air", R.string.vehicle_group_air);
            put("Vehicle Mobile Artillery", R.string.vehicle_group_mobile_artillery);
            put("Soldier Equiment", R.string.vehicle_group_soldier_equipment);
            put("Vehicle Boat", R.string.vehicle_group_boat);
            put("Weapon Stationary AA", R.string.vehicle_group_stationary_aa);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key) ? map.get(key) : R.string.na;
    }
}

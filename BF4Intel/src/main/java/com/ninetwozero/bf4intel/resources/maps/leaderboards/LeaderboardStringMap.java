package com.ninetwozero.bf4intel.resources.maps.leaderboards;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class LeaderboardStringMap {
    public static class Category {
        private static final Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("ID_WEB_GEOLB_ACCURACY_ALL", R.string.lb_name_accuracy);
                put("ID_WEB_GEOLB_ASSAULT", R.string.lb_name_assault);
                put("ID_WEB_GEOLB_ASSAULT_RIFLES", R.string.lb_name_assault_rifles);
                put("ID_WEB_GEOLB_CARBINES", R.string.lb_name_carbine_rifles);
                put("ID_WEB_GEOLB_COMMANDER_SCORE", R.string.lb_name_commander);
                put("ID_WEB_GEOLB_DMRS", R.string.lb_name_marksman_rifles);
                put("ID_WEB_GEOLB_ENGINEER", R.string.lb_name_engineer);
                put("ID_WEB_GEOLB_FAST_ATTACK_CRAFTS", R.string.lb_name_fast_attack_crafts);
                put("ID_WEB_GEOLB_HANDGUNS", R.string.lb_name_handguns);
                put("ID_WEB_GEOLB_HAND_GRANADES", R.string.lb_name_grenades);
                put("ID_WEB_GEOLB_HEALS", R.string.lb_name_heals);
                put("ID_WEB_GEOLB_KILLDEATH_RATIO", R.string.lb_name_kdr);
                put("ID_WEB_GEOLB_KILLS", R.string.lb_name_kills);
                put("ID_WEB_GEOLB_LMGS", R.string.lb_name_light_machine_guns);
                put("ID_WEB_GEOLB_MELEE", R.string.lb_name_melee);
                put("ID_WEB_GEOLB_PDWS", R.string.lb_name_personal_defence_weapons);
                put("ID_WEB_GEOLB_RECON", R.string.lb_name_recon);
                put("ID_WEB_GEOLB_REPAIRS", R.string.lb_name_repairs);
                put("ID_WEB_GEOLB_REVIVES", R.string.lb_name_revives);
                put("ID_WEB_GEOLB_ROCKET_LAUNCHERS", R.string.lb_name_rocket_launchers);
                put("ID_WEB_GEOLB_SCORE", R.string.lb_name_score);
                put("ID_WEB_GEOLB_SHOTGUNS", R.string.lb_name_shotguns);
                put("ID_WEB_GEOLB_SKILL", R.string.lb_name_skill);
                put("ID_WEB_GEOLB_SNIPER_RIFLES", R.string.lb_name_sniper_rifles);
                put("ID_WEB_GEOLB_SPM", R.string.lb_name_spm);
                put("ID_WEB_GEOLB_SQUAD_SCORE", R.string.lb_name_squad);
                put("ID_WEB_GEOLB_SUPPORT", R.string.lb_name_support);
                put("ID_WEB_GEOLB_TIME_PLAYED", R.string.lb_name_time_played);
                put("ID_WEB_GEOLB_VEHICLE_AIR_HELICOPTER_ATTACK", R.string.lb_name_attach_helicopter);
                put("ID_WEB_GEOLB_VEHICLE_AIR_HELICOPTER_SCOUT", R.string.lb_name_scout_helicopter);
                put("ID_WEB_GEOLB_VEHICLE_AIR_JET_ATTACK", R.string.lb_name_attack_jet);
                put("ID_WEB_GEOLB_VEHICLE_AIR_JET_FIGHTER", R.string.lb_name_fighter_jet);
                put("ID_WEB_GEOLB_VEHICLE_ANTI_AIR", R.string.lb_name_anti_air);
                put("ID_WEB_GEOLB_VEHICLE_INFANTRY_FIGHTING_VEHICLE", R.string.lb_name_infantry_fighting_vehicle);
                put("ID_WEB_GEOLB_VEHICLE_MAIN_BATTLE_TANKS", R.string.lb_name_main_battle_tank);
                put("ID_WEB_GEOLB_WINLOSS_RATIO", R.string.lb_name_wlr);
                
                put("WARSAW_ID_BL_MISSION_AIR_WOLF_NAME_LOWER", R.string.lb_name_mission_heli_pilot);
                put("WARSAW_ID_BL_MISSION_ASSAULT_RIFLE_MASTER_NAME_LOWER", R.string.lb_name_mission_rifle_expert);
                put("WARSAW_ID_BL_MISSION_BEST_ASSAULT_NAME_LOWER", R.string.lb_name_mission_best_assault);
                put("WARSAW_ID_BL_MISSION_BEST_ENGINEER_NAME_LOWER", R.string.lb_name_mission_best_engineer);
                put("WARSAW_ID_BL_MISSION_BEST_RECON_NAME_LOWER", R.string.lb_name_mission_best_recon);
                put("WARSAW_ID_BL_MISSION_BEST_SQUAD_PLAYER_NAME_LOWER", R.string.lb_name_mission_best_squad);
                put("WARSAW_ID_BL_MISSION_BEST_SUPPORT_NAME_LOWER", R.string.lb_name_mission_best_support);
                put("WARSAW_ID_BL_MISSION_BULLSEYE_KING_NAME_LOWER", R.string.lb_name_mission_bullseye_king);
                put("WARSAW_ID_BL_MISSION_CARBINE_MASTER_NAME_LOWER", R.string.lb_name_mission_carbine_expert);
                put("WARSAW_ID_BL_MISSION_HANDGUN_MASTER_NAME_LOWER", R.string.lb_name_mission_handgun_expert);
                put("WARSAW_ID_BL_MISSION_HATE_BOAT_NAME_LOWER", R.string.lb_name_mission_steersman);
                put("WARSAW_ID_BL_MISSION_KILLING_MACHINE_NAME_LOWER", R.string.lb_name_mission_killing_machine);
                put("WARSAW_ID_BL_MISSION_LIGHT_MACHINE_GUN_MASTER_NAME_LOWER", R.string.lb_name_mission_lmg_expert);
                put("WARSAW_ID_BL_MISSION_MARKSMAN_NAME_LOWER", R.string.lb_name_mission_marksman);
                put("WARSAW_ID_BL_MISSION_ROAD_WARRIOR_NAME_LOWER", R.string.lb_name_mission_road_king);
                put("WARSAW_ID_BL_MISSION_SERIAL_KILLER_NAME_LOWER", R.string.lb_name_mission_killstreaker);
                put("WARSAW_ID_BL_MISSION_SNIPER_RIFLE_MASTER_NAME_LOWER", R.string.lb_name_mission_sniper_expert);
                put("WARSAW_ID_BL_MISSION_TOP_COMMANDER_NAME_LOWER", R.string.lb_name_mission_top_commander);
                put("WARSAW_ID_BL_MISSION_TOP_GUN_NAME_LOWER", R.string.lb_name_mission_ace_aviator);
                put("WARSAW_ID_BL_MISSION_VEHICLE_DESTROYER_NAME_LOWER", R.string.lb_name_mission_vehicle_destroyer);
            }
        };

        public static int get(final String key) {
            return map.containsKey(key) ? map.get(key) : R.string.na;
        }
    }

    public static class AreaType {
        private static final Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("world", R.string.lb_area_world);
            }
        };

        public static int get(final String key) {
            return map.containsKey(key) ? map.get(key) : R.string.na;
        }
    }
}

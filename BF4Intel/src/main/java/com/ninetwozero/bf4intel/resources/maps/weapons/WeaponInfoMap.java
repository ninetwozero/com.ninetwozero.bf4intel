package com.ninetwozero.bf4intel.resources.maps.weapons;

import com.ninetwozero.bf4intel.datatypes.WeaponInfo;

import java.util.HashMap;
import java.util.Map;

/*
    Format is either one of the two following:
    #1: new WeaponInfo(Category)
    #2: new WeaponInfo(Category, damage, accuracy, hip fire, range, rate, single, burst, auto)
 */
public class WeaponInfoMap {
    private final Map<String, WeaponInfo> map = new HashMap<String, WeaponInfo>() {
        {        
            put("WARSAW_ID_P_INAME_40MM", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_40MM_FLASH", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_40MM_LVG", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_40MM_SHG", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_40MM_SMK", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_ACB90", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_BAYONETT", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_BPKNIFE1", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_BPKNIFE2", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_BPKNIFE3", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_BPKNIFE4", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_BPKNIFE5", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_BPKNIFE6", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_BPKNIFE7", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_BPKNIFE8", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_C4", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_CLAYMORE", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_DEFIB", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_DIVERKNIFE", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_FGM148", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_FIM92", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_FLARE", new WeaponInfo("WARSAW_ID_P_CAT_GRENADE", 0, 0, 0, 0, 0, false, false, false));
            put("WARSAW_ID_P_INAME_FLASHBANG", new WeaponInfo("WARSAW_ID_P_CAT_GRENADE", 0, 0, 0, 0, 0, false, false, false));
            put("WARSAW_ID_P_INAME_IGLA", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_IMPACT", new WeaponInfo("WARSAW_ID_P_CAT_GRENADE", 0, 0, 0, 0, 0, false, false, false));
            put("WARSAW_ID_P_INAME_KNIFE14100BT", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_KNIFE2142", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_KNIFEPRECISION", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_M136", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 0, 0, 0, 0, 0, true, false, false));
            put("WARSAW_ID_P_INAME_M15", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_M18", new WeaponInfo("WARSAW_ID_P_CAT_GRENADE", 0, 0, 0, 0, 0, false, false, false));
            put("WARSAW_ID_P_INAME_M2", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_M26_FLECHETTE", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_M26_FRAG", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_M26_MASS", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_M26_SLUG", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_M32MGL", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 0, 0, 0, 0, 0, true, false, false));
            put("WARSAW_ID_P_INAME_M34", new WeaponInfo("WARSAW_ID_P_CAT_GRENADE", 0, 0, 0, 0, 0, false, false, false));
            put("WARSAW_ID_P_INAME_M67", new WeaponInfo("WARSAW_ID_P_CAT_GRENADE", 0, 0, 0, 0, 0, false, false, false));
            put("WARSAW_ID_P_INAME_MACHETE", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_MBTLAW", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_MORTAR", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_REPAIR", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_RPG7", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_SHANK", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_INAME_SMAW", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_SRAW", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_STARSTREAK", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 0, 0, 0, 0, 0, true, false, false));
            put("WARSAW_ID_P_INAME_V40", new WeaponInfo("WARSAW_ID_P_CAT_GRENADE", 0, 0, 0, 0, 0, false, false, false));
            put("WARSAW_ID_P_INAME_XM25", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_XM25_FLECHETTE", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_INAME_XM25_SMK", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_SP_WNAME_USAS12NV", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 70, 20, 10, 60, 300, true, false, true));
            put("WARSAW_ID_P_WNAME_870", new WeaponInfo("WARSAW_ID_P_CAT_SHOTGUN", 90, 20, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_93R", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 30, 24, 15, 60, 1100, true, true, false));
            put("WARSAW_ID_P_WNAME_A91", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 35, 25, 65, 800, true, false, true));
            put("WARSAW_ID_P_WNAME_ACR", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 40, 25, 60, 880, true, false, true));
            put("WARSAW_ID_P_WNAME_AEK971", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 50, 28, 50, 900, true, true, true));
            put("WARSAW_ID_P_WNAME_AK12", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 50, 28, 50, 680, true, true, true));
            put("WARSAW_ID_P_WNAME_AK5C", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 40, 25, 60, 700, true, false, true));
            put("WARSAW_ID_P_WNAME_AKU12", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 40, 25, 60, 680, true, true, true));
            put("WARSAW_ID_P_WNAME_AMR2", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 100, 100, 100, 10, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_AMR2CQB", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 100, 100, 100, 10, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_AMR2MID", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 100, 100, 100, 10, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_CBJMS", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 30, 30, 15, 75, 700, true, true, true));
            put("WARSAW_ID_P_WNAME_CSLR4", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 100, 100, 60, 20, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_CZ75", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 35, 27, 15, 73, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_CZ805", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 50, 28, 50, 700, true, true, true));
            put("WARSAW_ID_P_WNAME_DBV12", new WeaponInfo("WARSAW_ID_P_CAT_SHOTGUN", 70, 25, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_FAMAS", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 45, 28, 55, 1000, true, true, true));
            put("WARSAW_ID_P_WNAME_FN57", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 25, 30, 21, 70, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_FYJS", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 80, 100, 50, 20, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_G36C", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 40, 25, 60, 650, true, true, true));
            put("WARSAW_ID_P_WNAME_GALIL21", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 40, 25, 60, 770, true, false, true));
            put("WARSAW_ID_P_WNAME_GALIL23", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 50, 28, 50, 770, true, false, true));
            put("WARSAW_ID_P_WNAME_GALIL52", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 45, 40, 42, 60, 650, true, false, true));
            put("WARSAW_ID_P_WNAME_GALIL53", new WeaponInfo("WARSAW_ID_P_CAT_DMR", 60, 60, 60, 40, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_GLOCK18", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 30, 21, 15, 70, 1100, true, false, true));
            put("WARSAW_ID_P_WNAME_HAWK", new WeaponInfo("WARSAW_ID_P_CAT_SHOTGUN", 95, 15, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_HK45C", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 40, 25, 10, 70, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_JNG90", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 100, 100, 75, 20, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_JS2", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 25, 30, 15, 75, 900, true, true, true));
            put("WARSAW_ID_P_WNAME_LSAT", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 30, 40, 28, 30, 700, false, false, true));
            put("WARSAW_ID_P_WNAME_M1014", new WeaponInfo("WARSAW_ID_P_CAT_SHOTGUN", 70, 20, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_M16A4", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 50, 28, 50, 800, true, true, false));
            put("WARSAW_ID_P_WNAME_M1911", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 40, 40, 10, 70, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_M200", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 100, 100, 100, 20, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_M240", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 40, 40, 42, 10, 650, false, false, true));
            put("WARSAW_ID_P_WNAME_M249", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 30, 40, 32, 20, 800, false, false, true));
            put("WARSAW_ID_P_WNAME_M39", new WeaponInfo("WARSAW_ID_P_CAT_DMR", 60, 75, 60, 30, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_M40A5", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 100, 100, 60, 20, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_M412REX", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 65, 40, 18, 48, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_M416", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 50, 28, 50, 750, true, false, true));
            put("WARSAW_ID_P_WNAME_M4A1", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 40, 25, 60, 800, true, true, false));
            put("WARSAW_ID_P_WNAME_M82A3", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 100, 100, 100, 10, 0, true, false, true));
            put("WARSAW_ID_P_WNAME_M82A3CQB", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 100, 100, 100, 10, 0, true, false, true));
            put("WARSAW_ID_P_WNAME_M82A3MID", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 100, 100, 100, 10, 0, true, false, true));
            put("WARSAW_ID_P_WNAME_M9", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 30, 30, 15, 76, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_M98B", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 100, 100, 80, 20, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_MG4", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 30, 40, 32, 20, 800, false, false, true));
            put("WARSAW_ID_P_WNAME_MK11", new WeaponInfo("WARSAW_ID_P_CAT_DMR", 60, 75, 60, 30, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_MP443", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 30, 30, 13, 76, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_MX4", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 30, 30, 15, 65, 830, true, false, true));
            put("WARSAW_ID_P_WNAME_P226", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 30, 30, 15, 80, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_P90", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 25, 30, 15, 75, 900, true, false, true));
            put("WARSAW_ID_P_WNAME_PDR", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 30, 40, 25, 76, 750, true, false, true));
            put("WARSAW_ID_P_WNAME_PECHENEG", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 40, 40, 42, 10, 600, false, false, true));
            put("WARSAW_ID_P_WNAME_PP2000", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 30, 30, 15, 75, 650, true, false, true));
            put("WARSAW_ID_P_WNAME_QBB95", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 30, 35, 32, 35, 650, true, false, true));
            put("WARSAW_ID_P_WNAME_QBS09", new WeaponInfo("WARSAW_ID_P_CAT_SHOTGUN", 95, 15, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_QBU88", new WeaponInfo("WARSAW_ID_P_CAT_DMR", 50, 70, 40, 40, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_QBZ951", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 45, 28, 55, 650, true, true, true));
            put("WARSAW_ID_P_WNAME_QSZ92", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 25, 30, 14, 80, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_RFBTARGET", new WeaponInfo("WARSAW_ID_P_CAT_DMR", 60, 70, 60, 40, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_RPK12", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 30, 40, 32, 30, 600, true, false, true));
            put("WARSAW_ID_P_WNAME_SAIGA12", new WeaponInfo("WARSAW_ID_P_CAT_SHOTGUN", 70, 25, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_SAR21", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 45, 28, 55, 600, true, false, true));
            put("WARSAW_ID_P_WNAME_SCARH", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 40, 50, 42, 50, 620, true, false, true));
            put("WARSAW_ID_P_WNAME_SCARHSV", new WeaponInfo("WARSAW_ID_P_CAT_DMR", 60, 55, 60, 33, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_SCORP", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 20, 30, 10, 65, 1000, true, true, true));
            put("WARSAW_ID_P_WNAME_SCOUTELIT", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 80, 100, 50, 30, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_SG553", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 40, 25, 60, 830, true, true, true));
            put("WARSAW_ID_P_WNAME_SHORTY", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 60, 20, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_SKS", new WeaponInfo("WARSAW_ID_P_CAT_DMR", 50, 60, 40, 50, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_SPAS12", new WeaponInfo("WARSAW_ID_P_CAT_SHOTGUN", 90, 25, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_SRS", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 100, 100, 70, 30, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_STEYRAUG", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 45, 28, 55, 700, true, false, true));
            put("WARSAW_ID_P_WNAME_SV98", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 90, 100, 55, 25, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_SVD12", new WeaponInfo("WARSAW_ID_P_CAT_DMR", 60, 75, 60, 30, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_TAURUS44", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 85, 65, 24, 40, 0, true, false, false));
            put("WARSAW_ID_P_WNAME_TYPE88", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 30, 40, 32, 20, 700, false, false, true));
            put("WARSAW_ID_P_WNAME_TYPE95B1", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 45, 28, 55, 650, true, true, true));
            put("WARSAW_ID_P_WNAME_ULTIM", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 30, 40, 28, 30, 590, true, true, true));
            put("WARSAW_ID_P_WNAME_UMP45", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 40, 30, 20, 65, 600, true, true, true));
            put("WARSAW_ID_P_WNAME_UMP9", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 30, 30, 15, 65, 700, true, true, true));
            put("WARSAW_ID_P_WNAME_USAS12", new WeaponInfo("WARSAW_ID_P_CAT_BATTLEPICKUP", 70, 20, 10, 60, 300, true, false, true));
            put("WARSAW_ID_P_WNAME_UTAS", new WeaponInfo("WARSAW_ID_P_CAT_SHOTGUN", 90, 30, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_XP0_WNAME_ASVAL", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 35, 25, 10, 60, 900, true, false, true));
            put("WARSAW_ID_P_XP0_WNAME_DAO12", new WeaponInfo("WARSAW_ID_P_CAT_SHOTGUN", 70, 25, 10, 60, 0, true, false, false));
            put("WARSAW_ID_P_XP0_WNAME_F2000", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 45, 28, 55, 850, true, false, true));
            put("WARSAW_ID_P_XP0_WNAME_GOL", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 100, 100, 70, 28, 0, true, false, false));
            put("WARSAW_ID_P_XP0_WNAME_M60E4", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 40, 40, 42, 15, 570, false, false, true));
            put("WARSAW_ID_P_XP1_VNAME_UCAV", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_XP1_WNAME_L85A2", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 35, 28, 55, 750, true, false, true));
            put("WARSAW_ID_P_XP1_WNAME_L96A1", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 100, 100, 60, 20, 0, true, false, false));
            put("WARSAW_ID_P_XP1_WNAME_MP7", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 25, 28, 15, 75, 950, true, false, true));
            put("WARSAW_ID_P_XP1_WNAME_MTAR21", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 30, 30, 25, 65, 900, true, true, true));
            put("WARSAW_ID_P_XP1_WNAME_RPK74", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 30, 50, 35, 10, 600, true, false, true));
            put("WARSAW_ID_P_XP2_INAME_40MM_3GL", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_XP2_INAME_AAMINE", new WeaponInfo("WARSAW_ID_P_CAT_GADGET"));
            put("WARSAW_ID_P_XP2_WNAME_AR160", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 30, 50, 50, 50, 700, true, false, true));
            put("WARSAW_ID_P_XP2_WNAME_AWS", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 30, 40, 28, 30, 800, false, false, true));
            put("WARSAW_ID_P_XP2_WNAME_SR2", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 40, 30, 10, 70, 900, true, false, true));
            put("WARSAW_ID_P_XP2_WNAME_SR338", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 55, 75, 60, 30, 0, true, false, false));
            put("WARSAW_ID_P_XP2_WNAME_SW40", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 65, 12, 20, 45, 0, true, false, false));
            /*FINAL STAND*/
            put("WARSAW_ID_P_XP4_WNAME_RAILGUN", new WeaponInfo("WARSAW_ID_P_CAT_SNIPER", 100, 100, 100, 10, 0, true, false, false));
            put("WARSAW_ID_P_XP4_INAME_KNIFETANTO", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_XP4_INAME_KNIFEBIPOD", new WeaponInfo("WARSAW_ID_P_CAT_KNIFE"));
            put("WARSAW_ID_P_XP4_WNAME_SPYDER", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 100, 75, 15, 0, 1, true, false, false));

            put("WARSAW_ID_P_WNAME_AN94", new WeaponInfo("WARSAW_ID_P_CAT_ASSAULTRIFLE", 24, 67, 45, 78, 600, true, true, true));
            put("WARSAW_ID_P_WNAME_GROZA1", new WeaponInfo("WARSAW_ID_P_CAT_CARBINE", 26, 47, 37, 57, 725, true, false, true));
            put("WARSAW_ID_P_WNAME_L86A1", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 24, 55, 45, 67, 700, false, false, true));
            put("WARSAW_ID_P_WNAME_GROZA4", new WeaponInfo("WARSAW_ID_P_CAT_PDW", 23, 65, 32, 84, 700, true, false, true));
            put("WARSAW_ID_P_WNAME_SADDLEGUN", new WeaponInfo("WARSAW_ID_P_CAT_SIDEARM", 52, 15, 37, 23, 0, true, false, false));

            /*XP6*/
            put("WARSAW_ID_P_XP6_INAME_M60ULT", new WeaponInfo("WARSAW_ID_P_CAT_LMG", 43, 28, 45, 50, 570, false, false, true));
        }
    };

    public WeaponInfo get(final String key) {
        return map.containsKey(key)? map.get(key) : new WeaponInfo(key);
    }
}

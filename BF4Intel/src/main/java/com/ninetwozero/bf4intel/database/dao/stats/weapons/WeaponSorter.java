package com.ninetwozero.bf4intel.database.dao.stats.weapons;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WeaponSorter extends AbstractSorter<List<Weapon>> {

    public static final List<String> WEAPON_GROUP_CODE =
        new ArrayList<String>(
            Arrays.asList("WARSAW_ID_P_CAT_ASSAULTRIFLE", "WARSAW_ID_P_CAT_CARBINE", "WARSAW_ID_P_CAT_SHOTGUN",
                "WARSAW_ID_P_CAT_LMG", "WARSAW_ID_P_CAT_PDW", "WARSAW_ID_P_CAT_DMR", "WARSAW_ID_P_CAT_SNIPER",
                "WARSAW_ID_P_CAT_SIDEARM", "WARSAW_ID_P_CAT_GRENADE", "WARSAW_ID_P_CAT_KNIFE", "WARSAW_ID_P_CAT_GADGET"));
    private List<Weapon> weaponList;


    public WeaponSorter(List<Weapon> weaponList) {
        this.weaponList = weaponList;
    }

    @Override
    protected List<Weapon> sortByProgress() {
        List<Weapon> sortedByProgress = new ArrayList<Weapon>(weaponList);
        Collections.sort(sortedByProgress, Weapon.Comparators.PROGRESS);
        return sortedByProgress;
    }

    @Override
    protected List<Weapon> sortByCategory() {
        return weaponList;
    }
}

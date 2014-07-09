package com.ninetwozero.bf4intel.database.dao.stats.weapons;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WeaponSorter extends AbstractSorter<List<Weapon>> {

    public static final List<String> WEAPON_GROUP_CODE =
        new ArrayList<String>(Arrays.asList("wA", "wC", "waS", "wL", "waPDW", "wD", "wSR", "wH", "wG", "wSP", "wX"));
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

package com.ninetwozero.bf4intel.datatypes;

public class WeaponInfo {
    public static final int NONE = -1;

    private String category;
    private int damage = NONE;
    private int accuracy = NONE;
    private int range = NONE;
    private int hipFire = NONE;
    private boolean singleFire;
    private boolean burstFire;
    private boolean autoFire;
    private int rateOfFire;


    public WeaponInfo(String category) {
        this.category = category;
    }

    public WeaponInfo(String category, int damage, int accuracy, int range, int hipFire, int rateOfFire, boolean singleFire, boolean burstFire, boolean autoFire) {
        this.category = category;
        this.damage = damage;
        this.accuracy = accuracy;
        this.range = range;
        this.hipFire = hipFire;
        this.rateOfFire = rateOfFire;
        this.singleFire = singleFire;
        this.burstFire = burstFire;
        this.autoFire = autoFire;
    }

    public String getCategory() {
        return category;
    }

    public int getDamage() {
        return damage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getRange() {
        return range;
    }

    public int getHipFire() {
        return hipFire;
    }

    public int getRateOfFire() {
        return rateOfFire;
    }

    public boolean isSingleFire() {
        return singleFire;
    }

    public boolean isBurstFire() {
        return burstFire;
    }

    public boolean isAutoFire() {
        return autoFire;
    }
}

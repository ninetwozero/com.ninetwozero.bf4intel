package com.ninetwozero.bf4intel.json.stats.details;

import com.google.gson.annotations.SerializedName;

public class StatsDetails {

    /*Multiplayer score*/
    @SerializedName("assault")
    private int assaultScore;
    @SerializedName("engineer")
    private int engineerScore;
    @SerializedName("support")
    private int supportScore;
    @SerializedName("recon")
    private int reconScore;
    @SerializedName("commander")
    private int commanderScore;
    @SerializedName("sc_squad")
    private int squadScore;
    @SerializedName("sc_vehicle")
    private int vehicleScore;
    @SerializedName("sc_award")
    private int awardScore;
    @SerializedName("sc_unlock")
    private int unlockScore;
    @SerializedName("totalScore")
    private int totalScore;

    /*General*/
    @SerializedName("kills")
    private int kills;
    @SerializedName("deaths")
    private int deaths;
    @SerializedName("killAssits")
    private int killAssits;
    @SerializedName("kdRatio")
    private double kdRatio;
    @SerializedName("numWins")
    private int wins;
    @SerializedName("numLosses")
    private int losses;
    @SerializedName("shotsFired")
    private int shotsFired;
    @SerializedName("shotsHit")
    private int shotsHit;
    @SerializedName("accuracy")
    private double accuracy;

    /*Game modes*/
    @SerializedName("conquest")
    private int conquest;
    @SerializedName("rush")
    private int rush;
    @SerializedName("teamdeatmatch")
    private int deathMatch;
    @SerializedName("domination")
    private int domination;
    @SerializedName("flagrunner")
    private int captureTheFlag;
    @SerializedName("obliteration")
    private int obliteration;
    /*
    Missing in JSON
    @SerializedName("")
    private int airSupperiority;
    @SerializedName("")
    private int defuse;*/

    /*Team*/
    @SerializedName("repairs")
    private int repairs;
    @SerializedName("revives")
    private int revives;
    @SerializedName("heals")
    private int heals;
    @SerializedName("resupplies")
    private int resupplies;
    @SerializedName("avengerKills")
    private int avengerKills;
    @SerializedName("saviorKills")
    private int saviorKills;
    @SerializedName("suppressionAssists")
    private int suppresionAssists;
    @SerializedName("quitPercentage")
    private double quits;

    /*Extra*/
    @SerializedName("dogtagsTaken")
    private int dogtagTaken;
    @SerializedName("vehiclesDestroyed")
    private int vehiclesDestroyed;
    @SerializedName("vehicleDamage")
    private int vehicleDamage;
    @SerializedName("headshots")
    private int headshots;
    @SerializedName("longestHeadshot")
    private double longestHeadshot;
    @SerializedName("killStreakBonus")
    private int highestKillStreak;
    @SerializedName("nemesisKills")
    private int nemesisKills;
    @SerializedName("nemesisStreak")
    private int highestNemesisStreak;

    /*Game mode extra*/
    @SerializedName("flagCaptures")
    private int flagsCaptured;
    @SerializedName("flagDefend")
    private int flagsDefended;

    public int getAssaultScore() {
        return assaultScore;
    }

    public int getEngineerScore() {
        return engineerScore;
    }

    public int getSupportScore() {
        return supportScore;
    }

    public int getReconScore() {
        return reconScore;
    }

    public int getCommanderScore() {
        return commanderScore;
    }

    public int getSquadScore() {
        return squadScore;
    }

    public int getVehicleScore() {
        return vehicleScore;
    }

    public int getAwardScore() {
        return awardScore;
    }

    public int getUnlockScore() {
        return unlockScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getKillAssits() {
        return killAssits;
    }

    public double getKdRatio() {
        return kdRatio;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public int getShotsHit() {
        return shotsHit;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getConquest() {
        return conquest;
    }

    public int getRush() {
        return rush;
    }

    public int getDeathMatch() {
        return deathMatch;
    }

    public int getDomination() {
        return domination;
    }

    public int getCaptureTheFlag() {
        return captureTheFlag;
    }

    public int getObliteration() {
        return obliteration;
    }

    public int getRepairs() {
        return repairs;
    }

    public int getRevives() {
        return revives;
    }

    public int getHeals() {
        return heals;
    }

    public int getResupplies() {
        return resupplies;
    }

    public int getAvengerKills() {
        return avengerKills;
    }

    public int getSaviorKills() {
        return saviorKills;
    }

    public int getSuppresionAssists() {
        return suppresionAssists;
    }

    public double getQuits() {
        return quits;
    }

    public int getDogtagTaken() {
        return dogtagTaken;
    }

    public int getVehiclesDestroyed() {
        return vehiclesDestroyed;
    }

    public int getVehicleDamage() {
        return vehicleDamage;
    }

    public int getHeadshots() {
        return headshots;
    }

    public double getLongestHeadshot() {
        return longestHeadshot;
    }

    public int getHighestKillStreak() {
        return highestKillStreak;
    }

    public int getNemesisKills() {
        return nemesisKills;
    }

    public int getHighestNemesisStreak() {
        return highestNemesisStreak;
    }

    public int getFlagsCaptured() {
        return flagsCaptured;
    }

    public int getFlagsDefended() {
        return flagsDefended;
    }
}

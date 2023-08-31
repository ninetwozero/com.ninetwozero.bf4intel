package com.ninetwozero.bf4intel.repository;

import androidx.lifecycle.LiveData;

import com.ninetwozero.bf4intel.database.BF4RoomDatabase;
import com.ninetwozero.bf4intel.database.entities.SoldierOverviewEntity;
import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;

public class SoldierOverviewRepository {

    private static SoldierOverviewRepository repoInstance;

    private final BF4RoomDatabase db;

    private SoldierOverviewRepository(final BF4RoomDatabase database) {
        db = database;

    }

    public static SoldierOverviewRepository getInstance(final BF4RoomDatabase database) {
        if (repoInstance == null) {
            synchronized (SoldierOverviewRepository.class) {
                if (repoInstance == null) {
                    repoInstance = new SoldierOverviewRepository(database);
                }
            }
        }
        return repoInstance;
    }

    public void insert(SoldierOverviewEntity entity) {
        db.soldierOverviewDAO().insert(entity);
    }

    public LiveData<SoldierOverview> getSoldierOverviewWith(String soldierId, String platformId) {
        return db.soldierOverviewDAO().getSoldierOverviewWith(soldierId, platformId);
    }
}

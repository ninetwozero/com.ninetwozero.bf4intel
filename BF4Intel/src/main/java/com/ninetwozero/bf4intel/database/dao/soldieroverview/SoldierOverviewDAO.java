package com.ninetwozero.bf4intel.database.dao.soldieroverview;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ninetwozero.bf4intel.database.entities.SoldierOverviewEntity;
import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;

@Dao
public interface SoldierOverviewDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SoldierOverviewEntity entity);

    @Query("SELECT json FROM soldier_overview where soldierId = :soldierId AND platformId = :platformId")
    LiveData<SoldierOverview> getSoldierOverviewWith(String soldierId, String platformId);
}

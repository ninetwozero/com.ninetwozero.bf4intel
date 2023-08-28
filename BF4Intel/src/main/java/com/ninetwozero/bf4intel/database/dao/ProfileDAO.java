package com.ninetwozero.bf4intel.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ninetwozero.bf4intel.database.entities.ProfileEntity;

import java.util.List;

@Dao
public interface ProfileDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(ProfileEntity profileEntity);

    //TODO may not need it at all
    @Query("SELECT * FROM user_profile")
    LiveData<List<ProfileEntity>> getAllProfiles();

    @Query("SELECT userId FROM user_profile WHERE username = :playername")
    LiveData<String> getId(String playername);

    @Query("SELECT username FROM user_profile WHERE userId = :id")
    LiveData<String> getUsername(String id);
}

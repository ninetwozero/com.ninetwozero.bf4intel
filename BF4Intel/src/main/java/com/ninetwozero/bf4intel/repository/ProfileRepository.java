package com.ninetwozero.bf4intel.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.ninetwozero.bf4intel.database.BF4RoomDatabase;
import com.ninetwozero.bf4intel.database.entities.ProfileEntity;

public class ProfileRepository {

    private static ProfileRepository repoInstance;

    private final BF4RoomDatabase db;
    private MediatorLiveData<ProfileEntity> observableProfile;

    private ProfileRepository(final BF4RoomDatabase database) {
        db = database;
        observableProfile = new MediatorLiveData<>();

        //observableProfile.addSource(db.profileDao().
    }

    public static ProfileRepository getInstance(final BF4RoomDatabase database) {
        if(repoInstance == null) {
            synchronized (ProfileRepository.class) {
                if (repoInstance == null) {
                    repoInstance = new ProfileRepository(database);
                }
            }
        }
        return repoInstance;
    }

    public void insert(ProfileEntity profile) {
        db.profileDao().insert(profile);
    }

    public LiveData<String> getId(final String playername) {
        return db.profileDao().getId(playername);
    }

    public LiveData<String> getUsername(final String id) {
        return db.profileDao().getUsername(id);
    }
}

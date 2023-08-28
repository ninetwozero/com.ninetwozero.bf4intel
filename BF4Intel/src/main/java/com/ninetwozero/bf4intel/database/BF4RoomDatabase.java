package com.ninetwozero.bf4intel.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ninetwozero.bf4intel.database.dao.ProfileDAO;
import com.ninetwozero.bf4intel.database.entities.ProfileEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ProfileEntity.class},
        version = 1,
        exportSchema = false) //TODO change to true to support migrations
public abstract class BF4RoomDatabase extends RoomDatabase {

    public abstract ProfileDAO profileDao();

    private static volatile BF4RoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BF4RoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (BF4RoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BF4RoomDatabase.class,
                            "bf4_room_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

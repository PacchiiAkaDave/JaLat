package com.example.jalat.db.syllables;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author Jan Weimer
 *
 * */
@Database(entities = Syllable.class, version = 1, exportSchema = false)
public abstract class SyllablesDB extends RoomDatabase {

    public abstract SyllableDao SyllableDao();

    private static volatile SyllablesDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SyllablesDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SyllablesDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SyllablesDB.class, "syllables_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

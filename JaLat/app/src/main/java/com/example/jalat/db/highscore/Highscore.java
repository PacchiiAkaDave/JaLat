package com.example.jalat.db.highscore;

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
@Database(entities = User.class, version = 1, exportSchema = false)
public abstract class Highscore extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile Highscore INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static Highscore getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Highscore.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Highscore.class, "score_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}

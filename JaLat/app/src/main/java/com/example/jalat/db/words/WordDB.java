package com.example.jalat.db.words;

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
@Database(entities = Word.class, version = 1, exportSchema = false)
public abstract class WordDB extends RoomDatabase {


    public abstract WordDao WordDao();

    private static volatile WordDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WordDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDB.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

package com.example.jalat.db.syllables;

import android.app.Application;
import java.util.List;


/**
 *
 * @author Jan Weimer
 *
 * */
public class SyllableRepository {

    private SyllableDao mSyllableDao;
    //private List<Syllable> allSyllables;

    SyllableRepository(Application application) {
        SyllablesDB db = SyllablesDB.getDatabase(application);
        mSyllableDao = db.SyllableDao();
        //allSyllables = mSyllableDao.readSyllables();
    }

    List<Syllable> getAllSyllables() {
        return mSyllableDao.readSyllables();
    }

    void insert(Syllable syl) {
        SyllablesDB.databaseWriteExecutor.execute(() -> {
            mSyllableDao.addSyllable(syl);
        });
    }
}

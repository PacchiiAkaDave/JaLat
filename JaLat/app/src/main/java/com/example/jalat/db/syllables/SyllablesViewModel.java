package com.example.jalat.db.syllables;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import java.util.List;


/**
 *
 * @author Jan Weimer
 *
 * */
public class SyllablesViewModel extends AndroidViewModel {

    private SyllableRepository mSyllable;

    //private final List<Syllable> mAllSyllable;

    public SyllablesViewModel(@NonNull Application application) {
        super(application);
        mSyllable = new SyllableRepository(application);
        //mAllSyllable = mSyllable.getAllSyllables();
    }

    public List<Syllable> getAllSyllables() { return mSyllable.getAllSyllables(); }

    public void insert(Syllable syllable) { mSyllable.insert(syllable); }
}

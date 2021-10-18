package com.example.jalat.db.words;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import java.util.List;


/**
 *
 * @author Jan Weimer
 *
 * */
public class WordViewModel extends AndroidViewModel {

    private WordRepository mWord;

    //private final List<Word> mAllWords;

    //private final List<Word> mAllKanji;
    //private final List<Word> mAllKataHira;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWord = new WordRepository(application);
       // mAllWords = mWord.getAllWords();
       // mAllKanji = mWord.getWordsKanji();
        //mAllKataHira = mWord.getWordsKataHira();
    }

    public List<Word> getAllWords() { return mWord.getAllWords(); }

    public List<Word> getWordsKanji() { return mWord.getWordsKanji(); }

    public List<Word> getWordsKataHira() { return mWord.getWordsKataHira(); }

    public void insert(Word word) { mWord.insert(word); }


}

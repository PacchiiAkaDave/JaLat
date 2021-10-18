package com.example.jalat.db.words;

import android.app.Application;
import java.util.List;



/**
 *
 * @author Jan Weimer
 *
 * */
public class WordRepository {

    private WordDao mWordDao;
    //private List<Word> allWords;
    //private List<Word> wordsKanji;
    //private List<Word> wordsKataHira;

    WordRepository(Application application) {
        WordDB db = WordDB.getDatabase(application);
        mWordDao = db.WordDao();
        //allWords = mWordDao.allWords();
        //wordsKanji = mWordDao.allWordsKanji();
        //wordsKataHira = mWordDao.allWordsHiraganaKatakana();
    }

    List<Word> getAllWords() {
        return mWordDao.allWords();
    }

    void insert(Word word) {
        WordDB.databaseWriteExecutor.execute(() -> {
            mWordDao.addWord(word);
        });
    }

    List<Word> getWordsKanji() { return mWordDao.allWordsKanji(); }

    List<Word> getWordsKataHira() { return mWordDao.allWordsHiraganaKatakana(); }


}

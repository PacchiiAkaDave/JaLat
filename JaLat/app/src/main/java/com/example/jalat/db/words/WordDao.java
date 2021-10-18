package com.example.jalat.db.words;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


/**
 *
 * @author Jan Weimer
 *
 * */
@Dao
public interface WordDao {

    @Insert
    void addWord(Word word);

    @Query("Select * From table_word")
    List<Word> allWords();

    @Query("Select * From table_word Where kanji is NOT NULL")
    List<Word> allWordsKanji();

    @Query("Select * From table_word Where kanji is NULL")
    List<Word> allWordsHiraganaKatakana();


}

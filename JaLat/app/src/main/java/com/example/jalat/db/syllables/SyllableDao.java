package com.example.jalat.db.syllables;


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
public interface SyllableDao {

    @Insert
    void addSyllable(Syllable syl);

    @Query("Select * From table_syllable")
    List<Syllable> readSyllables();

}

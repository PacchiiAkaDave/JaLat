package com.example.jalat.db.syllables;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 *
 * @author Jan Weimer
 *
 * */
@Entity(tableName = "table_syllable")
public class Syllable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "romaji")
    String Romaji;

    @NonNull
    @ColumnInfo(name = "katakana")
    String Katakana;

    @NonNull
    @ColumnInfo(name = "hiragana")
    String Hiragana;

    public Syllable(String ro, String Ka, String Hira){
        Romaji = ro;

        Katakana = Ka;

        Hiragana = Hira;
    }

    public Syllable(){}

    public String getRomaji() { return Romaji;}

    public String getKatakana() { return Katakana;}

    public String getHiragana() { return Hiragana;}

}

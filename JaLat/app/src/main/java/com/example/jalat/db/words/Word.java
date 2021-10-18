package com.example.jalat.db.words;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 *
 * @author Jan Weimer
 *
 * */
@Entity(tableName = "table_word")
public class Word {

    @PrimaryKey @NonNull
    @ColumnInfo(name = "Romaji")
    String Romaji;
    @ColumnInfo(name = "kanji")
    String Kanji;
    @ColumnInfo(name = "hiragana")
    String Hiragana;
    @ColumnInfo(name = "katakana")
    String Katakana;


    //Woerter, die es nur in Hiragana und Katakana gibt
    public Word(String ro, String hira, String kat){
        Kanji = null;
        Romaji = ro;
        Hiragana = hira;
        Katakana = kat;
    }

    //Woerter, die es nur in Kanji gibt
    public Word(String ro, String ka){
        Kanji = ka;
        Romaji = ro;
        Hiragana = null;
        Katakana = null;
    }

    public String getRomaji(){
        return Romaji;
    }
    public String getKanji(){
        return Kanji;
    }
    public String getHiragana() { return Hiragana;}
    public String getKatakana() { return Katakana;}

    public Word(){
    }

}

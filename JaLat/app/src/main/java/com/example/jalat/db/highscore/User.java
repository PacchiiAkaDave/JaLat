package com.example.jalat.db.highscore;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 * @author Jan Weimer
 *
 * */
@Entity(tableName = "user_data")
public class User {
    @ColumnInfo(name = "score")
    int score;
    @PrimaryKey(autoGenerate = true)
    long ID;
    @ColumnInfo(name = "name")
    String name;

    //besitzt Name und eine Punktzahl
    public User(int score, String name){
        this.name = name;
        this.score = score;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

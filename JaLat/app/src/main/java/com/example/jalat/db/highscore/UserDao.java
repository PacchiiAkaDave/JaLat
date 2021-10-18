package com.example.jalat.db.highscore;




import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 *
 * @author Jan Weimer
 *
 * */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addUser(User user);

    @Query("Select * From user_data Order BY score DESC")
    List<User> readHighscore();


}

package com.example.jalat.db.highscore;

import android.app.Application;



import java.util.List;


/**
 *
 * @author Jan Weimer
 *
 * */
public class HighscoreRepository {

    private UserDao mUserDao;
    //private List<User> mAllUser;

    //DB wird geholt
    HighscoreRepository(Application application) {
        Highscore db = Highscore.getDatabase(application);
        mUserDao = db.userDao();
       // mAllUser = mUserDao.readHighscore();
    }

    List<User> getAllUsers() {
        return mUserDao.readHighscore();
    }

    //Einsetzen der Daten in einem Background-Thread
    void insert(User user) {
        Highscore.databaseWriteExecutor.execute(() -> {
            mUserDao.addUser(user);
        });
    }
}

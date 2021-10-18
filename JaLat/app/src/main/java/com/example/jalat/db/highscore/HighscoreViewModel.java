package com.example.jalat.db.highscore;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;



import java.util.List;


/**
 *
 * @author Jan Weimer
 *
 * */
public class HighscoreViewModel extends AndroidViewModel {

    private HighscoreRepository mRepository;

    //private List<User> mAllUser;

    public HighscoreViewModel (Application application) {
        super(application);
        mRepository = new HighscoreRepository(application);
        //mAllUser = mRepository.getAllUsers();
    }

    public List<User> getAllUser() { return mRepository.getAllUsers(); }

    public void insert(User user) { mRepository.insert(user); }
}

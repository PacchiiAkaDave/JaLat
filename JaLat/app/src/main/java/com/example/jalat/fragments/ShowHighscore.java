package com.example.jalat.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jalat.R;
import com.example.jalat.activity.MainActivity;
import com.example.jalat.db.highscore.Highscore;
import com.example.jalat.db.highscore.HighscoreAdapter;
import com.example.jalat.db.highscore.HighscoreViewModel;
import com.example.jalat.db.highscore.User;

import java.util.List;


/**
 *
 * @author Jan Weimer
 *
 * */
public class ShowHighscore extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public ShowHighscore() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_highscore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //HighscoreAdapter wird in RecyclerView gesetzt
        recyclerView = (RecyclerView) view.findViewById(R.id.highscoreView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new HighscoreAdapter(MainActivity.getHighscore());
        recyclerView.setAdapter(mAdapter);

        view.invalidate();
    }
}

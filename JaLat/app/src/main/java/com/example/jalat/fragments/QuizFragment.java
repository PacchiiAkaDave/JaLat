package com.example.jalat.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;
import com.example.jalat.R;


/**
 *
 * @author Jan Weimer
 *
 * */
public class QuizFragment extends Fragment implements View.OnClickListener{

    ToggleButton Quiz;
    ToggleButton Eingabe;
    Button play;
    NavController navController;

    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        Quiz = (ToggleButton) view.findViewById(R.id.quiz);
        Quiz.setOnClickListener(this);
        Eingabe = (ToggleButton) view.findViewById(R.id.input);
        Eingabe.setOnClickListener(this);
        play = (Button) view.findViewById(R.id.play);
        play.setOnClickListener(this);
        play.setEnabled(false);
    }


    //Auswahl ob Quiz oder Eingabe
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.input:
                play.setEnabled(true);
                Eingabe.setChecked(true);
                Quiz.setChecked(false);
                break;
            case R.id.quiz:
                play.setEnabled(true);
                Quiz.setChecked(true);
                Eingabe.setChecked(false);
                break;
            case R.id.play:
                if(Quiz.isChecked()) {
                    navController.navigate(R.id.action_quizFragment_to_gameFragment);
                } else {
                    navController.navigate(R.id.action_quizFragment_to_inputFragment);
                }
                break;
        }
    }
}

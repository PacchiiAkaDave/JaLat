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

import com.example.jalat.activity.MainActivity;
import com.example.jalat.R;


/**
 *
 * @author Jan Weimer
 *
 * */
public class FontFragment extends Fragment implements View.OnClickListener{

    ToggleButton Romaji;
    ToggleButton Japanisch;
    Button next;
    NavController navController;

    public FontFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_font, container, false);
    }

    //Initialisierung der Togglebuttons
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        Romaji = (ToggleButton) view.findViewById(R.id.romaji);
        Romaji.setOnClickListener(this);
        Japanisch = (ToggleButton) view.findViewById(R.id.japanese);
        Japanisch.setOnClickListener(this);
        //Button wird anfangs disabled
        next = (Button) view.findViewById(R.id.but_to_quiz);
        next.setOnClickListener(this);
        next.setEnabled(false);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            //Nach dem Togglen wird "next" enabled
            case R.id.romaji:
                next.setEnabled(true);
                Romaji.setChecked(true);
                Japanisch.setChecked(false);
                MainActivity.isJapanese = false;
                break;
            case R.id.japanese:
                next.setEnabled(true);
                Japanisch.setChecked(true);
                Romaji.setChecked(false);
                MainActivity.isJapanese = true;
                break;
            case R.id.but_to_quiz:
                navController.navigate(R.id.action_fontFragment_to_quizFragment);
                break;
        }
    }
}

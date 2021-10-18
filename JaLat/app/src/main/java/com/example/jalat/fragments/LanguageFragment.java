package com.example.jalat.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
public class LanguageFragment extends Fragment implements View.OnClickListener {

    ToggleButton Katakana;
    ToggleButton Hiragana;
    ToggleButton Kanji;
    Button move_Frag;
    NavController navController;


    public LanguageFragment() {
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
        return inflater.inflate(R.layout.fragment_language, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        move_Frag = (Button) view.findViewById(R.id.but_to_font);
        move_Frag.setOnClickListener(this);
        move_Frag.setEnabled(false);

        Hiragana = (ToggleButton) view.findViewById(R.id.Hiragana);
        Hiragana.setOnClickListener(this);
        Katakana = (ToggleButton) view.findViewById(R.id.Katakana);
        Katakana.setOnClickListener(this);
        Kanji = (ToggleButton) view.findViewById(R.id.Kanji);
        Kanji.setOnClickListener(this);
        //Bei Silben ist Kanji disabled, da diese Sprache keine Silben besitzt
        if(MainActivity.isWord == false){
            Kanji.setEnabled(false);
        }

    }

    //Einstellung der Sprachen
    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.Hiragana:
                move_Frag.setEnabled(true);
                Hiragana.setChecked(true);
                Katakana.setChecked(false);
                Kanji.setChecked(false);
                MainActivity.isHiragana = true;
                MainActivity.isKatakana = false;
                MainActivity.isKanji = false;
                break;
            case R.id.Katakana:
                move_Frag.setEnabled(true);
                Katakana.setChecked(true);
                Hiragana.setChecked(false);
                Kanji.setChecked(false);
                MainActivity.isHiragana = false;
                MainActivity.isKatakana = true;
                MainActivity.isKanji = false;
                break;
            case R.id.Kanji:
                move_Frag.setEnabled(true);
                Kanji.setChecked(true);
                Katakana.setChecked(false);
                Hiragana.setChecked(false);
                MainActivity.isHiragana = false;
                MainActivity.isKatakana = false;
                MainActivity.isKanji = true;
                break;
            case R.id.but_to_font:
                navController.navigate(R.id.action_languageFragment_to_fontFragment);
                break;

        }
    }


}

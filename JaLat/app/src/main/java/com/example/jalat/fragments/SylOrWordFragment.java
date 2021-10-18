package com.example.jalat.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;
import com.example.jalat.R;
import com.example.jalat.activity.MainActivity;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 *
 *
 * @author Jan Weimer
 *
 * */
public class SylOrWordFragment extends Fragment implements View.OnClickListener{

    ToggleButton words;
    ToggleButton syllables;
    Button next;
    Button Highscore;
    NavController navController;
    Switch sw;
    String currLang;

    public SylOrWordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reset();

        //https://developer.android.com/guide/navigation/navigation-custom-back Quelle wie man Backpress handhabt
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            //Activity wird beendet ( App wird geschlossen)
            @Override
            public void handleOnBackPressed() {
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


    }

    private void reset(){
        MainActivity.setToZero();
        MainActivity.isWord =false;
        MainActivity.isSyllable=false;
        MainActivity.isKanji=false;
        MainActivity.isKatakana=false;
        MainActivity.isHiragana=false;
        MainActivity.isJapanese=false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_syl_or_word, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        next = (Button) view.findViewById(R.id.toLanguage);
        next.setOnClickListener(this);
        next.setEnabled(false);
        Highscore = (Button) view.findViewById(R.id.highscore);
        Highscore.setOnClickListener(this);
        words = (ToggleButton) view.findViewById(R.id.words);
        words.setOnClickListener(this);
        words.setChecked(false);
        syllables = (ToggleButton) view.findViewById(R.id.syllables);
        syllables.setOnClickListener(this);
        syllables.setChecked(false);
        sw = (Switch) view.findViewById(R.id.switch_language);


        /*
        * Ermittlung der momentanen Sprache, und Steuerung anhand eines Switches
        *
        * Bei Toggle des Switches wird die Activity mit neuer Sprache neugestartet
        * */
        currLang = getResources().getConfiguration().locale.getLanguage();
        if(currLang.equals("eng")){
            sw.setChecked(false);
        }
        if(currLang.equals("de")){
            sw.setChecked(true);
        }
        sw.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    langChange("de");
                    sw.setChecked(true);
                } else{
                    langChange("eng");
                    sw.setChecked(false);
                }
                getActivity().startActivity(getActivity().getIntent());
            }
        }));
    }

    //Holfsmethode um Sprache zu wechseln
    private void langChange(String lang) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lang);
        res.updateConfiguration(conf, dm);
    }



    //Auswahl Woerter oder Silben und Zugang zum Highscore
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toLanguage:
                navController.navigate(R.id.action_sylOrWordFragment_to_languageFragment);
                break;
            case R.id.highscore:
                navController.navigate(R.id.action_sylOrWordFragment_to_showHighscore);
                break;
            case R.id.words:
                next.setEnabled(true);
                words.setChecked(true);
                syllables.setChecked(false);
                MainActivity.isSyllable = false;
                MainActivity.isWord = true;
                break;
            case R.id.syllables:
                next.setEnabled(true);
                words.setChecked(false);
                syllables.setChecked(true);
                MainActivity.isSyllable = true;
                MainActivity.isWord = false;
                break;
        }
    }
}

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jalat.R;
import com.example.jalat.activity.MainActivity;
import com.example.jalat.helpclasses.GameHandler;



/**
 *
 * @author Jan Weimer
 *
 * */
public class InputFragment extends Fragment implements View.OnClickListener{

    Button exit;
    Button confirm;
    EditText input;
    NavController navController;
    String[] question;
    String[] answer;
    GameHandler gameHandler;
    TextView task;
    TextView points;
    Toast toast;
    CharSequence right = "=)";
    CharSequence wrong = "=(";
    int duration = Toast.LENGTH_SHORT;


    public InputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);



        //Silben
        //Groeße der Arrays setzen
        if(MainActivity.isSyllable){
            answer = new String[MainActivity.getSyllablesList().size()];
            question = new String[MainActivity.getSyllablesList().size()];
            //Japanische Zeichen
            if(MainActivity.isJapanese){
                for(int i = 0; i < answer.length; i++){
                    if(MainActivity.isHiragana){
                        question[i] = MainActivity.getSyllablesList().get(i).getHiragana();
                        answer[i] = MainActivity.getSyllablesList().get(i).getRomaji();
                    }
                    if(MainActivity.isKatakana){
                        question[i] = MainActivity.getSyllablesList().get(i).getKatakana();
                        answer[i] = MainActivity.getSyllablesList().get(i).getRomaji();
                    }

                }
            }
            //Romaji
            else {
                for (int i = 0; i < answer.length; i++) {
                    if (MainActivity.isHiragana) {
                        answer[i] = MainActivity.getSyllablesList().get(i).getHiragana();
                        question[i] = MainActivity.getSyllablesList().get(i).getRomaji();
                    }
                    if (MainActivity.isKatakana) {
                        answer[i] = MainActivity.getSyllablesList().get(i).getKatakana();
                        question[i] = MainActivity.getSyllablesList().get(i).getRomaji();
                    }

                }
            }
        }

        //Woerter
        if(MainActivity.isWord){
            if(MainActivity.isKanji) {
                answer = new String[MainActivity.getKanji().size()];
                question = new String[MainActivity.getKanji().size()];
                if(MainActivity.isJapanese){
                    for(int i = 0; i < answer.length; i++){
                        question[i] = MainActivity.getKanji().get(i).getKanji();
                        answer[i] = MainActivity.getKanji().get(i).getRomaji();
                    }

                } else {
                    for(int i = 0; i < answer.length; i++){
                        answer[i] = MainActivity.getKanji().get(i).getKanji();
                        question[i] = MainActivity.getKanji().get(i).getRomaji();
                    }
                }
            } else {
                //Arrays fuellen
                answer = new String[MainActivity.getHiraKata().size()];
                question = new String[MainActivity.getHiraKata().size()];
                if(MainActivity.isJapanese){
                    for(int i = 0; i < answer.length; i++){
                        if(MainActivity.isHiragana){
                            question[i] = MainActivity.getHiraKata().get(i).getHiragana();
                            answer[i] = MainActivity.getHiraKata().get(i).getRomaji();
                        }
                        if(MainActivity.isKatakana){
                            question[i] = MainActivity.getHiraKata().get(i).getKatakana();
                            answer[i] = MainActivity.getHiraKata().get(i).getRomaji();
                        }

                    }
                } else {
                    for(int i = 0; i < answer.length; i++){
                        if(MainActivity.isHiragana){
                            answer[i] = MainActivity.getHiraKata().get(i).getHiragana();
                            question[i] = MainActivity.getHiraKata().get(i).getRomaji();
                        }
                        if(MainActivity.isKatakana){
                            answer[i] = MainActivity.getHiraKata().get(i).getKatakana();
                            question[i] = MainActivity.getHiraKata().get(i).getRomaji();
                        }

                    }
                }
            }

        }

        gameHandler = new GameHandler(question,answer,answer.length);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        exit = (Button) view.findViewById(R.id.quit_input);
        exit.setOnClickListener(this);
        confirm = (Button) view.findViewById(R.id.confirm_answer);
        confirm.setOnClickListener(this);
        input = (EditText) view.findViewById(R.id.answer);
        task = (TextView) view.findViewById(R.id.task2);
        points = (TextView) view.findViewById(R.id.points);
        task.setText(gameHandler.generateQuestion());
        points.setText("points: "+ MainActivity.getPoints());

        view.invalidate();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.quit_input:
                if(MainActivity.getPoints() == 0) {
                    navController.navigate(R.id.action_inputFragment_to_sylOrWordFragment);
                } else {
                    navController.navigate(R.id.action_inputFragment_to_insertFragment);
                }
                break;
            case R.id.confirm_answer:
                if(gameHandler.checkCorrectness(input.getText().toString())){
                    MainActivity.pointsIncrement();
                    toast = Toast.makeText(getContext(),right,duration);
                } else {
                    if(MainActivity.getPoints() != 0) {
                        MainActivity.pointsDecrement();
                    }
                    toast = Toast.makeText(getContext(),wrong,duration);
                }
                task.setText(gameHandler.generateQuestion());
                points.setText("points: "+ MainActivity.getPoints());
                toast.show();

                v.invalidate();
                break;
        }
    }
}

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
public class GameFragment extends Fragment implements View.OnClickListener{

    //Array von Buttons (wird als Parameter im Gamehandler übergeben)
    Button[] buttons = new Button[4];
    Button quit;
    NavController navController;
    TextView points;
    TextView task;
    GameHandler gameHandler;
    String[] question;
    String[] answer;
    Toast toast;
    CharSequence right = "=)";
    CharSequence wrong = "=(";
    int duration = Toast.LENGTH_SHORT;




    public GameFragment() {
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
                    //Hiragana
                    if(MainActivity.isHiragana){
                        question[i] = MainActivity.getSyllablesList().get(i).getHiragana();
                        answer[i] = MainActivity.getSyllablesList().get(i).getRomaji();
                    }
                    //Katakana
                    if(MainActivity.isKatakana){
                        question[i] = MainActivity.getSyllablesList().get(i).getKatakana();
                        answer[i] = MainActivity.getSyllablesList().get(i).getRomaji();
                    }

                }
            }
            //Romaji
            else {
                for (int i = 0; i < answer.length; i++) {
                    //Hiragana
                    if (MainActivity.isHiragana) {
                        answer[i] = MainActivity.getSyllablesList().get(i).getHiragana();
                        question[i] = MainActivity.getSyllablesList().get(i).getRomaji();
                    }
                    //Katakana
                    if (MainActivity.isKatakana) {
                        answer[i] = MainActivity.getSyllablesList().get(i).getKatakana();
                        question[i] = MainActivity.getSyllablesList().get(i).getRomaji();
                    }

                }
            }
        }

        //Woerter
        if(MainActivity.isWord){
            //Kanji
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
                        //Hiragana
                        if(MainActivity.isHiragana){
                            question[i] = MainActivity.getHiraKata().get(i).getHiragana();
                            answer[i] = MainActivity.getHiraKata().get(i).getRomaji();
                        }
                        //Katakana
                        if(MainActivity.isKatakana){
                            question[i] = MainActivity.getHiraKata().get(i).getKatakana();
                            answer[i] = MainActivity.getHiraKata().get(i).getRomaji();
                        }

                    }
                } else {
                    for(int i = 0; i < answer.length; i++){
                        //Hiragana
                        if(MainActivity.isHiragana){
                            answer[i] = MainActivity.getHiraKata().get(i).getHiragana();
                            question[i] = MainActivity.getHiraKata().get(i).getRomaji();
                        }
                        //Katakana
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
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        quit = (Button) view.findViewById(R.id.quit_game);
        quit.setOnClickListener(this);
        buttons[0] = (Button) view.findViewById(R.id.answer1);
        buttons[0].setOnClickListener(this);
        buttons[1] = (Button) view.findViewById(R.id.answer2);
        buttons[1].setOnClickListener(this);
        buttons[2] = (Button) view.findViewById(R.id.answer3);
        buttons[2].setOnClickListener(this);
        buttons[3] = (Button) view.findViewById(R.id.answer4);
        buttons[3].setOnClickListener(this);
        points = (TextView) view.findViewById(R.id.points);
        points.setText("points:"+MainActivity.getPoints());
        task = (TextView) view.findViewById(R.id.task);
        task.setText(gameHandler.generateQuestion());
        gameHandler.generateButtons(buttons);



        view.invalidate();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            //Navigation bei Punktestand ungleich 0 zum Dateneinsetzung in die DB
            case R.id.quit_game:
                if(MainActivity.getPoints() == 0) {
                    navController.navigate(R.id.action_gameFragment_to_sylOrWordFragment);
                } else {
                    navController.navigate(R.id.action_gameFragment_to_insertFragment);
                }
                break;
            case R.id.answer1:
                eventHandling(0,v);
                break;
            case R.id.answer2:
                eventHandling(1,v);
                break;
            case R.id.answer3:
                eventHandling(2,v);
                break;
            case R.id.answer4:
                eventHandling(3,v);
                break;

        }
    }

    /**
     *
     * @param i welcher Button
     * @param v die View in der wir uns befinden
     * */
    private void eventHandling(int i,View v){
        //bei richtiger Antwort Punktesteigerung
        if(gameHandler.checkCorrectness((String)buttons[i].getText())){
            MainActivity.pointsIncrement();
            toast = Toast.makeText(getContext(),right,duration);
        } else {
            //andererseits Senkung bei Puntkestand ungleich 0
            if(MainActivity.getPoints()!= 0){
                MainActivity.pointsDecrement();
            }
            toast = Toast.makeText(getContext(),wrong,duration);
        }
        //neue Aufgabe erstellt
        task.setText(gameHandler.generateQuestion());
        //Anordnung der Buttons
        gameHandler.generateButtons(buttons);
        //Aktualisierung der Punktzahl in der View
        points.setText("points:"+ MainActivity.getPoints());
        //Feedback durch Toast
        toast.show();

        v.invalidate();
    }
}

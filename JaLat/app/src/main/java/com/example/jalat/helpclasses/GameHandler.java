package com.example.jalat.helpclasses;

import android.widget.Button;

import java.util.Random;


/**
 *
 * @author Jan Weimer
 *
 * */
public class GameHandler {

    private String[][] syl_pack;
    private int size;
    private int current;
    private Random rand = new Random();


    /**
     * Konstruktor des GameHandlers
     * Array bestehend aus zwei Reihen: Fragen und Antworten jeweils
     *
     * @param question Fragen
     * @param answer Antworten
     * @param size Groeße der Reihen
     *
     * */
    public GameHandler(String[] question, String[] answer, int size){
        syl_pack = new String[2][size];
        syl_pack[0] = question;
        syl_pack[1] = answer;
        this.size = size;
    }

    /**
     * @param answer gegebene Antwort
     *
     * @return sind Antwort und Loesung identisch
     *
     * */
    public boolean checkCorrectness(String answer){
        return answer.equals((String)syl_pack[1][current]);
    }

    /**
     * @return generierte Frage (nicht die dieselbe hintereinander)
     * */
    public String generateQuestion(){
        int tmp;
        do{
            tmp = rand.nextInt(size);
        }while(tmp==current);
        current = tmp;
        return syl_pack[0][current];
    }

    /**
     * @param buttons Loesung an bestimmter Stelle generiert
     *                falsche Antwort zufaellig ausgesucht
     * */
    public void generateButtons(Button[] buttons){
        int buttonsize = buttons.length;
        int rightAnswer = rand.nextInt(buttonsize);
        int wrongAnswer[] = new int[3];
        int j = 0;
        buttons[rightAnswer].setText(syl_pack[1][current]);
        do{
            wrongAnswer[0] = rand.nextInt(size);
        }while(wrongAnswer[0] == current);
        do{
            wrongAnswer[1] = rand.nextInt(size);
        }while(wrongAnswer[1] == current || wrongAnswer[1] == wrongAnswer[0]);
        do{
            wrongAnswer[2] = rand.nextInt(size);
        }while(wrongAnswer[2] == current || wrongAnswer[2] == wrongAnswer[1] || wrongAnswer[2] == wrongAnswer[0]);
        for(int i = 0; i < buttonsize; i++){
            if(i == rightAnswer){
                continue;
            }
            buttons[i].setText(syl_pack[1][wrongAnswer[j]]);
            j++;
        }
    }

}

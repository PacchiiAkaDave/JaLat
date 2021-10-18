package com.example.jalat.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import com.example.jalat.R;
import com.example.jalat.db.highscore.HighscoreViewModel;
import com.example.jalat.db.highscore.User;
import com.example.jalat.db.syllables.Syllable;
import com.example.jalat.db.syllables.SyllablesViewModel;
import com.example.jalat.db.words.Word;
import com.example.jalat.db.words.WordViewModel;
import java.util.List;


/**
 *
 * @author Jan Weimer
 *
 * */
public class MainActivity extends AppCompatActivity {

    //Einstellungen fuer die Modi
    public static boolean isKatakana;
    public static boolean isKanji;
    public static boolean isHiragana;
    public static boolean isSyllable;
    public static boolean isWord;
    public static boolean isJapanese;

    //ViewModels der DBs und die Zwischenspeicherung in Listen
    private SyllablesViewModel syllablesViewModel;
    private static List<Syllable> syllables;
    private WordViewModel wordViewModel;
    private static List<Word> wordsKanji;
    private static List<Word> wordsHiraKata;
    private static int points; // Punktestand
    private static HighscoreViewModel highscoreViewModel;



    /**
     * Nutzung eines weiteren Threads um die DBs zu laden
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread t1 = new Thread(()-> {

                syllablesViewModel = new ViewModelProvider(this).get(SyllablesViewModel.class);
                syllables = syllablesViewModel.getAllSyllables();
                wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
                wordsKanji = wordViewModel.getWordsKanji();
                wordsHiraKata = wordViewModel.getWordsKataHira();
                highscoreViewModel = new ViewModelProvider(this).get(HighscoreViewModel.class);

         });
        t1.start();




    }


    //fuer Woerter gibt es mehr Punkte
    public static void pointsIncrement(){
        if(isWord){
            points+=200;
        } else {
            points += 100;
        }
    }

    //fuer falsche Antworten
    public static void pointsDecrement(){
        points -= 100;
    }

    //wird genutzt um den Punktestand anzuzeigen und diesen am Ende eines Spiels in die DB zu schreiben
    public static int getPoints(){
        return points;
    }

    /**wird benutzt in der Reset Methode
     *
     * @see com.example.jalat.fragments.SylOrWordFragment
     *
     */
    public static void setToZero(){
        points = 0;
    }

    /**wird genutzt um Spieler in die DB zu schreiben
     *
     * @see com.example.jalat.fragments.InsertFragment
     *
     * */
    public static void insert(String name, int score){
        highscoreViewModel.insert(new User(score,name));
    }

    /**
     * Highscore wird wiedergegeben aus der ViewModel
     *
     * @return Highscore als Liste
     * */
    public static List<User> getHighscore(){
        return highscoreViewModel.getAllUser();
    }

    /**
     * @return Liste für Silben
     * */
    public static List<Syllable> getSyllablesList(){
        return syllables;
    }

    /**
     * @return Liste für Woerter in Hiragana und Katakana
     * */
    public static List<Word> getHiraKata(){
        return wordsHiraKata;
    }

    /**
     * @return Liste für Woerter in Kanji
     * */
    public static List<Word> getKanji(){
        return wordsKanji;
    }


}

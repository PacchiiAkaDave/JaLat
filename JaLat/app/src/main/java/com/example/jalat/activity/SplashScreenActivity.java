package com.example.jalat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.jalat.R;
import com.example.jalat.db.highscore.HighscoreViewModel;
import com.example.jalat.db.syllables.Syllable;
import com.example.jalat.db.syllables.SyllablesViewModel;
import com.example.jalat.db.words.Word;
import com.example.jalat.db.words.WordViewModel;


/**
 *
 * @author Jan Weimer
 *
 * */
public class SplashScreenActivity extends AppCompatActivity {

    //Dauer des Splashscreens
    private final int SPLASH_SCREEN_DURATION = 2000;
    //Zugriff auf die DBs
    private SyllablesViewModel syllablesViewModel;
    private WordViewModel wordViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Nach Ablauf der Zeit wird Sequenzabfolge innerhalb gestartet.
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Erstellung eines Intents.
                Diese Activity wird beendet und man gelangt zur MainActivity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_SCREEN_DURATION);

        //Instanziierung der Datenbanken in einem neuen Thread.
        //Bei erstmaligem Start werden Daten eingefügt.
        Thread t1 = new Thread(()-> {

            syllablesViewModel = new ViewModelProvider(this).get(SyllablesViewModel.class);
            if(syllablesViewModel.getAllSyllables().size() == 0) {
                syllablesViewModel.insert(new Syllable("a", "ア", "あ"));
                syllablesViewModel.insert(new Syllable("shi", "シ", "し"));
                syllablesViewModel.insert(new Syllable("tsu", "ツ", "つ"));
                syllablesViewModel.insert(new Syllable("ne", "ネ", "ね"));
                syllablesViewModel.insert(new Syllable("yo", "ヨ", "よ"));
                syllablesViewModel.insert(new Syllable("mi", "ミ", "み"));
                syllablesViewModel.insert(new Syllable("ha", "ハ", "は"));
            }

            wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
            if(wordViewModel.getAllWords().size() == 0){
                //Kanji
                wordViewModel.insert(new Word("soku","足"));
                wordViewModel.insert(new Word("jin","人"));
                wordViewModel.insert(new Word("nin","花"));
                wordViewModel.insert(new Word("den", "田"));
                wordViewModel.insert(new Word("hon","本"));
                wordViewModel.insert(new Word("sei","正"));
                wordViewModel.insert(new Word("chiku","竹"));
                //Hiragana und Katakana
                wordViewModel.insert(new Word("Kon'nichiwa","こん'にちわ","コン'ニチワ"));
                wordViewModel.insert(new Word("Inu","いぬ","イヌ"));
                wordViewModel.insert(new Word("Ki","き","キ"));
                wordViewModel.insert(new Word("Banana","ばなな","バナナ"));
                wordViewModel.insert(new Word("Chikin","ちきん","チキン"));
                wordViewModel.insert(new Word("Fujin","ふじん","フジン"));
                wordViewModel.insert(new Word("Mizu","みず","ミズ"));
            }

        });
        t1.start();

    }
}

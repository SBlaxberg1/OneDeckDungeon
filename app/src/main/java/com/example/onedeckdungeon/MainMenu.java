package com.example.onedeckdungeon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_layout);

        setupHighScoreFile();
    }

    private void setupHighScoreFile()
    {
        if (!fileExist("highscore.txt")) {
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(new
                        File(getFilesDir()+File.separator+"highscore.txt")));
                bufferedWriter.write( "0");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    public void startNewGame(View view) {
        Intent newGame = new Intent(this, PlayGame.class);
        startActivity(newGame);
    }

    public void goToRules(View view)
    {
        Intent rulesPage = new Intent(this, RulesPage.class);
        startActivity(rulesPage);
    }

    public void goToHighScore(View view)
    {
        Intent highScorePage = new Intent(this, HighScore.class);
        startActivity(highScorePage);
    }

    public void goToSettings(View view)
    {
        Intent settingsPage = new Intent(this, Settings.class);
        startActivity(settingsPage);
    }

}
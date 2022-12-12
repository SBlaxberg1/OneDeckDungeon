package com.example.onedeckdungeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HighScore extends AppCompatActivity {

    private TextView highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_layout);
        highScore = (TextView) findViewById(R.id.highScoreText);
        highScore.setText(getHighScore());
    }

    private String getHighScore() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new
                    File(getFilesDir()+File.separator+"highscore.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "0";
        }
        String read = "";
        StringBuilder builder = new StringBuilder("");

        while(true){
            try {
                if (!((read = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
                return "0";
            }
            builder.append(read);
        }
        Log.d("Output", builder.toString());
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (String.valueOf(builder) != "")
        {
            return String.valueOf(builder);
        }
        return "0";
    }

}
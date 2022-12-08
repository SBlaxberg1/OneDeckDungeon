package com.example.onedeckdungeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    private RadioGroup difficultyChoice;
    private static int difficulty = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        difficultyChoice = (RadioGroup) findViewById(R.id.settingsChoice);
        difficultyChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.easyButton)
                {
                    difficulty = 1;
                } else if (checkedId == R.id.medButton)
                {
                    difficulty = 2;
                } else if (checkedId == R.id.hardButton)
                {
                    difficulty = 3;
                }
            }
        });
    }

    public static int getDifficulty()
    {
        return difficulty;
    }

}
package com.example.onedeckdungeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    private RadioGroup difficultyChoice;
    private RadioButton easyButton;
    private RadioButton medButton;
    private RadioButton hardButton;
    private static int difficulty = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        easyButton = (RadioButton) findViewById(R.id.easyButton);
        medButton = (RadioButton) findViewById(R.id.medButton);
        hardButton = (RadioButton) findViewById(R.id.hardButton);

        if (difficulty == 1)
        {
            easyButton.setChecked(true);
        } else if (difficulty == 2)
        {
            medButton.setChecked(true);
        } else if (difficulty == 3)
        {
            hardButton.setChecked(true);
        }

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
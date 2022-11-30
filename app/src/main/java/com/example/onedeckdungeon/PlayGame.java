package com.example.onedeckdungeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PlayGame extends AppCompatActivity {

    private GameModel gameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playgame_layout);

        gameModel = new GameModel();
    }
}
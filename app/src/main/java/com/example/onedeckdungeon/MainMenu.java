package com.example.onedeckdungeon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    private GameModel gameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_layout);

    }

    public void startNewGame(View view) {
        Intent newGame = new Intent(this, PlayGame.class);
        startActivity(newGame);
    }
}
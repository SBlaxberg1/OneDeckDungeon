package com.example.onedeckdungeon;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PlayGame extends AppCompatActivity {

    private GameModel gameModel;
    private ImageView cardOne;
    private ImageView cardTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playgame_layout);

        cardOne = (ImageView) findViewById(R.id.cardView1);
        cardTwo = (ImageView) findViewById(R.id.cardView2);

        gameModel = new GameModel();
        startGame();
    }

    private void startGame()
    {
        cardOne.setImageResource(R.drawable.card_back);
        cardTwo.setImageResource(R.drawable.card_back);
    }

    public void updateCardImages()
    {
        cardOne.setImageResource(getCardImage(gameModel.topCard()));
        cardTwo.setImageResource(getCardImage(gameModel.secondCard()));
    }

    public void explore(View view)
    {
        gameModel.explore();
        updateCardImages();
    }

    private int getCardImage(Card c)
    {
        if (c.faceUp)
        {
            if (c.value == -1)
            {
                return R.drawable.woodendoor;
            } else if (c.value == 0) {
                return R.drawable.goblin;
            } else if (c.value == 1) {
                return R.drawable.holygrail;
            } else if (c.value == 2) {
                return R.drawable.loot2;
            } else if (c.value == 2) {
                return R.drawable.loot2;
            } else if (c.value == 3) {
                return R.drawable.loot3;
            } else if (c.value == 4) {
                return R.drawable.loot4;
            } else if (c.value == 5) {
                return R.drawable.loot5;
            } else if (c.value == 6) {
                return R.drawable.loot6;
            } else if (c.value == 7) {
                return R.drawable.loot7;
            } else if (c.value == 8) {
                return R.drawable.loot8;
            } else if (c.value == 9) {
                return R.drawable.loot9;
            } else if (c.value == 10) {
                return R.drawable.loot10;
            } else
                return R.drawable.card_default;
        } else
        {
            return R.drawable.card_back;
        }
    }
}
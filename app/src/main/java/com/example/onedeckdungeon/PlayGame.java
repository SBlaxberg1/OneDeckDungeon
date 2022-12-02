package com.example.onedeckdungeon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PlayGame extends AppCompatActivity {

    private GameModel gameModel;
    private TextView lootAmount;
    private TextView relicCount;
    private DeckRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playgame_layout);

        lootAmount = (TextView) findViewById(R.id.loot_amount);
        relicCount = (TextView) findViewById(R.id.relics_amount);

        gameModel = new GameModel();

        // data to populate the RecyclerView with
        List<Card> viewCards = gameModel.getDungeon().getDeck();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvCards);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(PlayGame.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new DeckRecycleViewAdapter(this, viewCards);
        recyclerView.setAdapter(adapter);
    }

    public void explore(View view)
    {
        gameModel.explore();
        adapter.notifyItemChanged(0);
        adapter.notifyItemChanged(1);
    }

    public void traverseFirst(View view)
    {
        if (gameModel.topCard().getFaceUp() && gameModel.topCard().getValue() > 1) {
            gameModel.traverse(1);
            displayCollectToast(1);
            notifyAllCards();
            lootAmount.setText(String.valueOf(gameModel.getLootCount()));
            relicCount.setText(String.valueOf(gameModel.getRelicCount()));

            if (gameModel.getGameStatus() == 1)
            {
                //win();
            } else if (gameModel.getGameStatus() == 2)
            {
                //lose();
            }
        }
    }

    public void traverseSecond(View view)
    {
        if (gameModel.secondCard().getFaceUp() && gameModel.secondCard().getValue() > 1)
        {
            gameModel.traverse(2);
            displayCollectToast(2);
            notifyAllCards();
            lootAmount.setText(String.valueOf(gameModel.getLootCount()));
            relicCount.setText(String.valueOf(gameModel.getRelicCount()));

            if (gameModel.getGameStatus() == 1)
            {
                //win();
            } else if (gameModel.getGameStatus() == 2)
            {
                //lose();
            }
        }
    }

    public void displayCollectToast(int choice)
    {
        Card traveled = gameModel.getLastTraveled();
        Card landedOn = gameModel.getLandedOn();

        int travelAmount = traveled.getValue();

        if (!landedOn.getFaceUp())
        {
            Toast.makeText(this, "You traversed " + travelAmount + " rooms through the dungeon.", Toast.LENGTH_SHORT).show();
        } else
        {
            if (landedOn.getValue() == 1)
                Toast.makeText(this, "You traversed " + travelAmount + " rooms through the dungeon and collected a Relic!", Toast.LENGTH_LONG).show();
            else if (landedOn.getValue() == 0)
                Toast.makeText(this, "You traversed " + travelAmount + " rooms through the dungeon but encountered a monster! You lose!", Toast.LENGTH_LONG).show();
            else if (landedOn.getValue() == -1)
            {
                if (gameModel.getRelicCount() < 4)
                    Toast.makeText(this, "You traversed " + travelAmount + " rooms through the dungeon and see the exit, but don't have all of the relics yet.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "You traversed " + travelAmount + " rooms through the dungeon and escaped through the exit! You win!", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "You traversed " + travelAmount + " rooms through the dungeon and collected some loot!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void notifyAllCards()
    {
        for (int i = 0; i < gameModel.getDungeon().getDeck().size(); i++)
        {
            adapter.notifyItemChanged(i);
        }
    }

}
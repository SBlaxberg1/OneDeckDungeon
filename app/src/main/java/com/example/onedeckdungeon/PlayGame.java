package com.example.onedeckdungeon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PlayGame extends AppCompatActivity {

    private GameModel gameModel;
    private TextView lootAmount;
    private TextView relicCount;
    private DeckRecycleViewAdapter adapter;
    private ImageView mem1;
    private ImageView mem2;
    private ImageView mem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playgame_layout);

        lootAmount = (TextView) findViewById(R.id.loot_amount);
        relicCount = (TextView) findViewById(R.id.relics_amount);
        mem1 = (ImageView) findViewById(R.id.memory_slot1);
        mem2 = (ImageView) findViewById(R.id.memory_slot2);
        mem3 = (ImageView) findViewById(R.id.memory_slot3);

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
        if (gameModel.getGameStatus() == 2)
        {
            lose();
        }
    }

    public void traverse(int choice)
    {
        gameModel.traverse(choice);
        displayCollectToast();
        notifyAllCards();
        lootAmount.setText(String.valueOf(gameModel.getLootCount()));
        relicCount.setText(String.valueOf(gameModel.getRelicCount()));

        if (gameModel.getGameStatus() == 1)
        {
            win();
        } else if (gameModel.getGameStatus() == 2)
        {
            lose();
        }
    }

    public void traverseFirst(View view)
    {
        if (gameModel.topCard().getFaceUp() && gameModel.topCard().getValue() > 1) {
            traverse(1);
        }
    }

    public void traverseSecond(View view)
    {
        if (gameModel.secondCard().getFaceUp() && gameModel.secondCard().getValue() > 1)
        {
            traverse(2);
        }
    }

    public void memorize(View view)
    {
        gameModel.memorize();
        updateMemories();
        notifyAllCards();
    }

    private void retrace(int choice)
    {
        gameModel.retrace(choice);
        updateMemories();
        notifyAllCards();
    }

    public void retrace1(View view)
    {
        retrace(0);
    }

    public void retrace2(View view)
    {
        retrace(1);
    }

    public void retrace3(View view)
    {
        retrace(2);
    }

    private void updateMemories()
    {
        if (gameModel.getMemories().size() > 0)
            mem1.setImageResource(gameModel.getMemories().get(0).getImage());
        else
            mem1.setImageResource(R.drawable.memory_empty);

        if (gameModel.getMemories().size() > 1)
            mem2.setImageResource(gameModel.getMemories().get(1).getImage());
        else
            mem2.setImageResource(R.drawable.memory_empty);

        if (gameModel.getMemories().size() > 2)
            mem3.setImageResource(gameModel.getMemories().get(2).getImage());
        else
            mem3.setImageResource(R.drawable.memory_empty);
    }

    public void displayCollectToast()
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
            {}//Toast.makeText(this, "You traversed " + travelAmount + " rooms through the dungeon but encountered a monster! You lose!", Toast.LENGTH_LONG).show();
            else if (landedOn.getValue() == -1)
            {
                if (gameModel.getRelicCount() < 4)
                    Toast.makeText(this, "You traversed " + travelAmount + " rooms through the dungeon and see the exit, but don't have all of the relics yet.", Toast.LENGTH_LONG).show();
                else
                {}//Toast.makeText(this, "You traversed " + travelAmount + " rooms through the dungeon and escaped through the exit! You win!", Toast.LENGTH_LONG).show();
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

    public void goToRules(View view)
    {
        Intent rulesPage = new Intent(this, RulesPage.class);
        startActivity(rulesPage);
    }

    private void win()
    {
        int newScore = gameModel.getScore();
        if (newScore > Integer.parseInt(getHighScore()))
            writeHighScore(newScore);
        showWinPopup();
    }

    private void lose()
    {
        showLosePopup();
    }

    public void showWinPopup() {

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.winpopup_layout, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        LinearLayout view = new LinearLayout(this);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                finish();
                return true;
            }
        });
    }

    public void showLosePopup() {

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.losepopup_layout, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        LinearLayout view = new LinearLayout(this);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                finish();
                return true;
            }
        });
    }

    private void writeHighScore(int highScore) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new
                    File(getFilesDir()+File.separator+"highscore.txt")));
            bufferedWriter.write(highScore + "");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getHighScore() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new
                    File(getFilesDir()+File.separator+"highscore.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String read = "";
        StringBuilder builder = new StringBuilder("");

        while(true){
            try {
                if (!((read = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            builder.append(read);
        }
        Log.d("Output", builder.toString());
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(builder);
    }

}
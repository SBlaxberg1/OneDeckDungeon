package com.example.onedeckdungeon;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class GameModel {

    private Deck dungeon;
    private List<Card> memories;
    private int lootCount;
    private int relicCount;
    private int highScore;
    private int gameStatus; // 0=playing, 1=won, 2=lost
    private Card lastTraveled;
    private Card landedOn;

    public GameModel()
    {
        dungeon = new Deck();
        lootCount = 0;
        relicCount = 0;
        highScore = 0;
        gameStatus = 0;
        lastTraveled = dungeon.topCard();
    }

    public void explore(){
        Card top = dungeon.topCard();
        Card second = dungeon.secondCard();
        if (top.getFaceUp() == false){
            top.setFaceUp(true);
            second.setFaceUp(true);
        }
    }

    public void memorize(){
        Card top = dungeon.topCard();
        if (memories.size() < 3) {
            if (top.getValue() >= 2 && top.getValue() <= 10) {
                memories.add(top);
                dungeon.removeTop();
            }
        }
    }

    public void retrace(int choice){
        dungeon.addToTop(memories.get(choice));
        memories.remove(choice);
    }

    public void traverse(int choice){
        Card top = dungeon.topCard();
        Card second = dungeon.secondCard();
        int traverseValue = 0;
        if (choice == 1)
        {
            traverseValue = top.getValue();
            lastTraveled = top;
        }
        else if (choice == 2)
        {
            traverseValue = second.getValue();
            lastTraveled = second;
        }

        for (int i = 0; i < traverseValue; i++)
        {
            top = dungeon.topCard();
            dungeon.addToBottom(top);
            dungeon.removeTop();
        }

        landedOn = dungeon.topCard();

        collect();

        // TODO - REMOVE, BUGTESTING ONLY
        for (int i = 0; i < dungeon.getDeck().size(); i++) {
            Log.i("gameModel", dungeon.getDeck().get(i).value + ", " + dungeon.getDeck().get(i).faceUp);
        }

    }

    public void collect() {
        Card top = dungeon.topCard();
        if (top.getFaceUp()) {
            if (top.getValue() == 1) {
                relicCount++;
                dungeon.removeTop();
            } else if (top.getValue() == 0) {
                lose();
            } else if (top.getValue() == -1) {
                if (relicCount == 4)
                    win();
            } else {
                lootCount++;
                dungeon.removeTop();
            }
        }
    }

    public void win() {
        if (lootCount > highScore){
            highScore = (lootCount * 3);
        }
        gameStatus = 1;
    }

    public void lose(){
        gameStatus = 2;
    }

    public Card topCard(){
        return dungeon.topCard();
    }

    public Card secondCard(){
        return dungeon.secondCard();
    }

    public int getLootCount() {
        return lootCount;
    }

    public int getRelicCount() {
        return relicCount;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public Card getLastTraveled() { return lastTraveled; }

    public Card getLandedOn() { return landedOn; }
}

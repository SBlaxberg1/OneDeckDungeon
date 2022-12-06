package com.example.onedeckdungeon;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private Deck dungeon;
    private List<Card> memories;
    private int lootCount;
    private int relicCount;
    private int score;
    private int gameStatus; // 0=playing, 1=won, 2=lost
    private Card lastTraveled;
    private Card landedOn;

    public GameModel()
    {
        dungeon = new Deck();
        memories = new ArrayList<>();
        lootCount = 0;
        relicCount = 0;
        score = 0;
        gameStatus = 0;
        lastTraveled = dungeon.topCard();
    }

    public void explore(){
        Card top = dungeon.topCard();
        boolean traversable;
        if (!top.getFaceUp()){
            top.setFaceUp(true);
            Card second = dungeon.secondCard();
            second.setFaceUp(true);
        }

        traversable = canTraverse();
        if (!traversable){
            lose();
        }
    }

    public void memorize(){
        Card top = dungeon.topCard();
        if (top.getFaceUp()) {
            if (memories.size() < 3) {
                if (top.getValue() >= 2 && top.getValue() <= 10) {
                    memories.add(top);
                    dungeon.removeTop();
                }
            }
        }
    }

    public void retrace(int choice){
        if (memories.size() > choice) {
            dungeon.addToTop(memories.get(choice));
            memories.remove(choice);
        }
    }

    public void traverse(int choice){
        Card top = dungeon.topCard();
        Card second = dungeon.secondCard();
        boolean traversable;
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

        traversable = canTraverse();
        if (!traversable){
            lose();
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

    public boolean canTraverse(){
        Card top = dungeon.topCard();
        Card second = dungeon.secondCard();

        if (top.getFaceUp() && second.getFaceUp()){
            if ((top.getValue() < 2 && second.getValue() < 2) && (memories.size() == 0)){
                return false;
            }
        }
        if (top.getFaceUp() && !second.getFaceUp()){
            if (top.getValue() < 2 && (memories.size() == 0)){
                return false;
            }
        }
        return true;
    }

    public void win() {
        score = (lootCount * 4);
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

    public Deck getDungeon() {
        return dungeon;
    }

    public List<Card> getMemories() {
        return memories;
    }

    public int getLootCount() {
        return lootCount;
    }

    public int getRelicCount() {
        return relicCount;
    }

    public int getScore() {
        return score;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public Card getLastTraveled() { return lastTraveled; }

    public Card getLandedOn() { return landedOn; }
}

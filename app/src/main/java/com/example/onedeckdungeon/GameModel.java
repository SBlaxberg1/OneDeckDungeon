package com.example.onedeckdungeon;

import java.util.List;

public class GameModel {

    private Deck dungeon;
    private List<Card> memories;
    int lootCount = 0;
    int relicCount = 0;

    public void explore(){
        Card top = dungeon.topCard();
        Card second = dungeon.secondCard();
        if (top.getFaceUp() == false){
            top.setFaceUp(true);
            second.setFaceUp(false);
        }
    }

    public void memorize(){
        Card top = dungeon.topCard();
        if (top.getValue() >= 2 && top.getValue() <= 10){
           memories.add(top);
           dungeon.removeTop();
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
            traverseValue = top.getValue();
        else if (choice == 2)
            traverseValue = second.getValue();

        for (int i = 0; i < traverseValue; i++){
            dungeon.addToBottom(top);
            dungeon.removeTop();
        }
        collect();
    }

    public void collect(){
        Card top = dungeon.topCard();
        if (top.getValue() == 1)
            relicCount++;
        else if (top.getValue() == 0)
            lose();
        else if (top.getValue() == -1){
            if (relicCount == 4)
                win();
        }
        else{
            lootCount++;
            dungeon.removeTop();
        }

    }

    public void win(){

    }

    public void lose(){

    }

}

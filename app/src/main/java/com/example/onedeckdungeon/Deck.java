package com.example.onedeckdungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck extends Card{
    private List<Card> deck;

    public Deck(){
        initCards();
        shuffleDeck();
    }

    public void initCards(){
        deck = new ArrayList<Card>();
        Card tempCard;
        int placeHolder = 1;
        int count = 0;

        for (int i = 0; i < 54; i++){
            tempCard = new Card();
            if (i >= 0 && i <= 1) {
                tempCard.value = -1;
                tempCard.faceUp = false;
                deck.add(tempCard);
            } else if (i >= 2 && i <= 13){
                tempCard.value = 0;
                tempCard.faceUp = false;
                deck.add(tempCard);
            } else if (i >= 14 && i <= 53){
                tempCard.value = placeHolder;
                tempCard.faceUp = false;
                deck.add(tempCard);
                count++;
                if (count == 4){
                    placeHolder++;
                    count = 0;
                }
            }
        }



    }

    public void shuffleDeck(){
        Collections.shuffle(deck);

        for (int i = 0; i < 54; i++) {
            System.out.print(deck.get(i).value + " ");
        }
    }
}

package com.example.onedeckdungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
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
        int imagePlaceholder = 0;

        for (int i = 0; i < 54; i++){
            tempCard = new Card();
            if (i >= 0 && i <= 1) {
                tempCard.setValue(-1);
                tempCard.setFaceUp(false);
                tempCard.setImage(R.drawable.woodendoor);
                deck.add(tempCard);
            } else if (i >= 2 && i <= 13){
                tempCard.setValue(0);
                tempCard.setFaceUp(false);
                tempCard.setImage(R.drawable.goblin);
                deck.add(tempCard);
            } else if (i >= 14 && i <= 53){
                tempCard.setValue(placeHolder);
                tempCard.setFaceUp(false);
                imagePlaceholder = getCardImagePlaceholder(placeHolder);
                tempCard.setImage(imagePlaceholder);
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
        do {
            if (topCard().getValue() < 2 && secondCard().getValue() < 2)
                Collections.shuffle(deck);
        }while (topCard().getValue() < 2 && secondCard().getValue() < 2);
    }

    private int getCardImagePlaceholder(int i) {
        if (i == 1) {
            return R.drawable.holygrail;
        } else if (i == 2) {
            return R.drawable.loot2;
        } else if (i == 3) {
            return R.drawable.loot3;
        } else if (i == 4) {
            return R.drawable.loot4;
        } else if (i == 5) {
            return R.drawable.loot5;
        } else if (i == 6) {
            return R.drawable.loot6;
        } else if (i == 7) {
            return R.drawable.loot7;
        } else if (i == 8) {
            return R.drawable.loot8;
        } else if (i == 9) {
            return R.drawable.loot9;
        } else
            return R.drawable.loot10;
    }

    public Card topCard(){
        return deck.get(0);
    }

    public Card secondCard(){
        return deck.get(1);
    }

    public void removeTop(){
        deck.remove(0);
    }

    public void addToTop(Card c){
        deck.add(0, c);
    }

    public void addToBottom(Card c){
        deck.add(c);
    }

    public List<Card> getDeck() { return deck; }
}
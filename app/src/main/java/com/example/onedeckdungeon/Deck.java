package com.example.onedeckdungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public final int MAX_CARDS_HARD = 53;
    public final int MAX_CARDS_MED = 51;
    public final int MAX_CARDS_EASY = 48;
    public final int MONSTER_COUNT_HARD = 12;
    public final int MONSTER_COUNT_MED = 10;
    public final int MONSTER_COUNT_EASY = 8;
    public final int RELIC_COUNT_HARD = 4;
    public final int RELIC_COUNT_MED = 3;
    public final int RELIC_COUNT_EASY = 2;
    public final int EXIT_COUNT_MEDEASY = 2;
    public final int EXIT_COUNT_HARD = 1;
    private List<Card> deck;

    public Deck(int difficulty){
        initCards(difficulty);
        shuffleDeck();
    }

    public void initCards(int difficulty){
        deck = new ArrayList<Card>();
        Card tempCard;
        int placeHolder = 2;
        int count = 0;
        int imagePlaceholder = 0;
        int maxCards = 0, numMonsters = 0, numRelics = 0, numExits = 0;
        if (difficulty == 1){
            maxCards = MAX_CARDS_EASY;
            numMonsters = MONSTER_COUNT_EASY;
            numRelics = RELIC_COUNT_EASY;
            numExits = EXIT_COUNT_MEDEASY;
        } else if (difficulty == 2){
            maxCards = MAX_CARDS_MED;
            numMonsters = MONSTER_COUNT_MED;
            numRelics = RELIC_COUNT_MED;
            numExits = EXIT_COUNT_MEDEASY;
        } else if (difficulty == 3){
            maxCards = MAX_CARDS_HARD;
            numMonsters = MONSTER_COUNT_HARD;
            numRelics = RELIC_COUNT_HARD;
            numExits = EXIT_COUNT_HARD;
        }
        int normalCards = maxCards - numMonsters - numRelics - numExits;

        for (int i = 0; i < numExits; i++) {
            tempCard = new Card();
            tempCard.setValue(-1);
            tempCard.setFaceUp(false);
            tempCard.setImage(R.drawable.woodendoor);
            deck.add(tempCard);
        }
        for (int i = 0; i < numMonsters; i++) {
            tempCard = new Card();
            tempCard.setValue(0);
            tempCard.setFaceUp(false);
            tempCard.setImage(R.drawable.goblin);
            deck.add(tempCard);
        }
        for (int i = 0; i < numRelics; i++) {
            tempCard = new Card();
            tempCard.setValue(1);
            tempCard.setFaceUp(false);
            tempCard.setImage(R.drawable.holygrail);
            deck.add(tempCard);
        }
        for (int i = 0; i < normalCards; i++) {
            tempCard = new Card();
            tempCard.setValue(placeHolder);
            tempCard.setFaceUp(false);
            imagePlaceholder = getCardImagePlaceholder(placeHolder);
            tempCard.setImage(imagePlaceholder);
            deck.add(tempCard);
            count++;
            if (count == 4) {
                placeHolder++;
                count = 0;
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
        if (i == 2) {
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

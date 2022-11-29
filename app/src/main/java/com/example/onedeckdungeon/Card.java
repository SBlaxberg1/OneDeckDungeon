package com.example.onedeckdungeon;

public class Card {
    protected int value;
    protected boolean faceUp;

    public void setValue(int v){
        value = v;
    }

    public int getValue(){
        return value;
    }

    public void setFaceUp(boolean b){
        faceUp = b;
    }

    public boolean getFaceUp(){
        return faceUp;
    }
}

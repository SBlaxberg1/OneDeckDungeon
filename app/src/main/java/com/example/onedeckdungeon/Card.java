package com.example.onedeckdungeon;

public class Card {
    private int value;
    private boolean faceUp;
    private int image;

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

    public void setImage(int i) { image = i; }

    public int getImage() { return image; }
}

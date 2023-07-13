package com.mygdx.game;

public class Square {
    int width,height;
    int x,y;
    int i,j;
    boolean isEmpty;

    public Square(int i,int j,int x,int y) {
        this.x = x;
        this.y = y;
        this.j = j;
        this.x = x;
        isEmpty = false;
    }
    public Square(int x,int y){
        this.x = x;
        this.y = y;
        i = (x-(MyGdxGame.SCR_WIDTH-1920)/2)/60;
        j = (y-(MyGdxGame.SCR_HEIGHT-1080)/2)/60;
    }

    public Square(int x,int y,boolean isEmpty){
        this.x = x;
        this.y = y;
        this.isEmpty = isEmpty;
        i = (x-(MyGdxGame.SCR_WIDTH-1920)/2)/60;
        j = (y-(MyGdxGame.SCR_HEIGHT-1080)/2)/60;
    }

    @Override
    public String toString() {

        return x+"/"+y+"/"+"/"+i+"/"+j;
    }

    public String isEmptyToString(){
        return String.valueOf(isEmpty);
    }
}

package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;

public class NewHouse {
    float width, height;
    float x = SCR_WIDTH / 10, y = SCR_HEIGHT / 8;
    int cost;
    Texture img;
    Button buttonBuyHouse, buttonInfHouse;

//    boolean bought = false;
    NewHouse(int delta, Texture img, int cost){
        this.img = img;
        this.cost = cost;
        width = height = 250;
        x = SCR_WIDTH / 10 + delta * 350;
        y = SCR_HEIGHT - 300;

    }


}

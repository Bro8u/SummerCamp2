package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;

public class NewHouse {
    float width, height;
    float x = SCR_WIDTH / 10, y = SCR_HEIGHT / 8;
    Texture img;
    Button buttonBuyHouse;
    NewHouse(int delta, Texture img){
        this.img = img;
        width = height = 300;
        x = SCR_WIDTH / 10 + delta * 350;
        y = SCR_HEIGHT / 8;
    }


}

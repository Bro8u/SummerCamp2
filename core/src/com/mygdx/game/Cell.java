package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;

public class Cell {
    int x, y, width, height;
    float advert = 0.5F;
    int averageCheck = 30, emploee = 5;
    int type, quantityOfCafe;

    Texture img = new Texture("build0.png");

    Cell(int x, int y, int width, int height, int type){
      this.x = x;
      this.y = y;
      this. width = width;
      this.height = height;
      this.type = type;
    };
    boolean pressed(float tx, float ty){
        if (x <= tx && tx <= x + width && ty <= SCR_HEIGHT - y && SCR_HEIGHT - ty <= y + height){
            return true;
        }
        return false;
    }
    public void draw(MyGdxGame game){
        game.batch.draw(new Texture("build" + type+ ".png"),x,y,120,120 );
    }


}

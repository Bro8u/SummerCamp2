package com.mygdx.game;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;
import com.badlogic.gdx.graphics.Texture;

public class Button {
    float width, height;
    float x, y;
    Texture img;
    Button(float width,float height,float x,float y, Texture img){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.img = img;
    };
    boolean pushed(float tx, float ty){
        if (x < tx && tx < x + width && y < ty && ty < y + height){
            return true;
        }
        return false;
    }

}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class ScreenGame implements Screen {
    MyGdxGame mgg;
    Texture imgBackGround;
    Button1 button1Market;

    int numHouse = -1;

    public ScreenGame(MyGdxGame g) {
        mgg = g;
        imgBackGround = new Texture("backgroundMain.jpg");
        button1Market = new Button1(100, 100, SCR_WIDTH - 100,
                SCR_HEIGHT - 100, new Texture("buttonMarket.png"));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        mgg.batch.begin();
        mgg.batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        mgg.batch.draw(button1Market.img,
                button1Market.x,
                button1Market.y,
                button1Market.width,
                button1Market.height);
        if (numHouse != -1){
            mgg.batch.draw(new Texture("build" + numHouse + ".jpeg"), 0, 0, 300, 300 );
        }
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX(), y = Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            if (button1Market.pushed(x, y)){
                mgg.setScreen(mgg.screenMarket);
            }
        }
        mgg.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        imgBackGround.dispose();
        button1Market.img.dispose();
    }
}

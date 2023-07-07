package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class ScreenGame implements Screen {
    MyGdxGame mgg;
    Texture imgBackGround;
    Button buttonMarket;
    public ScreenGame(MyGdxGame g) {
        mgg = g;
        imgBackGround = new Texture("backgroundMain.jpg");
        buttonMarket = new Button(100, 100, SCR_WIDTH - 100,
                SCR_HEIGHT - 100, new Texture("buttonMarket.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        mgg.batch.begin();
        mgg.batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        mgg.batch.draw(buttonMarket.img,
                buttonMarket.x,
                buttonMarket.y,
                buttonMarket.width,
                buttonMarket.height);
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX(), y =  Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            System.out.println("hi");
//            if (market.pushed(x, y)){
//                mgg.setScreen(mgg.screenMarket);
//            }
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
        buttonMarket.img.dispose();
    }
}

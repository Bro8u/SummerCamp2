package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ScreenMarket implements Screen {
    NewHouse[] newHouses;
    Button buttonBackToGame, buttonInfCafe, buttonInfHotel, buttonInfBuilding, buttonBackToMarket;
    Texture imgBackGround;
    MyGdxGame mgg;
    int flag = -1;
    public ScreenMarket(MyGdxGame g){
        mgg = g;
        imgBackGround = new Texture("backgroundMarket.jpg");

        buttonBackToGame = new Button(100, 100, 0, SCR_HEIGHT - 100, new Texture("buttonBack.png"));

        buttonInfCafe = new Button(500, 500, 500, 500, 10, 0X8F1D1D, new Texture("WindowInf.png"), new DescriptionCafe().INF );
        buttonInfHotel = new Button(500, 500, 500, 500, 10, 0X8F1D1D, new Texture("WindowInf.png"), new DescriptionHostel().INF );
        buttonInfBuilding = new Button(500, 500, 500, 500, 10, 0X8F1D1D, new Texture("WindowInf.png"), new DescriptionBuilding().INF );
        buttonBackToMarket = new Button(100, 100, 600, SCR_HEIGHT - 300 , new Texture("buttonBack.png"));

        newHouses = new NewHouse[3];

        for(int i = 0; i < newHouses.length; i++) {
            Texture img = new Texture("build" + Integer.toString(i + 2) + ".jpeg");
            newHouses[i] = new NewHouse(i, img, i * 100 + 100);
            newHouses[i].buttonBuyHouse = new Button(250, 100,
                    newHouses[i].x, newHouses[i].y - 100, new Texture("buyHouse.jpeg"));
            newHouses[i].buttonInfHouse = new Button(250, 100,
                    newHouses[i].x, newHouses[i].y - 200, new Texture("buttonINF.png"));
        }
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta){
        mgg.camera.update();
        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        mgg.batch.begin();
        mgg.batch.draw(imgBackGround, 0, 0, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);

        mgg.batch.draw(buttonBackToGame.img,
                buttonBackToGame.x,
                buttonBackToGame.y,
                buttonBackToGame.width,
                buttonBackToGame.height);
        for(int i = 0; i < newHouses.length; i++){
            mgg.batch.draw(newHouses[i].img,
                    newHouses[i].x,
                    newHouses[i].y,
                    newHouses[i].width,
                    newHouses[i].height);
            mgg.batch.draw(newHouses[i].buttonBuyHouse.img,
                    newHouses[i].buttonBuyHouse.x,
                    newHouses[i].buttonBuyHouse.y,
                    newHouses[i].buttonBuyHouse.width,
                    newHouses[i].buttonBuyHouse.height);
            mgg.batch.draw(newHouses[i].buttonInfHouse.img,
                    newHouses[i].buttonInfHouse.x,
                    newHouses[i].buttonInfHouse.y,
                    newHouses[i].buttonInfHouse.width,
                    newHouses[i].buttonInfHouse.height);
        }
        if (Gdx.input.justTouched()){
            float x = Gdx.input.getX(), y = Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            for(int i = 0; i < newHouses.length; i++){
                if (newHouses[i].buttonBuyHouse.pushed(x, y) && newHouses[i].cost <= mgg.TotalMoney ){
                    mgg.bought = (i + 2);
                    mgg.setScreen(mgg.screenGame);
                    mgg.TotalMoney -= newHouses[i].cost;
                }
                if (newHouses[i].buttonInfHouse.pushed(x, y)){
                    if (i == 0){
                        flag = 0;
                    }
                    if (i == 1){
                        flag = 1;
                    }
                    if (i == 2){
                        flag = 2;
                    }
                }
            }
            if (buttonBackToGame.pushed(x, y)){
                mgg.setScreen(mgg.screenGame);
            }

        }
        if (flag == 0){
            mgg.batch.draw(buttonInfCafe.img,
                    buttonInfCafe.x,
                    buttonInfCafe.y,
                    buttonInfCafe.width,
                    buttonInfCafe.height);
            mgg.batch.draw(buttonBackToMarket.img,
                    buttonBackToMarket.x,
                    buttonBackToMarket.y,
                    buttonBackToMarket.width,
                    buttonBackToMarket.height);
            buttonInfCafe.draw(mgg);
        }
        if (flag == 1){
            mgg.batch.draw(buttonInfHotel.img,
                    buttonInfHotel.x,
                    buttonInfHotel.y,
                    buttonInfHotel.width,
                    buttonInfHotel.height);
            mgg.batch.draw(buttonBackToMarket.img,
                    buttonBackToMarket.x,
                    buttonBackToMarket.y,
                    buttonBackToMarket.width,
                    buttonBackToMarket.height);
        }
        if (flag == 2){
            mgg.batch.draw(buttonInfBuilding.img,
                    buttonInfBuilding.x,
                    buttonInfBuilding.y,
                    buttonInfBuilding.width,
                    buttonInfBuilding.height);
            mgg.batch.draw(buttonBackToMarket.img,
                    buttonBackToMarket.x,
                    buttonBackToMarket.y,
                    buttonBackToMarket.width,
                    buttonBackToMarket.height);
        }
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX(), y = Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            if (buttonBackToMarket.pushed(x, y)){
                flag = -1;
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
        for(int i = 0; i < newHouses.length; i++){

            newHouses[i].img.dispose();

            newHouses[i].buttonBuyHouse.img.dispose();

            newHouses[i].buttonInfHouse.img.dispose();

        }
        buttonBackToGame.img.dispose();
        buttonInfBuilding.img.dispose();
        buttonInfCafe.img.dispose();
        buttonBackToMarket.img.dispose();


    }

}

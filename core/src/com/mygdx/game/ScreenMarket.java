package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenMarket implements Screen {
    NewHouse[] newHouses;
    Button1 button1BackToGame, button1InfCafe, button1InfHotel, button1InfBuilding, button1BackToMarket;
    Texture imgBackGround;
    MyGdxGame mgg;
    int flag = -1;
    public ScreenMarket(MyGdxGame g){
        mgg = g;
        imgBackGround = new Texture("backgroundMarket.jpg");

        button1BackToGame = new Button1(100, 100, 0, SCR_HEIGHT - 100, new Texture("buttonBack.png"));

        button1InfCafe = new Button1(300, 300, 1000, 1000, (int) SCR_HEIGHT / 20, 0X8F1D1D, new Texture("WindowInf.png"), new DescriptionCafe().INF );
        button1InfHotel = new Button1(500, 500, 500, 500, 10, 0X8F1D1D, new Texture("WindowInf.png"), new DescriptionHostel().INF );
        button1InfBuilding = new Button1(500, 500, 500, 500, 10, 0X8F1D1D, new Texture("WindowInf.png"), new DescriptionBuilding().INF );
        button1BackToMarket = new Button1(100, 100, 600, SCR_HEIGHT - 300 , new Texture("buttonBack.png"));

        newHouses = new NewHouse[3];

        for(int i = 0; i < newHouses.length; i++) {
            Texture img = new Texture("build" + Integer.toString(i + 2) + ".jpeg");
            newHouses[i] = new NewHouse(i, img, i * 100 + 100);
            newHouses[i].button1BuyHouse = new Button1(250, 100,
                    newHouses[i].x, newHouses[i].y - 100, new Texture("buyHouse.jpeg"));
            newHouses[i].button1InfHouse = new Button1(250, 100,
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

        mgg.batch.draw(button1BackToGame.img,
                button1BackToGame.x,
                button1BackToGame.y,
                button1BackToGame.width,
                button1BackToGame.height);
        for(int i = 0; i < newHouses.length; i++){
            mgg.batch.draw(newHouses[i].img,
                    newHouses[i].x,
                    newHouses[i].y,
                    newHouses[i].width,
                    newHouses[i].height);
            mgg.batch.draw(newHouses[i].button1BuyHouse.img,
                    newHouses[i].button1BuyHouse.x,
                    newHouses[i].button1BuyHouse.y,
                    newHouses[i].button1BuyHouse.width,
                    newHouses[i].button1BuyHouse.height);
            mgg.batch.draw(newHouses[i].button1InfHouse.img,
                    newHouses[i].button1InfHouse.x,
                    newHouses[i].button1InfHouse.y,
                    newHouses[i].button1InfHouse.width,
                    newHouses[i].button1InfHouse.height);
        }
        if (Gdx.input.justTouched()){
            float x = Gdx.input.getX(), y = Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            for(int i = 0; i < newHouses.length; i++){
                if (newHouses[i].button1BuyHouse.pushed(x, y) && newHouses[i].cost <= mgg.TotalMoney ){
                    mgg.updateScreenGame(i + 2);
                    mgg.setScreen(mgg.screenGame);
                    mgg.TotalMoney -= newHouses[i].cost;
                }
                if (newHouses[i].button1InfHouse.pushed(x, y)){
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
            if (button1BackToGame.pushed(x, y)){
                mgg.setScreen(mgg.screenGame);
            }

        }
        if (flag == 0){
            mgg.batch.draw(button1InfCafe.img,
                    button1InfCafe.x,
                    button1InfCafe.y,
                    button1InfCafe.width,
                    button1InfCafe.height);
            mgg.batch.draw(button1BackToMarket.img,
                    button1BackToMarket.x,
                    button1BackToMarket.y,
                    button1BackToMarket.width,
                    button1BackToMarket.height);
            button1InfCafe.draw(mgg);
        }
        if (flag == 1){
            mgg.batch.draw(button1InfHotel.img,
                    button1InfHotel.x,
                    button1InfHotel.y,
                    button1InfHotel.width,
                    button1InfHotel.height);
            mgg.batch.draw(button1BackToMarket.img,
                    button1BackToMarket.x,
                    button1BackToMarket.y,
                    button1BackToMarket.width,
                    button1BackToMarket.height);
        }
        if (flag == 2){
            mgg.batch.draw(button1InfBuilding.img,
                    button1InfBuilding.x,
                    button1InfBuilding.y,
                    button1InfBuilding.width,
                    button1InfBuilding.height);
            mgg.batch.draw(button1BackToMarket.img,
                    button1BackToMarket.x,
                    button1BackToMarket.y,
                    button1BackToMarket.width,
                    button1BackToMarket.height);
        }
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX(), y = Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            if (button1BackToMarket.pushed(x, y)){
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

            newHouses[i].button1BuyHouse.img.dispose();

            newHouses[i].button1InfHouse.img.dispose();

        }
        button1BackToGame.img.dispose();
        button1InfBuilding.img.dispose();
        button1InfCafe.img.dispose();
        button1BackToMarket.img.dispose();


    }

}

package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;
public class ScreenMarket implements Screen {
    NewHouse[] newHouses;
    Button buttonBack;
    Texture imgBackGround;
    MyGdxGame mgg;

    public ScreenMarket(MyGdxGame g){
        mgg = g;
        mgg.number = -1;
        imgBackGround = new Texture("backgroundMarket.jpg");

        buttonBack = new Button(100, 100, 0, SCR_HEIGHT - 100, new Texture("buttonBack.png"));

        newHouses = new NewHouse[4];

        for(int i = 0; i < newHouses.length; i++) {
            Texture img = new Texture("build" + Integer.toString(i + 2) + ".jpeg");
            newHouses[i] = new NewHouse(i, img);
            newHouses[i].buttonBuyHouse = new Button(300, 100,
                    newHouses[i].x, newHouses[i].y - 100, new Texture("buyHouse.jpeg"));
        }
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta){

        mgg.batch.begin();
        mgg.batch.draw(imgBackGround, 0, 0, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
        mgg.batch.draw(buttonBack.img, buttonBack.x, buttonBack.y, buttonBack.width, buttonBack.height);

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
        }
        if (Gdx.input.justTouched()){
            float x = Gdx.input.getX(), y = Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            for(int i = 0; i < newHouses.length; i++){
                if (newHouses[i].buttonBuyHouse.pushed(x, y)){
                    newHouses[i].buttonBuyHouse = new Button(300, 100,
                            newHouses[i].x, newHouses[i].y - 100, new Texture("buyHouse.jpeg"));
                    mgg.number = (i + 2);
                    mgg.setScreen(mgg.screenGame);

                }
            }
            if (buttonBack.pushed(x, y)){
                mgg.setScreen(mgg.screenGame);
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
        }
        buttonBack.img.dispose();
    }
}

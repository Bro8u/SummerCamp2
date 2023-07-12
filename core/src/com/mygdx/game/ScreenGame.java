package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.TimeUtils;

public class ScreenGame implements Screen {
    MyGdxGame mgg;
    Texture imgBackGround;
    Button1 button1Market, buttonCloseInf;
    ViewCafe viewCafe, help;
    ArrayList<Cell> HavingHouses;
    Map<Integer, Integer> howManyOfEachType;
    Matrix4 transformMatrix;
    int flagInf = -1;


    int numHouse = -1;
    public ScreenGame(MyGdxGame g) {
        mgg = g;
        HavingHouses = new ArrayList<Cell>();
        howManyOfEachType = new HashMap<>();
        Matrix4 transformMatrix = new Matrix4();
        transformMatrix.translate((int) SCR_WIDTH / 10, (int) SCR_HEIGHT * 5 / 10, 0); // переместить текст в позицию (x, y)
        transformMatrix.scale((int) SCR_WIDTH / 10, (int) SCR_HEIGHT / 10, 1); // масштабирование текста по осям X и Y
        transformMatrix.rotate(0, 0, 1, 0); // поворот текста на заданный угол

        imgBackGround = new Texture("backgroundMain.jpg");
        buttonCloseInf = new Button1(100, 100, SCR_WIDTH * 5 / 10 - 100, SCR_HEIGHT * 6 / 10 - 100, new Texture("buttonClose.png"));
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
        for(int i = 0; i < HavingHouses.size(); i++) {
            mgg.batch.draw(new Texture("build" + HavingHouses.get(i).type + ".png"), HavingHouses.get(i).x, HavingHouses.get(i).y, 120, 120);
        }
        if (numHouse != -1) {
            mgg.batch.draw(new Texture("build" + numHouse + ".png"), 150, 150, 300,  300);
            HavingHouses.add(new Cell(150, 150, 300,  300, numHouse));
            if (howManyOfEachType.containsKey(numHouse)){
                howManyOfEachType.put(numHouse, howManyOfEachType.get(numHouse) + 1 );
            }
            else{
                howManyOfEachType.put(numHouse, 1 );
            }

            numHouse = -1;
        }
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX(), y = Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            if (button1Market.pushed(x, y)){
                mgg.setScreen(mgg.screenMarket);
            }
        }
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX(), y = Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            for(int i = 0; i < HavingHouses.size(); i++){
                if (HavingHouses.get(i).pressed(x, y)){
                    flagInf = HavingHouses.get(i).type;
//         // see a view with dinamic parameters, which depends on time which a plaier is gamingIDTH /
                }
            }
        }
        if (flagInf != -1){
            if (howManyOfEachType.containsKey(numHouse)){
                howManyOfEachType.put(numHouse, howManyOfEachType.get(numHouse) + 1 );
            }
            else{
                howManyOfEachType.put(numHouse, 1 );
            }
            ViewCafe help = new ViewCafe((float) (TimeUtils.millis() - mgg.startTime),
                    (int) howManyOfEachType.get(flagInf), mgg.people,
                    (int) HavingHouses.get(flagInf).advert,
                    (int) HavingHouses.get(flagInf).emploee,
                    (int) HavingHouses.get(flagInf).averageCheck);
            if (transformMatrix == null){
                mgg.batch.draw(help.img, SCR_WIDTH / 10,SCR_HEIGHT / 10 , SCR_WIDTH * 7 / 10, SCR_HEIGHT * 5 / 10);
                mgg.font.draw(mgg.batch, help.INF, SCR_WIDTH / 10, SCR_HEIGHT * 5 / 10);
                mgg.batch.draw(buttonCloseInf.img, buttonCloseInf.x, buttonCloseInf.y, buttonCloseInf.width, buttonCloseInf.height);
            }
            else {


                mgg.batch.draw(help.img, SCR_WIDTH / 10, SCR_HEIGHT / 10, SCR_WIDTH * 7 / 10, SCR_HEIGHT * 5 / 10);
                mgg.batch.setProjectionMatrix(mgg.camera.combined);
                mgg.batch.setTransformMatrix(transformMatrix);
                mgg.font.draw(mgg.batch, help.INF, SCR_WIDTH / 10, SCR_HEIGHT * 5 / 10);
                mgg.batch.draw(buttonCloseInf.img, buttonCloseInf.x, buttonCloseInf.y, buttonCloseInf.width, buttonCloseInf.height);
            }
        }

        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX(), y = Gdx.input.getY();
            mgg.touch.set(x, y, 0);
            mgg.camera.unproject(mgg.touch);
            if (buttonCloseInf.pushed(x, y)){
                flagInf = -1;
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

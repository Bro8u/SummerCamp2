package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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

    Texture blue,red,background,background2, home;
    Button goToBuyHome;
    Sprite sprite;
    int x,y;
    boolean touchHome,touchSquare,pressedHome = false ,completedHome;

    Square[][] mas;
    boolean [][] isEmpty;
    Map<String,String> map = new HashMap<>();
    Preferences prefs;
    ArrayList<Home> list;

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

        blue = new Texture("blue.png");
        background = new Texture("background.png");
        background2 = new Texture("background2.png");
        home = new Texture("home.png");
        red = new Texture("red.png");


        goToBuyHome = new Button();

        touchHome = false;
        touchSquare = false;

        completedHome = false;

        x = 0;
        y = 0;

        mas = new Square[18][32];
        isEmpty = new boolean[18][32];
        list = new ArrayList<>();

        prefs = Gdx.app.getPreferences("My Preferences");

        for (int i = 0; i<18;i++){
            for(int j = 0;j<32;j++){
                mas[i][j] = new Square(i,j,(MyGdxGame.SCR_WIDTH-1920)/2+j*60,(17-i)*60);
                isEmpty[i][j] = false;
                if (i==7||i==8||i>=13){
                    mas[i][j].isEmpty = true;
                    isEmpty[i][j] = true;
                }
                if (j==10||j==11||j==22||j==23){
                    mas[i][j].isEmpty = true;
                    isEmpty[i][j] = true;
                }
                prefs.putString(i+" "+j,mas[i][j].toString());
                prefs.flush();
            }
        }
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {

        mgg.batch.begin();
        mgg.batch.draw(blue,0,0, SCR_WIDTH, SCR_HEIGHT);
        mgg.batch.draw(background,( SCR_WIDTH-1920)/2 , ( SCR_HEIGHT-1080)/2, 1920, 1080);

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
            HavingHouses.add(new Cell(0, 0, 120,  120, numHouse));
//            if (howManyOfEachType.containsKey(numHouse)){
//                howManyOfEachType.put(numHouse, howManyOfEachType.get(numHouse) + 1 );
//            }
//            else{
//                howManyOfEachType.put(numHouse, 1 );
//            }

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
//        if (Gdx.input.justTouched()) {
//            float x = Gdx.input.getX(), y = Gdx.input.getY();
//            mgg.touch.set(x, y, 0);
//            mgg.camera.unproject(mgg.touch);
//            for(int i = 0; i < HavingHouses.size(); i++){
//                if (HavingHouses.get(i).pressed(x, y)){
//                    flagInf = HavingHouses.get(i).type;
////         // see a view with dinamic parameters, which depends on time which a plaier is gamingIDTH /
//                }
//            }
//        }
//        if (Gdx.input.justTouched()) {
//            float x = Gdx.input.getX(), y = Gdx.input.getY();
//            mgg.touch.set(x, y, 0);
//            mgg.camera.unproject(mgg.touch);
//            if (buttonCloseInf.pushed(x, y)){
//                flagInf = -1;
//            }
//        }
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button1) {
                if(pressedHome) {
                    if (screenX > (SCR_WIDTH - 1920) / 2 && screenX < (SCR_WIDTH - 1920) / 2 + 1920) {
                        x = screenX;
                        y = SCR_HEIGHT - screenY;
                        pressedHome = false;
                        if (!isEmpty[(y - (SCR_HEIGHT - 1080) / 2) / 60][(x - (SCR_WIDTH - 1920) / 2) / 60]) {
                            x = (x - (SCR_WIDTH - 1920) / 2) / 60;
                            y = (y - (SCR_HEIGHT - 1080) / 2) / 60;
                            fillArray(1,y,x);
                            fillArray(2,y,x);

                            if (mas[17-y][x].isEmpty) {
                                x = 0;
                                y = 0;
                            } else {
                                x = (SCR_WIDTH - 1920) / 2 + x * 60;
                                y = (SCR_HEIGHT - 1080) / 2 + y * 60;
                                completedHome = true;
                            }
                        }
                    }
                }
                return true;
            }
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (screenX>x && screenX<x+240 &&screenY> SCR_HEIGHT-y-240 && screenY< SCR_HEIGHT-y){
                    pressedHome = true;
                    x=screenX;
                    y=MyGdxGame.SCR_WIDTH-screenY;

                }
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button1) {
                return true;
            }
        });

//        if (flagInf != -1 && pressedHome == false){
//            if (howManyOfEachType.containsKey(numHouse)){
//                howManyOfEachType.put(numHouse, howManyOfEachType.get(numHouse) + 1 );
//            }
//            else{
//                howManyOfEachType.put(numHouse, 1 );
//            }
//            ViewCafe help = new ViewCafe((float) (TimeUtils.millis() - mgg.startTime),
//                    (int) howManyOfEachType.get(flagInf), mgg.people,
//                    (int) HavingHouses.get(flagInf).advert,
//                    (int) HavingHouses.get(flagInf).emploee,
//                    (int) HavingHouses.get(flagInf).averageCheck);
//            if (transformMatrix == null){
//                mgg.batch.draw(help.img, SCR_WIDTH / 10,SCR_HEIGHT / 10 , SCR_WIDTH * 7 / 10, SCR_HEIGHT * 5 / 10);
//                mgg.font.draw(mgg.batch, help.INF, SCR_WIDTH / 10, SCR_HEIGHT * 5 / 10);
//                mgg.batch.draw(buttonCloseInf.img, buttonCloseInf.x, buttonCloseInf.y, buttonCloseInf.width, buttonCloseInf.height);
//            }
//            else {
//
//
//                mgg.batch.draw(help.img, SCR_WIDTH / 10, SCR_HEIGHT / 10, SCR_WIDTH * 7 / 10, SCR_HEIGHT * 5 / 10);
//                mgg.batch.setProjectionMatrix(mgg.camera.combined);
//                mgg.batch.setTransformMatrix(transformMatrix);
//                mgg.font.draw(mgg.batch, help.INF, SCR_WIDTH / 10, SCR_HEIGHT * 5 / 10);
//                mgg.batch.draw(buttonCloseInf.img, buttonCloseInf.x, buttonCloseInf.y, buttonCloseInf.width, buttonCloseInf.height);
//            }
//        }
        mgg.batch.draw(home, x, y, 300,  300);
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
    public void fillArray(int flag, int i, int j){
        if (flag==1){
            mas[i][j].isEmpty = true;
            mas[i-1][j].isEmpty = true;
            mas[i][j+1].isEmpty = true;
            mas[i-1][j+1].isEmpty = true;

            prefs.putString(i+" "+j,mas[i][j].toString());
            prefs.putString((i-1)+" "+j,mas[i-1][j].toString());
            prefs.putString(i+" "+(j+1),mas[i][j+1].toString());
            prefs.putString((i-1)+" "+(j+1),mas[i-1][j+1].toString());
            prefs.flush();

        }
        if (flag == 2){
            isEmpty[i][j] = true;
            isEmpty[i-1][j] = true;
            isEmpty[i][j+1] = true;
            isEmpty[i-1][j+1] = true;
        }
    }

    public int fromString(int flag,String str){
        String[] arr = str.split("/");
        if (flag==1){
            return Integer.parseInt(arr[0]);
        }
        if (flag==2){
            return Integer.parseInt(arr[1]);
        }
        if (flag==3){
            return Integer.parseInt(arr[2]);
        }
        if (flag==4){
            return Integer.parseInt(arr[3]);
        }
        if (flag==5){
            if (Boolean.parseBoolean(arr[4])==false){
                return 0;
            }
            if (Boolean.parseBoolean(arr[4])==true){
                return 1;
            }

        }
        return -1;
    }
    public void drawHome(int i){
        mgg.batch.draw(list.get(i).texture,list.get(i).square.x,list.get(i).square.y);
    }
}

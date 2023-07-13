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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class ScreenGame implements Screen {
    MyGdxGame mgg;
    Texture imgBackGround;
    Button1 button1Market, buttonCloseInf,confirmHomeBuild;
    ViewCafe viewCafe, help;
    ArrayList<Cell> havingHouses;
    int length;
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
    int flag = 0;
    int hx,hy;
    int sqWidth,sqHeight;
    int flagConfirm;
    public ScreenGame(MyGdxGame g) {
        mgg = g;
        havingHouses = new ArrayList<>();
        length = 0;
        howManyOfEachType = new HashMap<>();
        Matrix4 transformMatrix = new Matrix4();
        transformMatrix.translate((int) SCR_WIDTH / 10, (int) SCR_HEIGHT * 5 / 10, 0); // переместить текст в позицию (x, y)
        transformMatrix.scale((int) SCR_WIDTH / 10, (int) SCR_HEIGHT / 10, 1); // масштабирование текста по осям X и Y
        transformMatrix.rotate(0, 0, 1, 0); // поворот текста на заданный угол

        imgBackGround = new Texture("backgroundMain.jpg");
        buttonCloseInf = new Button1(100, 100, SCR_WIDTH * 5 / 10 - 100, SCR_HEIGHT * 6 / 10 - 100, new Texture("buttonClose.png"));
        button1Market = new Button1(100, 100, SCR_WIDTH - 100,
                SCR_HEIGHT - 100, new Texture("buttonMarket.png"));
        confirmHomeBuild = new Button1(100,100,SCR_WIDTH-100,SCR_HEIGHT-100,new Texture("confirm.png"));

        blue = new Texture("blue.png");
        background = new Texture("background.png");
        background2 = new Texture("background2.png");
        home = new Texture("home.png");
        red = new Texture("red.png");


        goToBuyHome = new Button();

        touchHome = false;
        touchSquare = false;
        completedHome = false;

        mas = new Square[18][32];
        isEmpty = new boolean[18][32];
        list = new ArrayList<>();

        prefs = Gdx.app.getPreferences("My Preferences");

        sqWidth = SCR_WIDTH/32;
        sqHeight = SCR_HEIGHT/18;

        for (int i = 0; i<18;i++){
            for(int j = 0;j<32;j++){
                mas[i][j] = new Square(i,j,j*(SCR_WIDTH/32),(17-i)*(SCR_HEIGHT/18));
                isEmpty[i][j] = false;
                if (i==7||i==8||i>=13){
                    mas[i][j].isEmpty = true;
                    isEmpty[i][j] = true;
                }
                if (j==10||j==11||j==22||j==23){
                    mas[i][j].isEmpty = true;
                    isEmpty[i][j] = true;
                    prefs.putString(i+" "+j,mas[i][j].toString());
                    prefs.flush();
                }

            }
        }
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {

        mgg.batch.begin();
        mgg.batch.draw(background2,0 , 0, SCR_WIDTH, SCR_HEIGHT);

        if (flag==0){
            mgg.batch.draw(button1Market.img,
                    button1Market.x,
                    button1Market.y,
                    button1Market.width,
                    button1Market.height);

            if (Gdx.input.justTouched()) {
                float x = Gdx.input.getX(), y = Gdx.input.getY();
                mgg.touch.set(x, y, 0);
                mgg.camera.unproject(mgg.touch);
                if (button1Market.pushed(x, y)){
                    mgg.setScreen(mgg.screenMarket);
                }
            }
            for (int i =0;i<havingHouses.size();i++){
                havingHouses.get(i).draw(mgg );
            }


        }
        if (flag ==1){
            confirmHomeBuild.draw(mgg);
            for (int i = 17; i >=0; i--) {
                for (int j = 0; j < 32; j++) {
                     Square sq = new Square(fromString(1,prefs.getString(i+" "+j)),fromString(2,prefs.getString(i+" "+j)));
                }
            }
            if (numHouse != -1) {
                havingHouses.add(new Cell(0, 0, SCR_WIDTH/32*2,  SCR_HEIGHT/18*2, numHouse));
                length++;
                havingHouses.get(length-1).draw(mgg);
                numHouse = -1;
            }
            for (int i = 17; i >=0; i--) {
                for (int j = 0; j < 32; j++) {
                    Square sq = mas[i][j];
                    if (mas[i][j].isEmpty){
                        mgg.batch.draw(red,sq.x,sq.y,SCR_WIDTH/32,SCR_HEIGHT/18);
                    }
                }
            }


            Gdx.input.setInputProcessor(new InputAdapter(){
                @Override
                public boolean touchUp(int screenX, int screenY, int pointer, int button1) {
                    if(pressedHome) {
                            hx = screenX;
                            hy = SCR_HEIGHT - screenY;
                            pressedHome = false;

                                hx = hx/ (SCR_WIDTH/32);
                                hy = hy/(SCR_HEIGHT/18);

                                if (mas[17-hy][hx].isEmpty) {
                                    hx=0;
                                    hy=0;
                                } else {
                                    if(hy+1==18||mas[hy+1][hx].isEmpty){
                                        x = hx;
                                        y = hy;
                                        hx =  hx * sqWidth;
                                        hy =  (hy-1) * sqHeight;
                                        flagConfirm = 1;
                                    } else if (hx+1==32||mas[hy][hx+1].isEmpty){
                                        x = hx;
                                        y = hy;
                                        hx = (hx-1) * sqWidth;
                                        hy = hy * sqHeight;
                                        flagConfirm = 2;
                                    } else if ((hx+1==32&&hy+1==18)||(mas[hy+1][hx+1].isEmpty)){
                                        x = hx;
                                        y = hy;
                                        hx = (hx-1) * sqWidth;
                                        hy = (hy-1) * sqHeight;
                                        flagConfirm = 3;
                                    } else {
                                        x = hx;
                                        y = hy;
                                        hx =  hx * sqWidth;
                                        hy =  hy * sqHeight;
                                        flagConfirm = 0;
                                    }
                                    havingHouses.get(length-1).x = hx;
                                    havingHouses.get(length-1).y = hy;
                                }
                    }
                    if (completedHome) {
                        if (flagConfirm ==0) {
                            fillArray(1,y,x);
                            fillArray(2,y,x);
                            flag = 0;
                            havingHouses.get(length-1).x = hx;
                            havingHouses.get(length-1).y = hy;
                        } else if (flagConfirm == 1){
                            fillArray(1,y-1,x);
                            fillArray(2,y-1,x);
                            flag = 0;
                            havingHouses.get(length-1).x = hx;
                            havingHouses.get(length-1).y = hy;
                        } else if (flagConfirm == 2){
                            fillArray(1,y,x-1);
                            fillArray(2,y,x-1);
                            flag = 0;
                            havingHouses.get(length-1).x = hx;
                            havingHouses.get(length-1).y = hy;
                        } else if (flagConfirm ==3){
                            fillArray(1,y-1,x-1);
                            fillArray(2,y-1,x-1);
                            flag = 0;
                            havingHouses.get(length-1).x = hx;
                            havingHouses.get(length-1).y = hy;
                        }
                        completedHome = false;
                    }
                    return true;
                }
                @Override
                public boolean touchDragged(int screenX, int screenY, int pointer) {
                    hx = havingHouses.get(length-1).x;
                    hy = havingHouses.get(length-1).y;
                    if (screenX>hx && screenX<hx+sqWidth*2 && screenY> SCR_HEIGHT-hy-sqHeight*2 && screenY< SCR_HEIGHT-hy){
                        pressedHome = true;
                        return true;
                    }
                    return true;
                }

                @Override
                public boolean touchDown(int screenX, int screenY, int pointer, int button1) {
                    if (confirmHomeBuild.pushed(screenX,screenY)){
                        completedHome = true;
                    }
                    return true;
                }
            });

                havingHouses.get(length-1).draw(mgg);
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
    public void fillArray(int flag, int i, int j){
        int fy=17- i,fx = j;
        if (flag==1){
            mas[fy][fx].isEmpty = true;
            mas[fy-1][fx].isEmpty = true;
            mas[fy][fx+1].isEmpty = true;
            mas[fy-1][fx+1].isEmpty = true;

            prefs.putString(fy+" "+fx,mas[fy][fx].toString());
            prefs.putString((fy-1)+" "+fx,mas[fy-1][fx].toString());
            prefs.putString(fy+" "+(fx+1),mas[fy][fx+1].toString());
            prefs.putString((fy-1)+" "+(fx+1),mas[fy-1][fx+1].toString());

            prefs.putBoolean("f"+fy+" "+fx,true);
            prefs.flush();

        }
        if (flag == 2){
            isEmpty[fy][fx] = true;
            isEmpty[fy-1][fx] = true;
            isEmpty[fy][fx+1] = true;
            isEmpty[fy-1][fx+1] = true;
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
        mgg.batch.draw(list.get(i).texture,list.get(i).square.x,list.get(i).square.y,sqWidth*2,sqHeight*2);
    }
    public Texture retHome(int i){
        return new Texture("build" + havingHouses.get(i).type + ".png");
    }
    public boolean isEmptyFromString(String str){

        return Boolean.valueOf(str);
    }
}

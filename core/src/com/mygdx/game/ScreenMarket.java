//package com.mygdx.game;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Texture;
//import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
//import static com.mygdx.game.MyGdxGame.SCR_WIDTH;
//
//public class ScreenMarket implements Screen {
//
//    NewHouse[] newHouses;
//    Button back = new Button(100, 100, 0, SCR_HEIGHT - 100, new Texture("buttonBack.png"));
//    Texture imgBackGround;
//    MyGdxGame mgg;
//    public ScreenMarket(MyGdxGame g){
//        mgg = g;
//
//        imgBackGround = new Texture("backgroundMarket.jpg");
//
//        newHouses = new NewHouse[5];
//
//        for(int i = 0; i < newHouses.length; i++) {
//            Texture img = new Texture("house" + Integer.toString(i));
//            newHouses[i] = new NewHouse(i, img);
//        }
//    }
//
//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void render(float delta){
//        mgg.batch.begin();
//        mgg.batch.draw(imgBackGround, 0, 0, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
//
//        for(int i = 0; i < newHouses.length; i++){
//            mgg.batch.draw(newHouses[i].img,
//                    newHouses[i].x,
//                    newHouses[i].y,
//                    newHouses[i].width,
//                    newHouses[i].height);
//            mgg.batch.draw(newHouses[i].buyHouse.img,
//                    newHouses[i].buyHouse.x,
//                    newHouses[i].buyHouse.y,
//                    newHouses[i].buyHouse.width,
//                    newHouses[i].buyHouse.height);
//        }
////        if (Gdx.input.justTouched()){
////            float x = Gdx.input.getX(), y = Gdx.input.getY();
////            mgg.touch.set(x, y, 0);
////            mgg.camera.unproject(mgg.touch);
////            for(int i = 0; i < newHouses.length; i++){
////                if (newHouses[i].buyHouse.x <= x  && x <= newHouses[i].buyHouse.x + newHouses[i].buyHouse.width &&
////                 newHouses[i].buyHouse.y <= y  && y <= newHouses[i].buyHouse.x + newHouses[i].buyHouse.height){
////                    // close ScreenMarket and open ScreeenGame with a new house on the screen
////                }
////            }
////        }
//        mgg.batch.end();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//
//}

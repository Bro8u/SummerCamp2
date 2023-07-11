package com.mygdx.game;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Button1 {
    float width, height;
    float x, y;
    Texture img;
    private int textX;
    private int textY;

    private BitmapFont font = new BitmapFont();

    public static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("appetite.ttf"));
    public static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    private String text;
    private Texture picture;

    private boolean withText = true;
    private boolean withPicture = true;

    Button1(float width, float height, float x, float y, Texture img){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.img = img;
    }
    public Button1(int x, int y, String text, int fontSize, int fontColor) {
        this.x = x;
        this.y = y;
        this.text = text;
        withPicture = false;
        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        parameter.borderWidth = 3;
        parameter.borderColor = Color.BLACK;
        parameter.size = fontSize;
        parameter.color = new Color(fontColor);
        this.font = generator.generateFont(parameter);
        GlyphLayout gl = new GlyphLayout(this.font, text);
        this.textX = (int) gl.width;
        this.textY = (int) gl.height;
        this.width = this.textX;
        this.height = this.textY;
    }
    public Button1(int x, int y, int sizeX, int sizeY, int fontSize, int fontColor, Texture picture, String text) {
        this.x = x;
        this.y = y;
        this.width = sizeX;
        this.height = sizeY;
        this.img = picture;

    }
    public void draw(MyGdxGame game) {
        if (withPicture) {
            game.batch.draw(img, x, y, width, height);
        }
//        if (withPicture && withText) {
//            this.font.draw(game.batch, this.text, this.x + (width - textX) / 2,
//                    this.y + (height + textY) / 2);
//        }
//        if (!withPicture && withText) {
//            this.font.draw(game.batch, this.text, this.x, this.y + textY);
//        }
    }
    boolean pushed(float tx, float ty){
        if (x <= tx && tx <= x + width && ty <= SCR_HEIGHT - y && SCR_HEIGHT - ty <= y + height){
            return true;
        }
        return false;
    }

}

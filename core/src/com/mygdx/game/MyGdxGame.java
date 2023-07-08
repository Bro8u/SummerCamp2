package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends Game {
	// ширина и высота экрана
	public static float SCR_WIDTH;
	public static float SCR_HEIGHT;

	// системные объекты
	SpriteBatch batch; // Объект, отвечающий за вывод изображений
	OrthographicCamera camera; // пересчитывает размеры для различных экранов
	int number;
	Vector3 touch;

//	BitmapFont font; // шрифт
	ScreenGame screenGame;
	ScreenMarket screenMarket;

	@Override
	public void create() {
		batch = new SpriteBatch(); // создать объект, отвечающий за вывод изображений
		camera = new OrthographicCamera();
		touch = new Vector3();
		SCR_WIDTH = Gdx.graphics.getWidth();
		SCR_HEIGHT = Gdx.graphics.getHeight();
		number = -1;
//		createFont();

		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
//		screenIntro = new ScreenIntro(this);
		screenMarket = new ScreenMarket(this);

		screenGame = new ScreenGame(this);



//		setScreen(screenIntro);
		setScreen(screenGame);
	}


//	void createFont(){
//		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("wellwait.otf"));
//		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//		parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
//		parameter.size = 50;
//		parameter.color = Color.ORANGE;
//		parameter.borderWidth = 3;
//		parameter.borderColor = Color.BLACK;
//		font = generator.generateFont(parameter);
//	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}

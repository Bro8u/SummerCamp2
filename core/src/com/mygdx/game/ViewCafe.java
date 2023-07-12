package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class ViewCafe {
    float time;
    Texture img = new Texture("backGroundView.png");
    int quantityOfCafe = 1, people, advert, emploee, averageCheck ;
    ViewCafe(float time, int quantityOfCafe, int people, int advert, int emploee, int averageCheck){
        this.time = time;
        this.quantityOfCafe = quantityOfCafe;
        this.emploee = emploee;
        this.people = people;
        this.advert = advert;
        this.averageCheck = averageCheck;
    }
    int GetCusomers(){return (int) people / quantityOfCafe;}
    int GetProfit(){
        return (int) (quantityOfCafe * ((GetCusomers() * averageCheck) * advert - (int) emploee * 0.8)) * (int) (time / 60000);
    }
    String INF = "Общая выручка: " + Integer.toString(GetProfit()) + "\n" +
            "Всего посетило клиентов: \n" +
            "Средний чек: \n" +
            "Месячная выручка: \n" +
            "Прирост новых посетителей за последний месяц: \n" +
            "Способы увеличения прибыли: \n" +
            "Реклама на TV: 5000 \n";

}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArrayData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Texture texture;
    private Vector2 position;
    private  float speed;
    private  int fireRate;// частота выстрела пули
    private  int fireCounter;// счетчик для пуль
    // создаем героя с определенным местоположением и скоростью
    public Hero() {
        speed = 8.0f;
        position = new Vector2(50,328);
        texture = new Texture("spaceMashine.png");
        fireCounter = 0;
        fireRate = 10;
    }

        // отрисовываем его
    public  void render(SpriteBatch spriteBatch){
        spriteBatch.draw(texture,position.x, position.y);
    }

        // проверяем нажата ли кнопка на клаыиатуре, если да, то куда будет двигаться
        // так же и на тачпаде
    public  void update(){

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            position.x -= speed;
            if(position.x < 0) position.x = 0;
        }


        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            position.x += speed;
            if(position.x > 1216) position.x = 1216;
        }


        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            position.y += speed;
            if(position.y > 720) position.y=-64;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            position.y -= speed;
            if(position.y < -64) position.y=720;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.L)){// если нажала на L, то счетчик увеличивается
            fireCounter++;
            if(fireCounter >= fireRate){ // если счетчик стал больше или равен частоте, то счетчик =0
                fireCounter = 0;
                fire();
            }
        }

        if(Gdx.input.isTouched()){
            if(Gdx.input.getX() < position.x + 32) position.x -= speed;
            if(Gdx.input.getX() > position.x + 32) position.x += speed;

           if(Gdx.graphics.getHeight() - Gdx.input.getY() > position.y + 32) position.y += speed;
            if(Gdx.graphics.getHeight() - Gdx.input.getY() < position.y + 32) position.y -= speed;

        }
    }

    // метод для стрельбы пулями
    public  void  fire(){
        for (int i = 0; i < MyGdxGame.bullets.length; i++) {
            if(!MyGdxGame.bullets[i].isActive()){
                MyGdxGame.bullets[i].setup(position.x + 48, position.y + 25);
                break;
            }
        }
    }

}

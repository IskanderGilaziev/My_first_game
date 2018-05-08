package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Vector2 position;// позиция х и у
    private  float speed;// example speed = 1.5f;
    private  boolean active;// для задания определенной части активыных пуль, а которые в запасе пока не активны

    public Vector2 getPosition() {
        return position;
    }

    public boolean isActive() {
        return active;
    }

    //создаем звезду с рандомным положением и скоростью
    public Bullet() {
        position = new Vector2(0,0);
        speed = 10.0f;
        active = false;
    }

    // выключает пулю
    public  void disable(){
        active = false;

    }
    //включаем пули
    public  void setup(float x, float y){
        position.x = x;
        position.y = y;
        active = true;
    }

    // при достижении звездой границы, заново ее пересоздаем в рандом месте и скоростью
    public  void update(){
        position.x += speed;
        if(position.x > 1280){
           disable();
        }
    }
}

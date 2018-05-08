package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {

    private class Star{
        private Vector2 position;// позиция х и у
        private  float speed;// example speed = 1.5f;
        //создаем звезду с рандомным положением и скоростью
        public Star() {
            position = new Vector2((float)Math.random()*1280,(float)Math.random()*720);
            speed = 1.0f + (float)Math.random()*5.0f;
        }
        // при достижении звездой границы, заново ее пересоздаем в рандом месте и скоростью
        public  void update(){
            position.x -= speed;
            if(position.x < -20){
                position.x = 1280;
                position.y = (float)Math.random()*720;
                speed = 1.0f + (float)Math.random()*5.0f;
            }
        }
    }




    private Texture texture;
    private  Texture textureStar;
    private Star stars[];

    public Background(){
    texture = new Texture("space1.jpg");
    textureStar = new Texture("star.png");
    stars = new Star[25];
        for (int i = 0; i <stars.length ; i++) {
            stars[i] = new Star();
        }
    }

    public  void render(SpriteBatch batch){

        batch.draw(texture,0,0);
        for (int i = 0; i < stars.length; i++) {
            //batch.draw(textureStar,  stars[i].position.x,  stars[i].position.y);

            // для моргания звезд
            float scale = 1.0f;
            if(Math.random() < 0.05f)  scale = 2f;
            batch.draw(textureStar,  stars[i].position.x,  stars[i].position.y,18,17,36,36,scale,scale,0.0f,0,0,36,36,false,false);

        }
}

public void update(){
    for (int i = 0; i < stars.length; i++) {
        stars[i].update();
    }
}
}

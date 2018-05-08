package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroids {
     public static  Texture texture; // одна картинка для всего
    private Vector2 position;// позиция х и у
    private  float speed;// example speed = 1.5f;
    private  float angle;//угол поворота астероида
    private Rectangle rectangle;// область столкновения астероида с пулями или героем

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Asteroids() {
        position = new Vector2((float)Math.random()*1280+1280,(float)Math.random()*720);
        speed = 4.0f + (float)Math.random()*4.0f;
        angle = (float)Math.random()*360;
        if(texture ==null){
            texture = new Texture("asteroid.png");
        }
        rectangle = new Rectangle(position.x,position.y,256,256 );
    }


    public  void render(SpriteBatch batch ){
        //batch.draw(texture, position.x, position.y);
        // задается позиция астероида с его углом поворота и областью его использования(т.е. какую долю его крутить или обрезать и т.д.)
        batch.draw(texture,position.x,position.y,128,128,128,128,1.0f,1.0f,angle,0,0,256,256,false,false);
    }
        //пересоздаем астероиды с рандомными значениями
    public  void recreate(){
        position.x = (float) Math.random()*1280 +1280;
        position.y = (float)Math.random()*720;
        speed = 4.0f + (float)Math.random()*4.0f;
        angle = (float)Math.random()*360;
    }


    public  void update(){
        position.x -= speed;
        angle += speed/2;// задаем скорость поворота у астероида
        if(position.x < -64){// при достижении конца экрана, создаем новый астероид
            recreate();
        }
        // область столкновения астероида с пулями или героем
        rectangle.x = position.x;
        rectangle.y = position.y;

    }
}

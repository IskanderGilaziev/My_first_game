package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;// область для отрисовки всего
	private  Background background; // наш фон космоса
	private  Hero hero;
	private Asteroids [] asteroids;
	public  static  Bullet bullets [];//
	private  Texture bulletTexture;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		hero = new Hero();

		asteroids = new Asteroids[15];// задаем определенное количество астероидов и  их создаем
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i] = new Asteroids();
		}
			// задаем количество пуль и отрисовываем их
			bullets = new Bullet[200];
			for (int j = 0; j <bullets.length ; j++) {
				bullets[j] =  new Bullet();
			}
			bulletTexture = new Texture("push.png");


	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);// отрисовываем  фон космоса

		hero.render(batch);//так же героя рисуем и астероиды
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].render(batch);
		}

		//проверяем на активность пули
		for (int i = 0; i < bullets.length; i++) {
			if(bullets[i].isActive())
				batch.draw(bulletTexture,bullets[i].getPosition().x - 32,bullets[i].getPosition().y - 16);
		}

		batch.end();// конец отрисовки
	}

	public  void update(){
		background.update();// инициализируем работу фона, героя и астероида
		hero.update();
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].update();
		}
		// создаем те пули, которые активны
		for (int i = 0; i < bullets.length; i++) {
			if(bullets[i].isActive())
				bullets[i].update();
			//перебираем астероиды  и опрашиваем на наличие пули в хитбоксе(поле пересечния) координаты пули
			for (int j = 0; j < asteroids.length; j++) {
				if(asteroids[j].getRectangle().contains(bullets[i].getPosition())){
					asteroids[j].recreate() ;
					bullets[i].disable();
					break;
				}
			}
		}
	}


	@Override
	public void dispose () {
		batch.dispose();

	}
}

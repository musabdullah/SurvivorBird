package com.atilsamancioglu.survivorbirdstarter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.w3c.dom.Text;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background;
	Texture bird;
	Texture enemy1;
	Texture enemy2;
	Texture enemy3;
	float birdX = 0;
	float birdY = 0;
	int gameState = 0;
	float velocity = 0;
	float gravity = 0.1f;
	float enemyVelocity = 4;

	int numberOfEnemies = 4;
	float[] enemyX = new float[numberOfEnemies];
	float distance = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		bird = new Texture("bird.png");
		enemy1 = new Texture("enemy.png");
		enemy2 = new Texture("enemy.png");
		enemy3 = new Texture("enemy.png");

		distance = Gdx.graphics.getWidth()/2;

		birdX = Gdx.graphics.getWidth()/4;
		birdY = Gdx.graphics.getHeight()/2;

		for (int i =0; i<numberOfEnemies; i++){
			enemyX[i]=Gdx.graphics.getWidth() - enemy1.getWidth()/2 +i*distance;
		}

	}

	@Override
	public void render () {

		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if (gameState == 1){

			if (Gdx.input.justTouched()){
				velocity = -3;
			}

			for (int i=0; i<numberOfEnemies; i++){
				if (enemyX[i]<Gdx.graphics.getWidth()/14){
					enemyX[i] = enemyX[i] + numberOfEnemies*distance;
				}else{
					enemyX[i] = enemyX[i] - enemyVelocity;
				}


				batch.draw(enemy1, enemyX[i],50, Gdx.graphics.getWidth()/14, Gdx.graphics.getHeight()/10);
				batch.draw(enemy2, enemyX[i],140, Gdx.graphics.getWidth()/14, Gdx.graphics.getHeight()/10);
				batch.draw(enemy3, enemyX[i],400, Gdx.graphics.getWidth()/14, Gdx.graphics.getHeight()/10);

			}

			if (birdY>0 || velocity <0){
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}
		}else if (gameState == 0){
			if (Gdx.input.justTouched()){
				gameState = 1;
			}
		}

		batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/18,Gdx.graphics.getHeight()/12);
		batch.end();
	}

	@Override
	public void dispose () {

	}
}

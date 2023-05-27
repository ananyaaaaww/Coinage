//package com.zappycode.coinage.game;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.utils.ScreenUtils;
//
//import java.awt.Rectangle;
//import java.util.ArrayList;
//import java.util.Random;
//
//public class Coinage extends ApplicationAdapter {
//	SpriteBatch batch;
//	Texture background;
//	Texture[] man;
//	int manState;  //there are 4 images of man
//	int pause;
//	float gravity=0.5f;
//	float velocity=0;
//	int manY=0;  //current position  (Y)
//
//	Random random;
//
//	ArrayList<Integer> coinXs=new ArrayList<Integer>();//x position of coins
//	ArrayList<Integer> coinYs=new ArrayList<Integer>();//y position of coins
//	ArrayList<Rectangle> coinRectangles=new ArrayList<Rectangle>();
//	Texture coin;
//	int coinCount;
//
//	ArrayList<Integer> bombXs=new ArrayList<Integer>();//x position of coins
//	ArrayList<Integer> bombYs=new ArrayList<Integer>();//y position of coins
//	Texture bomb;
//	int bombCount;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		background=new Texture("bg.png");
//		man=new Texture[4];
//		man[0]=new Texture("frame-1.png");
//		man[1]=new Texture("frame-2.png");
//		man[2]=new Texture("frame-3.png");
//		man[3]=new Texture("frame-4.png");
//		manY=Gdx.graphics.getHeight()/2;  //initial pos of man.  Middle of screen
//
//		coin=new Texture("coin.png");//setting up coin
//		bomb=new Texture("bomb.png");
//		random=new Random();
//	}
//
//	public void makeCoin() //as there are multiple coins we are using a method
//	{
//		float height=random.nextFloat()*Gdx.graphics.getHeight();//to keep coins at diff heights
//		coinYs.add((int)height); // y should be random
//		coinXs.add(Gdx.graphics.getWidth()); //x is fixed
//	}
//
//	public void makeBomb() //as there are multiple coins we are using a method
//	{
//		float height=random.nextFloat()*Gdx.graphics.getHeight();//to keep coins at diff heights
//		bombYs.add((int)height); // y should be random
//		bombXs.add(Gdx.graphics.getWidth()); //x is fixed
//	}
//
//
//	@Override
//	public void render () {
//		batch.begin();
//		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//
//		//BOMBS
//		if(bombCount<250)
//		{
//			bombCount++;
//		}else {
//			bombCount=0;
//			makeBomb();
//		}
//
//		for(int i=0;i<bombXs.size();i++)
//		{
//			batch.draw(bomb,bombXs.get(i),bombYs.get(i));
//			bombXs.set(i,bombXs.get(i)-18);
//		}
//
//		//COINS
//		if(coinCount<100)
//		{
//			coinCount++;
//		}else {
//			coinCount=0;
//			makeCoin();
//		}
//
//		coinRectangles.clear();
//		for(int i=0;i<coinXs.size();i++)
//		{
//			batch.draw(coin,coinXs.get(i),coinYs.get(i));
//			coinXs.set(i,coinXs.get(i)-10);//to change speed,change val here
//			coinRectangles.add(new Rectangle(coinXs.get(i),coinYs.get(i),coin.getWidth(),coin.getHeight()));
//		}
//
//
//		if(Gdx.input.justTouched())  //jump when the user tap on the screen
//		{
//			velocity=-10;
//		}
//		if(pause<8) {
//			pause++;
//		}
//		else
//		{
//			pause=0;
//			if(manState<3) {  manState++;  }
//			else {  manState=0;  }
//		}
//		velocity += gravity;
//		manY -=velocity;
//		if(manY <=0)
//		{
//			manY=0;
//		}
//		batch.draw(man[manState],Gdx.graphics.getWidth()/2-man[manState].getWidth()/2,manY); // to draw man in the middle
//		batch.end();
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//	}
//}

package com.zappycode.coinage.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.ScreenUtils;


import java.util.ArrayList;
import java.util.Random;

public class Coinage extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] man;
	int manState;  //there are 4 images of man
	int pause;
	float gravity=0.4f;
	float velocity=0;
	int manY=0;  //current position  (Y)
	Rectangle manRectangle;
	int score=0;
	BitmapFont font;
	int gameState=0;
	Texture dizzy;


	Random random;


	ArrayList<Integer> coinXs=new ArrayList<Integer>();//x position of coins
	ArrayList<Integer> coinYs=new ArrayList<Integer>();//y position of coins
	ArrayList<Rectangle> coinRectangles=new ArrayList<Rectangle>();
	Texture coin;
	int coinCount;

	ArrayList<Integer> bombXs=new ArrayList<Integer>();//x position of coins
	ArrayList<Integer> bombYs=new ArrayList<Integer>();//y position of coins
	ArrayList<Rectangle> bombRectangles=new ArrayList<Rectangle>();
	Texture bomb;
	int bombCount;


	@Override
	public void create () {
		batch = new SpriteBatch();
		background=new Texture("bg.png");
		man=new Texture[4];
		man[0]=new Texture("frame-1.png");
		man[1]=new Texture("frame-2.png");
		man[2]=new Texture("frame-3.png");
		man[3]=new Texture("frame-4.png");
		manY=Gdx.graphics.getHeight()/2;  //initial pos of man.  Middle of screen

		coin=new Texture("coin.png");//setting up coin
		bomb=new Texture("bomb.png");//setting up bomb
		random=new Random();
		font=new BitmapFont();  //score font
		font.setColor(Color.WHITE);
		font.getData().setScale(10);
		dizzy=new Texture("dizzy.png");
	}

	public void makeCoin() //as there are multiple coins we are using a method
	{
		float height=random.nextFloat()*Gdx.graphics.getHeight();//to keep coins at diff heights
		coinYs.add((int)height); // y should be random
		coinXs.add(Gdx.graphics.getWidth()); //x is fixed
	}

	public void makeBomb() //as there are multiple coins we are using a method
	{
		float height=random.nextFloat()*Gdx.graphics.getHeight();//to keep coins at diff heights
		bombYs.add((int)height); // y should be random
		bombXs.add(Gdx.graphics.getWidth()); //x is fixed
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


		if(gameState==1){
			//Game is alive
			//BOMB
			if(bombCount<250)
			{
				bombCount++;
			}else {
				bombCount=0;
				makeBomb();
			}

			bombRectangles.clear();
			for(int i=0;i<bombXs.size();i++) {
				batch.draw(bomb, bombXs.get(i), bombYs.get(i));
				bombXs.set(i,bombXs.get(i)-5);//to change speed,change val here
				bombRectangles.add(new Rectangle(bombXs.get(i),bombYs.get(i),bomb.getWidth(),bomb.getHeight()));
			}


			//COINS
			if(coinCount<100)
			{
				coinCount++;
			}else {
				coinCount=0;
				makeCoin();
			}

			coinRectangles.clear();
			for(int i=0;i<coinXs.size();i++) {
				batch.draw(coin, coinXs.get(i), coinYs.get(i));
				coinXs.set(i,coinXs.get(i)-5);//to change speed,change val here
				coinRectangles.add(new Rectangle(coinXs.get(i),coinYs.get(i),coin.getWidth(),coin.getHeight()));

			}


			if(Gdx.input.justTouched())  //jump when the user tap on the screen
			{
				velocity=-10;
			}
			if(pause<8) {
				pause++;
			}
			else
			{
				pause=0;
				if(manState<3) {  manState++;  }
				else {  manState=0;  }
			}
			velocity += gravity;
			manY -=velocity;
			if(manY <=0)
			{
				manY=0;
			}
		}
		else if(gameState==0)
		{
			//waiting to start
			if(Gdx.input.justTouched())
				gameState=1;
		}
		else if(gameState==2){
			//game over
			if(Gdx.input.justTouched())
				gameState=1;
			manY=Gdx.graphics.getHeight()/2;
			score=0;
			velocity=0;
			coinYs.clear();
			coinXs.clear();
			coinRectangles.clear();
			coinCount=0;
			bombYs.clear();
			bombXs.clear();
			bombRectangles.clear();
			bombCount=0;
		}

		if(gameState==2) {   //dizzy man image when game is over
			batch.draw(dizzy, Gdx.graphics.getWidth() / 2 - man[manState].getWidth() / 2, manY); // to draw man in the middle
		}
		else{
			batch.draw(man[manState], Gdx.graphics.getWidth() / 2 - man[manState].getWidth() / 2, manY); // to draw man in the middle
		}



		manRectangle=new Rectangle(Gdx.graphics.getWidth()/2-man[manState].getWidth()/2,manY,man[manState].getWidth(),man[manState].getHeight());

		//collision with coin
		for(int i=0;i<coinRectangles.size();i++) {
			if (Intersector.overlaps(manRectangle, coinRectangles.get(i))) {
				score++;
				coinRectangles.remove(i);
				coinXs.remove(i);
				coinYs.remove(i);
				break;
			}
		}

		//collision with bomb
		for(int i=0;i<bombRectangles.size();i++) {
			if (Intersector.overlaps(manRectangle, bombRectangles.get(i))) {
				gameState=2;
			}
		}


		font.draw(batch,String.valueOf(score),100,200);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}

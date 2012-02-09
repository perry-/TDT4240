package com.example.mygame;


import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;


public class TitleScreen extends State {
	private Image heliImage = new Image(R.drawable.heli1_east);
	private Image aImage = new Image(R.drawable.icon);
	private Image wallVerImage = new Image(R.drawable.wall_vertical);
	private Image backgroundImage = new Image(R.drawable.background);
	private Sprite aSprite;
	private Sprite westWall;
	private Sprite backSprite;
	private Sprite heliSprite;
	 
	
	
	public TitleScreen() {
		backSprite = new Sprite(backgroundImage);
		heliSprite = new Sprite(heliImage);
		aSprite = new Sprite(aImage);
		westWall = new Sprite(wallVerImage);
		westWall.setPosition(4, 215);
		aSprite.setPosition(200, 120);
		aSprite.setSpeed(40, 0); // it should move right direction, but since collides bug, it will move (-40,0),  If we input (-40,0), it move (40,0), after collides, helicopter is disappeared. bug?
		heliSprite.setPosition(40, 120);
		heliSprite.setSpeed(40, 0);
		 
		}
	
	public void draw(android.graphics.Canvas canvas){
		backSprite.draw(canvas);
	    
	    westWall.draw(canvas);
	    aSprite.draw(canvas);
	    heliSprite.draw(canvas);
	    }
	
	public void update(float dt) {
		 
		if(aSprite.getX()>=300)
		 {
			System.out.println("crash east border!");
		aSprite.setSpeed(-aSprite.getSpeed().getX(), aSprite.getSpeed().getY());
		}
		
		else if(aSprite.collides(westWall)) // collides is true first time, and change the object direction.
		{
		System.out.println("crash west border!");
		aSprite.setSpeed(-aSprite.getSpeed().getX(), aSprite.getSpeed().getY());
		}
		
		else if(aSprite.collides(heliSprite)) // This collides is judged since the above collides. First execution collides will be true at the first time without any judge.
		{
		System.out.println("crash each other!");
		aSprite.setSpeed(-aSprite.getSpeed().getX(), aSprite.getSpeed().getY());
		heliSprite.setScale(-1, 1);
		heliSprite.setPosition(heliSprite.getPosition().getX() + heliImage.getWidth(), heliSprite.getPosition().getY());
		heliSprite.setSpeed(-aSprite.getSpeed().getX(), aSprite.getSpeed().getY());
		}
		
		if(heliSprite.getX()>=300)
		 {
			System.out.println("crash east border!");
			heliSprite.setSpeed(-heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
		}
		
		westWall.update(dt);
		aSprite.update(dt);
		heliSprite.update(dt);
		
	}
}

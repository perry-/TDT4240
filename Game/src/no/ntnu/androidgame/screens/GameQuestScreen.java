package no.ntnu.androidgame.screens;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.GameMusic;
import no.ntnu.androidgame.models.Building;
import no.ntnu.androidgame.models.GameQuest;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;

/**
 * The game quest screen is the starting point of a new game.
 * 
 * When active, the screen draws a map of gloshaugen with its buildings, an explanation
 * of the symbols, and whether or not the buildings are locked, open, or completed. 
 * You can click on a building to start playing it.
 * 
 * The model holds a list of buildings. When starting to play, push
 * a building screen with the appropriate model.
 * 
 * @author vegare, raudsand
 * @see GameQuest
 */
public class GameQuestScreen extends State {
	
	private ArrayList<Building> buildings;
	
	private GameQuest model;
	
	private Image glos;
	
	public GameQuestScreen(GameQuest model) {
		this.model = model;		
		this.addTouchListener(new TouchListener() {
			
			@Override
			public boolean onTouchUp(MotionEvent event) {
				for(Building b:buildings){
					if (b.getOpen_image().getBoundingBox().contains(event.getX()-b.getImage_xpos(), event.getY()-b.getImage_ypos())) {
						getGame().pushState(new BuildingScreen(GameQuestScreen.this, b));
					} 
				}
				return false;
			}
			
			@Override
			public boolean onTouchMove(MotionEvent event) {
				return false;
			}
			
			@Override
			public boolean onTouchDown(MotionEvent event) {
				return false;
			}
		});
		
		glos = Constants.GLOSHAUGENMAP;
		buildings = model.getBuildings();
	}
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		glos.draw(canvas,0,0);
		
		for(Building b:buildings){
			if (b.isLocked()) {
				b.getClosed_image().draw(canvas, b.getImage_xpos(),b.getImage_ypos());
			} else if (b.isComplete()) {
				b.getCompleted_image().draw(canvas, b.getImage_xpos(),b.getImage_ypos());
			} else {
				b.getOpen_image().draw(canvas, b.getImage_xpos(),b.getImage_ypos());
			}
		}
	}
	/**
	 * Stops the level music when the Building given as argument is completed.
	 * Pushes ThankyouScreen
	 * 
	 * @param completed - The Building that is completed
	 */
	public void buildingComplete(Building completed) {
		getGame().popState(); // Pops building
		GameMusic.stopLevelMusic();
		
		// Check game status
		if (model.isComplete()) {
			getGame().popState(); // Pops GameQuest
			
			GameMusic.stopLevelMusic();
			getGame().pushState(new VideoScreen(2));
			
		} else if (model.allBuildingsLocked()) {
			model.unlockBuilding();
			getGame().pushState(new ThankyouScreen());
		} else {
			getGame().pushState(new ThankyouScreen());
		}
	}
}
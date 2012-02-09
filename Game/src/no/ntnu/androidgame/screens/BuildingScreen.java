package no.ntnu.androidgame.screens;

import android.graphics.Canvas;

import android.graphics.Paint;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.GameMusic;
import no.ntnu.androidgame.models.Building;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * The building screen draws a building, and maintains progression.
 * 
 * When active, information about the building is drawn. This screen
 * also pushes and pops the level screens that belongs to it.
 * 
 * Implements WidgetListener to react to events on widgets
 * 
 * @author vegare
 * @see Building
 */
public class BuildingScreen extends State implements WidgetListener {

	private GameQuestScreen parent;
	private Image background = Constants.BACKGROUND;
	
	private Building model;

	private TextButton startBtn;
	private TextButton returnBtn;
	
	/**
	 * Constructor that creates buttons and stops the menumusic
	 * 
	 * @param parent - GameQuestScreen is the parent of this BuildingScreen
	 * @param model - the Building-model of this screen
	 */
	public BuildingScreen(GameQuestScreen parent, Building model) {
		this.model = model;
		this.parent = parent;
		
		startBtn = new TextButton(25f, 360f, "Play building", Constants.buttonPaint);
		addTouchListener(startBtn);
		startBtn.addWidgetListener(this);
		
		returnBtn = new TextButton(25f, 400f, "Return to Gloeshaugen", Constants.buttonPaint);
		addTouchListener(returnBtn);
		returnBtn.addWidgetListener(this);
		
		GameMusic.stopMenuMusic();
	}
	/**
	 * Method that is invoked when an action has been performed with an widget
	 * on the screen.
	 * 
	 * If play building button is pressed, the current level is pushed on the statestack
	 * If return button is pressed, the gloeshaugen map is presented
	 * 
	 * @author rodsjo, vegare
	 * @param action - WidgetActino that has taken place
	 */
	@Override
	public void actionPerformed(WidgetAction action) {
		if (action.getSource() == startBtn) {
			GameMusic.playLevelMusic();
			model.getCurrentLevel().load();
			getGame().pushState(new LevelScreen(this, model.getCurrentLevel()));
		} else if (action.getSource() == returnBtn) {
			getGame().popState(); // Pops building
		}
	}

	/**
	 * This method will pop the current level screen, and proceed with the game.
	 * 
	 * If more levels remain in the building, the next level will be pushed.
	 * If the building is complete, the building screen will pop itself.
	 */
	public void nextLevel() {
		getGame().popState(); // Pops level screen
		model.getCurrentLevel().unload();
		model.increaseLevel();
		
		if (model.isComplete()) {
			parent.buildingComplete(model);
		} else {
			model.getCurrentLevel().load();
			getGame().pushState(new LevelScreen(this, model.getCurrentLevel()));
		}
	}
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		background.draw(canvas, 0, 0);
		model.getPhoto().draw(canvas, 50f, 80f);
		
		Paint p = new Paint(Font.WHITE_SANS_BOLD_16);
				
		if (model.isLocked()) {
			canvas.drawText(model.getName() + " is locked!", Constants.WINDOW_WIDTH*0.1f, 50f, p);
		}
		else if (model.isComplete()) {
			canvas.drawText(model.getName() + " is completed!", Constants.WINDOW_WIDTH*0.1f, 50f, p);
		} else {
			canvas.drawText(model.getName() + " is still unconquered!", Constants.WINDOW_WIDTH*0.1f, 50f, p);
			startBtn.draw(canvas);
		}

		canvas.drawText(model.getDescription(), 25f, 270f, p);
		returnBtn.draw(canvas);
	}
}
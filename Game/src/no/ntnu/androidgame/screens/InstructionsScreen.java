package no.ntnu.androidgame.screens;

import no.ntnu.androidgame.Constants;

import android.graphics.Canvas;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;
/**
 * Informs the user about how to play the game. Game controls and informs about enemies and
 * crates in the game.
 * 
 * The text and images are statically implemented to ease the layout and implementation
 * 
 * @author rodsjo
 */
public class InstructionsScreen extends State implements WidgetListener {
	
	private Image instructions = Constants.INSTRUCTIONS;
	private Image instructionsEnemies = Constants.INSTRUCTIONS_ENEMIES;
	
	private TextButton nextBtn;
	
	private int clicks = 0;
	
	/**
	 * Constructor that creates the buttons
	 * 
	 * @author rodsjo
	 */
	public InstructionsScreen() {

		nextBtn = new TextButton(Constants.WINDOW_WIDTH*0.8f, Constants.WINDOW_HEIGHT*0.9f, "Next");
		addTouchListener(nextBtn);
		nextBtn.addWidgetListener(this);
	}
	/**
	 * Draws the images and buttons to the canvas
	 * 
	 * @author rodsjo
	 */
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		if (clicks == 0) {
			instructions.draw(canvas, 0, 0);
		} else {
			instructionsEnemies.draw(canvas, 0, 0);
		}
		
		nextBtn.draw(canvas);
	}
	/**
	 * Method that is invoked when an action has been performed with an widget
	 * on the screen
	 * 
	 * If next button is pressed, the next image is shown
	 * If menu button is pressed, this screen is popped away in the sky
	 */
	@Override
	public void actionPerformed(WidgetAction action) {
		
		if (clicks == 0) {
			clicks = 1;
		} else {
			clicks = 0;
			getGame().popState();
		}
	}
}
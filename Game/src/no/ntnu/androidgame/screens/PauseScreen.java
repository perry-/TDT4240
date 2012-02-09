package no.ntnu.androidgame.screens;

import no.ntnu.androidgame.Constants;
import android.graphics.Canvas;

import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * This screen is active when the user pause the game.
 * It displays pause graphics and a button to return (by popping).
 * 
 * @author vegare, rodsjo
 */
public class PauseScreen extends State implements WidgetListener {

	private Image background = Constants.BACKGROUND_WITH_LOGO;
	
	private TextButton backBtn;
	
	/**
	 * Constructor. Creates a Pause-screen that pauses the game. Has a back-button
	 */
	public PauseScreen() {
		backBtn = new TextButton(100f, 225f, "Return to game");
		backBtn.addWidgetListener(this);
		addTouchListener(backBtn);
	}
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		background.draw(canvas, 0, 0);
		backBtn.draw(canvas);
	}
	@Override
	public void actionPerformed(WidgetAction action) {
		if (action.getSource() == backBtn) {
			getGame().popState(); // Pops PauseScreen
		}
	}
}
package no.ntnu.androidgame.screens;

import no.ntnu.androidgame.Constants;

import no.ntnu.androidgame.Main;

import no.ntnu.androidgame.R;
import android.graphics.Canvas;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * This is the first screen presented to the user. It displays a warning screen to the user
 * informs him/her about what (s)he can expect to meet in the game.
 * 
 * The text and warning-symbols are statically implemented with a picture
 * 
 * @author rodsjo
 *
 */
public class WarningScreen extends State implements WidgetListener{
	
	private Image warning = new Image(R.drawable.warning);
	private TextButton startBtn;
	
	/**
	 * Creates a new WarningScreen. The warningscreen contains warnings of what to expect in the game.
	 * Has a next-button that when pressed, pushes a new IntroScreen()
	 */
	public WarningScreen(Main main) {
		startBtn = new TextButton(Constants.WINDOW_WIDTH*0.45f, Constants.WINDOW_HEIGHT*0.95f, "start", Constants.buttonPaint);
		startBtn.addWidgetListener(this);
		addTouchListener(startBtn);
		
		//GameMusic.loadSound(main);
	}
	@Override
	public void draw(Canvas canvas) {
		warning.draw(canvas, 0, 0);
		startBtn.draw(canvas);
	}
	@Override
	public void actionPerformed(WidgetAction action) {
		if (action.getSource() == startBtn) {
			getGame().popState();
			getGame().pushState(new IntroScreen());
		}
	}
}
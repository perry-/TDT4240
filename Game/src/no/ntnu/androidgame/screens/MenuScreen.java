package no.ntnu.androidgame.screens;

import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.models.GameQuest;
import android.graphics.Canvas;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * Menu-screen of the game. The user can press different buttons to go to different screens.
 * When the user presses "Start Game"-button a GameQuest is made, and a VideoScreen is pushed to the
 * State-stack
 * 
 * Implements WidgetListener
 * 
 * @author rodsjo
 */
public class MenuScreen extends State implements WidgetListener {
	
	private Image background = Constants.BACKGROUND_WITH_LOGO;
	private TextButton creditsBtn, instructionsBtn, startGameBtn;
	
	/**
	 * Creates the buttons, and adds them to the widgetlistener. 
	 * 
	 * @author rodsjo
	 */
	public MenuScreen() {
		creditsBtn = new TextButton(Constants.WINDOW_WIDTH*0.35f, Constants.WINDOW_HEIGHT*0.7f, "Credits", Constants.buttonPaint);
		instructionsBtn = new TextButton(Constants.WINDOW_WIDTH*0.32f, Constants.WINDOW_HEIGHT*0.6f, "Instructions", Constants.buttonPaint);
		startGameBtn = new TextButton(Constants.WINDOW_WIDTH*0.30f, Constants.WINDOW_HEIGHT*0.4f, "Start game", Constants.startButtonPaint);
		
		addTouchListener(creditsBtn);
		addTouchListener(instructionsBtn);
		addTouchListener(startGameBtn);
		
		creditsBtn.addWidgetListener(this);
		instructionsBtn.addWidgetListener(this);
		startGameBtn.addWidgetListener(this);
	}
	/**
	 * Draws the background and all buttons
	 * 
	 * @author rodsjo
	 */
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		background.draw(canvas, 0, 0);
		creditsBtn.draw(canvas);
		instructionsBtn.draw(canvas);
		startGameBtn.draw(canvas);
	}
	/**
	 * When the user interacts width the screen, actions are made. When the actions are at the buttons,
	 * different screens are pushed at the State-stack.
	 * 
	 * When start game button is pressed a new GameQuest is made, and a VideoScreen is pushed
	 * on the state stack
	 * 
	 * @author rodsjo
	 */
	@Override
	public void actionPerformed(WidgetAction action) {
		if (action.getSource() == creditsBtn) {
			getGame().pushState(new AboutScreen());
		} else if (action.getSource() == instructionsBtn) {
			getGame().pushState(new InstructionsScreen());
		} else if (action.getSource() == startGameBtn) {
			// Creates a new Game
			GameQuest gq = new GameQuest();
			getGame().pushState(new GameQuestScreen(gq));
			getGame().pushState(new VideoScreen(0));
		}	
	}
}

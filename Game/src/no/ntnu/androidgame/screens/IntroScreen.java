package no.ntnu.androidgame.screens;

import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.GameMusic;
import android.graphics.Canvas;
import android.graphics.Paint;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;
 
/**
 * This screen presents the logo and the history and plot of the game
 * 
 * Has a skip intro button to go directly to the menu screen
 * Has e next button to see rest of the history and gameplot.
 * 
 * @author rodsjo
 */
public class IntroScreen extends State implements WidgetListener {
	
	private TextButton nextBtn;
	
	private Image[] alfieAndAndroid = {Constants.ALFIE_LOVES_ANDROID_FRAME0, 
			Constants.ALFIE_LOVES_ANDROID_FRAME1, 
			Constants.ALFIE_LOVES_ANDROID_FRAME2,
			Constants.ALFIE_LOVES_ANDROID_FRAME1};
	
	private static Image background = Constants.BACKGROUND_WITH_LOGO;
	
	private int clicks = 0;
	private int numberOfLettersOnOneLine = 45;
	private float counter = 0;
	private float frame_length = 0.2f;
	
	private String historyText1; 	// The text is dived into two parts because it's too long
	private String historyText2;	// to fit into one screen
	
	private boolean showHistoryText2 = false; 	// Is set to true when user presses "next..." button
	/**
	 * Default constructor. Creates the history text and buttons. Starts playing menu music
	 * 
	 */
	public IntroScreen() {
		historyText1 = "NTNU, Gloeshaugen 2010: The Brainwasher aka. Alf Inge Wang has just brainwashed" +
		" three fellow colleagues from the Department of Computer and Information Science to follow him" +
		" and his love for Android. The BrainWasher is in charge of the popular course TDT4240 Software" +
		" Architecture.";

		historyText2 = "He has convinced his three teaching assistants from his department that Android would be a" +
		" suitable platform for this course's exercises. The technical assistants (Guldbrandsen and Storstein) has of course" +
		" realized that he is mentally deranged and crazy, and tried to stop him. In their attempt, they got" +
		" caught and are now prisoners at Gloeshaugen campus. Your task is to free the technical assistants" +
		" by any means necessary and stop the Android evolution once and for all for this course. " +
		" If you succeed in freeing the technical assistants in this game you will be saluted with" +
		" glory and honour for the rest of your days. Now, please hurry and save the technical assistants from the crude BrainWasher!" ;;

		nextBtn = new TextButton(Constants.WINDOW_WIDTH*0.4f, Constants.WINDOW_HEIGHT*0.95f, "next...", Constants.buttonPaint);
		nextBtn.addWidgetListener(this);
		addTouchListener(nextBtn);
		
		GameMusic.playMenuMusic();
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		background.draw(canvas, 0, 0);
		nextBtn.draw(canvas);
		
		Paint paint = new Paint();
		paint.setColor(Constants.HISTORYTEXTCOLOR);
		
		if (showHistoryText2) {
			// drawText can only print one line at a time, so needs a method to create linebrakes
			printText(historyText2, canvas, 25f, 130f, paint);
			
		} else {
			printText(historyText1, canvas, 25f, 280f, paint);
			Image animation = alfieAndAndroid[(int) (Math.floor(counter/frame_length) % alfieAndAndroid.length)];
			animation.draw(canvas, 60f, 80f);
		}
	}
	/**
	 * Method that prints text to the canvas, using canvas.drawText. The text is broken up in lines,
	 * and the length of one line is set by the private variable numberOfLettersOnOneLine
	 * 
	 * @param text - The text that should be displayed
	 * @param canvas - Canvas where the text should be placed
	 * @param x - x-position of text origin
	 * @param y - y-position of text origin
	 * @param paint - the paint-formatting used on the text
	 */
	private void printText(String text, Canvas canvas, float x, float y, Paint paint) {
		String line = "";
		for (int i = 0; i < text.length(); i++) {
			if (text.length() > numberOfLettersOnOneLine) {
				line = text.substring(0, numberOfLettersOnOneLine);
				canvas.drawText(line, x, y + i*14, paint);

				text = text.substring(numberOfLettersOnOneLine);
			} else {
				canvas.drawText(text, x, y + i*14, paint);
				break;
			}
		}
	}
	@Override
	public void update(float dt) {
		super.update(dt);
		counter += dt;
	}
	/**
	 * Method that is invoked when an action has been performed with an widget
	 * on the screen
	 * 
	 * @author rodsjo
	 */
	@Override
	public void actionPerformed(WidgetAction action) {
		
		if (clicks == 0) {
			clicks = 1;
			showHistoryText2 = true;
		} else {
			getGame().popState(); // This state is popped away from the stack
			getGame().pushState(new MenuScreen());
		}
	}
}
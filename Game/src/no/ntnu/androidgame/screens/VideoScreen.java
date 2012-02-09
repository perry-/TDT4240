package no.ntnu.androidgame.screens;

import no.ntnu.androidgame.Constants;
import android.graphics.Canvas;
import android.graphics.Paint;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * A video screen plays a full screen animation, and then pops itself.
 * 
 * @author vegare, rodsjo
 */
public class VideoScreen extends State implements WidgetListener {

	private TextButton continueBtn;
	
	private float counter = 0;
	private float frame_length = 0.3f;
	
	private int numberOfLettersOnOneLine = 45;
	
	private String textWhenDead;
	private String textWhenAlive;
	private String textWhenWon;
	
	// Animation id tells which animation to be played
	// and which text to appear
	// 0 for new quest (InfoHilde)
	// 1 for dead (Alfie laughs)
	// 2 game completed (Technical assistants salute)
	// 3 show gloshaugen info picture
	private int animation_id;
	
	private Image background = Constants.BACKGROUND;
	
	private Image[] alfie_laugh_mohahaha = {Constants.ALFIE_LAUGH_MOHAHAH_FRAME0, 
			Constants.ALFIE_LAUGH_MOHAHAH_FRAME1, 
			Constants.ALFIE_LAUGH_MOHAHAH_FRAME0, 
			Constants.ALFIE_LAUGH_MOHAHAH_FRAME2};
	/**
	 * Constructor. Creates an animation based on if the player is alive or dead
	 * 
	 * @param animation_id - 0 for alive, 1 for dead
	 */
	public VideoScreen(int animation_id) {
		this.animation_id = animation_id;
		
		textWhenDead = "MOHAHAHAHAHA! You died! I Win! ANDROID FOR THE WIN!";
		
		textWhenAlive = "Hurry up and explore the Gloeshaugen campus! Free the" +
				" technical assistants so they can help us with our Android-problems!" +
				" Good luck on your journey... Take care... Greetings from Info-Hilde<3";
		
		textWhenWon = "Congratulations! You have made it through the game! Kjetil Guldbrandsen and" +
				" Kjell Ivar Storstein salutes you! They can help you with all your Android problems." +
				" Feel free to contact them at any time, and may Alf Inge Wang remember the moral of this" +
				"game for next years...";
		
		continueBtn = new TextButton(Constants.WINDOW_WIDTH*0.7f, Constants.WINDOW_HEIGHT*0.95f, "continue", Constants.buttonPaint);
		addTouchListener(continueBtn);
		continueBtn.addWidgetListener(this);
	}
	/**
	 * Draws text, animation, background and buttons to the canvas
	 */
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		background.draw(canvas, 0, 0);
		
		Paint paint = new Paint();
		paint.setColor(Constants.HISTORYTEXTCOLOR);
		
		if (animation_id == 0) { // Player starts a new quest
			canvas.drawText("Good luck!", 100f, 50f, Font.WHITE_SANS_BOLD_20);
			Image infohilde = Constants.INFOHILDE;
			infohilde.draw(canvas, 50f, 70f);
			printText(textWhenAlive, canvas, 25f, 320f, paint);
			
		} else if (animation_id == 1) { // Player died
			canvas.drawText("You died...", 100f, 50f, Font.RED_SANS_BOLD_20);
			printText(textWhenDead, canvas, 25f, 250f, paint);
			
			Image animation = alfie_laugh_mohahaha[(int) (Math.floor(counter/frame_length) % alfie_laugh_mohahaha.length)];
			animation.draw(canvas, 120f, 100f);
		} else if (animation_id == 2) { // Player has completed the game
			canvas.drawText("CONGRATULATIONS!", 50f, 50f, Font.WHITE_SANS_BOLD_20);
			Image gold_cup = Constants.GOLD_CUP;
			gold_cup.draw(canvas, 0f, 70f);
			printText(textWhenWon, canvas, 25f, 300f, paint);
		} else if (animation_id == 3) { // Player starts a new quest, and information picture is shown
			canvas.drawText("Welcome to Gloeshaugen", 40f, 50f, Font.WHITE_SANS_BOLD_20);
			Image gloshaugen_info = Constants.GLOSHAUGEN_INFO;
			gloshaugen_info.draw(canvas, 50f, 70f);
		}
		continueBtn.draw(canvas);
	}
	/**
	 * Method that prints text to the canvas, using canvas.drawText. The text is broken up in lines,
	 * and the length of one line is set as the private variable numberOfLettersOnOneLine
	 * 
	 * This method can also be used by other screen-classes
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
	@Override
	public void actionPerformed(WidgetAction action) {
		if (action.getSource() == continueBtn) {
			if (animation_id == 0) { // If it's a new quest, show gloshaugen info picture
				getGame().popState(); // Pops the video screen
				getGame().pushState(new VideoScreen(3));
			} else {
				getGame().popState(); // Pops the video screen, behind it should be a GameQuestScreen
			}
		}
	}
}

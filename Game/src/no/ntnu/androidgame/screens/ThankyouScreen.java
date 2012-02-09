package no.ntnu.androidgame.screens;

import android.graphics.Canvas;

import android.graphics.Typeface;
import android.view.MotionEvent;
import no.ntnu.androidgame.Constants;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;

/**
 * Placed after a building, to indicate that the player must move on.
 * 
 * @author vegare
 *
 */
public class ThankyouScreen extends State {

	private static final String msg  = "Thank you!";
	private static final String msg2 = "But the teaching assistants ";
	private static final String msg3 = "are  in  another  building!";
	
	private float counter = 0;
	private float frame_length = 0.3f;
	private static final float MAX_WAIT = 10.0f;
	
	private static Image[] frames = {Constants.THANKYOU_FRAME0, Constants.THANKYOU_FRAME1};

	@Override
	public boolean onTouchUp(MotionEvent event) {
		getGame().popState(); // Video screen
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		// Draw background
		Image background = frames[(int) (Math.floor(counter/frame_length) % frames.length)];
		background.draw(canvas, 0, 0);
		
		// Draw text
		Font f = new Font(255, 255, 255, 16, Typeface.SANS_SERIF, Typeface.BOLD);
		canvas.drawText(msg, 50, 150, f);
		canvas.drawText(msg2, 50, 200, f);
		canvas.drawText(msg3, 50, 220, f);
	}
	@Override
	public void update(float dt) {
		super.update(dt);
		counter += dt;
		if (counter > MAX_WAIT) {
			getGame().popState(); // Pops Video screen
		}
	}
}
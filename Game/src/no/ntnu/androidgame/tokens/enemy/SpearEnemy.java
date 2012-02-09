package no.ntnu.androidgame.tokens.enemy;

import no.ntnu.androidgame.R;
import sheep.graphics.Image;

/**
 * An enemy without any firearm. May only harm you by collision.
 * 
 * @author vegare
 *
 */
public class SpearEnemy extends Enemy {
	
	private static Image[] se_images = {new Image(R.drawable.se_frame0),new Image(R.drawable.se_frame1),new Image(R.drawable.se_frame2)};

	public SpearEnemy(float tickTime) {
		super(se_images, tickTime);
	}
}

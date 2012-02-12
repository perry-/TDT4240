package no.perry.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * @author Perry
 *
 */
public class Ball extends Sprite {
	private Image image;
	private int spriteWidth, spriteHeight;	// ball dimensions
	static private Ball BallInstance = null;

	/**
	 * Constructor that sets the hitbox and coordinates, while initiating the animation variables
	 * @param bitmap bitmap of the sprite
	 * @param x the x-position of the ball
	 * @param y the y-position of the ball
	 */
	protected Ball(Image img) {
		super(img);
		this.image = img;
		spriteWidth = (int) image.getWidth();
		spriteHeight = (int) image.getHeight();
	}

	public static Ball instance(Image instanceImg) {
		if(null == BallInstance) {
			BallInstance = new Ball(instanceImg);
		}
		return BallInstance;
	}

	/**
	 * Returns the helicopter width
	 * @return spriteWidth the width as integer
	 */
	public int getWidth(){
		return spriteWidth;
	}

	/**
	 * Returns the helicopter height
	 * @return spriteHeight the height as an integer
	 */
	public int getHeight(){
		return spriteHeight;
	}

}

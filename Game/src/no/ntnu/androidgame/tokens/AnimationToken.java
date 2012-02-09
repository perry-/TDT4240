package no.ntnu.androidgame.tokens;

import sheep.graphics.Image;
import sheep.graphics.SpriteView;

/**
 * 
 * Abstract class for the animated tokens i the game.
 * 
 * @author raudsand
 * @see Token
 */
public abstract class AnimationToken extends Token {
	
	// List of frames
	private Image[] images;
	
	private int frameCount;
	private int currentFrame;
	private float tickTime;
	private float timeLeft;
	
	private boolean isAnimated = true;
	
	/**
	 * Constructor
	 * 
	 * @param images		List of sprite images
	 * @param tickTime		Time between each frame
	 * @param currentFrame	First frame of animation
	 */
	public AnimationToken(Image[] images, float tickTime, int currentFrame) {	
		
		super(images[0]);
		
		this.images = images;
		this.tickTime = tickTime;
		this.frameCount = images.length;
		this.currentFrame = currentFrame;
		
		timeLeft = 0f;
	}
	
	/**
	 * Turn animation on or off
	 * 
	 * @param isAnimated Current status
	 */
	public void setAnimated(boolean isAnimated) {
		this.isAnimated = isAnimated;
	}
	
	@Override
	public void update(float dt) {
		
		if (isAnimated) {
			timeLeft += dt;
		}

		// Flip to next frame
		if(timeLeft >= tickTime)
		{
			currentFrame = (currentFrame + 1) % frameCount;
			setView((SpriteView)images[currentFrame]);
			timeLeft -= tickTime;
		}
			
		super.update(dt);
	}
}
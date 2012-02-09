package no.perry.heli;

import sheep.game.Sprite;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

/**
 * Heli object that contains all the attributes for each helicopter
 * @author Perry
 *
 */
public class Heli extends Sprite {
	private Bitmap bitmap;
	private int frameNr, currentFrame, framePeriod;	// variables for shifting the sprite rectangle between frames
	private int spriteWidth, spriteHeight;	// helicopter dimensions
	private int x, y;	// helicopter coordinates
	private long frameTicker;	// the time of the last frame update
	private Rect hitbox; // helicopter hitbox
	private int id, direction = 0;


	/**
	 * Constructor that sets the hitbox and coordinates, while initiating the animation variables
	 * @param bitmap bitmap of the sprite
	 * @param x the x-position of this helicopter
	 * @param y the y-position of this helicopter
	 * @param frameCount number of frames in the sprite
	 */
	public Heli(Bitmap bitmap, int x, int y, int frameCount) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		currentFrame = 0;
		frameNr = frameCount;
		framePeriod = 100;
		spriteWidth = bitmap.getWidth() / frameCount;
		spriteHeight = bitmap.getHeight();
		hitbox = new Rect(0, 0, spriteWidth, spriteHeight);
		this.setShape(spriteWidth, spriteHeight);
		frameTicker = 0l;
	}

	public void update(long dt){
		//		super.update(dt);

		if (dt > frameTicker + framePeriod) {
			frameTicker = dt;
			// increment the frame
			currentFrame++;
			if (currentFrame >= frameNr) {
				currentFrame = 0;
			}
		}
		// define the rectangle to cut out sprite
		this.hitbox.left = currentFrame * spriteWidth;
		this.hitbox.right = this.hitbox.left + spriteWidth;

	}


	@Override
	public void draw(Canvas canvas){
		setX(getX() + getSpeed().getX()/100);
		setY(getY()+ getSpeed().getY()/100);

		Rect destRect = new Rect((int) getX(), (int) getY(), (int) getX() + spriteWidth, (int) getY() + spriteHeight);
		canvas.drawBitmap(bitmap, hitbox, destRect, null);
	}

	/**
	 * Sets the y position
	 * @param f the new y value
	 */
	public void setY(float f) {
		this.y = (int) f;
	}

	/**
	 * Sets the x position
	 * @param f the new x value
	 */
	public void setX(float f) {
		this.x = (int) f;
	}

	public void setPosition(float x, float y){
		this.x = (int) x;
		this.y = (int) y;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
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

	/**
	 * Returns the helicopters ID
	 * @return id The ID as an integer
	 */
	public int getId(){
		return id;
	}

	/**
	 * Sets the helicopters ID
	 * @param id The ID as an integer
	 */
	public void setId(int id){
		this.id = id;
	}

	/** 
	 * A method for flipping the helicopters image, and setting the facing direction
	 */
	public void flip(){
		Matrix m = new Matrix();
		m.preScale(-1, 1);
		Bitmap dst = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
		dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
		bitmap = new BitmapDrawable(dst).getBitmap();

		if(direction == 0){ 
			direction = 1;
		}else if (direction == 1){
			direction = 0;
		}

	}

	public Rect getHitbox(){
		return new Rect((int)getX(), (int)getY(), (int)getX() + spriteWidth, (int)getY() + spriteHeight);
	}

	/** 
	 * A method that returns the facing direction of the heli, where 0 is left and 1 is right
	 * @return either 0 or 1, depending on direction
	 */
	public int getDirection(){
		return this.direction;
	}

}

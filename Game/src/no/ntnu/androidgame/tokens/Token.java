package no.ntnu.androidgame.tokens;

import no.ntnu.androidgame.models.Level;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * This is an abstract class for all tokens i the game. Tokens are like
 * the main character, enemies, crates, weapons, bullets etc. This class extends
 * Sprite, so it has got position, acceleration and speed already
 * 
 * @author rodsjo
 *
 */
public abstract class Token extends Sprite implements CollisionListener {
	
	// Parent level
	protected Level level;
	
	// Stored dimension in pixels
	protected float width = 0;
	protected float height = 0;

	/**
	 * Empty constructor
	 * 
	 * Exists to allow empty token generation from
	 * level parser and similar helping classes.
	 */
	public Token() {}
	
	/**
	 * Image constructor
	 * 
	 * @param image Static image
	 */
	public Token(Image image) {
		super(image);
		
		width = image.getWidth();
		height = image.getHeight();
	}

	/**
	 * This function is used to set any parameter from a text file.
	 * If you want any token specific setters, overload the function
	 * and use super() to go through the common ones.
	 * 
	 * This function is ONLY for values common to all tokens!
	 * 
	 * @param key		Parameter to set
	 * @param value		Value of the parameter
	 */
	public void loadParam(String key, String value) {
		
		if (key.equals("x")) {
			setX(Integer.parseInt(value) + getWidth()/2.0f);
		}
		else if (key.equals("y")) {
			setY(Integer.parseInt(value) - getHeight()/2.0f);
		}
	}
	
	@Override
	public void collided(Sprite a, Sprite b) {}

	//
	// GETTERS AND SETTERS
	//
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setX(float x) {
		setPosition(x, getY());
	}
	
	public void setY(float y) {
		setPosition(getX(), y);
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}
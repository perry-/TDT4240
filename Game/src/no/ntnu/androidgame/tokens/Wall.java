package no.ntnu.androidgame.tokens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * A wall can be either horizontal (floors) or vertical (walls).
 * 
 * The main character can not walk through a wall.
 * It will also kill bullets on collision.
 * 
 * @author vegare
 */
public class Wall extends Token {

	// Thickness in pixels
	public static final int THICKNESS = 5;
	
	protected int height;
	protected int width;
	
	/**
	 * Constructor
	 * 
	 * Used for dynamic wall creation (no size)
	 */
	public Wall() {
		
		// Collision groups
		setGroup(TokenGroups.WALL);
		setMask(TokenGroups.ENEMY | TokenGroups.WALL | TokenGroups.CRATE | TokenGroups.DOOR);
	}
	
	/**
	 * Constructor
	 * 
	 * Used for static wall creation (fixed size)
	 * 
	 * @param x			Left-most x value
	 * @param y			Top-most y value
	 * @param height	Height (0 if horizontal)
	 * @param width		Width  (0 if vertical)
	 */
	public Wall(float x, float y, int height, int width) {
		
		this();
		
		setPosition(x, y);
		
		if (height == 0) {
			setWidth(width);
		} else {
			setHeight(height);
		}
	}
	
	/**
	 * Set the wall to be vertical, with given height
	 * 
	 * @param height Height in pixels
	 */
	private void setHeight(int height) {
		this.height = height;
		this.width  = THICKNESS;
		
		setShape(THICKNESS, height);
	}

	/**
	 * Set the wall to be horizontal, with given width
	 * 
	 * @param width Width in pixels
	 */
	private void setWidth(int width) {
		this.height = THICKNESS;
		this.width  = width;
		
		setShape(width, THICKNESS);
	}
	
	//
	// Wall parameters
	//
	public int 	wallWidth()  	{ return width;  }
	public int 	wallHeight() 	{ return height; }
	public boolean 	isHorizontal() 	{ return width >= height; }
	
	@Override
	public void loadParam(String key, String value) {
		
		super.loadParam(key, value);
		
		// Set wall specific width/height
		if (key.equals("width")) {
			setWidth(Integer.parseInt(value));
		} else if (key.equals("height")) {
			setHeight(Integer.parseInt(value));
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		
		super.draw(canvas);
		
		Paint p = new Paint();
		
		// Shadow
		p.setColor(Color.BLACK);
		canvas.drawRect(getX()+1, getY()+1, getX()+width+1, getY()+height+1, p);
		
		// Wall
		p.setColor(Color.rgb(184, 138, 0));
		canvas.drawRect(getX(), getY(), getX()+width, getY()+height, p);
	}
}
package no.ntnu.androidgame.tokens.weapons;

import no.ntnu.androidgame.tokens.Token;
import no.ntnu.androidgame.tokens.TokenGroups;
import sheep.collision.Rectangle;
import sheep.game.Sprite;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * A bullet is a token created by a weapon. It has a given
 * speed and angle, and is destroyed whenever it hits a 
 * wall or a human.
 * 
 * @author vegare
 * @see Weapon
 */
public class Bullet extends Token {

	// Speed of bullet in pixels per second
	private static final float SPEED = 100f;
	
	// Radius in pixels
	private static final float RADIUS = 2f;
	
	// Max life time of bullet (in seconds)
	private float lifetime  = 3;
	
	/**
	 * Constructor with default angle
	 * 
	 * @param x			Start x position
	 * @param y			Start y position
	 * @param moveLeft	Direction to move
	 */
	public Bullet(float x, float y, boolean moveLeft) {
		
		this(x, y, moveLeft, 0);
	}
	
	/**
	 * Constructor with specific angle
	 * 
	 * @param x			Start x position
	 * @param y			Start y position
	 * @param moveLeft	Direction to move
	 * @param angle		Movement angle in degrees
	 */
	public Bullet(float x, float y, boolean moveLeft, float angle) {
		
		float xSpeed = (float) (SPEED * Math.cos( angle*Math.PI / 180 ));
		float ySpeed = (float) (SPEED * Math.sin( angle*Math.PI / 180 ));
		
		setPosition(x, y);
		setSpeed((moveLeft ? -xSpeed : xSpeed), -ySpeed);
		
		setOffset(RADIUS, RADIUS);
		setShape(new Rectangle(RADIUS, RADIUS));
		
		// Collision groups
		setGroup(TokenGroups.BULLET);
		setMask(TokenGroups.CRATE | TokenGroups.DOOR | TokenGroups.BULLET);
	}
	
	@Override
	public void collided(Sprite a, Sprite b) {
		
		// If a bullet collides with something, it should die
		level.removeToken(this);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		lifetime -= dt;
		
		if (lifetime < 0) {
			level.removeToken(this);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		Paint p = new Paint();
		p.setColor(Color.WHITE);
		
		canvas.drawCircle(getX(), getY(), RADIUS, p);
	}
}
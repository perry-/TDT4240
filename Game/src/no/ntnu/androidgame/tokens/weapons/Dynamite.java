package no.ntnu.androidgame.tokens.weapons;

import android.graphics.Canvas;
import sheep.game.Sprite;
import sheep.graphics.Image;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.tokens.Token;
import no.ntnu.androidgame.tokens.Wall;

/**
 * Dynamite! Boom
 * 
 * @author rodsjo, vegare
 *
 */
public class Dynamite extends Token {

	private float timer = Constants.DYNAMITE_TIME;
	private static Image dynamite = Constants.DYNAMITE;

	private boolean wallUnder = false;
	
	public Dynamite(float x, float y) {
		super(dynamite);
		setPosition(x, y);
		setSpeed(0, 0);
		Constants.SOUND_FUSE.play();
	}
	
	/**
	 * If we collide with a floor, make sure the player
	 * is located directly above it, not in the middle.
	 */
	private void snapToFloor(Wall w) {
		wallUnder = true;
		setY(w.getY() - 20/2.0f);
	}
	
	@Override
	public void collided(Sprite a, Sprite b) {
		if (b instanceof Wall) {
			// Check wall location
			Wall w = (Wall) b;
			if (w.isHorizontal()) {
				snapToFloor(w);
			}
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		
		// This fixes an obscure bug, that occurs
		// when you draw the dynamite the first time.
		// For some reason, it's located at (0,0) for
		// a short while, before being moved. By skipping
		// one frame, we are able to avoid the bug.
		if (timer < Constants.DYNAMITE_TIME) {
			super.draw(canvas);
		}
	}

	@Override
	public void update(float dt) {
		// Check timer
		timer -= dt;
		if (timer <= 0) {
			explode();
		}
		
		// Add acceleration
		setYSpeed(Math.min(Constants.TERMINAL_VELOCITY,
						   getSpeed().getY() + Constants.GRAVITY * dt));
		
		// Check wall collision
		if (wallUnder) setYSpeed(Math.min(0, getSpeed().getY()));
		
		super.update(dt);
	}

	private void explode() {
		
		level.removeToken(this);
		level.addToken(new Explosion(getX(), getY()));
		// TODO: Play sound
	}
}

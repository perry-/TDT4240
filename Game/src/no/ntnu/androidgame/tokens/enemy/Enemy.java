package no.ntnu.androidgame.tokens.enemy;

import sheep.game.Sprite;
import sheep.graphics.Image;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.models.weapons.Weapon;
import no.ntnu.androidgame.tokens.AnimationToken;
import no.ntnu.androidgame.tokens.TokenGroups;
import no.ntnu.androidgame.tokens.weapons.Bullet;
import no.ntnu.androidgame.tokens.weapons.Explosion;

/**
 * Abstract Enemy class 
 * 
 * @author rodsjo
 *
 *
 * @todo Generalize. A lot!
 *
 */
public abstract class Enemy extends AnimationToken {

	// Seconds to wait before turning
	protected static float WAIT_TIME = 1;
	
	// Weapon
	protected Weapon weapon;
	
	// Used to determine direction of enemy
	protected boolean facingLeft = false;
	
	// How far the enemy should move
	// (positive = right, negative = left)
	protected int move;
	
	// Starting x position
	protected int startX;
	
	// Wait counter
	protected float wait = 0;
	
	
	public Enemy(Image[] e_images, float tickTime) {
		super(e_images ,tickTime, 0);
		
		// Collision groups
		setGroup(TokenGroups.ENEMY);
		setMask(TokenGroups.WALL | TokenGroups.CRATE | TokenGroups.DOOR);
	}

	
	@Override
	public void collided(Sprite a, Sprite b) {

		if (b instanceof Bullet || b instanceof Explosion) {
			Constants.SOUND_SCREAM.play();
			level.removeToken(this);
		}
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		
		// Are we waiting
		if (wait > 0) {
			wait -= dt;
			
			// Are we done waiting?
			if (wait <= 0) {
				wait = 0;
				facingLeft = !facingLeft;
			}

			return;
		}
		
		// Should we be waiting? Or move?
		// Also flipping and stops the animation at the appropriate directions.
		float x = getX();
		float translate = Constants.ENEMY_SPEED * dt;
		
		if (facingLeft) {
			setFlipVertical(true);	
			if ((x - translate) < startX) {
				wait = WAIT_TIME;
				setAnimated(false);
			} else {
				setX(x - translate);
				setAnimated(true);
			}
			
		} else {
			setFlipVertical(false);
			if ((x + translate) > (startX + move)) {
				wait = WAIT_TIME;
				setAnimated(false);
			} else {
				setX(x + translate);
				setAnimated(true);
			}
		}
		
	}
	
	/**
	 * Avoid shooting yourself by adding this safety
	 * margin before firing a bullet.
	 */
	protected float weaponSafetyMargin() {
		return facingLeft ? -width : width;
	}

	/**
	 * Called from an update function. Looks for the 
	 * main characters, and fires the weapon at will.
	 * 
	 * @param dt Time delay
	 */
	protected void aimAndShoot(float dt) {
		
		// Return if you can't shoot
		if (weapon == null)
			return;
		
		weapon.update(dt);
		
		if (!weapon.is_ready())
			return;
		
		// Aim
		float x = getX();
		float y = getY();
		float x_diff = level.getMainCharacter().getX() - x;
		float height_diff = Math.abs(level.getMainCharacter().getY() - y);
		
		if (height_diff > 40)
			return;
		
		// If in position: fire
		Bullet[] bullets;
		if (facingLeft && x_diff < 10) {
			
			bullets = weapon.fire(x + weaponSafetyMargin(),
								  y,
								  facingLeft);
			
		} else if (!facingLeft && x_diff > 10) {

			bullets = weapon.fire(getX() + weaponSafetyMargin(),
								  getY(),
								  facingLeft);
		} else {
			
			bullets = new Bullet[0];
		}
		
		for (Bullet b : bullets) {
			level.addToken(b);
		}
	}
	
	@Override
	public void loadParam(String key, String value) {
		super.loadParam(key, value);
		
		if (key.equals("x")) {
			startX = Integer.parseInt(value);
	 	} else if (key.equals("move")) {
			move = Integer.parseInt(value);
			
			if (move < 0) {
				facingLeft = true;
				move = Math.abs(move);
				startX -= move;
			} else {
				facingLeft = false;
			}
		}
	}
}

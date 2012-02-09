package no.ntnu.androidgame.models.weapons;

import sheep.audio.Sound;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.tokens.weapons.Bullet;

/**
 * A weapon is responsible for creating bullet(s) upon request.
 * It also maintains a pause between each time you can fire.
 * 
 * @author vegare
 * @see Bullet
 */
public abstract class Weapon {
	
	// Current wait time (seconds)
	protected float wait_time = 0.0f;
	
	/**
	 * Determine if the weapon is ready to fire new bullets.
	 * @return boolean - true if the user can fire again
	 */
	public boolean is_ready() {
		
		return wait_time <= 0;
	}
	
	/**
	 * Decrease the waiting time.
	 * 
	 * @param dt - Delay in seconds
	 */
	public void update(float dt) {
		
		if (wait_time > 0) {
			wait_time = Math.max(0, wait_time-dt);
		}
	}
	
	/**
	 * Fire the weapon, and return the new bullets.
	 * Override createBullets for each specific weapon,
	 * _not_ this function.
	 * 
	 * @param x			- Starting x (gun point)
	 * @param y			- Starting y (gun point)
	 * @param moveLeft	- Direction of the bullets, left of right
	 * @return			Bullet[] - List of bullets
	 */
	public Bullet[] fire(float x, float y, boolean moveLeft) {
		
		if (!is_ready()) {
			return new Bullet[]{};
		}
		
		// Start delay
		wait_time = getRecoveryTime();
		
		return createBullets(x, y, moveLeft);
	}
	
	/**
	 * Create the bullets to launch after fire.
	 * Override this function to create a weapon.
	 * 
	 * @return List of bullets
	 */
	protected abstract Bullet[] createBullets(float x, float y, boolean moveLeft);

	protected abstract float getRecoveryTime();
	
	/**
	 * Returns the sound of the gun being fired.
	 * Override this function to create a weapon
	 * 
	 * @return Sound instance
	 */
	public abstract Sound getGunFire();

	/**
	 * Returns the sound of an empty gun.
	 * This is the same for all guns.
	 * 
	 * @return Sound instance
	 */
	public Sound getGunEmpty() {
		return Constants.SOUND_EMPTYGUN;
	}
}

package no.ntnu.androidgame.models.weapons;

import sheep.audio.Sound;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.tokens.enemy.GunEnemy;
import no.ntnu.androidgame.tokens.weapons.Bullet;

/**
 * Fires one bullet, with medium recovery time.
 * The easiest weapon the main character can carry.
 * Also used by basic gun enemies.
 * 
 * @author vegare
 * @see GunEnemy
 */
public class Handgun extends Weapon {

	// Recovery time in seconds
	protected float recovery_time = 0.8f;
	
	@Override
	protected Bullet[] createBullets(float x, float y, boolean moveLeft) {
		
		// One bullet, positioned at the tip of the gun
		return new Bullet[]{ new Bullet(x,y,moveLeft) };
	}

	@Override
	public Sound getGunFire() {
		return Constants.SOUND_HANDGUN;
	}
	
	@Override
	protected float getRecoveryTime() {
		return recovery_time;
	}
}
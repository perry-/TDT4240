package no.ntnu.androidgame.models.weapons;

import sheep.audio.Sound;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.tokens.enemy.ShotgunEnemy;
import no.ntnu.androidgame.tokens.weapons.Bullet;

/**
 * Fires three bullets in different angles.
 * High recovery time. Used by shotgun enemies
 * and main character on some levels.
 * 
 * @author vegare
 * @see ShotgunEnemy
 */
public class Shotgun extends Weapon {

	// Recovery time in seconds
	protected float recovery_time = 1.2f;
	
	@Override
	protected Bullet[] createBullets(float x, float y, boolean moveLeft) {
		
		// Three bullets with different angles
		return new Bullet[]{ new Bullet(x, y, moveLeft),
					 		 new Bullet(x, y, moveLeft, 15),
					 		 new Bullet(x, y, moveLeft, -15)};
	}

	@Override
	public Sound getGunFire() {
		return Constants.SOUND_SHOTGUN;
	}

	@Override
	protected float getRecoveryTime() {
		return recovery_time;
	}
}
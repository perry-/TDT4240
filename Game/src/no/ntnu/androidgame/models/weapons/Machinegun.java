package no.ntnu.androidgame.models.weapons;

import sheep.audio.Sound;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.tokens.weapons.Bullet;

/**
 * Fires three bullets at the same time. Low recovery.
 * Only used by main character on levels with many
 * enemies at the same level. Don't abuse this one! :)
 * 
 * @author vegare
 *
 */
public class Machinegun extends Weapon {

	// Recovery time in seconds
	protected float recovery_time = 0.5f;
	
	@Override
	protected Bullet[] createBullets(float x, float y, boolean moveLeft) {
		
		// Difference between bullets
		// measured in pixels
		int diff = 0;
		if (moveLeft)	diff = -20;
		else			diff =  20;
			
		// Three bullets coming in a row
		return new Bullet[]{ new Bullet(x,		  y, moveLeft),
							 new Bullet(x+diff,	  y, moveLeft),
							 new Bullet(x+2*diff, y, moveLeft)};
	}

	@Override
	public Sound getGunFire() {
		return Constants.SOUND_MACHINEGUN;
	}
	
	@Override
	protected float getRecoveryTime() {
		return recovery_time;
	}
}
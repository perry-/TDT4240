package no.ntnu.androidgame.tokens.enemy;

import no.ntnu.androidgame.R;
import no.ntnu.androidgame.models.weapons.Shotgun;
import sheep.graphics.Image;

/**
 * Enemy with shotgun. Watch out for these fuckers...
 * 
 * @author vegare
 *
 */
public class ShotgunEnemy extends Enemy {

	private static Image[] she_images = {new Image(R.drawable.she_frame0),new Image(R.drawable.she_frame1),new Image(R.drawable.she_frame2)};

	/**
	 * Constructor
	 */
	public ShotgunEnemy(float tickTime) {
		super(she_images, tickTime);
		weapon = new Shotgun();
	}

	@Override
	public void update(float dt) {
		
		aimAndShoot(dt);
		super.update(dt);
	}
}

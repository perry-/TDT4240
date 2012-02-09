package no.ntnu.androidgame.tokens.enemy;

import sheep.graphics.Image;
import no.ntnu.androidgame.R;
import no.ntnu.androidgame.models.weapons.Handgun;

/**
 * Enemy with ordinary handgun.
 * 
 * @author vegare
 *
 */
public class GunEnemy extends Enemy {

	private static Image[] ge_images = {new Image(R.drawable.ge_frame0),new Image(R.drawable.ge_frame1),new Image(R.drawable.ge_frame2)};
	/**
	 * Constructor
	 */
	public GunEnemy(float tickTime) {
		super(ge_images, tickTime);
		weapon = new Handgun();
	}

	@Override
	public void update(float dt) {
		
		aimAndShoot(dt);
		super.update(dt);
	}
}

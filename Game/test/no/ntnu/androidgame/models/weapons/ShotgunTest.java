package no.ntnu.androidgame.models.weapons;

import junit.framework.Assert;
import no.ntnu.androidgame.tokens.weapons.Bullet;
import android.test.AndroidTestCase;

public class ShotgunTest extends AndroidTestCase {

	public void testShotgunFire() throws Throwable {
		
		Shotgun gun = new Shotgun();
		Bullet[] bullets = gun.fire(30, 40, false);
		
		// Assure one bullet was created
		Assert.assertEquals(bullets.length, 3);
		
		// Make sure the bullet was properly created
		Assert.assertEquals(bullets[0].getX(), 30.0f);
		Assert.assertEquals(bullets[0].getY(), 40.0f);
		Assert.assertTrue(bullets[0].getSpeed().getX() > 0);
		Assert.assertTrue(bullets[0].getSpeed().getY() == 0);
		Assert.assertTrue(bullets[1].getSpeed().getY() < 0);
		Assert.assertTrue(bullets[2].getSpeed().getY() > 0);
	}
}

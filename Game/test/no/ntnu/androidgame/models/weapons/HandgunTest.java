package no.ntnu.androidgame.models.weapons;

import no.ntnu.androidgame.tokens.weapons.Bullet;
import junit.framework.Assert;
import android.test.AndroidTestCase;

public class HandgunTest extends AndroidTestCase {

	public void testHandgunFire() throws Throwable {
		
		Handgun gun = new Handgun();
		Bullet[] bullets = gun.fire(10, 20, false);
		
		// Assure one bullet was created
		Assert.assertEquals(bullets.length, 1);
		
		// Make sure the bullet was properly created
		Assert.assertEquals(bullets[0].getX(), 10.0f);
		Assert.assertEquals(bullets[0].getY(), 20.0f);
		Assert.assertTrue(bullets[0].getSpeed().getX() > 0);
		Assert.assertTrue(bullets[0].getSpeed().getY() == 0);
	}
	
	public void testHandgunReverseFire() throws Throwable {
		
		Handgun gun = new Handgun();
		Bullet[] bullets = gun.fire(20, 10, true);
		
		// Assure one bullet was created
		Assert.assertEquals(bullets.length, 1);
		
		// Make sure the bullet was properly created
		Assert.assertEquals(bullets[0].getX(), 20.0f);
		Assert.assertEquals(bullets[0].getY(), 10.0f);
		Assert.assertTrue(bullets[0].getSpeed().getX() < 0);
		Assert.assertTrue(bullets[0].getSpeed().getY() == 0);
	}
}

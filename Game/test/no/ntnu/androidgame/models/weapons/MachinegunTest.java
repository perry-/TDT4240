package no.ntnu.androidgame.models.weapons;

import junit.framework.Assert;
import no.ntnu.androidgame.tokens.weapons.Bullet;

public class MachinegunTest {
	
	public void testShotgunFire() throws Throwable {
		
		Machinegun gun = new Machinegun();
		Bullet[] bullets = gun.fire(40, 50, false);
		
		// Assure one bullet was created
		Assert.assertEquals(bullets.length, 3);
		
		// Make sure the bullet was properly created
		Assert.assertEquals(bullets[0].getX(), 40.0f);
		Assert.assertEquals(bullets[0].getY(), 50.0f);
		Assert.assertTrue(bullets[0].getSpeed().getX() > 0);
		Assert.assertTrue(bullets[0].getSpeed().getY() == 0);
		
		// Make sure the order is correct
		Assert.assertTrue(bullets[0].getX() > bullets[1].getX());
		Assert.assertTrue(bullets[1].getX() > bullets[2].getX());
	}
}

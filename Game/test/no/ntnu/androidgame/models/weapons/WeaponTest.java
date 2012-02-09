package no.ntnu.androidgame.models.weapons;

import junit.framework.Assert;
import no.ntnu.androidgame.tokens.weapons.Bullet;
import android.test.AndroidTestCase;

public class WeaponTest extends AndroidTestCase {

	public void testWeaponDelay() throws Throwable {
		
		Weapon gun = new Handgun();
		
		// Initial
		Assert.assertEquals(gun.wait_time, 0.0f);
		
		// Legal fire
		Bullet[] b1 = gun.fire(0, 0, true);
		Assert.assertEquals(b1.length, 1);
		Assert.assertEquals(gun.wait_time, gun.getRecoveryTime());
		
		// Illegal followup fire
		Bullet[] b2 = gun.fire(0, 0, true);
		Assert.assertEquals(b2.length, 0);
		
		// Wait, but not enough
		gun.update(gun.getRecoveryTime() / 2);
		Assert.assertFalse(gun.is_ready());
		
		Bullet[] b3 = gun.fire(0, 0, true);
		Assert.assertEquals(b3.length, 0);
		Assert.assertTrue(gun.wait_time > 0.0f);
		Assert.assertTrue(gun.wait_time < gun.getRecoveryTime());
		
		// Wait long enough
		gun.update(gun.getRecoveryTime());
		Assert.assertTrue(gun.is_ready());
		
		Bullet[] b4 = gun.fire(0, 0, true);
		Assert.assertEquals(b4.length, 1);
		Assert.assertEquals(gun.wait_time, gun.getRecoveryTime());
		
	}
}

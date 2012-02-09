package no.ntnu.androidgame.models;

import junit.framework.Assert;
import android.test.AndroidTestCase;

public class GameQuestTest extends AndroidTestCase {

	/*
	public void testGameQuest() throws Throwable {
		
		GameQuest gq = new GameQuest();
		
		// Start
		Assert.assertTrue(gq.getBuildings().size() > 0);
		Assert.assertFalse(gq.isComplete());
		Assert.assertFalse(gq.allLocked());
		
		// Complete
		for (Building b : gq.getBuildings()) {

			b.unlock();
			
			Assert.assertFalse(b.isComplete());
			for (int i=0; i<b.getLevelCount(); i++) {
				b.increaseLevel();
			}
			Assert.assertTrue(b.isComplete());
		}
		
		Assert.assertTrue(gq.isComplete());
	}
	*/
}

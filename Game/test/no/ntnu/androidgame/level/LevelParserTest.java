package no.ntnu.androidgame.level;

import junit.framework.Assert;
import no.ntnu.androidgame.tokens.Wall;
import android.test.AndroidTestCase;

public class LevelParserTest extends AndroidTestCase {

	public void testLevelParserArgs() throws Throwable {
		
		Wall t1 = new Wall();
		Wall t2 = new Wall();
		Wall t3 = new Wall();
		Wall t4 = new Wall();
		
		LevelParser.parseArguments(t1, "x=10,y=20,width=200");
		LevelParser.parseArguments(t2, "x=20,y=30,height=300");
		LevelParser.parseArguments(t3, "x=30,y=40,width=400,height=500");
		LevelParser.parseArguments(t4, "x=40,y=50,height=600,width=700");
		
		// Position
		Assert.assertEquals(t1.getX(), 10.0f);
		Assert.assertEquals(t1.getY(), 20.0f);
		
		// Width and height
		Assert.assertEquals(t1.wallWidth(),  200);
		Assert.assertEquals(t1.wallHeight(), Wall.THICKNESS);

		Assert.assertEquals(t2.wallWidth(),  Wall.THICKNESS);
		Assert.assertEquals(t2.wallHeight(), 300);

		Assert.assertEquals(t3.wallWidth(),  Wall.THICKNESS);
		Assert.assertEquals(t3.wallHeight(), 500);

		Assert.assertEquals(t4.wallWidth(),  700);
		Assert.assertEquals(t4.wallHeight(), Wall.THICKNESS);
	}
	
	public void testLevelParserLine() throws Throwable {
		
	}
	
	public void testLevelParserLevel() throws Throwable {
		
	}
}

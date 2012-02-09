package no.ntnu.androidgame.tokens.weapons;

import android.graphics.Canvas;

import sheep.graphics.Image;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.tokens.AnimationToken;
import no.ntnu.androidgame.tokens.TokenGroups;

public class Explosion extends AnimationToken {
	
	private static Image[] explotion = {Constants.EXPLOTION_FRAME0, 
										Constants.EXPLOTION_FRAME1,
										Constants.EXPLOTION_FRAME2,
										Constants.EXPLOTION_FRAME3,
										Constants.EXPLOTION_FRAME4,
										Constants.EXPLOTION_FRAME5,
										Constants.EXPLOTION_FRAME4,
										Constants.EXPLOTION_FRAME3,
										Constants.EXPLOTION_FRAME2,
										Constants.EXPLOTION_FRAME1};
	
	// Time to stay alive
	private static float LIVE_TIME = 1;
	private float alive = 0;
	
	public Explosion(float x, float y) {
		
		super(explotion, 0.1f, 0);
		
		setPosition(x, y);
		Constants.SOUND_EXPLOSION.play();
		
		setGroup(TokenGroups.BULLET);
		setMask(TokenGroups.CRATE | TokenGroups.DOOR | TokenGroups.BULLET);
	}
	
	@Override
	public void draw(Canvas canvas) {
		// This fixes an obscure bug, that occurs
		// when you draw the dynamite the first time.
		// For some reason, it's located at (0,0) for
		// a short while, before being moved. By skipping
		// one frame, we are able to avoid the bug.
		if (alive > 0) {
			super.draw(canvas);
		}
	}
	@Override
	public void update(float dt) {
		super.update(dt);
		
		alive += dt;
		if (alive > LIVE_TIME) {
			level.removeToken(this);
		}
	}
}
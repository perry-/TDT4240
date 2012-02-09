package no.ntnu.androidgame.tokens;

import no.ntnu.androidgame.Constants;

public class Door extends Token {

	/**
	 * Constructor
	 * Load token with proper image
	 */
	public Door() {
		
		super(Constants.DOOR);
		
		// Collision groups
		setGroup(TokenGroups.DOOR);
		setMask(TokenGroups.ENEMY | TokenGroups.WALL | TokenGroups.BULLET | TokenGroups.CRATE | TokenGroups.DOOR);
	}
}
package no.ntnu.androidgame.tokens;

/**
 * Defines the token collision groups.
 * These are used to mask irrelevant collisions.
 * 
 * @author vegare
 */
public class TokenGroups {

	public static final long MAIN_CHARACTER	= 1;
	public static final long ENEMY			= 2;
	public static final long WALL			= 4;
	public static final long BULLET			= 8;
	public static final long CRATE			= 16;
	public static final long DOOR			= 32;
	public static final long BOSS 			= 64;
}

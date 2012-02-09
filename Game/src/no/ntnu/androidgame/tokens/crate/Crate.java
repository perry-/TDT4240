package no.ntnu.androidgame.tokens.crate;


import sheep.game.Sprite;
import sheep.graphics.Image;
import no.ntnu.androidgame.tokens.Token;
import no.ntnu.androidgame.tokens.TokenGroups;

/**
 * Abstract class for crates. A crate contains something the player
 * can pick up, and is spread across the levels to help him.
 * 
 * @author vegare
 */
public abstract class Crate extends Token {

	// How many items does it contain?
	// Differs for different crates.
	// It's parsed from the level-file
	protected int amount = 1;
	
	/**
	 * Constructor
	 * 
	 * @param image Crate representation
	 */
	public Crate(Image image) {
		
		super(image);
		
		// Collision groups
		setGroup(TokenGroups.CRATE);
		setMask(TokenGroups.ENEMY | TokenGroups.WALL | TokenGroups.BULLET | TokenGroups.CRATE | TokenGroups.DOOR);
	}
	
	/**
	 * A crate contains a certain amount of something.
	 * This parameter tells how many items there are
	 * in the given crate. Default is one.
	 * 
	 * @return Amount of items
	 */
	public int getAmount() {
		return amount;
	}
	
	@Override
	public void loadParam(String key, String value) {
		super.loadParam(key, value);
		if (key.equals("amount")) {
			this.amount = Integer.parseInt(value);
		}
	}

	@Override
	public void collided(Sprite a, Sprite b) {
		// Crate is picked up
		level.removeToken(this);
	}
}

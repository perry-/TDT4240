package no.ntnu.androidgame.screens;

import no.ntnu.androidgame.tokens.Token;

/**
 * A Token Listener adheres to the observer pattern, and observes
 * the current tokens of a Level object.
 * 
 * @author vegare
 * @see Level
 * @see Token
 */
public interface TokenListener {

	public void tokenAdded(Token t);
	public void tokenRemoved(Token t);
}
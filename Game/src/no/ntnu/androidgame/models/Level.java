package no.ntnu.androidgame.models;

import java.util.ArrayList;

import sheep.graphics.Image;

import no.ntnu.androidgame.level.LevelParser;
import no.ntnu.androidgame.screens.TokenListener;
import no.ntnu.androidgame.tokens.MainCharacter;
import no.ntnu.androidgame.tokens.Token;

/**
 * This class holds a single instance of a level.
 * 
 * Create a new level by providing it with a level ID. The level will
 * be loaded from a file, present in the resource folder. 
 * 
 * Once created, the level can be used as a model for a LevelScreen.
 * The LevelScreen will draw all tokens, check for collisions and
 * perform the appropriate actions.
 * 
 * @author vegare
 * @see LevelScreen
 */
public class Level {

	private int id;
	private GameQuest quest;
	private ArrayList<Token> tokens;
	private Image background;
	private ArrayList<TokenListener> tokenListeners;
	
	/**
	 * Constructor
	 * 
	 * @param quest		The quest that owns this level
	 * @param level_id	The file identifier of the level
	 */
	public Level(GameQuest quest, int level_id) {

		this.id = level_id;
		
		this.quest = quest;
		
		this.tokens = new ArrayList<Token>(20);
		this.tokenListeners = new ArrayList<TokenListener>(3);
	}

	/**
	 * Load from level file
	 */
	public void load() {
		// Parse level to fill token list
		tokens.addAll(LevelParser.parseLevel(id));
		
		for (Token t : tokens) {
			t.setLevel(this);
		}
	}
	
	/**
	 * Unload
	 */
	public void unload() {	
		tokens.clear();
	}
	
	/**
	 * Get the quest associated with this level.
	 * 
	 * @return Quest associated with level
	 */
	public GameQuest getQuest() {
		return quest;
	}
	/**
	 * Returns the background image for this level
	 * 
	 * @return Image - the background image for this level
	 */
	public Image getBackground() {
		return background;
	}
	/**
	 * Sets the background image for this level
	 * @param background - the background image for this level
	 */
	public void setBackground(Image background) {
		this.background = background;
	}
	//
	// Manipulate tokens
	//
	/**
	 * Adds a token to this level
	 * @param token - Token to add
	 */
	public void addToken(Token token) {
		token.setLevel(this);
		tokens.add(token);
		notifyNewToken(token);
	}
	/**
	 * Removes a token from this level
	 * @param token - Token to remove
	 */
	public void removeToken(Token token) {
		tokens.remove(token);
		notifyRemovedToken(token);
	}
	/**
	 * Returns a list containing all tokens in this level
	 * @return ArrayList<Token> - List containing all tokens in this level
	 */
	public ArrayList<Token> getTokens() {
		return tokens;
	}

	
	//
	// Token listening
	//
	/**
	 * Adds a TokenListener to this level
	 * @param tl - TokeListener to ass
	 */
	public void addTokenListener(TokenListener tl) {
		tokenListeners.add(tl);
	}
	/**
	 * Removes a TokenListener to this level
	 * @param tl - TokeListener to remove
	 */
	public void removeTokenListener(TokenListener tl) {
		tokenListeners.remove(tl);
	}
	/**
	 * Notifies listeners that the Token t has been added to the level
	 * 
	 * @param t - Token that has been added
	 */
	private void notifyNewToken(Token t) {
		for (TokenListener tl : tokenListeners) {
			tl.tokenAdded(t);
		}
	}
	/**
	 * Notifies listeners that the Token t has been removed from the level
	 * 
	 * @param t - Token that has been removed
	 */
	private void notifyRemovedToken(Token t) {
		for (TokenListener tl : tokenListeners) {
			tl.tokenRemoved(t);
		}
	}
	
	/**
	 * Finds the main character from a list of tokens.
	 * It ought to be the first one in the list every time, so this
	 * function is not as slow as it may seem.
	 * 
	 * @return MainCharacter - Main character or NULL if none is present
	 */
	public MainCharacter getMainCharacter() {
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i) instanceof MainCharacter) {
				return (MainCharacter) tokens.get(i);
			}
		}
		return null;
	}
}
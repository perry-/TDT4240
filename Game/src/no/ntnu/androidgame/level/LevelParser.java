package no.ntnu.androidgame.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.tokens.Token;
import no.ntnu.androidgame.tokens.TokenFactory;
import no.ntnu.androidgame.tokens.Wall;
import sheep.game.Game;

/**
 * Parses a level file from the resource folder, and returns
 * a list of Tokens that level contains. Will also initialize
 * the tokens with appropriate variables.
 * 
 * Each level file may consist of:
 *  - Blank lines
 *  - Comments (starting with #)
 *  - Token
 *  
 * The token format is as follows:
 *    ClassName(key=value,key=value,...,key=value)
 * 
 * The class name must correspond to the name set in TokenFactory.
 * Key and value will be sent to Token.setParam(key, value) and
 * must be parsed by the main class or by a subclass.
 * 
 * Unrecognized parameters will be ignored. The user needs to make
 * sure that the format is right, for example 'x' is an int while
 * 'name' is a string. See demo.lvl in /res/raw for an example.
 * 
 * Walls are anchored by their top left corner. All other tokens
 * are ancohored by their bottom left corners.
 * 
 * @author vegare
 * @see Token
 * @see TokenFactory
 */
public class LevelParser {
	
	/**
	 * Parses a level file from the resource folder.
	 * 
	 * @param id -Level ID
	 * @return ArrayList<Token> - List of tokens in the level
	 */
	public static ArrayList<Token> parseLevel(int id) {
		
		ArrayList<Token> tokens = new ArrayList<Token>(20);
		
		addSurroundingWalls(tokens);

		try
		{
			// Load from file
			InputStream is = Game.getInstance().getResources().openRawResource(id);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr, 8000);
			
			String line;
			
			while((line = br.readLine()) != null)
			{	
				if (line.length() == 0 || line.startsWith("#"))
					continue;
				// Parses the file, one line at a time
				tokens.add(parseLine(line));
			}
			
		} catch (IOException e) {
			System.err.println("ERROR in input reading level from file\n");
			e.printStackTrace();
		}
		
		return tokens;
	}

	/**
	 * Add four walls surrounding the playing area.
	 * All levels must have these, to restrict the
	 * position of all tokens.
	 * 
	 * @param tokens - Token list to expand
	 */
	private static void addSurroundingWalls(ArrayList<Token> tokens) {
		
		float left_x   = 0;
		float right_x  = Constants.WINDOW_WIDTH - Wall.THICKNESS;
		float top_y    = Constants.TOP_MENU_SIZE;
		float bottom_y = Constants.WINDOW_HEIGHT - Wall.THICKNESS;
		
		tokens.add(new Wall(left_x, top_y, 		Constants.WINDOW_HEIGHT, 0)); // Left wall
		tokens.add(new Wall(right_x, top_y, 	Constants.WINDOW_HEIGHT, 0)); // Right wall
		tokens.add(new Wall(left_x,  top_y, 	0, Constants.WINDOW_WIDTH));  // Roof
		tokens.add(new Wall(left_x,  bottom_y,	0, Constants.WINDOW_WIDTH));  // Floor
	}
	
	/**
	 * Parse one line of a level file, and create the
	 * appropriate token. The token format is as follows:
	 * 
	 *    ClassName(key=value,key=value,...,key=value)
	 * 
	 * @param line 	One line from a level file
	 * @return		A corresponding, initialized token
	 */
	protected static Token parseLine(String line) {

		// Parse text line
		int left_par_pos  = line.indexOf('(');
		int right_par_pos = line.indexOf(')');
		
		String name = line.substring(0,                left_par_pos);
		String args = line.substring(left_par_pos + 1, right_par_pos);
		
		// Create token		
		Token t = TokenFactory.createToken(name);
		parseArguments(t, args);
		
		return t;
	}
	
	/**
	 * Turn the comma separated list of arguments into a
	 * set of calls to token.setParam(key,value)
	 * 
	 * @param token		The token we want to initialize
	 * @param arguments	Comma separated list of arguments
	 */
	protected static void parseArguments(Token token, String arguments) {
		
		// Split arguments	
		int comma;
		int pos = 0;
		
		arguments = arguments + ',';
		
		while ((comma = arguments.indexOf(',', pos)) != -1)
		{	
			String s = arguments.substring(pos, comma);
			
			int equal_position = s.indexOf('=');
			
			String key = s.substring(0, equal_position);
			String val = s.substring(equal_position + 1);
			
			token.loadParam(key, val);
			
			pos = comma + 1;
		}
	}
}

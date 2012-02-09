package no.ntnu.androidgame.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import sheep.game.Game;

import no.ntnu.androidgame.tokens.Token;

/**
 * Uses Java reflection to parse a given level.
 * 
 * This class is deprecated, and will not be used in the final
 * implementation due to poor execution times. We'll keep it
 * around for potential future use, as it increases modifiability.
 *  
 * @author vegare
 * @deprecated
 */
public class LevelParserReflection {

	private static final String prefix = "no.ntnu.androidgame.tokens.";
	
	/**
	 * Parse a level file from the resource folder.
	 * 
	 * @param id	Level ID
	 * @return		List of tokens in level
	 */
	public static ArrayList<Token> parseLevel(int id) {
		
		ArrayList<Token> tokens = new ArrayList<Token>(20);

		try {
			
			// Parse level file
			InputStream is = Game.getInstance().getResources().openRawResource(id);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr, 8000);
			
			String line;
			
			while((line = br.readLine()) != null) {
				
				if (line.length() == 0 || line.startsWith("#"))
					continue;
				
				tokens.add(parseLine(line));
			}
			
		// TODO: Add proper error messages!
		} catch (IOException e) {
			// Error
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// Error
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// Error
			e.printStackTrace();
		} catch (InstantiationException e) {
			// Error
			e.printStackTrace();
		} catch (ClassCastException e) {
			// Error
			e.printStackTrace();
		} catch (SecurityException e) {
			// Error
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// Error
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// Error
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// Error
			e.printStackTrace();
		}
		
		return tokens;
	}
	
	/**
	 * Parses one line of a level file, and creates the
	 * appropriate token.
	 * 
	 * @param line 	One line from a level file
	 * @return		A corresponding, initialized token
	 * 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws ClassCastException
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	private static Token parseLine(String line) throws 	ClassNotFoundException,
														IllegalAccessException,
														InstantiationException,
														ClassCastException,
														SecurityException,
														NoSuchMethodException,
														IllegalArgumentException,
														InvocationTargetException {

		// Parse text line
		int left_par_pos  = line.indexOf('(');
		int right_par_pos = line.indexOf(')');
		
		String name = line.substring(0,                left_par_pos);
		String args = line.substring(left_par_pos + 1, right_par_pos);
		
		// Create token using reflection
		Class c = Class.forName(LevelParserReflection.prefix + name);
		Token t = (Token) c.newInstance();
		
		parseArguments(t, args);
		
		return t;
	}
	
	/**
	 * Initializes the given token with a set of comma separated
	 * arguments. For every (key=value) pair, reflection is used
	 * to call the appropriate setter, for example:
	 * 
	 *  (name="Joe") 	=> token.setName("Joe");
	 *  (speed=15)   	=> token.setSpeed(15);
	 * 
	 * @param token		The token we want to initialize
	 * @param arguments	Comma separated list of arguments
	 * 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private static void parseArguments(Token token, String arguments) throws SecurityException,
																			 NoSuchMethodException,
																			 IllegalArgumentException,
																			 IllegalAccessException,
																			 InvocationTargetException {
		// Split arguments	
		int comma;
		int pos = 0;
		
		arguments = arguments + ',';
		
		while ((comma = arguments.indexOf(',', pos)) != -1) {
			
			String s = arguments.substring(pos, comma);
			
			int equal_position = s.indexOf('=');
			
			String key = s.substring(0, equal_position);
			String val = s.substring(equal_position + 1);
			
			parseArgument(token, key, val);
			
			pos = comma + 1;
		}
	}
	
	/**
	 * Initializes the given token with a single argument.
	 * Accepts key and value, and uses reflection to call
	 * the appropriate setter, for example:
	 * 
	 *  ("name", "Joe") 	=> token.setName("Joe");
	 *  ("speed", "15")   	=> token.setSpeed(15);
	 *  
	 * @param token	The token we want to initialize
	 * @param key	The parameter we want to set
	 * @param value	The value to set the parameter to
	 * 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private static void parseArgument(Token token, String key, String value) throws SecurityException,
																					NoSuchMethodException,
																					IllegalArgumentException,
																					IllegalAccessException,
																					InvocationTargetException {
		// Capitalize first letter
		String fun = "set" + key.substring(0, 1).toUpperCase() + key.substring(1); 
		
		// Find proper value
		Class partypes[] = new Class[1];
		Object val;
		
		if (value.startsWith("\"")) {
			// String
			partypes[0] = String.class;
			val = value.replaceAll("\"", "").trim();
		} else {
			// Integer
			partypes[0] = Integer.TYPE;
			val = Integer.parseInt(value);
		}
		
		// Call proper set function
		token.getClass().getMethod(fun, partypes).invoke(token, val);
	}
}

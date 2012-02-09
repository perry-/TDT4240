package no.ntnu.androidgame.log;

/**
 * The logger class log everything in console/file-
 * 
 * It can be configured to just log some actions instead
 * of the possible things that can occur in the game.
 * 
 * Options are:
 * - Collisions
 * - Jumps
 * - Kill
 * - Explosions
 * 
 * Also it is used with singleton because there will be only
 * on object of logger per execution of game.
 * 
 * @author fernanr
 *
 */
public class Logger {
	public static int COLLISION = 1;
	public static int JUMP = 2;
	public static int KILL = 4;
	
	private static int mask;
	/**
	 * Singleton
	 * 
	 * @author rodsjo
	 */
	protected Logger() {
		mask = COLLISION | JUMP;
	}
	private static Logger LOGGER = null;

	/**
	 * Return an instance of the Logger
	 * 
	 * @return Logger instance
	 */
	public static Logger getInstance() {
		if (LOGGER == null) {
			LOGGER = new Logger();
		}
		return LOGGER;
	}

	/**
	 * Log text of a given type
	 * 
	 * @param eventType Static variable from Logger
	 * @param text		Text to log
	 */
	public void logText(int eventType, String text) {
		if((eventType & mask) != 0) {
			System.out.println(text);
		}
	}
}

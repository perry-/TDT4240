package no.ntnu.androidgame;
import android.content.Context;
import android.media.MediaPlayer;

/**
 * A class to play backgroundmusic during menuscreens, and levels. It uses Androids
 * MediaPlayer to play the music. The music is loaded from the Resources-folder
 * 
 * @author raudsand
 *
 * Deactivated 23.04.2010 for performance reasons.
 *
 */
public class GameMusic {

	//private static MediaPlayer menu;
	//private static MediaPlayer level;

	/**
	 * Loads menu-music and level-music, so it's ready to be played
	 * 
	 * @author raudsand
	 * @param context - The context the music should be played in
	 */
	public static void loadSound(Context context) {
		//menu = MediaPlayer.create(context, R.raw.menu);
		//level = MediaPlayer.create(context, R.raw.level);
	}
	/**
	 * Starts the music for the menuscreens. Makes it loop infinitely until it's stopped
	 * 
	 * @author raudsand
	 */
	public static void playMenuMusic() {
		//if (!menu.isPlaying()) {
		//	menu.seekTo(0);
		//	menu.setLooping(true);
		//	menu.start();
		//}	        	
	}
	/**
	 * Stops the menumusic
	 * 
	 * @author raudsand
	 */
	public static void stopMenuMusic() {
		//menu.release();
	}
	/**
	 * Starts the music for the levelcreens. Makes it loop infinitely until it's stopped
	 * 
	 * @author raudsand
	 */
	public static void playLevelMusic() {
		//if (!level.isPlaying()) {
		//	level.seekTo(0);
		//	level.setLooping(true);
		//	level.start();
		//}	        	
	}
	/**
	 * Stops the music on the levels
	 * 
	 * @author raudsand
	 */
	public static void stopLevelMusic() {
		//level.pause();
	}
}

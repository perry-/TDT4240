package no.ntnu.androidgame;

import sheep.audio.Sound;

import sheep.graphics.Font;
import sheep.graphics.Image;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * This class holds all constants used in the game
 * 
 * @author rodsjo, vegare, raudsand, fernando
 *
 */
public class Constants {

	/**
	 * IMAGES USED IN THE GAME
	 */
	// BACKGROUNDS
	public static final Image BACKGROUND_WITH_LOGO = new Image(R.drawable.background_logo);
	public static final Image BACKGROUND = new Image(R.drawable.background);
	
	public static final Image BACKGROUND_ITBYGGET = new Image(R.drawable.background_itbygget);
	public static final Image BACKGROUND_P15 = new Image(R.drawable.background_p15);
	public static final Image BACKGROUND_STRIPA = new Image(R.drawable.background_stripa);
	public static final Image BACKGROUND_REALBYGGET1 = new Image(R.drawable.background_realbygget1);
	public static final Image BACKGROUND_REALBYGGET2 = new Image(R.drawable.background_realbygget2);
	public static final Image BACKGROUND_ELBYGGET1 = new Image(R.drawable.background_elbygget1);
	public static final Image BACKGROUND_ELBYGGET2 = new Image(R.drawable.background_elbygget2);
	public static final Image BACKGROUND_MAIN = new Image(R.drawable.background_main);
	
	// CRATES AND WEAPONS
	public static final Image DOOR = new Image(R.drawable.door);
	public static final Image DYNAMITE = new Image(R.drawable.dynamite);
	public static final Image HEALTHPACK = new Image(R.drawable.healthpack);
	public static final Image AMMOPACK = new Image(R.drawable.ammopack);
	public static final Image DYNAMITEPACK = new Image(R.drawable.dynamitepack);
	public static final Image EXPLOTION_FRAME0 = new Image(R.drawable.explotion_frame0);
	public static final Image EXPLOTION_FRAME1 = new Image(R.drawable.explotion_frame1);
	public static final Image EXPLOTION_FRAME2 = new Image(R.drawable.explotion_frame2);
	public static final Image EXPLOTION_FRAME3 = new Image(R.drawable.explotion_frame3);
	public static final Image EXPLOTION_FRAME4 = new Image(R.drawable.explotion_frame4);
	public static final Image EXPLOTION_FRAME5 = new Image(R.drawable.explotion_frame5);
	
	// VIDEOSCREEN
	public static final Image INFOHILDE = new Image(R.drawable.infohilde);
	public static final Image GLOSHAUGEN_INFO = new Image(R.drawable.gloshaugen_info);
	public static final Image GOLD_CUP = new Image(R.drawable.pokal);
	public static final Image ALFIE_LAUGH_MOHAHAH_FRAME0 = new Image(R.drawable.alfie_animated_videoscreen_frame0);
	public static final Image ALFIE_LAUGH_MOHAHAH_FRAME1 = new Image(R.drawable.alfie_animated_videoscreen_frame1);
	public static final Image ALFIE_LAUGH_MOHAHAH_FRAME2 = new Image(R.drawable.alfie_animated_videoscreen_frame2);
	
	// MAP AND GAMEQUEST
	public static final Image GLOSHAUGENMAP = new Image(R.drawable.glos);
	
	public static final Image P15O = new Image(R.drawable.p15o);
	public static final Image P15C = new Image(R.drawable.p15c);
	public static final Image P15S = new Image(R.drawable.p15s);
	
	public static final Image ITBYGGETO = new Image(R.drawable.itbygget_o);
	public static final Image ITBYGGETC = new Image(R.drawable.itbygget_c);
	public static final Image ITBYGGETS = new Image(R.drawable.itbygget_s);
	
	public static final Image ELBYGGETO = new Image(R.drawable.elbygget_o);
	public static final Image ELBYGGETC = new Image(R.drawable.elbygget_c);
	public static final Image ELBYGGETS = new Image(R.drawable.elbygget_s);
	
	public static final Image REALBYGGETO = new Image(R.drawable.realbygget_o);
	public static final Image REALBYGGETC = new Image(R.drawable.realbygget_c);
	public static final Image REALBYGGETS = new Image(R.drawable.realbygget_s);
	
	public static final Image STRIPAO = new Image(R.drawable.stripa_o);
	public static final Image STRIPAC = new Image(R.drawable.stripa_c);
	public static final Image STRIPAS = new Image(R.drawable.stripa_s);
	
	public static final Image MAINBUILDINGO = new Image(R.drawable.mainbuilding_o);
	public static final Image MAINBUILDINGC = new Image(R.drawable.mainbuilding_c);
	public static final Image MAINBUILDINGS = new Image(R.drawable.mainbuilding_s);
	
	public static final Image BUILDING_P15 = new Image(R.drawable.building_p15);
	public static final Image BUILDING_IT = new Image(R.drawable.building_it);
	public static final Image BUILDING_MAIN = new Image(R.drawable.building_main);
	public static final Image BUILDING_STRIPA = new Image(R.drawable.building_stripa);
	public static final Image BUILDING_REALFAG = new Image(R.drawable.building_realfag);
	public static final Image BUILDING_ELEKTRO = new Image(R.drawable.building_elektro);
	
	// INTRODUCTION
	public static final Image ALFIE_LOVES_ANDROID_FRAME0 = new Image(R.drawable.alfie_loves_android_frame0);
	public static final Image ALFIE_LOVES_ANDROID_FRAME1 = new Image(R.drawable.alfie_loves_android_frame1);
	public static final Image ALFIE_LOVES_ANDROID_FRAME2 = new Image(R.drawable.alfie_loves_android_frame2);
	
	public static final Image INSTRUCTIONS = new Image(R.drawable.instructions);
	public static final Image INSTRUCTIONS_ENEMIES = new Image(R.drawable.instructions_enemies);
	
	// THANK YOU SCREEN
	public static final Image THANKYOU_FRAME0 = new Image(R.drawable.thankyou_frame0);
	public static final Image THANKYOU_FRAME1 = new Image(R.drawable.thankyou_frame1);
	
	/**
	 * TEXT AND BUTTONS COLORS
	 */
	public static final int HISTORYTEXTCOLOR = Color.WHITE;
	// buttonPaint[0]: Paint when buttons are idle
	// buttonPaint[1]: Paint when buttons are pressed down
	public static final Paint[] buttonPaint = {Font.WHITE_SANS_BOLD_16, Font.RED_SANS_BOLD_16};
	public static final Paint[] startButtonPaint = {Font.WHITE_SANS_BOLD_20, Font.RED_SANS_BOLD_20};
	
	/**
	 * SOUNDS
	 */
	public static Sound SOUND_SHOTGUN		= new Sound(R.raw.shotgun);
	public static Sound SOUND_EMPTYGUN		= new Sound(R.raw.emptychamber);
	public static Sound SOUND_HANDGUN		= new Sound(R.raw.handgun);
	public static Sound SOUND_MACHINEGUN	= new Sound(R.raw.machinegun);
	
	public static Sound SOUND_FUSE	= new Sound(R.raw.fuse);
	public static Sound SOUND_EXPLOSION	= new Sound(R.raw.explosion);
	
	public static Sound SOUND_CRATE		= new Sound(R.raw.ammo);
	public static Sound SOUND_DOOR		= new Sound(R.raw.health);
	public static Sound SOUND_SCREAM	= new Sound(R.raw.scream);
	public static Sound SOUND_LAUGH		= new Sound(R.raw.evil_laugh);
	/**
	 *  WINDOW SIZE (SET IN MAIN)
	 */
	public static int WINDOW_WIDTH  = 0;
	public static int WINDOW_HEIGHT = 0;
	public static final int TOP_PANEL_SIZE = 50;
	public static final int TOP_MENU_SIZE = 20;
	
	/**
	 *  LIVES
	 */
	
	// Number of max lives for the main character
	public static final int MAXLIVES = 10;
	
	// Number of start lives for the main character
	public static final int STARTLIVES = 4;
	
	// Alf Inge Wang final boss lives
	public static final int ALFIELIVES = 20;
	
	/**
	 * MAIN CHARACTER
	 */
	
	// Speed in x direction
	public static final float CHARACTER_SPEED = 50f;
	
	// Max falling speed
	public static final float TERMINAL_VELOCITY = 75f;
	
	// Jump speed
	public static final float JUMP_SPEED = 150f;
	
	/**
	 * ENEMIES
	 */
	
	// Speed in x direction
	public static final float ENEMY_SPEED = 50f;
	
	/**
	 * AMMUNITION	 
	 */
	
	// Number of bullets for the default gun
	public static final int GUNAMMUNITION = 15;
	
	// Number of dynamites
	public static final int DYNAMITEAMMUNITION = 3;
	
	// Time before detonation
	public static final int DYNAMITE_TIME = 3;
	
	/**
	 * SPEED AND GRAVITY
	 */
	
	// The forward speed of the main character
	public static final float MAINCHARACTERSPEED = 100f;
	
	// Gravity force
	public static float GRAVITY = 250f;
	
	/**
	 * DAMAGE DONE BY WEAPONS
	 */
	
	// Damage done by the dynamite
	public static final int DYNAMITEDAMAGE = 4;
	
	// Damage done by the bullet from the gun
	public static final int BULLETDAMAGE = 1;
	
	// Alf Inge Wang weapon 1 damage
	public static final int ALFIEWEAPON1DAMAGE = 2;
	
	// Alf Inge Wang weapon 2 damage
	public static final int ALFIEWEAPON2DAMAGE = 5;

}

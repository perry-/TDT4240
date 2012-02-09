package no.ntnu.androidgame;

/**
 * The Main-class for the whole application. It extends Android Activity, which means that our
 * application is an activity.
 * 
 * @author rodsjo, vegare
 * @see WarningScreen, Constants
 */
import no.ntnu.androidgame.screens.WarningScreen;
import sheep.game.Game;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class Main extends Activity {
    /**
     * Creates a new instance of a Game, which is from the Sheep-framework. On the Game we 
     * pushes and pops states, and plays music. The window-size of the screen is detected
     * and saved in the Constants-class.
     * 
     * A Warning-screen is pushed on the stack to start the application
     * 
     * @author rodsjo, vegare
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Game game = new Game(this, null);
        
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        Constants.WINDOW_HEIGHT = dm.heightPixels - Constants.TOP_PANEL_SIZE;
        Constants.WINDOW_WIDTH  = dm.widthPixels;
                
        game.pushState(new WarningScreen(this));
        setContentView(game);
    }
}
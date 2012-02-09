package no.perry.pong;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import sheep.game.Game;

public class PongActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this, null);
        Display display = getWindowManager().getDefaultDisplay(); 
        //Push the main state.
        game.pushState(new GameState(display));
        //View the game.
        setContentView(game);
    }
    
    
}
package no.perry.heli;

import android.app.Activity;
import android.os.Bundle;
import sheep.game.Game;

public class HeliActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this, null);
        //Push the main state.
        game.pushState(new GameState(game.getResources()));
        //View the game.
        setContentView(game);
    }
    
    
}


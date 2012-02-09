package lars.ntnu.helicopter;

import android.app.Activity;
import android.os.Bundle;
import sheep.game.Game;

public class HeliActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Game game = new Game (this, null);
        
        game.pushState(new TitleScreen(getResources()));
        
        
        setContentView(game);
    }
}
package com.example.mygame;

import sheep.game.Game;
import android.app.Activity;
import android.os.Bundle;


public class Gane extends Activity {
    /** Called when the activity is first created. */
       
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     // Create the game.
      Game game = new Game(this, null);
        // Push the main state.
         game.pushState(new TitleScreen());
        // View the game.
          setContentView(game);
        
    }
}
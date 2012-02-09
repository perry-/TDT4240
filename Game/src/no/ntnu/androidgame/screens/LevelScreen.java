package no.ntnu.androidgame.screens;

import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;
import android.view.KeyEvent;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.GameMusic;
import no.ntnu.androidgame.models.Level;
import no.ntnu.androidgame.tokens.Door;
import no.ntnu.androidgame.tokens.MainCharacter;
import no.ntnu.androidgame.tokens.Token;
import no.ntnu.androidgame.tokens.crate.AmmoPack;
import no.ntnu.androidgame.tokens.crate.Crate;
import no.ntnu.androidgame.tokens.crate.DynamitePack;
import no.ntnu.androidgame.tokens.crate.HealthPack;
import no.ntnu.androidgame.tokens.enemy.Enemy;
import no.ntnu.androidgame.tokens.enemy.boss.AlfieBoss;
import no.ntnu.androidgame.tokens.weapons.Bullet;
import no.ntnu.androidgame.tokens.weapons.Explosion;
import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.State;

/**
 * The screen that presents a level. It has a Level model that contains everything that
 * needs to be drawn to the screen.
 * 
 * This class also takes care of user interaction and collisions. The class implements
 * TokenListener and CollisionListener
 * 
 * @author vegare
 * @see Level
 * @see BuildingScreen
 */
public class LevelScreen extends State implements TokenListener, CollisionListener {

	private BuildingScreen parent;
	private Level model;
	private int fps = 0;
	
	private float fps_average = 0;
	private int fps_sum = 0;
	private int fps_count = 0;
	
	private CollisionLayer collisionLayer;
	
	/**
	 * Constructor that creates a collisionlayer and adds all tokens to that layer
	 * 
	 * @param parent
	 * @param model
	 */
	public LevelScreen(BuildingScreen parent, Level model) {
		this.parent = parent;
		this.model = model;
		
		model.addTokenListener(this);
		
		collisionLayer = new CollisionLayer();
		for (Token token : model.getTokens()) {
			collisionLayer.addSprite(token);
			token.addCollisionListener(token);
			token.addCollisionListener(this);
		}
	}
	
	@Override
	public void update(float dt) {
		collisionLayer.update(dt);
		fps = (int)(1/dt);
		fps_sum += fps;
		fps_count += 1;
		fps_average = fps_sum / fps_count;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		
		if (model.getBackground() != null) {
			model.getBackground().draw(canvas, 0, Constants.TOP_MENU_SIZE);
		}
		
		Paint p = new Paint();
		p.setColor(Constants.HISTORYTEXTCOLOR);
		canvas.drawText("FPS: " + Float.toString(fps_average), 10f, 15f, p);
		canvas.drawText("Health: " + model.getQuest().getHealth(), 100f, 15f, p);
		canvas.drawText("Ammo: " + model.getQuest().getAmmunition(), 170f, 15f, p);
		canvas.drawText("Dynamites: " + model.getQuest().getDynamite(), 250f, 15f, p);
		
		collisionLayer.draw(canvas, null);
	}

	@Override
	public boolean onKeyDown(KeyEvent event) {
		super.onKeyDown(event);
		
		switch(event.getKeyCode()) {
			case KeyEvent.KEYCODE_DPAD_CENTER:
				model.getMainCharacter().fire();
				break;
			case KeyEvent.KEYCODE_ENTER:
				model.getMainCharacter().fire();
				break;
			case KeyEvent.KEYCODE_SPACE:
				model.getMainCharacter().dynamite();
				break;
			case KeyEvent.KEYCODE_DPAD_UP:
				model.getMainCharacter().jump();
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				model.getMainCharacter().moveLeft(true);
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				model.getMainCharacter().moveRight(true);
				break;
			case KeyEvent.KEYCODE_P:
				pauseGame();
				break;
			case KeyEvent.KEYCODE_PERIOD:
				pauseGame();
				break;
		}
		
		return true;
	}

	@Override
	public boolean onKeyUp(KeyEvent event) {
		super.onKeyUp(event);

		switch(event.getKeyCode()) {
			case KeyEvent.KEYCODE_DPAD_LEFT:
				model.getMainCharacter().moveLeft(false);
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				model.getMainCharacter().moveRight(false);
				break;
		}
		
		return true;
	}
	/**
	 * Pause the game by pushing a pause screen.
	 * You can return from the pause screen by
	 * pressing a back button.
	 */
	private void pauseGame() {
		GameMusic.stopLevelMusic();
		getGame().pushState(new PauseScreen());
	}
	
	@Override
	public void tokenAdded(Token t) {
		collisionLayer.addSprite(t);
		t.addCollisionListener(t);
		t.addCollisionListener(this);
	}

	@Override
	public void tokenRemoved(Token t) {
		collisionLayer.removeSprite(t);
		t.removeCollisionListener(t);
		t.removeCollisionListener(this);
		
		if (t instanceof AlfieBoss) {
			Door door = new Door();
			door.loadParam("x", "280");
			door.loadParam("y", "425");
			model.addToken(door);
		}
	}

	@Override
	public void collided(Sprite a, Sprite b) {
		
		if (a instanceof MainCharacter) {
			
			if (b instanceof Door) {
				
				Constants.SOUND_DOOR.play();
				parent.nextLevel();
				
			} else if (b instanceof Crate) {
				Constants.SOUND_CRATE.play();
				Crate c = (Crate) b;
				if (c instanceof HealthPack) {
					model.getQuest().increaseHealth(c.getAmount());
				} else if (c instanceof AmmoPack) {
					model.getQuest().increaseAmmunition(c.getAmount());
				} else if (c instanceof DynamitePack) {
					model.getQuest().increaseDynamite(c.getAmount());
				}
				
			} else if (b instanceof Bullet || b instanceof Explosion || b instanceof Enemy) {

				model.getQuest().decreaseHealth();
				
				// Death
				if(model.getQuest().getHealth() <= 0) {
					getGame().popState(); // Level
					getGame().popState(); // Building
					getGame().popState(); // GameQuest
					GameMusic.stopLevelMusic();
					Constants.SOUND_LAUGH.play();
					getGame().pushState(new VideoScreen(1));
				}
			}
		}
	}
}

package no.ntnu.androidgame.tokens.enemy.boss;

import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.R;
import no.ntnu.androidgame.models.weapons.Handgun;
import no.ntnu.androidgame.tokens.TokenGroups;
import no.ntnu.androidgame.tokens.Wall;
import no.ntnu.androidgame.tokens.enemy.Enemy;
import no.ntnu.androidgame.tokens.weapons.Bullet;
import no.ntnu.androidgame.tokens.weapons.Explosion;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * The final boss!
 * 
 * @author rodsjo
 *
 */
public class AlfieBoss extends Enemy {

	private static Image[] ge_images = {new Image(R.drawable.ge_frame0),new Image(R.drawable.se_frame1),new Image(R.drawable.she_frame2)};
	
	private int lives = 3;
	private boolean wallUnder = false;
	private Vector2 start_position;
	
	/**
	 * Constructor
	 */
	public AlfieBoss() {
		super(ge_images, 0.1f);
		setSpeed(0, 0);
		
		start_position = new Vector2(0,0);

		setGroup(TokenGroups.BOSS);
		setMask(TokenGroups.CRATE | TokenGroups.DOOR);
	}

	/**
	 * If the player is not in the air, start a jump sequence
	 */
	public void jump() {
		
		if (getSpeed().getY() == 0)
			setYSpeed((float) (-Constants.JUMP_SPEED * Math.random() * 2));
	}
	
	@Override
	public void collided(Sprite a, Sprite b) {

		if (b instanceof Wall) {
			Wall w = (Wall) b;
			
			if (w.isHorizontal()) {
				if (w.getY() >= this.getY()) snapToFloor(w);
			}
		} else if (b instanceof Bullet || b instanceof Explosion) {
			lives--;
			setX(start_position.getX());
			setY(start_position.getY());
		}
	}
	
	/**
	 * If we collide with a floor, make sure the player
	 * is located directly above it, not in the middle.
	 */
	private void snapToFloor(Wall w) {
		wallUnder = true;
		setY(w.getY() - getHeight()/2.0f);
	}
	
	@Override
	public void update(float dt) {
		
		// Are we done yet?
		if (lives <= 0) {
			level.removeToken(this);
		}
		
		// Add acceleration
		setYSpeed(Math.min(Constants.TERMINAL_VELOCITY,
						   getSpeed().getY() + Constants.GRAVITY * dt));
		
		// Enforce speed limits
		if (wallUnder)     { setYSpeed(Math.min(0, getSpeed().getY())); }
		if (getY() < 100)  { setYSpeed(1); setY(100); }
		if (getY() > 420)  { setYSpeed(-1); setY(420); }
		
		// Reset triggers
		wallUnder = false;
		
		// Update
		if (weapon != null) {
			aimAndShoot(dt);
		}
		super.update(dt);
		
		// Jump?
		if (level.getMainCharacter().getY() < getY()) {
			jump();
		}
		
	}	
	
	@Override
	public void loadParam(String key, String value) {
		super.loadParam(key, value);
		
		// The main character must remember his
		// starting position in case of restart 
		if (key.equals("x")) {
			start_position.setX(Integer.parseInt(value) - getWidth()/2.0f);
		}
		else if (key.equals("y")) {
			start_position.setY(Integer.parseInt(value) - getHeight()/2.0f);
		}
		else if (key.equals("final")) {
			if (value.equals("true")) {
				weapon = new Handgun();
			}
		}
	}
}
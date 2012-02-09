package no.ntnu.androidgame.tokens;

import no.ntnu.androidgame.R;
import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.models.weapons.Handgun;
import no.ntnu.androidgame.models.weapons.Machinegun;
import no.ntnu.androidgame.models.weapons.Shotgun;
import no.ntnu.androidgame.models.weapons.Weapon;
import no.ntnu.androidgame.tokens.enemy.Enemy;
import no.ntnu.androidgame.tokens.weapons.Bullet;
import no.ntnu.androidgame.tokens.weapons.Dynamite;
import no.ntnu.androidgame.tokens.weapons.Explosion;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * This is the class for the main character. It should only be one.
 * The main character is controlled by the game user.
 * 
 * @author rodsjo, vegare
 */
public class MainCharacter extends AnimationToken {
	
	private Vector2 respawn_point;
	
	private Weapon weapon;
	
	// For movement
	private boolean moveLeft  = false;
	private boolean moveRight = false;
	
	// For wall collision detection
	private boolean wallOver = false;
	private boolean wallUnder = false;
	private boolean wallLeft = false;
	private boolean wallRight = false;
	
	// Animation
	private static Image[] mc_images = {new Image(R.drawable.mc_frame0),new Image(R.drawable.mc_frame1),new Image(R.drawable.mc_frame2)};
	
	/**
	 * Constructor
	 */
	public MainCharacter() {
		
		super(mc_images, 0.1f, 0);
		
		// Default values
		respawn_point = new Vector2(0, 0);
		weapon 		  = new Handgun();
		
		setPosition(0, 0);
		setSpeed(0, 0);
		setAnimated(false);

		// Collision groups
		setGroup(TokenGroups.MAIN_CHARACTER);
	}
	
	/**
	 * When the player dies, he is moved
	 * back to the starting point of the level.
	 */
	public void restartLevel() {
		setSpeed(0, 0);
		setPosition(respawn_point.getX(), respawn_point.getY());
	}

	@Override
	public void collided(Sprite a, Sprite b) {

		if (b instanceof Enemy || b instanceof Explosion || b instanceof Bullet) {
			restartLevel();
		} else if (b instanceof Wall) {

			// Check wall location
			// and act accordingly
			Wall w = (Wall) b;
			
			if (w.isHorizontal()) {
				if (w.getY() < this.getY()) wallOver = true;
				else						snapToFloor(w);
			} else {
				if (w.getX() < this.getX()) wallLeft = true;
				else						wallRight = true;
			}
		}
	}

	/**
	 * If we collide with a floor, make sure the player
	 * is located directly above it, not in the middle.
	 */
	private void snapToFloor(Wall w) {
		wallUnder = true;
		setY(w.getY() - height/2.0f);
	}

	@Override
	public void update(float dt) {
		
		float x = getX();
		float y = getY();
		float xspeed = getSpeed().getX();
		float yspeed = getSpeed().getY();
		
		// Add acceleration
		yspeed = Math.min(Constants.TERMINAL_VELOCITY,
				   		  yspeed + Constants.GRAVITY * dt);
		
		setYSpeed(yspeed);
		
		// Enforce speed limits
		if (wallOver)  setYSpeed(Math.max(0, yspeed));
		if (wallUnder) setYSpeed(Math.min(0, yspeed));
		
		if (wallLeft)  setXSpeed(Math.max(0, xspeed));
		if (wallRight) setXSpeed(Math.min(0, xspeed));
		
		// Reset triggers
		wallOver = wallUnder = wallLeft = wallRight = false;
		
		// Set extra limits
		// This is a bug fix, it shouldn't be necessary
		if (x < 0)
			setX(Wall.THICKNESS);
		else if (x > Constants.WINDOW_WIDTH)
			setX(Constants.WINDOW_WIDTH - width - Wall.THICKNESS);
		if (y < Constants.TOP_MENU_SIZE)
			setY(Constants.TOP_PANEL_SIZE + Wall.THICKNESS);
		else if (y > Constants.WINDOW_HEIGHT)
			setY(Constants.WINDOW_HEIGHT - height - Wall.THICKNESS);
		
		// Update
		super.update(dt);
		weapon.update(dt);
	}

	@Override
	public void loadParam(String key, String value) {
		
		// Default actions is necessary here!
		super.loadParam(key, value);
		
		// The main character must remember his
		// starting position in case of restart 
		if (key.equals("x")) {
			respawn_point.setX(Integer.parseInt(value));
		}
		else if (key.equals("y")) {
			respawn_point.setY(Integer.parseInt(value));
		}
		else if (key.equals("weapon")) {

			// Handgun is default
			if (value.equals("shotgun")) {
				weapon = new Shotgun();
			}
			else if (value.equals("machinegun")) {
				weapon = new Machinegun();
			}
		}
	}
	
	/**
	 * Avoid shooting yourself by adding this safety
	 * margin before firing a bullet.
	 */
	private float weaponSafetyMargin() {
		return getFlipVertical() ? -width : width;
	}
	
	/**
	 * Fire your weapon. Creates new bullets if the
	 * weapon is ready and ammunition is ok, otherwise
	 * it does nothing.
	 */
	public void fire() {
		
		if (weapon.is_ready()) {
			
			Bullet[] bullets = weapon.fire(getX() + weaponSafetyMargin(),
										   getY(),
										   getFlipVertical());
			
			if (bullets.length <= level.getQuest().getAmmunition())
			{
				weapon.getGunFire().play();
				for (Bullet b : bullets) {
					level.getQuest().decreaseAmmunition();
					level.addToken(b);
				}
			} else {
				weapon.getGunEmpty().play();
			}
		}
	}

	/**
	 * Drop a dynamite
	 */
	public void dynamite() {

		if (level.getQuest().getDynamite() > 0) {
			
			level.getQuest().decreaseDynamite();
			level.addToken(new Dynamite(getX(), getY()));
		}
	}	
	
	/**
	 * If the player is not in the air, start a jump sequence
	 * by setting a negative y speed. Gravity will get him
	 * back down again.
	 */
	public void jump() {
		
		if (getSpeed().getY() == 0)
			setYSpeed(-Constants.JUMP_SPEED);
	}

	/**
	 * Following a key press, set whether the player
	 * is moving left or not. Used for nice movement.
	 * 
	 * @param b Left status
	 */
	public void moveLeft(boolean b) {
		moveLeft = b;
		updateSpeed();
	}

	/**
	 * Following a key press, set whether the player
	 * is moving right or not. Used for nice movement.
	 * 
	 * @param b Right status
	 */
	public void moveRight(boolean b) {
		moveRight = b;
		updateSpeed();
	}
	
	/**
	 * Update speed after a change in left/right status
	 */
	private void updateSpeed() {
		if (moveRight && !moveLeft) {
			setAnimated(true);
			setXSpeed(Constants.CHARACTER_SPEED);
			setFlipVertical(false);
		} else if (!moveRight && moveLeft) {
			setAnimated(true);
			setXSpeed(-Constants.CHARACTER_SPEED);
			setFlipVertical(true);
		} else {
			setAnimated(false);
			setXSpeed(0f);
		}
	}
}
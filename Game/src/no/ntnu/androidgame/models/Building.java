package no.ntnu.androidgame.models;

import java.util.ArrayList;

import sheep.graphics.Image;

/**
 * A building is a set of levels.
 * 
 * The building also maintains its own progression. At all time, one level is
 * considered "active". You can increase this, or return the active level.
 * Once completed, the building will stop providing levels.
 * 
 * @author vegare, rodsjo
 * @see BuildingScreen
 */
public class Building {

	private ArrayList<Level> levels;

	private String name;
	private String description;
	private Image photo, levelBackground;
	private Image open_image, closed_image, completed_image;
	private int image_xpos, image_ypos;
	private int current_level;
	private boolean is_locked;
	private boolean is_complete;
	
	public Building(String name, String description, Image levelBackground, Image open_image, Image closed_image, Image completed_image, Image picture, int image_xpos, int image_ypos) {
	
		this.name = name;
		this.description = description;
		this.setLevelBackground(levelBackground);
		this.setImage_xpos(image_xpos);
		this.setImage_ypos(image_ypos);
		this.setPhoto(picture);
		this.setOpen_image(open_image);
		this.setClosed_image(closed_image);
		this.setCompleted_image(completed_image);
		this.levels = new ArrayList<Level>(5);
		
		current_level = 0;
		is_locked = false;
		is_complete = false;
	}
	/**
	 * Adds a level to this building, and sets the levelBackground
	 * 
	 * @param level - the building to be added
	 */
	public void addLevel(Level level) {
		if (!levels.contains(level)) {
			levels.add(level);
			level.setBackground(levelBackground);
		}
	}
	/**
	 * Returns the current level active in this building
	 * 
	 * @return Level - the active level
	 */
	public Level getCurrentLevel() {
		if(isComplete()) 
			return null;
		return levels.get(current_level);
	}
	/**
	 * Returns number of levels in this building
	 * 
	 * @return int - number of levels in this building
	 */
	public int getLevelCount() {
		return levels.size();
	}
	/**
	 * Returns whatever this building is completed or not
	 * @return boolean - whatever this building is completed or not
	 */
	public boolean isComplete() {
		return is_complete;
	}
	/**
	 * Increases the active level by one
	 */
	public void increaseLevel() {
		if(current_level+1 < levels.size()) {
			current_level++;
		} else {
			is_complete = true;
		}
	}

	/**
	 * Locks a building. Special buildings can be locked from the user, and unlocked
	 * later in the game
	 */
	public void lock()   		{ is_locked = true;  }
	/**
	 * Unlocks a building. Special buildings can be locked from the user, and unlocked
	 * later in the game
	 */
	public void unlock() 		{ is_locked = false; }
	/**
	 * Returns whatever the building is locked or not
	 * 
	 * @return boolean - whatever the building is locked or not
	 */
	public boolean isLocked()   { return is_locked; }
	
	//
	// GETTERS AND SETTERS
	//
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setLevelBackground(Image background) {
		this.levelBackground = background;
	}

	public Image getLevelBackground() {
		return levelBackground;
	}
	public void setOpen_image(Image open_image) {
		this.open_image = open_image;
	}

	public Image getOpen_image() {
		return open_image;
	}
	
	public void setPhoto(Image photo) {
		this.photo = photo;
	}

	public Image getPhoto() {
		return photo;
	}
	
	public void setClosed_image(Image closed_image) {
		this.closed_image = closed_image;
	}

	public Image getClosed_image() {
		return closed_image;
	}

	public void setImage_xpos(int image_xpos) {
		this.image_xpos = image_xpos;
	}

	public int getImage_xpos() {
		return image_xpos;
	}

	public void setImage_ypos(int image_ypos) {
		this.image_ypos = image_ypos;
	}

	public int getImage_ypos() {
		return image_ypos;
	}

	public void setCompleted_image(Image completed_image) {
		this.completed_image = completed_image;
	}

	public Image getCompleted_image() {
		return completed_image;
	}
}

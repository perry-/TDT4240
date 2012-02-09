package no.ntnu.androidgame.models;

import java.util.ArrayList;

import no.ntnu.androidgame.Constants;
import no.ntnu.androidgame.R;

/**
 * A game quest is an active instance of the game.
 * 
 * This class holds a set of buildings, which again contains levels.
 * The game quest also stores health, ammunition, amount of dynamites and so on for the user.
 * 
 * @author vegare
 * @see GameQuestScreen
 */
public class GameQuest {

	// List of buildings on campus
	private ArrayList<Building> buildings;
	
	// Game wide player parameters
	private int health;
	private int ammunition;
	private int dynamite;
	
	/**
	 * Constructor. Binds health, ammunition and dynamite from the Constants-class. And creates the buildings
	 */
	public GameQuest() {
		
		health = Constants.STARTLIVES;
		ammunition = Constants.GUNAMMUNITION;
		dynamite = Constants.DYNAMITEAMMUNITION;
		
		createBuildings();
	}

	/**
	 * Create the buildings for a game instance. Can be moved
	 * to a configuration file for further modifiability, but
	 * we decided against prioritizing this.
	 */
	private void createBuildings() {
		
		buildings = new ArrayList<Building>();
		
		Building p15 = new Building("P15", "Always choose the shortest path!", Constants.BACKGROUND_P15, Constants.P15O, Constants.P15C, Constants.P15S, Constants.BUILDING_P15, 167, 158);
		
		p15.addLevel(new Level(this, R.raw.p15_first));
		p15.addLevel(new Level(this, R.raw.p15_second));
		p15.addLevel(new Level(this, R.raw.p15_third));
		
		Building it = new Building("IT-bygget", "Fear the NullPointerException!",Constants.BACKGROUND_ITBYGGET, Constants.ITBYGGETO, Constants.ITBYGGETC, Constants.ITBYGGETS, Constants.BUILDING_IT, 41,205);
		
		it.addLevel(new Level(this, R.raw.it_first));
		it.addLevel(new Level(this, R.raw.it_second));
		it.addLevel(new Level(this, R.raw.it_third));
		
		Building el = new Building("El-bygget", "Shocked yet?", Constants.BACKGROUND_ELBYGGET1, Constants.ELBYGGETO, Constants.ELBYGGETC, Constants.ELBYGGETS, Constants.BUILDING_ELEKTRO,0,69);
		
		el.addLevel(new Level(this, R.raw.el_first));
		el.addLevel(new Level(this, R.raw.el_second));
		el.addLevel(new Level(this, R.raw.el_third));
		
		Building real = new Building("Realfagsbygget", "Know your calculus or die!", Constants.BACKGROUND_REALBYGGET1, Constants.REALBYGGETO, Constants.REALBYGGETC, Constants.REALBYGGETS, Constants.BUILDING_REALFAG,53,304);
		
		real.addLevel(new Level(this, R.raw.real_first));
		real.addLevel(new Level(this, R.raw.real_second));
		real.addLevel(new Level(this, R.raw.real_third));
		
		Building stripa = new Building("Stripa", "Know your calculus or die!", Constants.BACKGROUND_STRIPA, Constants.STRIPAO, Constants.STRIPAC, Constants.STRIPAS, Constants.BUILDING_STRIPA,102,152);
		
		stripa.addLevel(new Level(this, R.raw.st_first));
		stripa.addLevel(new Level(this, R.raw.st_second));
		stripa.addLevel(new Level(this, R.raw.st_third));
		
		Building main = new Building("Hovedbygget", "Know your calculus or die!", Constants.BACKGROUND_MAIN, Constants.MAINBUILDINGO, Constants.MAINBUILDINGC, Constants.MAINBUILDINGS, Constants.BUILDING_MAIN,88,43);
		
		main.addLevel(new Level(this, R.raw.main_first));
		main.addLevel(new Level(this, R.raw.main_second));
		main.addLevel(new Level(this, R.raw.main_third));
		main.addLevel(new Level(this, R.raw.main_fourth));
		
		buildings.add(p15);
		buildings.add(it);
		buildings.add(el);
		buildings.add(real);
		buildings.add(stripa);
		buildings.add(main);
		
		// Lock the last building
		main.lock();
	}

	/**
	 * See if a game is completed by checking completion
	 * status for all buildings in the game.
	 * 
	 * @return boolean - Completion status, true or false
	 */
	public boolean isComplete() {
		
		for (Building b : buildings) {
			if (!b.isComplete()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if some remaining levels are locked.
	 * If this is the case, you ought to unlock something!
	 * 
	 * @return boolean - Lock status
	 */
	public boolean allBuildingsLocked() {
		
		for (Building b : buildings) {
			if (!b.isComplete() && !b.isLocked()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Unlock one of the locked buildings.
	 * Usually the first one, but the interface does not
	 * guarantee a strict order of buildings.
	 */
	public void unlockBuilding() {
		for (Building b : buildings) {
			if (b.isLocked()) {
				b.unlock();
				return;
			}
		}
	}
	
	//
	// GETTERS AND SETTERS
	//
	//  - Buildings
	//  - Health
	//  - Ammunition
	//  - Dynamite
	//
	public ArrayList<Building> getBuildings() {
		return buildings;
	}
	
	public int getHealth() {
		return health;
	}

	public int getAmmunition() {
		return ammunition;
	}
	
	public int getDynamite() {
		return dynamite;
	}
	
	public void increaseHealth(int amount) {
		this.health += amount;
	}
	
	public void decreaseHealth() {
		this.health -= 1;
	}

	public void increaseAmmunition(int amount) {
		this.ammunition += amount;
	}
	
	public void decreaseAmmunition() {
		this.ammunition -= 1;
	}
	
	public void increaseDynamite(int amount) {
		this.dynamite += amount;
	}
	
	public void decreaseDynamite() {
		this.dynamite -= 1;
	}
}

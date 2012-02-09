package no.ntnu.androidgame.tokens;

import no.ntnu.androidgame.tokens.crate.AmmoPack;
import no.ntnu.androidgame.tokens.crate.DynamitePack;
import no.ntnu.androidgame.tokens.crate.HealthPack;
import no.ntnu.androidgame.tokens.enemy.GunEnemy;
import no.ntnu.androidgame.tokens.enemy.ShotgunEnemy;
import no.ntnu.androidgame.tokens.enemy.SpearEnemy;
import no.ntnu.androidgame.tokens.enemy.boss.AlfieBoss;

/**
 * The Token Factory is used to create a Token from a text string.
 * This is necessary because reflection is too slow.
 * 
 * Every time you create a new token, it must be added to this file
 * to make sure the LevelParser understands what it means.
 * 
 * @author vegare
 * @see LevelParser
 */
public class TokenFactory {

	public static Token createToken(String name) {
		
		Token token = null;
		
		// TODO: This can probably be done in a prettier way
		if (name.equals("MainCharacter")) {
			token = new MainCharacter();
		} else if (name.equals("SpearEnemy")) {
			token = new SpearEnemy(0.1f);
		} else if (name.equals("GunEnemy")) {
			token = new GunEnemy(0.1f);
		} else if (name.equals("ShotgunEnemy")) {
			token = new ShotgunEnemy(0.1f);
		} else if (name.equals("Wall")) {
			token = new Wall();
		} else if (name.equals("HealthPack")) {
			token = new HealthPack();
		} else if (name.equals("AmmoPack")) {
			token = new AmmoPack();
		} else if (name.equals("DynamitePack")) {
			token = new DynamitePack();
		} else if (name.equals("Door")) {
			token = new Door();
		} else if (name.equals("AlfieBoss")) {
			token = new AlfieBoss();
		}
		
		return token;
	}
}

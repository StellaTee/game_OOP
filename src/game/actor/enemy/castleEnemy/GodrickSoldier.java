package game.actor.enemy.castleEnemy;

import game.runes.RunesManager;
import game.utils.RandomNumberGenerator;
import game.weapon.Grossmesser;

/**
 * The soldier of Godrick, they will not attack Dogs
 * @author Zhi Hui Tee
 */

public class GodrickSoldier extends CastleEnemy {

  /**
   * Constructor
   */
  public GodrickSoldier(){
    super("Godrick Soldier", 'p', 198);
    addWeaponToInventory(new Grossmesser());
    RunesManager.getInstance().addActorToSourceList(this);
  }

  /**
   * Generate 38 - 70 runes when defeated by the player
   * @return The number of runes generated
   */
  @Override
  public int generateRunes() {
    return RandomNumberGenerator.getRandomInt(38, 70);
  }
}

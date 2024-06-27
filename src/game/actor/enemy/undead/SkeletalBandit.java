package game.actor.enemy.undead;

import game.runes.RunesManager;
import game.utils.RandomNumberGenerator;
import game.weapon.Scimitar;

/**
 * A subclass of Undead that represents a skeletalbandit in the game.
 * SkeletalBandit generate runes when defeated.
 * @author Belvinjeet Kaur
 */
public class SkeletalBandit extends Undead{

  /**
     * Creates a new instance of the SkeletalBandit class with a name of "SkeletalBandit", a display 
     * character of 'b', hit points value of 184, and a Scrimitar weapon added to the inventory.
     * It also registers the actor with the RunesManager as a source of runes.
     */
  public SkeletalBandit() {
    super("Skeletal Bandit", 'b', 184);
    addWeaponToInventory(new Scimitar());
    RunesManager.getInstance().addActorToSourceList(this);
  }

  /**
   * Generates a random number of runes between 35 and 892.
   *
   * @return the number of runes generated
   */
  @Override
  public int generateRunes(){
    return RandomNumberGenerator.getRandomInt(35, 892);
  }
}

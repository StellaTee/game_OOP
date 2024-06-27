package game.actor.enemy.oceanic;

import game.runes.RunesManager;
import game.utils.RandomNumberGenerator;
import game.weapon.GiantCrabPincer;

/**
 * A subclass of Oceanic that represents a giant crab in the game.
 * Lone wolves are powerful and generate runes when defeated.
 * @author Belvinjeet Kaur
 */
public class GiantCrab extends Oceanic{

  /**
   * Creates a new instance of the GiantCrab class with a name of "Giant Crab", a display 
   * character of 'C', hit points value of 407, and a GiantCrabPincer weapon added to the inventory.
   * It also registers the actor with the RunesManager as a source of runes.
   */
  public GiantCrab() {
      super("Giant Crab", 'C', 407);
      this.addWeaponToInventory(new GiantCrabPincer());
      RunesManager.getInstance().addActorToSourceList(this);
  }

  /**
   * Generates a random number of runes between 318 and 4961.
   *
   * @return the number of runes generated
   */
  public int generateRunes() {
      return RandomNumberGenerator.getRandomInt(318, 4961);
  }
}

package game.actor.enemy.oceanic;

import game.runes.RunesManager;
import game.utils.RandomNumberGenerator;
import game.weapon.GiantCrayFishPincer;

/**
 * A subclass of Oceanic that represents a giant crayfish in the game.
 * Lone wolves are powerful and generate runes when defeated.
 * @author Belvinjeet Kaur
 */
public class GiantCrayfish extends Oceanic{

  /**
   * Creates a new instance of the GiantCrayfish class with a name of "Giant Crayfish", a display 
   * character of 'R', hit points value of 4803, and a GiantCrayFishPincer weapon added to the inventory.
   * It also registers the actor with the RunesManager as a source of runes.
   */
  public GiantCrayfish() {
    super("Giant Crayfish", 'R', 4803);
    this.addWeaponToInventory(new GiantCrayFishPincer());
    RunesManager.getInstance().addActorToSourceList(this);
  }

  /**
   * Generates a random number of runes between 500 and 2374.
   *
   * @return the number of runes generated
   */
  @Override
  public int generateRunes(){
    return RandomNumberGenerator.getRandomInt(500, 2374);
  }

}
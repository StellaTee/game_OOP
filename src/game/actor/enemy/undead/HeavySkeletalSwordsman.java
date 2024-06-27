package game.actor.enemy.undead;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.runes.RunesManager;
import game.utils.RandomNumberGenerator;
import game.weapon.Grossmesser;

/**
 * A subclass of Undead that represents a heavyskeletalswordsman in the game.
 * HeavySkeletalSwordsman generate runes when defeated.
 * @author Belvinjeet Kaur
 */
public class HeavySkeletalSwordsman extends Undead{

  /**
     * Creates a new instance of the HeavySkeletalSwordsman class with a name of "HeavySkeletalSwordsman", a display 
     * character of 'q', hit points value of 153, and a Grossmesser weapon added to the inventory.
     * It also registers the actor with the RunesManager as a source of runes.
     */
  public HeavySkeletalSwordsman() {
    super("Heavy Skeletal Swordsman", 'q', 153);
    addWeaponToInventory(new Grossmesser());
    RunesManager.getInstance().addActorToSourceList(this);
  }

  /**
     * Gets the intrinsic weapon of the HeavySkeletalSwordsman.
     *
     * @return null
     */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return null;
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


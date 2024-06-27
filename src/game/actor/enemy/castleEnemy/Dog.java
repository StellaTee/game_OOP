package game.actor.enemy.castleEnemy;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.runes.RunesManager;
import game.utils.RandomNumberGenerator;

/**
 * A hostile creature, Dog. It is raised in the Stormveil Castle.
 * They will not attack Godrick soldiers.
 * @author Zhi Hui Tee
 */
public class Dog extends CastleEnemy {

  /**
   * Constructor
   */
  public Dog( ) {
    super("Dog", 'a', 104);
    RunesManager.getInstance().addActorToSourceList(this);
  }

  /**
   * Gets the intrinsic weapon of the Dog in the castle.
   * @return An IntrinsicWeapon object representing the Dog's bite attack.
   */
  public IntrinsicWeapon getIntrinsicWeapon(){
    return new IntrinsicWeapon(101, "bite", 93);
  }

  /**
   * Generate 52 - 1390 runes when defeated by the player
   * @return The number of runes generated
   */
  @Override
  public int generateRunes() {
    return RandomNumberGenerator.getRandomInt(52, 1390);
  }

}

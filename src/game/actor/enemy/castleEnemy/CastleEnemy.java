package game.actor.enemy.castleEnemy;

import game.actor.enemy.Enemies;
import game.actor.enemy.EnemyType;
import game.runes.RunesSource;

/**
 * Abstract class representing the CastleEnemy class which is a type of enemy that in the Castle.
 * It extends the abstract `Enemies` class and implements the `RunesSource` interface.
 * @author Zhi Hui Tee
 *
 */

public abstract class CastleEnemy extends Enemies implements RunesSource {

  /**
   * Creating a new `CastleEnemy` object with the given name, display character, and hit points.
   * @param name The name of the CastleEnemy.
   * @param displayChar The character used to display the CastleEnemy.
   * @param hitPoints The number of hit points the CastleEnemy has.
   */
  public CastleEnemy(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    addCapability(EnemyType.CASTLE_ENEMY);

  }

  /**
   * Returns the type of enemy as `EnemyType.CastleEnemy`.
   * @return The enemy type.
   */
  public EnemyType enemyType() {
    return EnemyType.CASTLE_ENEMY;
  }

  /**
   * Generates the number of runes that the dog can provide.
   * This method is abstract and must be implemented by the subclasses.
   * @return The number of runes generated.
   */
  public abstract int generateRunes();


}

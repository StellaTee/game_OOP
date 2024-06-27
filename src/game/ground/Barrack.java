package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.enemy.castleEnemy.GodrickSoldier;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents the Barrack in the Stormveil Castle.
 * It spawns Godrick Soldiers with a 45% chance at each turn.
 * @author Zhi Hui Tee
 */

public class Barrack extends Ground {
  /**
   * Constructor.
   */
  public Barrack() {
    super('B');
  }

  @Override
  public void tick(Location location) {
    super.tick(location);
    int probability = RandomNumberGenerator.getRandomInt(100);
    if (probability <= 45 && !location.containsAnActor()){
      location.addActor(new GodrickSoldier());
    }
  }

}

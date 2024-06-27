package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.enemy.castleEnemy.Dog;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents the Cage in the Stormveil Castle.
 * It spawns Dogs with a 37% chance at each turn.
 * @author Zhi Hui Tee
 */

public class Cage extends Ground {

  /**
   * Constructor.
   */
  public Cage() {
    super('<');
  }

  @Override
  public void tick(Location location) {
    super.tick(location);
    int probability = RandomNumberGenerator.getRandomInt(100);
    if (probability <= 37 && !location.containsAnActor()){
      location.addActor(new Dog());
    }
  }
}

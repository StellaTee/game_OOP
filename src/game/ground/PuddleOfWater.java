package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.actor.enemy.Enemies;
import game.actor.enemy.EnemyType;

/**
 * A class that represents a puddle of water.
 * It is a type of environment.
 * It has a chance to spawn an oceanic enemy.
 * @author Bel and Yi Xuan Lim
 */

public class PuddleOfWater extends Environment {
    private final EnemyType enemyType = EnemyType.OCEANIC;
    /**
     * Constructor.
     *
     */
    public PuddleOfWater() {
        super('~');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Enemies spawned = factory.createEnemy(enemyType);
        if (spawned != null && !location.containsAnActor()){
            location.addActor(spawned);
        }
    }

}
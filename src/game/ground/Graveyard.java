package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.actor.enemy.Enemies;
import game.actor.enemy.EnemyType;

/**
 * A class that represents a graveyard.
 * It is a type of environment.
 * It has a chance to spawn an undead enemy.
 * @author Yi Xuan Lim
 */
public class Graveyard extends Environment {
    private final EnemyType enemyType = EnemyType.UNDEAD;

    /**
     * Constructor.
     */
    public Graveyard() {
        super('n');
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

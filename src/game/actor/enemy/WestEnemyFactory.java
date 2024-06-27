package game.actor.enemy;

import game.actor.enemy.wolf.LoneWolf;
import game.actor.enemy.oceanic.GiantCrab;
import game.actor.enemy.undead.HeavySkeletalSwordsman;
import game.utils.RandomNumberGenerator;

/**
 * This class is used to create enemies on the west side of the map.
 * @author Yi Xuan Lim
 */
public class WestEnemyFactory implements EnemyFactory {

    /**
     * This method is used to create enemies on the west side of the map.
     * @param type The type of the enemy to be created
     * @return The enemy object that is created, otherwise null
     */
    @Override
    public Enemies createEnemy(EnemyType type) {
        int probability = RandomNumberGenerator.getRandomInt(100);
        switch (type) {
            case UNDEAD -> {
                if (probability <= 27)
                    return new HeavySkeletalSwordsman();
            }
            case DOG -> {
                if (probability <= 33)
                    return new LoneWolf();
            }
            case OCEANIC -> {
                if (probability <= 2)
                    return new GiantCrab();
            }
        }
        return null;
    }
}

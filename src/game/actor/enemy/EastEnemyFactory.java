package game.actor.enemy;

import game.actor.enemy.wolf.GiantDog;
import game.actor.enemy.oceanic.GiantCrayfish;
import game.actor.enemy.undead.SkeletalBandit;
import game.utils.RandomNumberGenerator;

/**
 * This class is used to create enemies on the east side of the map.
 * @author Yi Xuan Lim
 */
public class EastEnemyFactory implements EnemyFactory {

    /**
     * This method is used to create enemies on the east side of the map.
     * @param type The type of the enemy to be created
     * @return The enemy object that is created, otherwise null
     */
    @Override
    public Enemies createEnemy(EnemyType type) {
        int probability = RandomNumberGenerator.getRandomInt(100);
        switch (type) {
            case UNDEAD -> {
                if (probability <= 27)
                    return new SkeletalBandit();
            }
            case DOG -> {
                if (probability <= 4)
                    return new GiantDog();
            }
            case OCEANIC -> {
                if (probability <= 1)
                    return new GiantCrayfish();
            }
        }
        return null;
    }
}

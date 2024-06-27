package game.actor.enemy.oceanic;

import game.actor.enemy.Enemies;
import game.actor.enemy.EnemyType;
import game.runes.RunesSource;

/**
 * Abstract class representing the Oceanic class which is a type of enemy that can provide runes.
 * It extends the abstract `Enemies` class and implements the `RunesSource` interface.
 * @author Belvinjeet Kaur
 *
 */
public abstract class Oceanic extends Enemies implements RunesSource{

    /**
     * Constructor for the Oceanic class.
     * @param name the name of the Oceanic enemy.
     * @param displayChar the character that represents the Oceanic enemy on the map.
     * @param hitPoints the initial hit points of the Oceanic enemy.
    */

    public Oceanic(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(EnemyType.OCEANIC);
    }


    /**
     * Generates the number of runes that the oceanic can provide.
     * This method is abstract and must be implemented by the subclasses.
     * @return The number of runes generated.
     */
    public abstract int generateRunes();


    /**
     * Returns the type of enemy as `EnemyType.OCEANIC`.
     * @return The enemy type.
     */
    public EnemyType enemyType() {
        return EnemyType.OCEANIC;
    }

}


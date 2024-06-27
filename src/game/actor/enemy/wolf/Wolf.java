package game.actor.enemy.wolf;

import game.actor.enemy.Enemies;
import game.actor.enemy.EnemyType;
import game.runes.RunesSource;

/**
 * Abstract class representing the Dog class which is a type of enemy that can provide runes.
 * It extends the abstract `Enemies` class and implements the `RunesSource` interface.
 * Created by:
 * @author Belvinjeet Kaur
 *
 */
public abstract class Wolf extends Enemies implements RunesSource{

    /**
     * Creating a new `Dog` object with the given name, display character, and hit points.
     * @param name The name of the dog.
     * @param displayChar The character used to display the dog.
     * @param hitPoints The number of hit points the dog has.
     */
    public Wolf(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(EnemyType.DOG);
    }

    /**
     * Returns the type of enemy as `EnemyType.DOG`.
     * @return The enemy type.
     */
    public EnemyType enemyType() {
        return EnemyType.DOG;
    }

    /**
     * Generates the number of runes that the dog can provide.
     * This method is abstract and must be implemented by the subclasses.
     * @return The number of runes generated.
     */
    public abstract int generateRunes();

}

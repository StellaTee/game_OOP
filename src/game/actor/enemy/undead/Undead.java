package game.actor.enemy.undead;

import game.actor.enemy.Enemies;
import game.actor.enemy.EnemyType;
import game.runes.RunesSource;
import game.utils.Status;

/**
 * Abstract class representing the Undead class which is a type of enemy that can provide runes.
 * It extends the abstract `Enemies` class and implements the `RunesSource` interface.
 * Created by:
 * @author Belvinjeet Kaur
 *
 */
public abstract class Undead extends Enemies implements RunesSource{
    /**
     * Creating a new `Undead` object with the given name, display character, and hit points.
     * @param name The name of the Undead.
     * @param displayChar The character used to display the undead.
     * @param hitPoints The number of hit points the undead has.
     */
    public Undead(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(Status.RESPAWNABLE);
        addCapability(EnemyType.UNDEAD);
    }

    /**
     * Returns the type of enemy as `EnemyType.OCEANIC`.
     * @return The enemy type.
     */
    public EnemyType enemyType() {
        return EnemyType.UNDEAD;
    }

    /**
     * Generates the number of runes that the oceanic can provide.
     * This method is abstract and must be implemented by the subclasses.
     * @return The number of runes generated.
     */
    @Override
    public abstract int generateRunes();

}

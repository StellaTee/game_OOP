package game.actor.player;

import game.actor.enemy.Enemies;
import game.runes.RunesSource;


/**
 * Abstract class representing the Invade class which helps the enemy in attacking players or other hostile creatures.
 * It extends the abstract `Enemies` class and implements the `RunesSource` interface.
 * Created by:
 * @author Belvinjeet Kaur
 *
 */
public abstract class Invade extends Enemies implements RunesSource{
    
    /**
     * Creating a new `Invader` object with the given name, display character, and hit points.
     * @param name The name of the invader.
     * @param displayChar The character used to display the invader.
     * @param hitPoints The number of hit points the invader has.
     */
    public Invade(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Generates the number of runes that the invader can provide.
     * This method is abstract and must be implemented by the subclasses.
     * @return The number of runes generated.
     */
    public abstract int generateRunes();
    
}


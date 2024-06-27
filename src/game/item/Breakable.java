package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface for objects that can be broken by an Actor in a GameMap.
 * @author Yi Xuan Lim
 */
public interface Breakable {

    /**
     * Breaks the item and returns a string describing the action.
     * @param actor the Actor breaking the item
     * @param map the GameMap containing the item
     * @return a string describing the action
     */
    String breakItem(Actor actor, GameMap map);

    /**
     * Checks whether the item can be broken by the given Actor.
     * @param otherActor the Actor breaking the item
     * @return true if the item can be broken by the given Actor
     */
    boolean canBreak(Actor otherActor);
}

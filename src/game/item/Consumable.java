package game.item;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface for objects that can be consumed by an Actor in a GameMap.
 * @author Yi Xuan Lim
 */
public interface Consumable {

    /**
     * Consumes the item and returns a string describing the action.
     * @param actor the Actor consuming the item
     * @return a string describing the action
     */
    String consume(Actor actor);

    /**
     * Checks whether the item can be consumed by the given Actor.
     * @param actor the Actor consuming the item
     * @return true if the item can be consumed by the given Actor
     */
    String consumeMenu(Actor actor);
}

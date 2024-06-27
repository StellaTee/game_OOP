package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action for despawning an actor.
 * Created by:
 * @author Yi Xuan Lim
 *
 */
public class DespawnActorAction extends Action {
    /**
     * Target actor to be despawned
     */
    private Actor target;

    /**
     * Constructor for DespawnActorAction initialising the target actor
     * @param target actor to be despawned
     */
    public DespawnActorAction(Actor target){
        this.target = target;
    }

    /**
     * Executes the despawn action for the target actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(target);
        return target + " has been removed from the map";
    }

    /**
     * Returns string representation for the action in menu.
     * @param actor The actor performing the action.
     * @return string representation for action in menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

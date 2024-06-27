package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An action to travel across location of different or same maps
 * Created by:
 * @author Yi Xuan Lim
 */
public class TravelAction extends Action {
    /**
     * Location to travel to
     */
    private  Location destination;
    /**
     * Name of the map for our destination
     */
    private String destinationName;

    /**
     * Constructor for travel action to define where to travel to
     * @param destination Location to travel to
     * @param destinationName Name of map for our destination
     */
    public TravelAction(Location destination, String destinationName){
        this.destination = destination;
        this.destinationName = destinationName;
    }

    /**
     * Moves the actor from its current map to another location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        destination.addActor(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + destinationName;
    }
}

package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.TravelAction;
import game.utils.Status;

/**
 * Class representing Golden Fog Door that allows player to travel to other maps.
 * @author Yi Xuan Lim
 */
public class GoldenFogDoor extends Ground {
    /**
     * The location representing where player will go after using the door
     */
    private Location destination;
    /**
     * The location representing where the door is located at inside its map
     */
    private Location source;
    /**
     * String representing name for the map for source of the door
     */
    private String sourceName;
    /**
     * String representing name for the map the door is linked with
     */
    private String destinationName;

    /**
     * Constructor for Golden Fog Door represented as 'D' on the map
     * @param source location where the door is located on the map
     * @param sourceName string for the map of source of location
     */
    public GoldenFogDoor(Location source, String sourceName) {
        super('D');
        this.source = source;
        this.sourceName = sourceName;
    }

    /**
     * Defines what actions that actor can perform with it.
     * Golden Fog door allows only player to travel to other maps in the world via TravelAction.
     * @see TravelAction
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return ActionList with actions that defines what actions that actor can perform with it
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        this.source = location;
        if (!actor.hasCapability(Status.HOSTILE_TO_PLAYER) &&
        actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new TravelAction(destination,destinationName));
        }
        return actions;
    }

    /**
     * Gets the location of source of door
     * @return Location of source for door
     */
    public Location getSource() {
        return source;
    }

    /**
     * Gets location of destination of door
     * @return Location of destination for door
     */
    public Location getDestination() {
        return destination;
    }

    /**
     * Sets the destination that the door will link to
     * @param destination Location of destination for the door
     * @param destinationName String for map name of destination
     */
    public void setDestination(Location destination, String destinationName) {
        this.destination = destination;
        this.destinationName = destinationName;
    }

    /**
     * Gets the name of the map for the source
     * @return String representing name of map for source location
     */
    public String getSourceName() {
        return sourceName;
    }
}

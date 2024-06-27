package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;

/**
 * Class representing Cliff ground.
 * @author Yi Xuan Lim
 */
public class Cliff extends Ground {

    /**
     * Constructor for cliff.
     * It is represented as +
     *
     */
    public Cliff() {
        super('+');
    }

    /**
     * For each tick, player gets killed instantly
     * Other NPC/Enemies cannot fall off a cliff
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            Actor locationActor = location.getActor();
            if (locationActor.hasCapability(Status.HOSTILE_TO_ENEMY) &&
                    !locationActor.hasCapability(Status.HOSTILE_TO_PLAYER)){
                locationActor.addCapability(Status.WIPED);
            }
        }
    }
}

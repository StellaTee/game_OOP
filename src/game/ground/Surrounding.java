package game.ground;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.player.Invader;

/**
 * A class that provides utility methods to check the surroundings in the game.
 * 
 * @author Belvinjeet Kaur
 */
public class Surrounding{

    /**
     * Checks if the player is present in the surroundings of a given location for an invader.
     * @param locationOf The location to check the surroundings of.
     * @param invader The invader for which the surroundings are checked.
     * @return A list of Actor objects representing the players found in the surroundings.
     */

    // Check if player is in surroundings
    public List<Actor> isPlayerInSurroundings(Location locationOf, Invader invader) {
        Location currentLocation = invader.locationOf(locationOf);
        List<Actor> player = new ArrayList<>();

        // Iterate through the exits of the current location
        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();
            Actor destinationActor = destination.getActor();

            // Check if the destination actor is the player
            if (destinationActor != null && destinationActor.allowableActions(destinationActor, "", invader.map()).size() > 0) {
                player.add(destinationActor);
            }
        }

        return player;
    }
    

    /**
     * Checks if there are actors that can be followed in the surrounding locations of a given location.
     * @param locationOf The location to check the surrounding actors of.
     * @return A list of Actor objects representing the actors that can be followed in the surroundings.
     */
    public List<Actor> canFollow(Location locationOf) {
        List<Actor> actorList = new ArrayList<>();
        int x = locationOf.x();
        int y = locationOf.y();
        GameMap map = locationOf.map();

        for(int i = x - 1; i <= x + 1; i++){
            for(int j = y - 1; j <= y + 1; j++){
                if (i == x && j == y){
                    continue;
                }
                if (map.at(x, y).containsAnActor()){
                    actorList.add(map.at(x, y).getActor());
                }
            }
        }
        return actorList;
    }
    
}


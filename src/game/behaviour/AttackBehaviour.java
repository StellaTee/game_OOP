package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AttackBehaviour is a class that figures out a AttackAction that will attack an enemy.
 * @author Yi Xuan Lim
 * Modified by: Yi Xuan Lim
 *
 */
public class AttackBehaviour implements Behaviour{

    /**
     * Returns a AttackAction to attack an enemy, if there is enemy in the surrounding.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action, or null if no AttackAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        Map<String, Actor> attackableEnemies = new HashMap<>();
        boolean hasAttackableEnemies = false;

        // Find attackable enemies in surroundings
        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();
            Actor destinationActor = destination.getActor();
            if (destinationActor != null && destinationActor.allowableActions(actor, "", map).size() > 0) {
                hasAttackableEnemies = true;
                attackableEnemies.put(exit.getName(), destination.getActor());
            }
        }

        // if has attackable enemies
        if (hasAttackableEnemies) {
            // Get one random enemy to attack
            List<String> directions = new ArrayList<>(attackableEnemies.keySet());
            int numOfAttackableEnemies = attackableEnemies.size();
            String randomDirection = directions.get(RandomNumberGenerator.getRandomInt(numOfAttackableEnemies));
            Actor target = attackableEnemies.get(randomDirection);
            ActionList allowedActions = target.allowableActions(actor, randomDirection, map);
            if (allowedActions.size() > 1){
                // 50 % chance of attacking with special skill if has special skill
                if (RandomNumberGenerator.getRandomInt(100) <= 50)
                    return allowedActions.get(0);
                else
                    return allowedActions.get(1);
            } else {
                return allowedActions.get(0);
            }
        } else {
            return null;
        }
    }
}

package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actor.enemy.EnemyType;

/**
 * An Action to perform Area Attack.
 * Attacks surrounding actors.
 * Created by:
 * @author Yi Xuan Lim
 * Modified: Zhi Hui Tee
 */
public class AreaAttackAction extends Action {

    /**
     * Weapon to be used to perform area attack
     */
    private Weapon weapon;

    /**
     * Constructor for AreaAttackAction that specifies weapon to be used.
     * @param weapon weapon to be used to perform area attack
     */
    public AreaAttackAction(Weapon weapon){
        this.weapon = weapon;
    }

    /**
     * Executes the area attack action by calling AttackAction on surrounding actors
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string of result of execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String res = "";

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            Actor target = destination.getActor();
            res += System.lineSeparator() + menuDescription(actor);
            if (target != null) {
                // The actor and target which raised in the Stormveil Castle couldn't attack each other
                if ((actor.hasCapability(EnemyType.CASTLE_ENEMY) && target.hasCapability(EnemyType.CASTLE_ENEMY))){
                    continue;
                }
                else {
                    res += System.lineSeparator() + new AttackAction(target, exit.getName(), weapon)
                        .execute(actor, map) + "\n";
                }
            }

        }
        return res;
    }

    /**
     * Returns string representation for the action in menu.
     * @param actor The actor performing the action.
     * @return string representation for action in menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performed "+ weapon.verb() +" attack.";
    }
}

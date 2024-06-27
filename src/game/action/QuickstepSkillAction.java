package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.RandomNumberGenerator;
import game.weapon.GreatKnife;

/**
 * An action executed if an actor holding GreatKnife performs quickstep.
 * It'll attack and then move away from the current enemy
 * Created by:
 * @author Yi Xuan Lim
 *
 */
public class QuickstepSkillAction extends Action {
    /**
     * Target to get attacked
     */
    private Actor target;
    /**
     * Weapon to execute the quickstep sklll
     */
    private GreatKnife weapon;
    /**
     * Direction to attack
     */
    private String direction;
    /**
     * ActionList storing MoveItemAction for moving after attack
     */
    private ActionList nextAvailableMoves = new ActionList();

    /**
     * Constructor for QuickstepSkillAction
     * Specifies the target to attack, and direction of the target to attack
     * @param target actor to get attacked
     * @param direction string representing direction of attack
     */
    public QuickstepSkillAction(Actor target, String direction){
        this.target = target;
        this.weapon = new GreatKnife();
        this.direction = direction;
    }

    /**
     * Executes Quickstep skill. Actor will attack the target first,
     * before moving away to random location in its surrounding.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string representation of results
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String res = menuDescription(actor);
        // Attacks
        res += new AttackAction(target,direction,weapon).execute(actor, map);

        // Get available locations to move to
        Location here = map.locationOf(actor);
        for (Exit exit: here.getExits()){
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor))
                nextAvailableMoves.add(new MoveActorAction(destination, exit.getName()));

        }

        // Move
        if (nextAvailableMoves.size() == 0){
            res += "\nQuickstep fails";
        } else {
            int i = RandomNumberGenerator.getRandomInt(0,nextAvailableMoves.size()-1);
            res += "\n"+nextAvailableMoves.get(i).execute(actor,map);
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
        return actor + " attacks "+ target + " and moves away";
    }
}

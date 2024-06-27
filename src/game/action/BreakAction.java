package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.item.Breakable;
import game.utils.RandomNumberGenerator;

/**
 * An Action to break Breakable item/actor.
 * Created by:
 * @author Yi Xuan Lim
 */
public class BreakAction extends Action {
    /**
     * Target to be broken
     */
    private Breakable target;
    /**
     * Weapon used to break the target
     */
    private Weapon weapon;

    /**
     * Constructor for BreakAction that initialises the target to be broken.
     * @param target breakable target to be broken
     */
    public BreakAction(Breakable target){
        this.target = target;
    }

    /**
     * Executes the break action by using weapon,
     * and has probability to break item the same rate as weapon hit rate.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string representation of result of execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getWeaponInventory().size() < 1){
          weapon = actor.getIntrinsicWeapon();
        } else{
            weapon = actor.getWeaponInventory().get(0);
        }

        String res = "";
        if (RandomNumberGenerator.getRandomInt(100) <= weapon.chanceToHit())
            res = target.breakItem(actor, map);
        return res;
    }

    /**
     * Returns string representation for the action in menu.
     * @param actor The actor performing the action.
     * @return string representation for action in menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " breaks " +target;
    }
}

package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.RandomNumberGenerator;
import game.weapon.Uchigatana;

/**
 * An action executed if an actor holding Uchigatana performs Unsheathe.
 * It'll attack with 2 times the normal damage of the weapon with 60% hit rate.
 * Created by:
 * @author Yi Xuan Lim
 *
 */
public class UnsheatheSkillAction extends Action {
    /**
     * Weapon to be used to attack
     */
    private Uchigatana weapon;
    /**
     * Target actor to be attacked
     */
    private Actor target;

    /**
     * Constructor for Unsheathe skill action
     * Specifies the target.
     * @param target
     */
    public UnsheatheSkillAction(Actor target){
        this.target = target;
        weapon = new Uchigatana();
    }

    /**
     * Executes Unsheathe skill action on target defined.
     * "Unsheathe", a unique skill that deals 2x damage
     * of the weapon with a 60% chance to hit the enemy.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(RandomNumberGenerator.getRandomInt(100) <= 60)) {
            return actor + " misses " + target + ".";
        }

        String result = "";
        int damage = weapon.damage() * 2;
        result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);

        if (!target.isConscious()) {
            result += new DeathAction(actor).execute(target, map);
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes Uchigatana on " + target;
    }
}

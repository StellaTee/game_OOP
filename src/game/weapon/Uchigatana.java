package game.weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.SellAction;
import game.action.UnsheatheSkillAction;
import game.utils.Ability;
import game.utils.Status;
/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 115 damage with 80% hit rate
 * @author Yi Xuan Lim
 *
 */
public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slice", 80);
        addCapability(Ability.UNSHEATHE);
        addCapability(Status.SELLABLE);
        addCapability(Status.PURCHASABLE);
    }

    /**
     * Get an active skill action from the weapon.
     * @param target target actor
     * @param direction direction of the target
     * @return UnsheatheSkillAction
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new UnsheatheSkillAction(target);
    }

    /**
     * Get an active skill action from the weapon.
     * @param holder weapon holder
     * @return SellAction if the holder has the status of TRADEABLE else null
     */
    public Action getSkill(Actor holder) {
        if (Sellable.canSell(holder)) {
            return new SellAction(this);
        }
        return null;
    }
    /**
     * Get the purchase price of player
     * @return  int 5000
     */
    @Override
    public int getPlayerPurchasePrice() {
        return 5000;
    }

    /**
     * Purchase this weapon
     * @param actor the actor who wants to perform this purchase
     */
    @Override
    public void purchase(Actor actor) {
        actor.addWeaponToInventory(new Uchigatana());
    }

    /**
     * Get the sell price of player
     * @return int 500
     */
    @Override
    public int getPlayerSellPrice() {
        return 500;
    }

    /**
     * Sell this weapon
     * @param actor the actor who wants to sell the weapon
     */
    @Override
    public void sell(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }
}

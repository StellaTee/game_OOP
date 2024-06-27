package game.weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.SellAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * @author Adrian Kristanto
 * Modified by: Yi Xuan Lim
 *
 */
public class Club extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        addCapability(Status.SELLABLE);
        addCapability(Status.PURCHASABLE);
    }

    /**
     * Get the purchase price of player
     * @return int 600
     */
    @Override
    public int getPlayerPurchasePrice() {
        return 600;
    }

    /**
     * Purchase this weapon
     * @param actor the actor who wants to perform this purchase
     */
    @Override
    public void purchase(Actor actor) {
        actor.addWeaponToInventory(new Club());
    }

    /**
     * Get the sell price of player
     * @return int 100
     */
    @Override
    public int getPlayerSellPrice() {
        return 100;
    }

    /**
     * Sell this weapon
     * @param actor the actor who wants to sell the weapon
     */
    @Override
    public void sell(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }

    /**
     * Get an active skill action from the weapon.
     * @param holder weapon holder
     * @return SellAction if the holder has the status of TRADEABLE, else null
     */
    @Override
    public Action getSkill(Actor holder) {
        if (Sellable.canSell(holder))
            return new SellAction(this);
        return null;
    }

}

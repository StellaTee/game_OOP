package game.weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.SellAction;

/**
 * A simple weapon that can be used to attack.
 * It deals 274 damage with 50% hit rate
 * @author Belvinjeet Kaur
 *
 */
public class AstrologerStaff extends WeaponItem implements Purchasable,Sellable {

    /**
     * Constructor
     */
    public AstrologerStaff() {
        super("Astrologer's Staff", 'f', 274, "staff", 50);
    }


    /**
     * Get the purchase price of player
     * @return int 800
     */
    @Override
    public int getPlayerPurchasePrice() {
        return 800;
    }

    /**
     * Purchase this weapon
     * @param actor the actor who wants to perform this purchase
     */
    @Override
    public void purchase(Actor actor) {
        actor.addWeaponToInventory(new AstrologerStaff());
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
     *  Sell this weapon
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
package game.weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.QuickstepSkillAction;
import game.action.SellAction;
import game.utils.Ability;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * @author Yi Xuan Lim
 *
 */
public class GreatKnife extends WeaponItem implements Purchasable,Sellable {

    /**
     * Constructor
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "cut", 70);
        addCapability(Ability.QUICKSTEP);
        addCapability(Status.SELLABLE);
        addCapability(Status.PURCHASABLE);
    }

    /**
     * Get an active skill action from the weapon.
     * @param target the target of the skill
      * @param direction the direction of the skill
     * @return QuickstepSkillAction
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickstepSkillAction(target,direction);
    }

    /**
     * Get the purchase price of player
     * @return int 3500
     */
    @Override
    public int getPlayerPurchasePrice() {
        return 3500;
    }

    /**
     * Purchase this weapon
     * @param actor the actor who wants to perform this purchase
     */
    @Override
    public void purchase(Actor actor) {
        actor.addWeaponToInventory(new GreatKnife());
    }

    /**
     * Get the sell price of player
     * @return int 350
     */
    @Override
    public int getPlayerSellPrice() {
        return 350;
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

package game.weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.SellAction;

/**
 * Class representing Grafted Dragon.
 * It deals 89 damage with 90% attack accuracy. It is represented by N
 * Not purchasable, but sellable for 200 runes.
 * @author Yi Xuan Lim
 */
public class GraftedDragon extends WeaponItem implements Sellable {
    /**
     * Constructor for Grafted Dragon represented by N
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "graft", 90);
    }

    /**
     * Gets the selling price for current weapon
     * @return integer representing selling price
     */
    @Override
    public int getPlayerSellPrice() {
        return 200;
    }

    /**
     * Defines what happen when actor sells the weapon
     * @param actor the actor who wants to sell the weapon
     */
    @Override
    public void sell(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    @Override
    public Action getSkill(Actor holder) {
        if (Sellable.canSell(holder)) {
            return new SellAction(this);
        }
        return null;
    }

}

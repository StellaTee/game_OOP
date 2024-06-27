package game.weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.SellAction;

/**
 * Class representing Axe of Godrick.
 * It deals 142 damage with 84% attack accuracy. It is represented by T
 * Not purchasable, but sellable for 100 runes.
 * @author Yi Xuan Lim
 */
public class AxeOfGodrick extends WeaponItem implements Sellable {
    /**
     * Constructor for Axe of Godrick represented by T
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "axe", 84);
    }

    /**
     * Gets the selling price for current weapon
     * @return integer representing selling price
     */
    @Override
    public int getPlayerSellPrice() {
        return 100;
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

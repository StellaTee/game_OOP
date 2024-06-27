package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.action.SellAction;
import game.utils.Status;
import game.weapon.Sellable;

/**
 * Class for a Remembrance of the Grafted, represented by O.
 * This item cannot be purchased but can either be sold to any trader for 20000 runes
 * or exchanged with a trader that can accept remembrances for either one of Godrick's weapons
 * @author Yi Xuan Lim
 */
public class RemembranceOfGrafted extends Item implements Sellable {

    /***
     * Constructor for Remembrance of the Grafted
     * Represented by O
     * Can be used to exchange for other weapons when interacting with Finger Reader Enia
     * @see game.action.ExchangeAction
     * @see game.actor.Enia
     */
    public RemembranceOfGrafted() {
        super("Remembrance of Grafted", 'O', true);
        addCapability(Status.EXCHANGEABLE);
    }

    /**
     * Item can be sold to any trader for 20000 runes
     * @return Integer representing selling price by player
     */
    @Override
    public int getPlayerSellPrice() {
        return 20000;
    }

    /**
     * Defines what is done when current item is sold
     * @param actor the actor who wants to sell the weapon
     */
    @Override
    public void sell(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    @Override
    public Action getSkill(Actor holder) {
        if (Sellable.canSell(holder)){
            return new SellAction(this);
        }
        return null;
    }

}

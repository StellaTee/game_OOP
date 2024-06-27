package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runes.RunesManager;
import game.weapon.Sellable;

/**
 * An Action that allows Actor to sell Sellable weapon
 * from Actor's inventory and remove the item to the Actor's inventory
 * while adding corresponding number of runes to Actor.
 * Created by:
 * @author Yi Xuan Lim
 * Modified by: Zhi Hui Tee
 */
public class SellAction extends Action {
    /**
     * Sellable item to be sold
     */
    private Sellable item;
    /**
     * Integer representing the amount of runes actor can get from selling the item
     */
    private int sellPrice;

    /**
     * Constructor to create an Action for seller to sell Sellable item.
     * @param item the Item to be sold
     */
    public SellAction(Sellable item) {
        this.item =  item;
        sellPrice = item.getPlayerSellPrice();
    }

    /**
     * Removes an item from the actor's inventory
     * and adds corresponding number of runes for actor.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RunesManager.getInstance().addRunes(actor, item.getPlayerSellPrice());
        item.sell(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + item + " for " + item.getPlayerSellPrice();
    }
}

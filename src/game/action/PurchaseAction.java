package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runes.RunesManager;
import game.weapon.Purchasable;

/**
 * An Action that allows Actor to purchase Purchasable items
 * from Trader and add the item to the Actor's inventory
 * while deducting corresponding number of runes from Actor.
 * Created by:
 * @author Yi Xuan Lim
 * Modified by: Zhi Hui Tee
 */
public class PurchaseAction extends Action {
    /**
     * Purchasable item to be purchased
     */
    private Purchasable item;
    /**
     * Price of item to be purchased
     */
    private int itemPrice;

    /**
     * Constructor to create an Action where Actor purchases an Purchasable item from Trader.
     * @param item item to purchase
     */
    public PurchaseAction(Purchasable item) {
        this.item =  item;
        itemPrice = item.getPlayerPurchasePrice();
    }

    /**
     * Gives an item to the actor by putting that item into the actor's inventory
     * and deducts corresponding number of runes from actor.
     * Player can only buy item if it doesn't exceed the amount that he/she carries.
     * @param actor the actor performing the purchase action.
     * @param map   the map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int playerRunes = RunesManager.getInstance().getRunesAmount(actor);
        if (playerRunes >= itemPrice){
            RunesManager.getInstance().decreaseAmountOfRunes(actor, itemPrice);
            item.purchase(actor);
            return actor + " purchases " + item + " from Merchant Kale (price: "+ itemPrice +")" ;
        } else {
            return actor + " does not have enough runes to purchase " + item;
        }
    }

    /**
     * Returns string representation for the action in menu.
     * @param actor The actor performing the action.
     * @return string representation for action in menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + item + " from Merchant Kale (price: "+ itemPrice +")" ;
    }

}

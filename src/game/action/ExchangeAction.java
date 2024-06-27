package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * An Action to exchange an item for a weapon item.
 * Created by:
 * @author Yi Xuan Lim
 */
public class ExchangeAction extends Action {

    /**
     * Item to be exchanged
     */
    private Item itemToExchange;
    /**
     * Weapon to be exchanged using item
     */
    private WeaponItem exchangedWeapon;

    /**
     * Constructor for action to define what is to be exchanged.
     * @param itemToExchange item to be exchanged
     * @param exchangedWeapon weapon to be exchanged using item
     */
    public ExchangeAction(Item itemToExchange, WeaponItem exchangedWeapon){
        this.itemToExchange = itemToExchange;
        this.exchangedWeapon = exchangedWeapon;
    }

    /**
     * Exchanges the item for another weapon
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(itemToExchange);
        actor.addWeaponToInventory(exchangedWeapon);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " exchange " + itemToExchange + " for "+ exchangedWeapon;
    }
}

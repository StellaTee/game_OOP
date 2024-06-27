package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.PurchaseAction;
import game.utils.Status;
import game.weapon.Club;
import game.weapon.GreatKnife;
import game.weapon.Scimitar;
import game.weapon.Uchigatana;

/**
 * Class representing the Trader.
 * It extends the Actor since it is a type of actor.
 * Merchants/Traders are actors that allow the player to purchase and sell certain items or weapons.
 * The player can find them scattered across the Lands Between.
 * Created by:
 * @author Yi Xuan Lim
 *
 */
public class Trader extends Actor {

    /**
     * Constructor for Trader. Has the capability Status.TRADEABLE.
     */
    public Trader(){
        super("Merchant Kale", 'K',999);
        addCapability(Status.TRADEABLE);
    }

    /**
     * Defines what the Trader does for each turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction, since trader does not do anything for each turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * The trader can interact with Player only. Player can choose to purchase Purchasable WeaponItems.
     *
     * @param otherActor the Actor that might be interacting with Trader
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions that other Actors can do to the current Actor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (!otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            actions.add(new PurchaseAction(new Club()));
            actions.add(new PurchaseAction(new GreatKnife()));
            actions.add(new PurchaseAction(new Scimitar()));
            actions.add(new PurchaseAction(new Uchigatana()));
        }
        return actions;
    }


}

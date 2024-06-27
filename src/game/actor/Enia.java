package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.ExchangeAction;
import game.utils.Status;
import game.weapon.AxeOfGodrick;
import game.weapon.GraftedDragon;

/**
 * Class representing a new trader Finger Reader Enia represented by E
 * That can accept Remembrance of Grafted from player to exchanged  for either the Axe of Godrick or Grafted Dragon.
 * The player cannot purchase weapons from this trader, but they can still sell anything that is sellable to this trader.
 * @author Yi Xuan Lim
 */
public class Enia extends Actor {

    /**
     * Constructor for the trader that has capability tradeable represented by E
     */
    public Enia(){
        super("Finger Reader Enia",'E',999);
        addCapability(Status.TRADEABLE);
    }

    /**
     * Accept Remembrance of the Grafted from player and exchanged to Axe of Godrick or Grafted Dragon.
     * Cannot do anything to the trader, except selling (selling defined in player's playTurn)
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)
                && otherActor.hasCapability(Status.EXCHANGEABLE)){
            for (Item item: otherActor.getItemInventory()){
                if (item.hasCapability(Status.EXCHANGEABLE)){
                    actions.add(new ExchangeAction(item, new GraftedDragon()));
                    actions.add(new ExchangeAction(item, new AxeOfGodrick()));
                }
            }
        }
        return actions;
    }

    /**
     * Finger Reader Enia does nothing for its turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}

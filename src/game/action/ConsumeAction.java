package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.Consumable;

/**
 * An Action to perform consume/use a Consumable
 * Created by:
 * @author Yi Xuan Lim
 */
public class ConsumeAction extends Action {
    /**
     * Consumable to be used
     */
    private Consumable item;

    /**
     * Constructor initialising item to consume
     * @param item
     */
    public ConsumeAction(Consumable item){
        this.item = item;
    }

    /**
     * Executes consume method of the consumable to consume it.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return item.consume(actor);
    }

    /**
     * Returns string representation for the action in menu.
     * @param actor The actor performing the action.
     * @return string representation for action in menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return item.consumeMenu(actor);

    }
}

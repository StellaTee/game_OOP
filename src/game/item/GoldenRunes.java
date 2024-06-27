package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.action.ConsumeAction;
import game.runes.RunesManager;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Class defining Golden Runes item represented by '*' that cannot be purchased.
 * The item can be found scattered across the maps and is able to be picked up or dropped by player.
 * When consumed, this item can generate any amount of runes, ranging from 200-10000 runes
 * @author Yi Xuan Lim
 */
public class GoldenRunes extends Item implements Consumable {

    /**
     * Number of turns item has not been picked up
     */
    private int unpickCount = 0;

    /**
     * Constructor for Golden Runes that is portable, has display char '*'.
     */
    public GoldenRunes() {
        super("Golden Runes", '*', true);
    }

    /**
     * Defines what will happen to the item and actor when item is consumed.
     * When consumed, this item can generate any amount of runes, ranging from 200-10000 runes.
     * It will get removed from actor's inventory upon consumption
     * @param actor actor consuming the Golden Runes
     */
    @Override
    public String consume(Actor actor) {
        int runes = RandomNumberGenerator.getRandomInt(200,10000);
        RunesManager.getInstance().addRunes(actor, runes);
        actor.removeItemFromInventory(this);
        return consumeMenu(actor) + " and gained " + runes + " runes";
    }

    /**
     * Only allow to pick up and drop item when item is on ground
     * Despawns the item once it is not picked up after 5 rounds
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        List<Action> allowable = new ArrayList<>(getAllowableActions());
        for (Action action: allowable){
            removeAction(action);
        }

        unpickCount ++;
        if (unpickCount > 4){
            currentLocation.removeItem(this);
        }
    }

    /**
     * Allow actor to consume item if item is being carried by actor.
     * number of times the item is unpicked remains as 0
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        unpickCount = 0;
        if (getAllowableActions().size() == 0)
            addAction(new ConsumeAction(this));
    }

    @Override
    public String consumeMenu(Actor actor) {
        return actor + " consumes Golden Runes";
    }
}

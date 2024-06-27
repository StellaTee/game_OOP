package game.weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.utils.Status;

/**
 * A interface for item which is sellable
 * @author Yi Xuan Lim
 *
 */
public interface Sellable{

    /**
     * Get the sell price of player
     * @return int
     */
    int getPlayerSellPrice();

    /**
     * Sell this weapon
     * @param actor the actor who wants to sell the weapon
     */
    void sell(Actor actor);
    Action getSkill(Actor holder);

    /**
     * Check if the actor can sell the weapon
     * @param actor the actor who wants to sell the weapon
     * @return true if the actor has the capability TRADEABLE
     */
    static boolean canSell(Actor actor){
        return actor.hasCapability(Status.TRADEABLE);
    };

}

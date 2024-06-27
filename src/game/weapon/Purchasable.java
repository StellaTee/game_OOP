package game.weapon;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A interface for item which is purchasable
 * @author Yi Xuan Lim
 *
 */
public interface Purchasable {

    /**
     * Get the purchase price of player
     * @return int
     */
    int getPlayerPurchasePrice();

    /**
     * Purchase this weapon
     * @param actor the actor who wants to perform this purchase
     */
    void purchase(Actor actor);

}

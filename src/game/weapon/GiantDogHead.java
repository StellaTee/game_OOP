package game.weapon;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 314 damage with 90% hit rate
 * @author Belvinjeet Kaur
 *
 */
public class GiantDogHead extends WeaponItem{

    /**
     * constructor
     */
    public GiantDogHead() {
        super("Giant Dog Head", 'H', 314, "head bonks", 90);
        portable = false; // making the weapon not portable
    }
    
}

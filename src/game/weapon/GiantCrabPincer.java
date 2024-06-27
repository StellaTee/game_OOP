package game.weapon;

import edu.monash.fit2099.engine.weapons.WeaponItem;
/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 208 damage with 90% hit rate
 * @author Belvinjeet Kaur
 *
 */
public class GiantCrabPincer extends WeaponItem {

    /**
     * constructor
     */
    public GiantCrabPincer() {
        super("Giant Crab Pincer", 'P', 208, "slam", 90);
        portable = false; // making the weapon not portable
    }

}



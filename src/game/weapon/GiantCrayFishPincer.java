package game.weapon;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 527 damage with 100% hit rate
 * @author Belvinjeet Kaur
 *
 */
public class GiantCrayFishPincer extends WeaponItem{

    /**
     * constructor
     */
    public GiantCrayFishPincer() {
        super("Giant CrayFish Pincer", 'F', 527, "pinces", 100);
        portable = false; // making the weapon not portable
    }
    
}

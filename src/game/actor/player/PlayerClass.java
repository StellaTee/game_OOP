package game.actor.player;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Abstract class representing the classes/combat archetypes that determines the
 * starting hit points and weapon in inventory
 * Created by:
 * @author Yi Xuan Lim
 *
 */

public abstract class PlayerClass {
    /**
     * Starting weapon for the player class chosen
     */
    private WeaponItem startWeapon;
    /**
     * Integer representing starting hit points for actor of player class
     */
    private int startHitPoints;

    /**
     * Constructor for Player class that initialises the starting weapon and hit points.
     * @param startWeapon starting weapon for player class
     * @param startHitPoints starting hit points for player class
     */
    public PlayerClass(WeaponItem startWeapon, int startHitPoints) {
        this.startWeapon = startWeapon;
        this.startHitPoints = startHitPoints;
    }

    /**
     * Returns the WeaponItem to be added to player inventory when starting game.
     * @return WeaponItem representing starting weapon for player class chosen
     */
    public WeaponItem getStartWeapon(){
        return startWeapon;
    }

    /**
     * Returns the max starting hit points for player class
     * @return integer representing max starting hit points for player class.
     */
    public int getStartHitPoints(){
        return startHitPoints;
    }

}

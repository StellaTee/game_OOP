package game.actor.enemy.wolf;

import game.runes.RunesManager;
import game.utils.RandomNumberGenerator;
import game.weapon.GiantDogHead;

/**
 * A subclass of Dog that represents a lone wolf in the game.
 * Lone wolves are powerful and generate runes when defeated.
 * @author Belvinjeet Kaur
 */

public class GiantDog extends Wolf {

    /**
     * Creates a new instance of the GiantDog class with a name of "Giant Dog", a display 
     * character of 'G', hit points value of 693, and a GiantDogHead weapon added to the inventory.
     * It also registers the actor with the RunesManager as a source of runes.
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693);
        this.addWeaponToInventory(new GiantDogHead());
        RunesManager.getInstance().addActorToSourceList(this);
    }

    /**
     * Generates a random number of runes between 313 and 1808.
     *
     * @return the number of runes generated
     */
    @Override
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(313, 1808);
    }


}
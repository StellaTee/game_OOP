package game.actor.enemy.wolf;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.runes.RunesManager;
import game.utils.RandomNumberGenerator;

/**
 * A subclass of Dog that represents a lone wolf in the game.
 * Lone wolves are powerful and generate runes when defeated.
 *
 * @author Adrian Kristanto
 * Modified by: Belvinjeet Kaur
 */
public class LoneWolf extends Wolf {

    /**
     * Creates a new instance of the LoneWolf class with a name of "Lone Wolf", a display 
     * character of 'h', hit points value of 102.
     * It also registers the actor with the RunesManager as a source of runes.
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        RunesManager.getInstance().addActorToSourceList(this);
    }

    /**
     * Gets the intrinsic weapon of the LoneWolf.
     *
     * @return An IntrinsicWeapon object representing the LoneWolf's bite attack.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bite", 95);
    }

    /**
   * Generates a random number of runes between 55 and 1470.
   *
   * @return the number of runes generated
   */
    @Override
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(55, 1470);
    }
}


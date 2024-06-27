package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actor.player.Astrologer;
import game.actor.player.Bandit;
import game.actor.player.Samurai;
import game.actor.player.Wretch;
import game.utils.RandomNumberGenerator;

/**
 * A class representing an action to summon an ally or invader in the game.
 * Extends the Action class.
 * 
 * @author Belvinjeet Kaur
 */
public class SummonAction extends Action{
    /**
     * The random number generator used for generating random values.
     */
    private Random random;

    /**
     * A flag indicating whether a player is on the sign.
     */
    private boolean playerOnSign;

    /**
     * A flag indicating whether the object is summoned.
     */
    private boolean isSummoned;

    /**
     * The current hit points of the object.
     */
    private int hitPoints;

    /**
     * The weapon item associated with the object.
     */
    private WeaponItem weaponItem;

    /**
     * The name of the object.
     */
    private String name;



    /**
     * Constructs a SummonAction object.
     * Initializes the random number generator.
     */
    public SummonAction() {
        random = new Random();
    }

    /**
     * Spawns an ally based on a random role index.
     * Sets the name, hit points, and weapon item for the ally.
     */
    private void spawnAlly() {
        int roleIndex = RandomNumberGenerator.getRandomInt(0, 3);

        switch (roleIndex) {
            case 0:
                name = "Bandit";
                hitPoints = new Bandit().getStartHitPoints();
                weaponItem = new Bandit().getStartWeapon();
                break;
            case 1:
                name = "Samurai";
                hitPoints = new Samurai().getStartHitPoints();
                weaponItem = new Samurai().getStartWeapon();
                break;
            case 2:
                name = "Wretch";
                hitPoints = new Wretch().getStartHitPoints();
                weaponItem = new Wretch().getStartWeapon();
                break;
            case 3:
                name = "Astrologer";
                hitPoints = new Astrologer().getStartHitPoints();
                weaponItem = new Astrologer().getStartWeapon();
                break;
        } 
    }

    /**
     * Spawns an invader based on a random role index.
     * Sets the name, hit points, and weapon item for the invader.
     */
    private void spawnInvader() {
        int roleIndex = RandomNumberGenerator.getRandomInt(0, 3);
        
        switch (roleIndex) {
            case 0:
                name = "Bandit";
                hitPoints = new Bandit().getStartHitPoints();
                weaponItem = new Bandit().getStartWeapon();
                break;
            case 1:
                name = "Samurai";
                hitPoints = new Samurai().getStartHitPoints();
                weaponItem = new Samurai().getStartWeapon();
                break;
            case 2:
                name = "Wretch";
                hitPoints = new Wretch().getStartHitPoints();
                weaponItem = new Wretch().getStartWeapon();
                break;
            case 3:
                name = "Astrologer";
                hitPoints = new Astrologer().getStartHitPoints();
                weaponItem = new Astrologer().getStartWeapon();
                break;
        }
    }

    /**
     * Executes the summon action for the given actor on the game map.
     * If the player is on a sign, it has a 50% chance to spawn an ally, otherwise an invader.
     * @param actor The actor performing the action.
     * @param map The game map on which the action is being performed.
     * @return A string representing the description of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (playerOnSign) {
            if (random.nextDouble() < 0.5) {
                spawnAlly();
            } else {
                spawnInvader();
            }
        }
        return menuDescription(actor);
    }

    /**
     * Generates the menu description for the summon action.
     * @param actor The actor performing the action.
     * @return A string representing the menu description of the action.
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + "summoned";
    }
}



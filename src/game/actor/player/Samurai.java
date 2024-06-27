package game.actor.player;

import game.weapon.Uchigatana;

/**
 * Class representing the Samurai.
 * It extends the PlayerClass since it is a type of player class.
 * If the player chooses the samurai class,
 * they will start the game with Uchigatana as their starting weapon.
 * Their starting hit point will be 455
 * Created by:
 * @author Yi Xuan Lim
 *
 */
public class Samurai extends PlayerClass {

    /**
     * Constructor for Samurai class
     */
    public Samurai(){
        super(new Uchigatana(), 455);
    }
}

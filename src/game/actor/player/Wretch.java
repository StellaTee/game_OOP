package game.actor.player;

import game.weapon.Club;

/**
 * Class representing the Bandit.
 * It extends the PlayerClass since it is a type of player class.
 * if the player chooses the Wretch class,
 * they will start with 414 hit points and a Club as their weapon.
 * Created by:
 * @author Yi Xuan Lim
 *
 */
public class Wretch extends PlayerClass{

    /**
     * Constructor for Wretch class
     */
    public Wretch(){
        super(new Club(), 414);
    }
}

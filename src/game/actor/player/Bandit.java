package game.actor.player;


import game.weapon.GreatKnife;

/**
 * Class representing the Bandit.
 * It extends the PlayerClass since it is a type of player class.
 * If the player chooses the bandit class, their starting hit point is 414,
 * and they start with the Great Knife in their weapon inventory.
 * Created by:
 * @author Yi Xuan Lim
 *
 */

public class Bandit extends PlayerClass{

    /**
     * Constructor for Bandit class
     */
    public Bandit(){
        super(new GreatKnife(), 414);
    }

}
 
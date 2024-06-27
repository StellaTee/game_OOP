package game.actor.player;

import game.weapon.AstrologerStaff;

/**
 * Class representing the Astrologer.
 * It extends the PlayerClass since it is a type of player class.
 * If the player chooses the Astrologer class, their starting hit point is 396,
 * and they start with the AstrologerStaff in their weapon inventory.
 * Created by:
 * @author Belvinjeet Kaur
 *
 */
public class Astrologer extends PlayerClass{

    /**
     * Constructor for Astrologer class
     */
    public Astrologer(){
        super(new AstrologerStaff(), 396);
    }

}

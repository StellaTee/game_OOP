package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Status;
/**
 * A class that represents the site of lost grace.
 * It is a type of ground.
 * It is where the player restarts the game and where the player can rest and heal.
 * @author Zhi Hui Tee and Yi Xuan Lim
 */

public class SiteOfLostGrace extends Ground {

  private String name;

  /**
   * Constructor.
   *
   */
  public SiteOfLostGrace() {
    super('U');
    name = "The First Step";
    addCapability(Status.RESTING);
  }

  /**
   * Returns a string that represents the site of lost grace.
   * @return a string that represents the site of lost grace.
   */
  @Override
  public String toString() {
    return name;
  }

  /**
   * Returns the name of the site of lost grace.
   * @return the name of the site of lost grace.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns true if the actor can enter the site of lost grace, with the RESTING capability.
   * @param a the Actor to check
   * @return true if the actor can enter the site of lost grace.
   */
  @Override
  public boolean canActorEnter(Actor a) {
    return (a.hasCapability(Status.RESTING));
  }

}

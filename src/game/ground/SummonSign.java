package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Status;

/**
 * A subclass of Ground that represents a summoning sign in the game.
 * 
 * This class represents a summoning sign on the game map. Actors with the RESTING capability
 * can enter the site of lost grace where the summoning sign is located.
 * 
 * @author Belvinjeet Kaur
 */
public class SummonSign extends Ground {
  
    /**
     * Constructor for the SummonSign class.
     */
    public SummonSign() {
      super('=');
      addCapability(Status.RESTING);
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
  



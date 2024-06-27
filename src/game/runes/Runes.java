package game.runes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.action.RecoverDroppedRunesAction;
import game.utils.Ability;

/**
 * A class that represents the runes in the game.
 * @author Zhi Hui Tee and Yi Xuan Lim
 *
 */

public class Runes extends Item {

  /**
   * to keep track of the amount of runes
   */
  private int amountOfTheRune = 0;

  /**
   * to keep tracked of has the player pick up the runes is the past
   */
  private boolean runesHasBeenRecovered;

    /***
     * Constructor.
     */
    public Runes(RunesSource runesHolder) {
        super("runes", '$', false);
        addCapability(Ability.RETRIEVABLE);
        RunesManager.getInstance().addActorToSourceList(runesHolder);
    }

  /***
   * Constructor.
   */
    public Runes(Actor runesHolder) {
        super("runes", '$', false);
        addCapability(Ability.RETRIEVABLE);
        RunesManager.getInstance().getRunesHeld().put(runesHolder,this);
    }

  /**
   * Allow the player to pick up the runes
   * @param actor the actor that is picking up the runes
   */
  public void allowPickUp(Actor actor){
        if (actor.hasCapability(Ability.RETRIEVABLE))
            // allow to pick up runes
            // actor should only have one RecoverDroppedRunesAction
            if (this.getAllowableActions().size() == 0){
              addAction(new RecoverDroppedRunesAction(this));
            }
    }

  /**
   * Disallow the player to pick up the runes
   * @param action the action that is to be removed so the actor couldn't pick up the runes
   */
  public void disallowPickUp(RecoverDroppedRunesAction action){
        removeAction(action);
   }

  /**
   * Get the amount of runes
   * @return the amount of runes
   */
  public int getAmountOfTheRune(){
        return amountOfTheRune;
    }

  /**
   * Add the amount of runes
   * @param amountOfRunes the amount of runes to be added
   */
  public void addAmountOfRunes(int amountOfRunes){
        this.amountOfTheRune += amountOfRunes;
    }

  /**
   * Set the amount of runes
   * @param amountOfTheRune the amount of runes to be set
   */
  public void setAmountOfTheRune(int amountOfTheRune){
        this.amountOfTheRune = amountOfTheRune;
    }

  /**
   * Decrease the amount of runes
   * @param amountOfRunesSpent the amount of runes to be decreased
   */
  public void decreaseAmountOfRunes(int amountOfRunesSpent){
        this.amountOfTheRune -= amountOfRunesSpent;
    }

  /**
   * Get the runes has been recovered(pick up) or not
   * @return true if the runes has been recovered, false otherwise
   */
  public boolean getHasRunesBeenRecovered() {
      return runesHasBeenRecovered;
    }

  /**
   * Set the runes has been recovered(pick up) or not
   * @param runesHasBeenRecovered true if the runes has been recovered, false otherwise
   */
  public void setRunesHasBeenRecovered(boolean runesHasBeenRecovered) {
      this.runesHasBeenRecovered = runesHasBeenRecovered;
    }

}

package game.runes;

import edu.monash.fit2099.engine.actors.Actor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that represents the runes manager in the game.
 * It is a singleton class that only has one instance of runes manager.
 * Runes manager is responsible for keeping track of the amount of runes the actor has.
 * @author Zhi Hui Tee
 */
public class RunesManager{
  /**
   * Static variable instance of type RunesManager
   */
  private static RunesManager instance = null;

  /**
   * A list of runes source
   */
  private List<RunesSource> runesSourceList = new ArrayList <> ();

  /**
   * A hash map that keeps track of the runes object the actor has
   */
  private Map <Actor, Runes> runesHeld = new HashMap<>() {};

  // String, runes amount of the actor

  public int getPlayerRunes(String liveSituation) {
    return playerDeathRunes.get(liveSituation);
  }

  public void setPlayerRunes(String playerSituation, int runeAmount) {
    this.playerDeathRunes.put(playerSituation,runeAmount);
  }

  /**
   * A hash map that keeps track of the runes object the player have when it dies and alive
   */
  private Map <String, Integer> playerDeathRunes = new HashMap<>() {};

  /**
   * Private constructor for the class
   */
  private RunesManager (){

  }
  /**
   * The static factory method ensures that only one instance of the RunesManager class exists
   * @return an instance of RunesManager
   */
  public static RunesManager getInstance(){
    if (instance == null){
      instance = new RunesManager();
    }
    return instance;
  }

  /**
   * Get the runesHeld HashMap
   * @return runesHeld HashMap
   */
  public Map<Actor,Runes> getRunesHeld (){
    return runesHeld;
  }

  /**
   * Add the runes amount and update it in the hash map
   * @param actor the actor that holds the runes
   * @param runesToAdd the amount of runes to be added
   */
  public void addRunes (Actor actor, int runesToAdd){
    Runes rune = getRunes(actor);
    rune.addAmountOfRunes(runesToAdd);
    runesHeld.put(actor,rune);
  }

  /**
   * Deduct the runes amount and update it in the hash map
   * @param actor the actor that holds the runes
   * @param runesToDeduct the amount of runes to be deducted
   */
  public void decreaseAmountOfRunes(Actor actor, int runesToDeduct){
    Runes rune = getRunes(actor);
    rune.decreaseAmountOfRunes(runesToDeduct);
    runesHeld.put(actor,rune);
  }

  /**
   * set the runes amount of the actor
   * @param actor the actor that holds the runes
   * @param amount the amount of runes to be set
   */
  public void setRunesAmountOfActor (Actor actor, int amount){
    Runes rune = runesHeld.get(actor);
    rune.setAmountOfTheRune(amount);
    runesHeld.put(actor,rune);
  }

  /**
   * get the amount of runes the actor has
   * @param actor the actor that holds the runes
   * @return int the amount of runes the actor has
   */
  public int getRunesAmount (Actor actor){
    return runesHeld.get(actor).getAmountOfTheRune();
  }

  /**
   * when a new enemy is spawned add it inside the sourcelist
   * @param actor the actor that holds the runes
   */
  public void addActorToSourceList(RunesSource actor){
    runesSourceList.add(actor);
  }


  /**
   * when the enemy dies remove it from the list
   * @param actor the actor that holds the runes
   */
  public void removeActorFromSourceList (Actor actor){
    int index = runesSourceList.indexOf(actor);
    runesSourceList.remove(index);
  }

  /**
   * get the runes object of the actor
   * @param actor the actor that holds the runes
   * @return runes object of the actor
   */
  public Runes getRunes(Actor actor){
    return runesHeld.get(actor);
  }

  /**
   * Obtain the enemy to generate random amount of runes to be added to player when it dies
   * @param actorToBeFound the actor that holds the runes
   * @return the enemy that holds the runes
   */
    public RunesSource findRunesSource(Actor actorToBeFound){
      for (RunesSource actor : runesSourceList){
        if (actor == actorToBeFound){
          return actor;
        }
      }
      return null;
    }


  /**
   * transfer the runes from enemies to player when it dies
   * @param runeHolder the actor that receive the runes
   * @param actor the actor that holds the runes
   */
  public void transferRunes(Actor runeHolder, Actor actor){
    RunesSource actorToFind = findRunesSource(actor);
    Integer currentRunesHeld = runesHeld.get(runeHolder).getAmountOfTheRune();
    if (actorToFind != null && currentRunesHeld != null){
      int runesToBeTransferred = actorToFind.generateRunes();
      int updateCurrentRunes = runesToBeTransferred + currentRunesHeld;
      addRunes(runeHolder, updateCurrentRunes);
      Runes updatedRunes = new Runes(actor);
      updatedRunes.setAmountOfTheRune(updateCurrentRunes);
      runesHeld.put(runeHolder, updatedRunes);
    }
  }


}

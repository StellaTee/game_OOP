package game.actor.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.AttackAction;
import game.action.DespawnActorAction;
import game.behaviour.AttackBehaviour;
import game.behaviour.Behaviour;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;
import game.utils.Ability;
import game.utils.RandomNumberGenerator;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.Status;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for Enemies that extends the Actor.
 * The enemies are all resettable so Resettable interface is implemented.
 * Created by:
 * @author Yi Xuan Lim
 * Modified by: Yi Xuan Lim and Zhi Hui Tee
 */
public abstract class Enemies extends Actor implements Resettable{

  /**
   * HashMap storing behaviours with its integer key as priority
   */
  protected Map<Integer, Behaviour> behaviours = new HashMap<>();
  /**
   * Stores current location of the actor
   */
  private Location currentLocation;

  /**
   * Constructor.for Enemies.
   * Registers it as a resettable and add behaviours to it.
   *
   * @param name        the name of the Enemy
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Enemies(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    addCapability(Status.HOSTILE_TO_ENEMY);
    addCapability(Status.HOSTILE_TO_PLAYER);
    this.behaviours.put(999, new WanderBehaviour());
    this.behaviours.put(2, new AttackBehaviour());
    ResetManager.getInstance().registerResettable(this);
  }

  /**
   * At each turn, select a valid action to perform using their behaviours
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return the valid action that can be performed in that iteration or null if no valid action is found
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    // at each turn each enemy has 10% chance of being despawned unless following player
    if (this.hasCapability(Status.WIPED) ||!this.hasCapability(Status.FOLLOW) && RandomNumberGenerator.getRandomInt(100) <= 10){
      ResetManager.getInstance().removeResettable(this);
      return new DespawnActorAction(this);
    }
    setCurrentLocation(map.locationOf(this));

    // if enemy one block away from player, follow the player.
    for (Exit exit : currentLocation.getExits()) {
      Location destination = exit.getDestination();
      Actor target = destination.getActor();
      if (target == null) {
        continue;
      }
      if (!target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
        // if is player, can follow
        this.behaviours.put(50, new FollowBehaviour(target));
      }
      if (otherActorCanAttack(destination.getActor())){
        addCapability(Status.AOEATTACKABLE);
      }
    }

    // Get action from behaviour
    for (Behaviour behaviour : behaviours.values()) {
      Action action = behaviour.getAction(this, map);
      if (action != null)
        return action;
    }

    removeCapability(Status.AOEATTACKABLE);
    return new DoNothingAction();
  }

  /**
   * Defines what actions the otherActor can perform with it.
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    // if can attack
    if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActorCanAttack(otherActor)){
      // Has weapon
      if (otherActor.getWeaponInventory().size() > 0) {
        // attack with weapon
        actions.add(new AttackAction(this, direction, otherActor.getWeaponInventory().get(0)));
      } else {
        // attack action with intrinsic weapon
        actions.add(new AttackAction(this, direction));
      }

      // player can attack with intrinsic weapon as well
      if (otherActor.hasCapability(Ability.RETRIEVABLE))
          actions.add(new AttackAction(this,direction));

      // Special skill / area attack action
      for (WeaponItem weapon: otherActor.getWeaponInventory()){
        Action skill = weapon.getSkill(this);
        Action targetedSkill = weapon.getSkill(this,direction);
        if (skill != null)
          actions.add(skill);
        if (targetedSkill != null)
          actions.add(targetedSkill);
      }

    }
    return actions;
  }

  /**
   * Gets the enemy type for the enemy
   * @return EnemyType for the enemy
   */
  public abstract EnemyType enemyType();

  /**
   * Generate random amount of runes within range specified for amount of runes to be dropped when it gets killed.
   * @return integer representing number of runes to be dropped
   */
  public abstract int generateRunes();

  /**
   * Records the current location for the actor
   * @param location current location for the actor
   */
  public void setCurrentLocation(Location location){
    currentLocation = location;
  }

  /**
   * Returns boolean to indicate if otherActor can attack current enemy
   * @param otherActor otherActor to check if it can attack current actor
   * @return boolean indicating otherActor can attack current actor or not
   */
  public boolean otherActorCanAttack(Actor otherActor){
    if (otherActor.findCapabilitiesByType(EnemyType.class).size() > 0)
      return otherActor.findCapabilitiesByType(EnemyType.class).get(0) != enemyType();
    return true;
  }

  /**
   * Defines what to do when reset is called
   */
  @Override
  public void reset() {
    addCapability(Status.WIPED);
  }

}

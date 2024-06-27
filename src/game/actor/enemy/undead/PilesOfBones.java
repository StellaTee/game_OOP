package game.actor.enemy.undead;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.BreakAction;
import game.action.DespawnActorAction;
import game.actor.enemy.EnemyType;
import game.item.Breakable;
import game.utils.Ability;

/**
 * A class representing a pile of bones that can be broken by a player to obtain a weapon and spawn an enemy.
 * Extends Undead class and implements Breakable interface.
 * @author Belvinjeet Kaur
 */

public class PilesOfBones extends Undead implements Breakable {

    /**
     * number of times of unhitcount 
     */
    private int unhitCount = 1;

    /**
     * The actor representing the enemies to spawn.
     */
    private Actor enemiesToSpawn;

    /**
     * The weapon item for this game object.
     */
    private WeaponItem weaponItem;


    /**
     * Constructs a new pile of bones with the specified enemy actor.
     * Creates a new instance of the Piles of Bones class with a name of "PilesofBones", a display 
     * character of 'X', hit points value of 9999, retrieves the first weapon item from the enemy's weapon inventory 
     * and adds the BREAKABLE capability to the PilesOfBones object that allows it to be broken or destroyed by the player or other actors in the game.
     * @param enemy The actor representing the enemies to spawn.
     */
    public PilesOfBones(Actor enemy) {
        super("Piles of bones", 'X',9999);
        enemiesToSpawn = enemy;
        weaponItem = enemy.getWeaponInventory().get(0);
        addCapability(Ability.BREAKABLE);
    }

    /**
     * This method represents the turn that the actor takes in the game.
     * If the actor is on the map, its unhitCount will be incremented by one.
     * If the unhitCount reaches 3, the actor will be removed from the map and the enemy will be spawned.
     * If the actor is not on the map, it will not do anything and return a DoNothingAction.
     * @param actions A collection of possible Actions for the actor to take
     * @param lastAction The Action that was taken last turn, if any
     * @param map The GameMap containing the actor
     * @param display The Display where the map is drawn
     * @return a DoNothingAction.
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (map.contains(this)){
            unhitCount += 1;
        }

        // if the item doesn't get hit after three turns, it will spawn enemy
        if (unhitCount > 2){
            Location currentLocation = map.locationOf(this);
            map.removeActor(this);
            currentLocation.addActor(enemiesToSpawn);
        }
        return new DoNothingAction();
    }

    /**
     * Method that returns the list of allowable actions for the given actor.
     * @param otherActor The actor performing the action.
     * @param direction The direction of the action.
     * @param map The map on which this terrain exists.
     * @return An ActionList object containing the allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (canBreak(otherActor)){
            actions.add(new BreakAction(this));
        }
        return actions;
    }

    /**
   * Generates runes.
   *
   * @return 0
   */
    @Override
    public int generateRunes(){
        return 0;
    }

    /**
     * Drops the weapon item and removes the breakable object from the game map when it is broken.
     * @param actor The actor that breaks the object.
     * @param map The game map where the object is located.
     * @return A string describing the destruction of the object.
     */
    @Override
    public String breakItem(Actor actor,GameMap map) {
        // drop weapon when break
        map.locationOf(this).addItem(weaponItem);
        // remove from map when break
       return new DespawnActorAction(this).execute(actor,map);
    }

    /**
     * Determines if an actor is able to break this object based on its capabilities.
     * @param otherActor The actor attempting to break this object.
     * @return True if the actor is not of type UNDEAD and can break this object, false otherwise.
     */
    @Override
    public boolean canBreak(Actor otherActor) {
        return !otherActor.hasCapability(EnemyType.UNDEAD);
    }
}

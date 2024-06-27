package game.actor.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.AreaAttackAction;
import game.action.AttackAction;
import game.actor.enemy.EnemyType;
import game.behaviour.AttackBehaviour;
import game.behaviour.Behaviour;
import game.ground.Surrounding;
import game.utils.RandomNumberGenerator;
import game.utils.Status;


/**
 * A subclass of Invade that represents an invader in the game.
 * 
 * This class represents an invader entity in the game. Invaders are hostile actors that
 * can perform actions based on their behaviors and interact with other actors.
 * 
 * @author Belvinjeet Kaur
 */
public class Invader extends Invade{

    /**
   * HashMap storing behaviours with its integer key as priority
   */
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
   * Stores current location of the actor
   */
    private Location currentLocation;

    /**
    * The GameMap associated with the current instance.
    */
    private GameMap map;

    /**
     * Constructor for the Invader class.
     * 
     * @param name        The name of the invader.
     * @param displayChar The character used to display the invader.
     * @param hitPoints   The initial hit points of the invader.
     * @param weaponItem  The weapon item held by the invader.
     */
    public Invader(String name, char displayChar, int hitPoints, WeaponItem weaponItem) {
        super("Invader", 'ඞ', hitPoints); 
    }

    /**
     * Plays a turn for the invader.
     * 
     * This method determines the actions to be performed by the invader during its turn.
     * It considers the surrounding environment and available behaviors to select the appropriate action.
     * 
     * @param actions    The list of available actions.
     * @param lastAction The last action performed.
     * @param map        The game map.
     * @param display    The display used for visualization.
     * @return The action to be performed by the invader.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        Surrounding surrounding = new Surrounding();
        List<Actor> actorList = surrounding.isPlayerInSurroundings(map.locationOf(this), this);
        List<Actor> followList = surrounding.canFollow(map.locationOf(this));

        Action action = null;

        boolean isActorAround = false;
        if(actorList.size() > 0){
            for(int i = 0; i < actorList.size(); i++){
                if(!actorList.get(i).hasCapability(this.capabilitiesList().get(0))){
                    this.behaviours.put(10, new AttackBehaviour());
                    isActorAround = true;
                }
            }
        }

        if (!isActorAround){
            this.behaviours.remove(10);
        }

        for (Behaviour behaviour : behaviours.values()) {
            action = behaviour.getAction(this, map);
            // if attack behaviour
            if (action == null) {
                int i = 0;
                while(i < actions.size()){
                    if (actions.get(i).getClass() == AreaAttackAction.class || actions.get(i).getClass() == AttackAction.class){
                        i++;
                    }
                    else{
                        actions.remove(actions.get(i));
                    }
                }

                if (actions.size() > 0){
                    return actions.get(RandomNumberGenerator.getRandomInt(actions.size()));
                }
                else{
                    return new DoNothingAction();
                }

            }
        }
        return action;
    }


    /**
     * Returns the list of allowable actions for the invader.
     * 
     * This method determines the actions that the invader can perform when interacting with another actor
     * in the specified direction on the given game map.
     * 
     * @param otherActor  The actor being interacted with.
     * @param direction   The direction of interaction.
     * @param map         The game map.
     * @return The list of allowable actions for the invader.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        this.map = map;
        
        ActionList actions = new ActionList();
        WeaponItem weaponItem = null;

        // if can attack
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            // attack action with intrinsic weapon
            actions.add(new AttackAction(this, direction));
        }
        

            // player can attack with intrinsic weapon as well
        else if (this.capabilitiesList().size() > 0 && !otherActor.hasCapability(this.capabilitiesList().get(0))){
            if (!otherActor.getWeaponInventory().isEmpty()){
                weaponItem = otherActor.getWeaponInventory().get(0);
                if(RandomNumberGenerator.getRandomInt(0, 100)> 50){
                    actions.add(weaponItem.getSkill(this,direction));
                }
                actions.add(new AttackAction(this,direction));
            }
            else{
                if(otherActor.getIntrinsicWeapon().verb().equals("slams")){
                    actions.add(new AreaAttackAction(weaponItem));
                }
            }
        }

        return actions;
    }

    /**
     * Generates the number of runes for the invader.
     * 
     * This method generates a random number of runes that the invader possesses.
     * 
     * @return The number of runes generated.
     */
    @Override
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(1358, 5578);
    }


    /**
     * Sets the current location of the invader.
     * 
     * @param location The current location of the invader.
     */
    public void setCurrentLocation(Location location){
        currentLocation = location;
    }


    /**
     * Returns the location of the specified location.
     * 
     * This method returns the location of the invader based on the specified location.
     * 
     * @param location The location to retrieve the invader's location.
     * @return The invader's location.
     */
    public Location locationOf(Location location) {
        return null;
    }

    /**
     * Returns the game map associated with the invader.
     * 
     * @return The game map associated with the invader.
     */
    public GameMap map() {
        return null;
    }

    /**
     * Returns the type of enemy associated with the invader.
     * 
     * @return The type of enemy associated with the invader.
     */
    @Override
    public EnemyType enemyType() {
        return null;
    }
    

}


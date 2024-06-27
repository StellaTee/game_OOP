package game.actor.player;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropWeaponAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.AttackAction;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

/**
 * A class representing an ally in the game.
 * Extends the Actor class.
 * 
 * @author Belvinjeet Kaur
 */
public class Ally extends Actor {

    /**
    * The GameMap associated with the current instance.
    */
    private GameMap map;

    /**
     * Constructs an Ally object with the specified name, display character, and hit points.
     * @param name The name of the ally.
     * @param displayChar The character used to display the ally.
     * @param hitPoints The hit points of the ally.
     */
    public Ally(String name, char displayChar, int hitPoints) {
        super(name, 'A', hitPoints);
    }

    /**
     * Defines the behavior of the ally during its turn.
     * Implements the allowableActions() method to determine which actions are allowed for the ally.
     * Chooses an action from the available actions list and returns it.
     * @param actions The list of available actions.
     * @param lastAction The last action performed.
     * @param map The game map.
     * @param display The display used to show the game.
     * @return The action chosen by the ally.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        int i = 0;
        while(i < actions.size()){
            if(actions.get(i).getClass() == DropWeaponAction.class){
                actions.remove(actions.get(i));
            }
            i++;
        }

        int j = 0;
        while( j < actions.size()){
            j++;
        }

        if(actions.size() > 0){
            return actions.get(RandomNumberGenerator.getRandomInt(actions.size()));
        }
        else{
            return new DoNothingAction();
        }
    }

    /**
	 * Actions that otherActor can perform on current Player instance.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return ActionList constaining list of Actions that otherActor can perform on current Player instance.
	 */
    @Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        this.map = map;
        ActionList actions = new ActionList();

        if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)){
            if(!otherActor.getWeaponInventory().isEmpty()){
                WeaponItem weaponItem = otherActor.getWeaponInventory().get(0);
                if(RandomNumberGenerator.getRandomInt(100)> 80){
                    actions.add(weaponItem.getSkill(this, direction));
                }
            }
            actions.add(new AttackAction(this, direction));
        }
        return actions;
	}


}



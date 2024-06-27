package game.actor.player;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.AttackAction;
import game.action.ExchangeAction;
import game.action.ResetAction;
import game.actor.enemy.EnemyType;
import game.item.FlaskOfCrimsonTears;
import game.runes.Runes;
import game.runes.RunesManager;
import game.utils.Ability;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Yi Xuan Lim and Zhi Hui Tee
 *
 */

public class Player extends Actor implements Resettable {

	/**
	 * The Runes held by the Player instance
	 */
	private Runes runeItem;
	/**
	 * Death count for the current Player
	 */
	private static int deathCount = 0;
	/**
	 * Location storing the previous location of the Player
	 */
	private Location previousLocation;
	/**
	 * Location storing the location of the last visited Site of Lost Grace on map.
	 */
	private Location lastVisitedSiteLocation;
	/**
	 * Location storing location where player last died.
	 */
	private static Location lastDeathLocation = null;
	/**
	 * Menu object to display the menu options in console
	 */
	private final Menu menu = new Menu();
	/**
	 * PlayerClass chosen by Player
	 */
	private PlayerClass playerClass;

	private Runes previousLocationRunes = null;


	private static FlaskOfCrimsonTears flaskOfCrimsonTears = new FlaskOfCrimsonTears();

	/**
	 * Constructor for player.
	 * Initialises player with starting hitpoints and weapons of chosen PlayerClass
	 * @param playerClass chosen player class
	 */
	public Player(PlayerClass playerClass) {
		super("Tarnished", '@', 0);
		this.playerClass = playerClass;
		this.runeItem = new Runes(this);
		setDeathCount(0);
		addItemToInventory(runeItem);
		addCapability(Status.HOSTILE_TO_ENEMY);
		addCapability(Status.RESTING);
		addCapability(Ability.RETRIEVABLE);
		addWeaponToInventory(playerClass.getStartWeapon());
		resetMaxHp(playerClass.getStartHitPoints());
		addItemToInventory(flaskOfCrimsonTears);
		ResetManager.getInstance().registerResettable(this);
		RunesManager.getInstance().addRunes(this, runeItem.getAmountOfTheRune());
	}

	/**
	 * Handles operations that occur during a Player's turn,
	 * and displays the Action options that is available to be chosen to perform.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return Action corresponding to option chosen by the I/O input in console.
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null){
			return lastAction.getNextAction();
		}

		// If has wiped status
		if (hasCapability(Status.WIPED)){
			handleDeadRunes();
			// then when we die, we check if the lastDeathLocation has runes,
			// if it has rune item we delete the runes
			if (getLastDeathLocation() == null){
				setLastDeathLocation(previousLocation);
				return new ResetAction(this,map.locationOf(this));
			}

			// update the latest current death location
			setLastDeathLocation(previousLocation);
			// reset (remove actor from map, respawn at site of lost grace)
			return new ResetAction(this,map.locationOf(this));
		}

		// Track location before starting to execute other action
		this.previousLocation = map.locationOf(this);
		if (map.locationOf(this).getGround().hasCapability(Status.RESTING))
			actions.add(new ResetAction(this,map.locationOf(this)));

		// Check if can perform action with surrounding actors
		for (Exit exit: map.locationOf(this).getExits()){
			Location destination = exit.getDestination();
			if (destination.containsAnActor() && destination.getActor().hasCapability(Status.TRADEABLE)){
				addCapability(Status.TRADEABLE);
			}
			if (destination.containsAnActor() && otherActorCanAttack(destination.getActor())){
				addCapability(Status.AOEATTACKABLE);
			}
			if (destination.getGround().hasCapability(Status.RESTING)){
				actions.add(new ResetAction(this, destination));
			}

		}

		// Add special skill for weapons held by player (doesn't require target)
		for (WeaponItem weaponItem: this.getWeaponInventory()){
			Action skill = weaponItem.getSkill(this);
			if (skill != null){
				actions.add(skill);
			}
		}

		// Reset status
		removeCapability(Status.TRADEABLE);
		removeCapability(Status.AOEATTACKABLE);

		// return/print the console menu
		int runesAmount = RunesManager.getInstance().getRunesAmount(this);
		String displayPlayer = String.format("%s (%d/%d), runes: %d",
				this.name, this.hitPoints, this.getMaxHp(), runesAmount);
		display.println(displayPlayer);
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Returns state of whether otherActor can attack current Player instance.
	 * @param otherActor the actor that might be performing attack
	 * @return boolean indicating whether otherActor can attack current Player instance.
	 */
	private boolean otherActorCanAttack(Actor otherActor) {
		return otherActor.findCapabilitiesByType(EnemyType.class).size() > 0;
	}

	/**
	 * Handles operations on Runes that happens when player dies.
	 */
	private void handleDeadRunes(){
		if (getDeathCount() == 0) {
			runeItem.allowPickUp(this);      // allow RecoverDroppedRunesAction
		}

		setDeathCount(getDeathCount() + 1);		// increment death count by 1

		if (previousLocationRunes == null){
			previousLocationRunes = runeItem;
		}

		if (getLastDeathLocation() == previousLocation){
			setRuneItem(RunesManager.getInstance().getRunes(this));
			runeItem.allowPickUp(this);
			Player.setDeathCount(1);
			getLastDeathLocation().removeItem(previousLocationRunes);
		}

		// remove the rune item dropped last time of player death if died again
		// before grabbing the runes
		if (getDeathCount() > 1 && !getIsHasRecoveredRunes()) {
				getLastDeathLocation().removeItem(previousLocationRunes);
				setRuneItem(RunesManager.getInstance().getRunes(this));
				runeItem.allowPickUp(this);
				Player.setDeathCount(1);
		}
		else if (getDeathCount() >= 1 && getIsHasRecoveredRunes()){
			getLastDeathLocation().removeItem(previousLocationRunes);
			setRuneItem(RunesManager.getInstance().getRunes(this));
			runeItem.allowPickUp(this);
			Player.setDeathCount(1);
		}

		// drop runes on map when player dies
		removeItemFromInventory(runeItem);
		previousLocation.addItem(runeItem);
		previousLocationRunes = runeItem;
		Runes droppedRunes = RunesManager.getInstance().getRunes(this);
		RunesManager.getInstance().setPlayerRunes("death", droppedRunes.getAmountOfTheRune());
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
		ActionList actions = new ActionList();
		if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)){
			if (otherActor.getWeaponInventory().size() > 0)
				actions.add(new AttackAction(this,direction,otherActor.getWeaponInventory().get(0)));
			else
				actions.add(new AttackAction(this,direction));
		}
		return actions;
	}

	/**
	 * The player can also attack the hostile creature with their intrinsic weapon.
	 * If they choose to do this, they will punch the enemy, dealing 11 damage.
	 * @return IntrinsicWeapon for player.
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11,"punch",100);
	}

	@Override
	public void reset() {
		this.resetMaxHp(this.getMaxHp());
		// respawn at last visited site
		lastVisitedSiteLocation.addActor(this);
		removeCapability(Status.WIPED);
	}

	/**
	 * Obtains the state of whether player has recovered runes since its most recent death
	 * @return boolean representing whether player recovered runes since last death.
	 */
	public boolean getIsHasRecoveredRunes() {
		return runeItem.getHasRunesBeenRecovered();
	}

	/**
	 * Obtains the current death count for the Player before retrieving Runes.
	 * @return integer representing current player death count
	 */
	public int getDeathCount() {
		return deathCount;
	}

	/**
	 * Obtains last death location of actor
	 * @return location where player last died
	 */
	public static Location getLastDeathLocation() {
		return lastDeathLocation;
	}

	/**
	 * Obtains the current hit point for the player
	 * @return integer representing current player's hit point
	 */
	public int getHitPoints(){
		return this.hitPoints;
	}

	/**
	 * Returns the rune item for the Player instance
	 * @return Runes object for registered with Player instance.
	 */
	public Runes getRunesItem(){
		runeItem = RunesManager.getInstance().getRunes(this);
		return runeItem;
	}

	/**
	 * Sets the rune item for Player instance
	 * @param runeItem Runes item to be registered inside RunesManager under current actor
	 */
	public void setRuneItem(Runes runeItem) {
		this.runeItem = runeItem;
		RunesManager.getInstance().setRunesAmountOfActor(this,runeItem.getAmountOfTheRune());
	}

	/**
	 * Sets the player's death count to specified death count
	 * @param deathCount integer representing number of times player died before retrieving runes dropped.
	 */
	public static void setDeathCount(int deathCount) {
		Player.deathCount = deathCount;
	}

	/**
	 * Records the player's last death location
	 * @param lastDeathLocation location where the player most recently died.
	 */
	public static void setLastDeathLocation(Location lastDeathLocation) {
		Player.lastDeathLocation = lastDeathLocation;
	}

	/**
	 * Records the most recently visited Site of Lost Grace instance
	 * @param location location of Site of Lost Grace most recently visited.
	 */
	public void setLastVisitedSite(Location location) {
		if (location.getGround().hasCapability(Status.RESTING))
			this.lastVisitedSiteLocation = location;
	}

	/**
	 * Obtains the location of the last visited Site of Lost Grace instance
	 * @return Location of last visited Site of Lost Grace.
	 */
	public Location getLastVisitedSite() {
		return lastVisitedSiteLocation;
	}

	/**
	 * get the FlaskOfCrimsonTears of the player
	 * @return flaskOfCrimsonTears
	 */
	public static FlaskOfCrimsonTears getFlaskOfCrimsonTears() {
		return flaskOfCrimsonTears;
	}

	/**
	 * set the FlaskOfCrimsonTears of the player
	 * @param flaskOfCrimsonTears
	 */
	public static void setFlaskOfCrimsonTears(FlaskOfCrimsonTears flaskOfCrimsonTears) {
		Player.flaskOfCrimsonTears = flaskOfCrimsonTears;
	}
}

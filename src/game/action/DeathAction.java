package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actor.enemy.undead.HeavySkeletalSwordsman;
import game.actor.enemy.undead.PilesOfBones;
import game.actor.enemy.undead.SkeletalBandit;
import game.runes.RunesManager;
import game.utils.Ability;
import game.utils.Status;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Yi Xuan Lim
 *
 */
public class DeathAction extends Action {
    /**
     * Attacker that triggered the DeathAction for our target actor
     */
    private Actor attacker;

    /**
     * Constructor for Death Action initialising the attacker actor
     * @param actor
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was.
     * Runes will be transferred if player killed enemy,
     * and target will also be removed from the map.
     * When Undead enemies are killed, they will spawn PilesOfBones on the ground.
     * Player getting killed will trigger set of operations defined in playTurn of player
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        Location deathLocation = map.locationOf(target);
        if (target.hasCapability(Ability.RETRIEVABLE)) {
            target.addCapability(Status.WIPED);
            return target.playTurn(null,this,map,null).execute(target, map);

        }
        else if (target.hasCapability(Status.RESPAWNABLE)) {
            // make weapon of respawnable entities not drop weapon when killed
            target.getWeaponInventory().get(0).togglePortability();
        }

        ActionList dropActions = new ActionList();
        for (Item item : target.getItemInventory())
            dropActions.add(item.getDropAction(target));
        for (WeaponItem weapon : target.getWeaponInventory())
            dropActions.add(weapon.getDropAction(target));
        for (Action drop : dropActions)
            drop.execute(target, map);

        // remove actor that died from map
        result += System.lineSeparator() + menuDescription(target);
        result += System.lineSeparator() + new DespawnActorAction(target).execute(attacker, map);

        if (target.hasCapability(Status.RESPAWNABLE)){
            // spawn piles of bones when die
            // reset to max hit points
            if (target.hasCapability(Status.PURCHASABLE))
                deathLocation.addActor(new PilesOfBones(new SkeletalBandit()));
            else
                deathLocation.addActor(new PilesOfBones(new HeavySkeletalSwordsman()));
        }

        // only player killing Enemies can add runes to player
        if (attacker.hasCapability(Ability.RETRIEVABLE)) {
            RunesManager.getInstance().transferRunes(attacker, target);
            // remove the dead enemy from the source list of the Runes
            RunesManager.getInstance().removeActorFromSourceList(target);
        }

        return result;

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}

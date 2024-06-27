package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.player.Player;
import game.runes.RunesManager;
import game.utils.FancyMessage;
import game.utils.ResetManager;
import game.utils.Status;

/**
 * Action that Resets and respawn actor back to last visited site of grace.
 * Creaetd by:
 * @author Yi Xuan Lim
 */
public class ResetAction extends Action {
    /**
     * Player that caused the ResetAction to be executed
     */
    private Player player;
    /**
     * Location where Reset is called
     */
    private Location location;

    /**
     * Constructor for ResetAction
     * @param player player triggering the reset
     * @param location location where ResetAction is triggered
     */
    public ResetAction(Player player, Location location){
        this.player = player;
        this.location = location;
    }

    /**
     * Executes the Reset action and sets the last visited site as respawn point for Player.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret;
        map.removeActor(actor);
        if (location.getGround().hasCapability(Status.RESTING))
            player.setLastVisitedSite(location);
        if (!player.hasCapability(Status.WIPED))
            ret =  menuDescription(player);
        else
        {ret =  FancyMessage.YOU_DIED +
        "\nThe amount of runes dropped: " + RunesManager.getInstance().getRunesAmount(actor);
        // Player died, reset runes amount to
        RunesManager.getInstance().setRunesAmountOfActor(actor,0);
        }
        ResetManager.getInstance().run();
        return ret;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests on " + player.getLastVisitedSite().getGround() +" Lost Site of Grace.";
    }


}

package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.player.Player;
import game.runes.Runes;
import game.runes.RunesManager;
import game.utils.Status;

/**
 * Player has the option to recover the dropped runes
 * Created by:
 * @author Zhi Hui Tee
 * Modified by: Yi Xuan Lim
 */
public class RecoverDroppedRunesAction extends Action {
  private Runes runesDroppedByPlayer;


  /**
   * Constructor for action to recover dropped runes
   * @param target Runes object to recover
   */
  public RecoverDroppedRunesAction(Runes target) {
    this.runesDroppedByPlayer = target;
  }

  /**
   * Executes the action to recover the runes dropped on ground.
   * @param actor The actor performing the action (player).
   * @param map The map the actor is on.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    if (!(actor.hasCapability(Status.HOSTILE_TO_PLAYER))) {
      Location here = map.locationOf(actor);
      // when the player stand on top of the runes
      if (here.getItems().contains(runesDroppedByPlayer)) {
        // Pick up item when player stands on top of dropped runes
        int amountRunesDroppedByPlayer = RunesManager.getInstance().getPlayerRunes("death");
        RunesManager.getInstance().addRunes(actor,amountRunesDroppedByPlayer);
        // once player picked up, remove recover option from actions list
        // reset the attributes if the player pick up the runes
        runesDroppedByPlayer.disallowPickUp(this);
        runesDroppedByPlayer.setRunesHasBeenRecovered(true);
        here.removeItem(runesDroppedByPlayer);
        Player.setDeathCount(1);

        return actor + " retrieves Runes ( value: "
                + runesDroppedByPlayer.getAmountOfTheRune() + ")";
      }
    }
    return null;
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " retrieves Runes ";
  }
}

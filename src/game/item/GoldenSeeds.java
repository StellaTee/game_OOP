package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.action.ConsumeAction;
import game.actor.player.Player;
import game.utils.Status;

/**
 * A class that represents the golden seed in the game.
 * @author Zhi Hui Tee
 */

public class GoldenSeeds extends Item implements Consumable{

  /**
   * Constructor.
   */
  public GoldenSeeds() {
    super("Golden Seeds", 'G', false);
    addCapability(Status.HEALTH_UP);
    addAction(new ConsumeAction(this));
  }

  /**
   * When player consume this item, it will increase the uses left of the flask of crimson tears by 1
   * @param actor the Actor consuming the item
   * @return a description of the Golden Seeds.
   */

  @Override
  public String consume(Actor actor) {
    if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !actor.hasCapability(Status.HOSTILE_TO_PLAYER)){
      FlaskOfCrimsonTears flaskOfCrimsonTears = Player.getFlaskOfCrimsonTears();
      if (flaskOfCrimsonTears.getUsesLeft() > 0){
        flaskOfCrimsonTears.addUses();
      }
      // when the uses left is 0, the player will remove the flask of crimson tears from its inventory
      // therefore needed to create a new flask of crimson tears
      else if (flaskOfCrimsonTears.getUsesLeft() <= 0){
        FlaskOfCrimsonTears newFlaskOfCrimsonTears = new FlaskOfCrimsonTears();
        // as it is increasing by 1
        newFlaskOfCrimsonTears.setUsesLeft(1);
        Player.setFlaskOfCrimsonTears(newFlaskOfCrimsonTears);
        actor.addItemToInventory(newFlaskOfCrimsonTears);
      }
      return actor + " consumes Golden Seeds and has " + flaskOfCrimsonTears.getUsesLeft() + " Flask of Crimson Tears.";
    }
    else{
      return actor + " cannot consumes Golden Seeds";
    }

  }

  @Override
  public String consumeMenu(Actor actor) {
    return actor + " consumes Golden Seeds";
  }

}

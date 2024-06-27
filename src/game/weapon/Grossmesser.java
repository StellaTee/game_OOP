package game.weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.action.AreaAttackAction;
import game.action.SellAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 115 damage with 85% hit rate
 * @author Yi Xuan Lim and Zhi Hui Tee
 *
 */
public class Grossmesser extends WeaponItem implements Sellable {

  /**
   * Constructor.
   *
   */
    public Grossmesser() {
      super("Grossmesser", '?', 115, "spin", 85);
      addCapability(Status.SELLABLE);
    }

  /**
   * Get an active skill action from the weapon.
   * @param holder weapon holder
   * @return AreaAttackAction if the holder has the capability AOEATTACKABLE, SellAction if
   *  the holder has the status of TRADEABLE else null
   */
  @Override
  public Action getSkill(Actor holder) {
      if (holder.hasCapability(Status.AOEATTACKABLE)) {
          return new AreaAttackAction(this);
      }
      if (Sellable.canSell(holder)) {
          return new SellAction(this);
      }
      return null;
  }

  /**
   * Get the sell price of player
   * @return int 100
   */
  @Override
    public int getPlayerSellPrice() {
        return 100;
    }

  /**
   * Sell this weapon
   * @param actor the actor who wants to sell the weapon
   */
  @Override
    public void sell(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }
}

package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.action.ConsumeAction;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.Status;

/**
 * A class that represents the flask of crimson tears in the game.
 * @author Yi Xuan Lim
 */
public class FlaskOfCrimsonTears extends Item implements Resettable, Consumable {

    /**
     * The number of uses left for the flask.
     */
    private int usesLeft;

    /**
     * The maximum number of uses for the flask which is 2.
     */
    private static final int MAX_USES = 2;

    /**
     * The amount of health points the flask restores.
     */
    private static final int HEAL_PTS = 250;

    /**
     * Constructor.
     */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", 'c', false);
        addCapability(Status.HEALTH_UP);
        // gets used, restore health by 250 pts
        addAction(new ConsumeAction(this));
        this.usesLeft = MAX_USES;
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Returns the number of uses left for the FlaskOfCrimsonTears.
     * @return the number of uses left for the FlaskOfCrimsonTears
     */
    public int getUsesLeft() {
        return usesLeft;
    }

    /**
     * Increase the usage by 1
     * @return true if the current usage is less than 2, false otherwise
     */
    public boolean addUses(){
        if (usesLeft < MAX_USES){
            usesLeft += 1;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Decreases the number of uses left for the FlaskOfCrimsonTears by 1.
     */
    public void decreaseUsesLeft(){
        this.usesLeft -= 1;
    }

    /**
     * Returns the amount of health points the flask restores.
     * @return the amount of health points the flask restores
     */
    public int getHealPts(){
        return HEAL_PTS;
    }

    /**
     * Sets the number of uses left for the FlaskOfCrimsonTears.
     * @param usesLeft the number of uses left for the FlaskOfCrimsonTears
     */
    public void setUsesLeft(int usesLeft) {
        this.usesLeft = usesLeft;
    }

    /**
     * Resets the number of uses left for the FlaskOfCrimsonTears to 2 when the game reset.
     * Resets the FlaskOfCrimsonTears to the initial state.
     */
    @Override
    public void reset() {
        setUsesLeft(MAX_USES);
    }

    /**
     * Consumes the item and returns a string describing the action.
     * @param actor the Actor consuming the item
     * @return a string describing the action
     */
    @Override
    public String consume(Actor actor) {
        actor.heal(getHealPts());
        decreaseUsesLeft();
        if (getUsesLeft() < 1) {
            actor.removeItemFromInventory(this);
            ResetManager.getInstance().removeResettable(this);
        }
        return actor + " used " + this + ". Player's HP increased by " + getHealPts() +
                ". Item has "+ getUsesLeft() +" uses left.";
    }

    /**
     * Returns a menu of options for consuming the FlaskOfCrimsonTears.
     * @param actor the Actor consuming the item
     * @return a menu of options for consuming the FlaskOfCrimsonTears
     */
    @Override
    public String consumeMenu(Actor actor) {
        return actor + " consumes Flask of Crimson Tears " + "("+ getUsesLeft() +"/2)";
    }
}

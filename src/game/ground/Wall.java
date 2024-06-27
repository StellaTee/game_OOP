package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents a wall.
 * It is a type of ground.
 * It blocks thrown objects and all actor couldn't enter the wall.
 * @author Riordan D. Alfredo
 * Modified by: Nonw
 *
 */
public class Wall extends Ground {

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Returns false as all actor couldn't enter the wall.
	 * @param actor the Actor to check
	 * @return false as all actor couldn't enter the wall.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Returns true as the wall blocks thrown objects.
	 * @return true as the wall blocks thrown objects.
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}

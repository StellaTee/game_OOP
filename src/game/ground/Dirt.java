package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.item.GoldenRunes;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents bare dirt.
 * @author Riordan D. Alfredo
 * Modified by: Yi Xuan Lim
 *
 */
public class Dirt extends Ground {

	/**
	 * Constructor.
	 */
	public Dirt() {
		super('.');
	}

	/**
	 * Ground can also experience joy of time.
	 * Golden runes has a probability of 30% to be scattered on the ground, and 1% to be spawned on the ground.
	 *
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		if (RandomNumberGenerator.getRandomInt(100) <= 30 && RandomNumberGenerator.getRandomInt(100) <= 1)
			location.addItem(new GoldenRunes());
	}
}

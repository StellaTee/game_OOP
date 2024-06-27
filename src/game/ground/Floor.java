package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.enemy.castleEnemy.Dog;
import game.item.GoldenSeeds;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

/**
 * A class that represents the floor inside a building.
 * @author Riordan D. Alfredo
 *
 */
public class Floor extends Ground {
	/**
	 * Constructor.
	 */
	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return !(actor.hasCapability(Status.HOSTILE_TO_PLAYER));
	}

	/**
	 * when the floor is ticked, there is a 15% chance that a golden seed will spawn
	 * on the floor
	 * @param location The location of the Ground
	 */
	public void tick(Location location) {
		super.tick(location);
		int probability = RandomNumberGenerator.getRandomInt(100);
		boolean hasItem = false;
		// If there is already a golden seed on the floor, it will not spawn another
		for (int i = 0; i < location.getItems().size(); i++) {
			if (location.getItems().get(i).getDisplayChar() == 'G') {
				hasItem = true;
			}
		}
		if (probability <= 15 && !hasItem){
			location.addItem(new GoldenSeeds());
		}
	}
}

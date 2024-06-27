package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.enemy.EastEnemyFactory;
import game.actor.enemy.EnemyFactory;
import game.actor.enemy.WestEnemyFactory;

/**
 * An abstract class for all the grounds in the game.
 * @author Yi Xuan Lim and Bel
 */
public abstract class Environment extends Ground{

    /**
     * Factory for creating enemies.
     */
    public EnemyFactory factory;

    /**
     * The side of the map. Either EAST or WEST.
     */
    public String mapType = null;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Environment(char displayChar) {
        super(displayChar);
    }

    /**
     * All the ground can experience the joy of time. This decides spawn the enemies on the West
     * or east.
     * @param location The location of the environment.
     */
    @Override
    public void tick(Location location) {
        // sets the side of map and factory
        if (mapType == null){
            mapType = (location.x() <= location.map().getXRange().max() / 2)? "WEST" : "EAST";
            factory = mapType.equals("WEST")? new WestEnemyFactory() : new EastEnemyFactory();
        }

    }
    
}

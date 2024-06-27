package game.actor.enemy;

/**
 * Interface for creating enemy objects. It provides a method to create
 * enemy objects based on the given EnemyType. This allows each side of the map has
 * different behaviour of spawning enemies.
 * @author Yi Xuan Lim
 */
public interface EnemyFactory {
    public Enemies createEnemy(EnemyType type);
}


package game.runes;

/**
 * The RunesSource interface allows actors to generate runes in different behaviour
 * @author Zhi Hui Tee
 */
public interface RunesSource {

  /**
   * Generate runes
   * @return the amount of runes generated
   */
  int generateRunes ();
}

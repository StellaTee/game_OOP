package game.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Yi Xuan Lim
 *
 */
public class ResetManager {
    /**
     * List storing Resettables
     */
    private List<Resettable> resettables;
    /**
     * Single instance for entire Reset Manager class
     */
    private static ResetManager instance;


    /**
     * Constructor for Reset Manager
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Gets instance for ResetManager. Creates an instance if don't have instance.
     * @return single instance for ResetManager
     */
    public static ResetManager getInstance() {
        if (instance == null)
            instance = new ResetManager();
        return instance;
    }

    /**
     * Runs reset() method for all Resettable stored
     */
    public void run() {
        for (int i = resettables.size()-1; i >= 0; i--) {
            resettables.get(i).reset();
        }
    }

    /**
     * Adds resettable object to list of resettables
     * @param resettable object to be added to resettable list
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }

    /**
     * Remove resettable object from list of resettables
     * @param resettable object to be removed from resettable list
     */
    public void removeResettable(Resettable resettable) {
        resettables.remove(resettable);
    }
}

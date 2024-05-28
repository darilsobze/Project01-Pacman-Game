package h01.template;

/**
 * An interface that enables an object to be updated every n-th tick.
 */
public interface TickBased {
    /**
     * Returns the number of ticks between two updates.
     *
     * @return the number of ticks between two updates
     */
    default int getUpdateDelay() {
        return 0;
    }
}

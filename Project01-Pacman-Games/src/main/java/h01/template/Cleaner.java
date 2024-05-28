package h01.template;

/**
 * An interface for a cleaner robot.
 */
public interface Cleaner extends TickBased {
    /**
     * Handles the input from the graphical user interface.
     *
     * @param direction       the direction to turn to. If {@code -1} the robot should not turn.
     * @param shouldPutCoins  if {@code true} the robot should put a coin on the current field.
     * @param shouldPickCoins if {@code true} the robot should pick a coin from the current field.
     */
    void handleKeyInput(int direction, boolean shouldPutCoins, boolean shouldPickCoins);
}

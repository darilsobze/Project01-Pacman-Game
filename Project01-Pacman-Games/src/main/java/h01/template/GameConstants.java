package h01.template;

import java.util.concurrent.ThreadLocalRandom;

import static org.tudalgo.algoutils.student.io.PropertyUtils.getIntProperty;
import static org.tudalgo.algoutils.student.io.PropertyUtils.getLongProperty;

public final class GameConstants {
    public static final int WORLD_WIDTH = getIntProperty("h01.properties", "WORLD_WIDTH");
    public static final int WORLD_HEIGHT = getIntProperty("h01.properties", "WORLD_HEIGHT");
    public static final int CONTAMINANT_ONE_START_COINS_MULTIPLIER = getIntProperty(
        "h01.properties",
        "CONTAMINANT_ONE_START_COINS_MULTIPLIER"
    );
    public static final int CONTAMINANT_TWO_START_COINS_MULTIPLIER = getIntProperty(
        "h01.properties",
        "CONTAMINANT_TWO_START_COINS_MULTIPLIER"
    );
    public static final int CONTAMINANT_ONE_MIN_PUT_COINS = getIntProperty(
        "h01.properties",
        "CONTAMINANT_ONE_MIN_PUT_COINS"
    );
    public static final int CONTAMINANT_ONE_MAX_PUT_COINS = getIntProperty(
        "h01.properties",
        "CONTAMINANT_ONE_MAX_PUT_COINS"
    );
    public static final int CLEANER_CAPACITY = getIntProperty("h01.properties", "CLEANER_CAPACITY");
    public static final int TICK_DELAY = getIntProperty("h01.properties", "TICK_DELAY");
    private static final long _RANDOM_SEED = getLongProperty("h01.properties", "RANDOM_SEED");
    public static final long RANDOM_SEED = _RANDOM_SEED == 0 ? ThreadLocalRandom.current().nextLong() : _RANDOM_SEED;

    /**
     * Returns a string representation of the game constants.
     *
     * @return a string representation of the game constants
     */
    public static String getGameConstantsString() {
        return String.format(
            """
                World width: %d
                World height: %d
                Contaminant 1 start coins multiplier: %d
                Contaminant 2 start coins multiplier: %d
                Contaminant 1 min put coins: %d
                Contaminant 1 max put coins: %d
                Cleaner capacity: %d
                Tick delay: %d
                Random seed: %d""",
            WORLD_WIDTH,
            WORLD_HEIGHT,
            CONTAMINANT_ONE_START_COINS_MULTIPLIER,
            CONTAMINANT_TWO_START_COINS_MULTIPLIER,
            CONTAMINANT_ONE_MIN_PUT_COINS,
            CONTAMINANT_ONE_MAX_PUT_COINS,
            CLEANER_CAPACITY,
            TICK_DELAY,
            RANDOM_SEED
        );
    }
}

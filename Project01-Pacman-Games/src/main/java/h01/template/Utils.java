package h01.template;

import fopbot.Coin;
import fopbot.Direction;
import fopbot.World;

import java.awt.Point;
import java.util.Random;

/**
 * A collection of utility methods.
 */
public final class Utils {
    /**
     * Private constructor to prevent instantiation.
     */
    private Utils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * The random number generator used by this exercise.
     */
    public static Random rnd = new Random(GameConstants.RANDOM_SEED);

    /**
     * Gets a random integer between min and max (both inclusive).
     *
     * @param min inclusive minimum (must be smaller than max)
     * @param max inclusive maximum (must be greater than min)
     * @return a random integer between min and max (both inclusive)
     */
    public static int getRandomInteger(final int min, final int max) {
        return min + rnd.nextInt(max - min + 1);
    }

    /**
     * Gets the amount of coins at the given position.
     *
     * @param x the x coordinate of field to check
     * @param y the y coordinate of field to check
     * @return the amount of coins at the given position
     */
    public static int getCoinAmount(final int x, final int y) {
        return World.getGlobalWorld().getAllFieldEntities().stream()
            .filter(e -> e.getX() == x && e.getY() == y)
            .filter(e -> e instanceof Coin)
            .map(e -> (Coin) e)
            .mapToInt(Coin::getCount)
            .sum();
    }

    /**
     * Returns the direction from p1 to p2.
     *
     * @param p1 The first point
     * @param p2 The second point
     * @return The direction from p1 to p2
     */
    public static Direction getRelativeDirection(final Point p1, final Point p2) {
        if (p1.x == p2.x) {
            if (p1.y < p2.y) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        } else {
            if (p1.x < p2.x) {
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        }
    }

    /**
     * Returns {@code true} if the given coordinate is a valid coordinate in the current world.
     *
     * @param x the x coordinate to check
     * @param y the y coordinate to check
     * @return {@code true} if the given coordinate is a valid coordinate in the current world
     */
    public static boolean isValidCoordinate(final int x, final int y) {
        return x >= 0 && x < World.getWidth() && y >= 0 && y < World.getHeight();
    }
}

package h01.template;

import fopbot.Direction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores information about the walls of a field.
 */
public class WallBlock {
    /**
     * The Directions of the walls that surround the field.
     */
    Set<Direction> walls = new HashSet<>();

    /**
     * Creates a new {@link WallBlock} with the given walls.
     *
     * @param walls the walls that surround the field
     */
    public WallBlock(final Direction... walls) {
        Collections.addAll(this.walls, walls);
    }

    /**
     * Adds a wall to the field.
     *
     * @param wall the wall to add
     */
    public void addWall(final Direction wall) {
        this.walls.add(wall);
    }

    /**
     * Removes a wall from the field.
     *
     * @param wall the wall to remove
     */
    public void removeWall(final Direction wall) {
        this.walls.remove(wall);
    }

    /**
     * Gets the walls that surround the field.
     *
     * @return the walls that surround the field
     */
    public Set<Direction> getWalls() {
        return this.walls;
    }
}

package h01.template;

import fopbot.Direction;
import fopbot.World;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Stream;

/**
 * A {@link MazeGenerator} generates a maze using a maze generation algorithm and ensures that every field is reachable.
 */
public final class MazeGenerator {
    private MazeGenerator() {
    }

    /**
     * Generates a maze using a maze generation algorithm and ensures that every field is reachable.
     * Walls are placed using World.placeHorizontalWall and World.placeVerticalWall.
     */
    public static void generateMaze() {
        // Create a 2D array to keep track of the walls
        final WallBlock[][] walls = new WallBlock[World.getWidth()][World.getHeight()];
        for (var x = 0; x < World.getWidth(); x++) {
            for (var y = 0; y < World.getHeight(); y++) {
                walls[x][y] = new WallBlock(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
            }
        }

        // Create a 2D array to keep track of visited fields
        final var visited = new boolean[World.getWidth()][World.getHeight()];

        // Create a stack to track the visited cells
        final var stack = new ArrayDeque<Point>();

        // Start the maze generation from the top-left corner (0, 0)
        var current = new Point(0, 0);
        visited[current.x][current.y] = true;
        stack.push(current);

        // Continue until all cells have been visited
        while (!stack.isEmpty()) {
            // Get the unvisited neighboring cells of the current cell
            final var neighbours = getNeighbours(current)
                .stream()
                .filter(p -> !visited[p.x][p.y])
                .toList();
            if (!neighbours.isEmpty()) {
                // Choose a random neighboring cell
                final var next = neighbours.get(Utils.rnd.nextInt(neighbours.size()));
                visited[next.x][next.y] = true;

                walls[current.x][current.y].removeWall(Utils.getRelativeDirection(current, next));
                walls[next.x][next.y].removeWall(Utils.getRelativeDirection(next, current));

                stack.push(next);
                current = next;
            } else {
                // Backtrack to the previous cell
                current = stack.pop();
            }
        }

        // Place the walls
        for (var x = 0; x < World.getWidth(); x++) {
            for (var y = 0; y < World.getHeight(); y++) {
                final var wallBlock = walls[x][y];
                if (wallBlock != null) {
                    for (final var wall : wallBlock.getWalls()) {
                        switch (wall) {
                            case UP -> World.placeHorizontalWall(x, y);
                            case DOWN, LEFT -> { } // Not needed, will lead to an exception
                            case RIGHT -> World.placeVerticalWall(x, y);
                            default -> throw new IllegalStateException("Unexpected value: " + wall);
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns a list of neighboring cells for the given cell.
     *
     * @param p The cell coordinates
     * @return List of neighboring cells
     */
    private static List<Point> getNeighbours(final Point p) {
        //noinspection SuspiciousNameCombination
        return Stream.iterate(new Point(1, 0), p2 -> new Point(-p2.y, p2.x))
            .limit(4)
            .map(p2 -> new Point(p.x + p2.x, p.y + p2.y))
            .filter(p2 -> Utils.isValidCoordinate(p2.x, p2.y))
            .toList();
    }
}

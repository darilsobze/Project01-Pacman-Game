package h01.template;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;
import h01.CleaningRobot;
import h01.Contaminant1;
import h01.Contaminant2;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import static h01.template.GameConstants.TICK_DELAY;

/**
 * A {@link GameControllerBase} controls the game loop and the {@link Robot}s and checks the win condition.
 */
public abstract class GameControllerBase {
    /**
     * The {@link Timer} that controls the game loop.
     */
    private final Timer gameLoopTimer = new Timer();

    /**
     * The {@link GameInputHandler} that handles the input of the user.
     */
    private final GameInputHandler inputHandler = new GameInputHandler();

    /**
     * The {@link Robot}s that are controlled by the {@link GameControllerBase}.
     */
    protected final Set<Robot> robots = new HashSet<>();

    /**
     * The {@link CleaningRobot} {@link Robot}.
     */
    protected Robot cleaningRobot;

    /**
     * The {@link Contaminant1} {@link Robot}.
     */
    protected Robot contaminant1;

    /**
     * The {@link Contaminant2} {@link Robot}.
     */
    protected Robot contaminant2;

    /**
     * A {@link Map} that maps a {@link Robot} to the amount of ticks that have passed since the last tick action.
     */
    private final Map<Robot, Integer> robotTicks = new HashMap<>();

    /**
     * The {@link TimerTask} that is executed every tick.
     */
    private final TimerTask gameLoopTask = new TimerTask() {
        @Override
        public void run() {
            for (final Robot robot : GameControllerBase.this.robots) {
                if (!(robot instanceof final TickBased tb)) {
                    continue;
                }
                if (!GameControllerBase.this.robotTicks.containsKey(robot)) {
                    GameControllerBase.this.robotTicks.put(robot, 0);
                }
                if (GameControllerBase.this.robotTicks.get(robot) < tb.getUpdateDelay()) {
                    GameControllerBase.this.robotTicks.put(robot, GameControllerBase.this.robotTicks.get(robot) + 1);
                    continue;
                }
                GameControllerBase.this.robotTicks.put(robot, 0);
                // do tick action
                if (robot instanceof final Cleaner r) {
                    r.handleKeyInput(
                        GameControllerBase.this.inputHandler.getDirection(),
                        GameControllerBase.this.inputHandler.getShouldPickCoins(),
                        GameControllerBase.this.inputHandler.getShouldPutCoins()
                    );
                } else if (robot instanceof final Contaminant r) {
                    r.doMove();
                }
            }
            // check win condition
            checkWinCondition();
        }
    };

    /**
     * Gets the {@link CleaningRobot} {@link Robot}.
     *
     * @return the {@link CleaningRobot} {@link Robot}
     */
    public Robot getCleaningRobot() {
        return cleaningRobot;
    }

    /**
     * Gets the {@link Contaminant1} {@link Robot}.
     *
     * @return the {@link Contaminant1} {@link Robot}
     */
    public Robot getContaminant1() {
        return contaminant1;
    }

    /**
     * Gets the {@link Contaminant2} {@link Robot}.
     *
     * @return the {@link Contaminant2} {@link Robot}
     */
    public Robot getContaminant2() {
        return contaminant2;
    }

    /**
     * Starts the game loop.
     */
    public void startGame() {
        System.out.println("Starting game...");
        System.out.println(GameConstants.getGameConstantsString());
        this.gameLoopTimer.scheduleAtFixedRate(this.gameLoopTask, 0, TICK_DELAY);
    }

    /**
     * Stops the game loop.
     */
    public void stopGame() {
        this.gameLoopTimer.cancel();
    }

    /**
     * Sets up the game.
     */
    protected void setup() {
        setupWorld();
        setupRobots();
        this.inputHandler.install();
    }

    /**
     * Initializes the {@link World} and adds the {@link Robot}s to it.
     */
    public void setupWorld() {
        World.setSize(GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);
        World.setDelay(0);
        World.setVisible(true);
        MazeGenerator.generateMaze();
        World.getGlobalWorld().setFieldColor(0, World.getHeight() - 1, Color.YELLOW);
    }

    /**
     * Adds the {@link Robot}s to the {@link World}.
     */
    public void setupRobots() {
        this.robots.add(cleaningRobot = new CleaningRobot(
                0,
                0,
                Direction.UP,
                0
            )
        );
        this.robots.add(contaminant1 = new Contaminant1(
                World.getWidth() - 1,
                0,
                Direction.UP,
                GameConstants.CONTAMINANT_ONE_START_COINS_MULTIPLIER * World.getWidth() * World.getHeight()
            )
        );
        this.robots.add(contaminant2 = new Contaminant2(
                World.getWidth() - 1,
                World.getHeight() - 1,
                Direction.UP,
                GameConstants.CONTAMINANT_TWO_START_COINS_MULTIPLIER * World.getWidth() * World.getHeight()
            )
        );
    }

    /**
     * Checks the win condition.
     */
    public abstract void checkWinCondition();
}

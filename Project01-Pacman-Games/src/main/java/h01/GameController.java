package h01;

import fopbot.Robot;
import fopbot.World;
import h01.template.GameControllerBase;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

/**
 * A {@link GameController} controls the game loop and the {@link Robot}s and checks the win condition.
 */
public class GameController extends GameControllerBase {

    /**
     * Creates a new {@link GameControllerBase}.
     */
    public GameController() {
        setup();
    }

    @Override
    public void checkWinCondition() {
        // If all Offenders are turned off, the game is won
        if (getContaminant1().isTurnedOff() && getContaminant2().isTurnedOff()) {
            System.out.println("Cleaning robot won!");
            stopGame();
            return;
        }
        // if more than 50% of all fields are dirty, the game is lost
        int dirtyFields = 0;
        for (int x = 0; x < World.getWidth(); x++) {
            for (int y = 0; y < World.getHeight(); y++) {
                if (Utils.getCoinAmount(x, y) > 0) {
                    dirtyFields++;
                }
            }
        }
        // If the dumping area contains at least 200 coins, the game is won
        if (Utils.getCoinAmount(0, World.getHeight() - 1) >= 200) {
            getContaminant1().turnOff();
            getContaminant2().turnOff();
            System.out.println("Cleaning robot won!");
            stopGame();
            return;
        }

        if (dirtyFields >= (double) World.getWidth() * World.getHeight() / 2) {
            getCleaningRobot().turnOff();
            System.out.println("Contaminants won!");
            stopGame();
        }
    }
}

package h01;

import fopbot.Direction;
import fopbot.Robot;
import h01.template.Cleaner;
import h01.template.GameConstants;
import h01.template.TickBased;
import org.tudalgo.algoutils.student.Student;

/**
 * A robot that can clean the floor.
 */
public class CleaningRobot extends Robot implements Cleaner, TickBased {

    /**
     * Creates a new {@link CleaningRobot}.
     *
     * @param x             the initial x coordinate of the robot
     * @param y             the initial y coordinate of the robot
     * @param direction     the initial direction of the robot
     * @param numberOfCoins the initial number of coins of the robot
     */
    public CleaningRobot(final int x, final int y, final Direction direction, final int numberOfCoins) {
        super(x, y, direction, numberOfCoins);
    }

    @Override
    public void handleKeyInput(final int direction, final boolean shouldPutCoins, final boolean shouldPickCoins) {
        if (shouldPutCoins)
            if (hasAnyCoins())
                putCoin();
        if (shouldPickCoins && isOnACoin())
            if (getNumberOfCoins() < GameConstants.CLEANER_CAPACITY)
                pickCoin();
        if (direction==0){
            while(!isFacingUp()) {
                turnLeft();
            }
            if (isFrontClear())
                move();

        }
        if (direction==1){
            while(!isFacingRight()) {
                turnLeft();
            }
            if (isFrontClear())
                move();
        }
        if (direction==2){
            while(!isFacingDown()) {
                turnLeft();
            }
            if (isFrontClear())
                move();

        }
        if (direction==3){
            while(!isFacingLeft()) {
                turnLeft();
            }
            if (isFrontClear())
                move();

        }

    }
}

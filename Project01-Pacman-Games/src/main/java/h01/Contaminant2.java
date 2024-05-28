package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Contaminant;
import h01.template.TickBased;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

/**
 * A {@link Contaminant}-{@link Robot} that moves in a predefined way and contaminates the floor.
 */
public class Contaminant2 extends Robot implements Contaminant, TickBased {

    /**
     * Creates a new {@link Contaminant2}.
     *
     * @param x             the initial x coordinate of the robot
     * @param y             the initial y coordinate of the robot
     * @param direction     the initial direction of the robot
     * @param numberOfCoins the initial number of coins of the robot
     */
    public Contaminant2(final int x, final int y, final Direction direction, final int numberOfCoins) {
        super(x, y, direction, numberOfCoins, RobotFamily.SQUARE_AQUA);
    }

    @Override
    public int getUpdateDelay() {
        return 8;
    }

    @Override
    public void doMove() {
        if (!hasAnyCoins())
            turnOff();
        if (!hasAnyCoins() || isTurnedOff())
            return;
        while (Utils.getCoinAmount(getX(), getY())<2){
            putCoin();
        }
        Direction[] frontClear = new Direction[4];
        int indexInFrontClear = 0;
        for (int i=0; i<4; i++){
            turnLeft();
            if (isFrontClear()){
                frontClear[indexInFrontClear]= getDirection();
                indexInFrontClear++;
            }
        }

        turnLeft();
        if (isFrontClear()) {
            move();
        }
        else {
            indexInFrontClear = 0;
            while (frontClear[indexInFrontClear] != getDirection() && !isFrontClear()) {
                turnLeft();
                turnLeft();
                turnLeft();
                indexInFrontClear++;
            }
            if (isFrontClear())
                move();
        }

    }
}

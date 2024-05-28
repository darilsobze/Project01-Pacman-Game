package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Contaminant;
import h01.template.GameConstants;
import h01.template.TickBased;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

/**
 * A {@link Contaminant}-{@link Robot} that moves randomly and contaminates the floor.
 */
public class Contaminant1 extends Robot implements Contaminant, TickBased {

    /**
     * Creates a new {@link Contaminant1}.
     *
     * @param x             the initial x coordinate of the robot
     * @param y             the initial y coordinate of the robot
     * @param direction     the initial direction of the robot
     * @param numberOfCoins the initial number of coins of the robot
     */
    public Contaminant1(final int x, final int y, final Direction direction, final int numberOfCoins) {
        super(x, y, direction, numberOfCoins, RobotFamily.SQUARE_ORANGE);
    }

    @Override
    public int getUpdateDelay() {
        return 10;
    }

    @Override
    public void doMove() {
        if (!hasAnyCoins()){
            turnOff();
        }
        if (!hasAnyCoins() || isTurnedOff())
            return;
        int coinAmount = Utils.getRandomInteger(GameConstants.CONTAMINANT_ONE_MIN_PUT_COINS,GameConstants.CONTAMINANT_ONE_MAX_PUT_COINS);
        while (hasAnyCoins() && Utils.getCoinAmount(getX(),getY())<20 && coinAmount>0) {
            putCoin();
            coinAmount--;
        }


        Direction[] frontClear = new Direction[4];
        int indexInFrontClear =0;
        for (int i=0;i<4;i++){
            turnLeft();
            if (isFrontClear()){
                frontClear[indexInFrontClear]= getDirection();
                indexInFrontClear++;
            }

        }
        Direction freeDirection = frontClear[Utils.getRandomInteger(0,indexInFrontClear-1)];
        while(freeDirection != getDirection()){
            turnLeft();
        }
        if (isFrontClear()){
            move();
        }

    }
}

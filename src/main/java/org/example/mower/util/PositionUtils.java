package org.example.mower.util;

import lombok.experimental.UtilityClass;
import org.example.mower.object.*;


import java.util.List;

/**
 * Position utilities class.
 */
@UtilityClass
public class PositionUtils {

    /**
     * Caculate the position after one action (L R F) given lawn size and neighbour mowers
     *
     * @param position Current position
     * @param lawn     The lawn
     * @param mowers   Neighbour mowers
     * @param action   action (L R F) to be done
     * @return The position after move
     */
    public Position move(Position position, Lawn lawn, List<Mower> mowers, Action action) {
        switch (action) {
            case L:
                return turnL(position);
            case R:
                return turnR(position);
            case F:
                return moveForward(position, lawn, mowers);
        }
        return position;
    }


    /**
     * Move forward the mower's position
     */
    public Position moveForward(Position position, Lawn lawn, List<Mower> mowers) {

        int x = position.getX();
        int y = position.getY();
        int doneSteps = position.getDoneSteps();
        int maxX = lawn.getSizeX();
        int maxY = lawn.getSizeY();

        switch (position.getO()) {
            case E:
                if (x < maxX & !contains(mowers, x + 1, y)) x++;
                break;
            case N:
                if (y < maxY & !contains(mowers, x, y + 1)) y++;
                break;
            case S:
                if (y > 0 & !contains(mowers, x, y - 1)) y--;
                break;
            case W:
                if (x > 0 & !contains(mowers, x - 1, y)) x--;
                break;
        }
        return position.toBuilder().x(x).y(y).doneSteps(doneSteps + 1).build();

    }

    /**
     * Turn right the mower's position
     */
    public Position turnR(Position position) {

        int doneSteps = position.getDoneSteps();
        Orientation o = position.getO();

        switch (position.getO()) {
            case E:
                o = Orientation.S;
                break;
            case N:
                o = Orientation.E;
                break;
            case S:
                o = Orientation.W;
                break;
            case W:
                o = Orientation.N;
                break;
        }
        return position.toBuilder().o(o).doneSteps(doneSteps + 1).build();
    }

    /**
     * Turn left the Mower's position
     */
    public Position turnL(Position position) {

        int doneSteps = position.getDoneSteps();
        Orientation o = position.getO();

        switch (position.getO()) {
            case E:
                o = Orientation.N;
                break;
            case N:
                o = Orientation.W;
                break;
            case S:
                o = Orientation.E;
                break;
            case W:
                o = Orientation.S;
                break;
        }
        return position.toBuilder().o(o).doneSteps(doneSteps + 1).build();
    }

    /**
     * Check is the position(x,y) is occupied
     * To be improved for higher performance in case of big mowers list
     *
     * @param mowers List of mowers in the current lawn
     * @param x      Position x to check
     * @param y      Position y to check
     * @return Return true if already a mower, false otherwise
     */
    private boolean contains(List<Mower> mowers, int x, int y) {
        for (Mower mower : mowers) {
            if (mower.getPosition().getX() == x & mower.getPosition().getY() == y) return true;
        }
        return false;
    }
}

package org.example.mower.object;

import lombok.Getter;
import org.example.mower.util.PositionUtils;

import java.util.List;

/**
 * Represent a mower
 */
@Getter
public class Mower {

    /**
     * Current position of the mower
     */
    Position position;

    /**
     * List of actions to do with the mowers
     */
    List<Action> actions;

    /**
     * Count of actions
     */
    int allSteps;


    /**
     * Index of the mower, mowers with lower index have priority
     */
    int index;


    /**
     * Create a mower
     *
     * @param position  Current position of the mower
     * @param actions   Actions(steps) to be done with the mower
     * @param index     Mower index
     */
    public Mower(Position position, List<Action> actions, int index) {
        this.position = position;
        this.actions = actions;
        allSteps = actions.size();
        this.index=index;
    }

    /**
     * Move the mover with given steps, the lawn size and picture of neighbour mowers
     *
     * @param lawn      The lawn in which the mover moves
     * @param mowers    List of existing mowers
     * @param step      Step to go for the mower
     */
    public void move(Lawn lawn, List<Mower> mowers, int step){
        //Get current position
        Position position=this.position;
        int currentStep=this.position.getDoneSteps();

        //For each step, calculate the next position
        for (int i = currentStep+1; i <= currentStep+step; i++) {
            position= PositionUtils.move(position, lawn, mowers, this.actions.get(i));
        }

        //Update the mower's position after moves
        this.position=position;
    }

}

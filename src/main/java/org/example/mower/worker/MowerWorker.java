package org.example.mower.worker;

import org.example.mower.object.Lawn;
import org.example.mower.object.Mower;
import org.example.mower.util.MowerUtils;

import java.util.List;

/**
 * The work implementation to move the mower
 */
public class MowerWorker implements Worker {

    /**
     * The mower to move
     */
    private final Mower mower;


    /**
     * Index of the worker, equals to index of the mower
     */
    private final int index;

    /**
     * The lawn in which the worker moves the mower
     */
    private final Lawn lawn;

    /**
     * List of all mowers of given move step in the lawn
     */
    private final List<Mower> mowers;

    public MowerWorker(int index, Lawn lawn, List<Mower> mowers) {
        this.index = index;
        this.mowers = mowers;
        this.mower = mowers.get(index);
        this.lawn = lawn;
    }

    /**
     * Start the worker
     */
    public void start() {

        while (this.mower.getPosition().getDoneSteps() < this.mower.getAllSteps() - 1) { //loop until all steps done
            //Check how many steps to go
            int step = MowerUtils.stepToGo(this.mower, this.mowers);
            //Do the steps
            this.mower.move(lawn, mowers, step);
        }

    }

}

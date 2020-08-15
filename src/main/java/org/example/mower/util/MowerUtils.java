package org.example.mower.util;

import lombok.experimental.UtilityClass;
import org.example.mower.object.*;

import java.util.List;

/**
 * Mower utilities class.
 */
@UtilityClass
public class MowerUtils {

    /**
     * Calculate how many steps we can move forward for the mower given neighbour mowers
     * Core of algorithm, can have different implementation for various performance
     *
     * @return Return steps to go with current Lawn information
     */
    public int stepToGo(Mower mower, List<Mower> mowers) {

        int j = mower.getPosition().getDoneSteps();//currentStep
        int J = mower.getAllSteps() - 1;//currentAllSteps
        int step = J - j;

        for (int r = 0; r < mower.getIndex(); r++) {
            Mower neighbor = mowers.get(r);
            int i = neighbor.getPosition().getDoneSteps();//neighborCurrentStep
            int I = neighbor.getAllSteps() - 1;//neighborAllSteps
            int max;

            if (i < I) {

                int D = (Math.abs(neighbor.getPosition().getX() - mower.getPosition().getX()) + Math.abs(neighbor.getPosition().getY() - mower.getPosition().getY())) - 1;//distance

                if(D==0){
                    if(i>j) max=1;
                    else max=0;
                }
                else {
                    if (2 * I >= D + i + j) max = (D + i - j) / 2;
                    else max = D + i - I;
                }
                step = Math.min(step, max);
            }

        }
        for (int r = mowers.size() - 1; r > mower.getIndex(); r--) {
            Mower neighbor = mowers.get(r);
            int k = neighbor.getPosition().getDoneSteps();//neighborCurrentStep
            int K = neighbor.getAllSteps() - 1;//neighborAllSteps
            int max;

            if (k < K) {
                int D = (Math.abs(neighbor.getPosition().getX() - mower.getPosition().getX()) + Math.abs(neighbor.getPosition().getY() - mower.getPosition().getY())) - 1;//distance
                if (D == 0|D==1){
                    if(j==k) max = 1;
                    else   max=0;
                }
                else {
                    if (2 * K >= D + k + j) max = (D + k - j +1 ) / 2;
                    else max = D + k - K;
                }
                step = Math.min(step, max);
            }


        }

        return step;
    }


    public void printResult(List<Mower> mowers) {
        System.out.printf("\nThe result postion of mowers are:\n");
        for (int i = 0; i < mowers.size(); i++) {
            Position p = mowers.get(i).getPosition();
            System.out.printf("%d %d %s\n", p.getX(), p.getY(), p.getO());
        }
    }
}

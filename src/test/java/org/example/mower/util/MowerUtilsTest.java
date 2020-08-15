package org.example.mower.util;

import org.example.mower.object.Action;
import org.example.mower.object.Mower;
import org.example.mower.object.Orientation;
import org.example.mower.object.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MowerUtilsTest {

    @Test
    void stepToGo() {
        List<Action> actions=new ArrayList();


        // No action, step=0
        Mower mower=new Mower(new Position(1,1, Orientation.E,-1),actions,0);
        List<Mower> mowers=new ArrayList();
        assertEquals(MowerUtils.stepToGo(mower, mowers),0);

        actions.add(Action.F);
        actions.add(Action.F);
        actions.add(Action.F);
        actions.add(Action.F);
        actions.add(Action.L);
        actions.add(Action.F);

        // 6 actions, step=6
        mower=new Mower(new Position(1,1, Orientation.E,-1),actions,0);
        assertEquals(MowerUtils.stepToGo(mower, mowers),6);

        // D=2, step=1
        mower=new Mower(new Position(1,1, Orientation.E,-1),actions,0);
        mowers.add(mower);
        mowers.add(new Mower(new Position(4,1, Orientation.E,-1),actions,1));
        assertEquals(MowerUtils.stepToGo(mower, mowers),1);


        //X Y: Test distance=0, i=j, step=0
        mower=new Mower(new Position(1,1, Orientation.E,0),actions,1);
        mowers=new ArrayList();
        mowers.add(new Mower(new Position(1,2, Orientation.E,0),actions,0));
        mowers.add(mower);
        assertEquals(MowerUtils.stepToGo(mower, mowers),0);

        //X Y: Test distance=0, i>j, step=0
        mower=new Mower(new Position(1,1, Orientation.E,1),actions,1);
        mowers=new ArrayList();
        mowers.add(new Mower(new Position(1,2, Orientation.E,0),actions,0));
        mowers.add(mower);
        assertEquals(MowerUtils.stepToGo(mower, mowers),0);

        //X Y: Test distance=3, i=j, step=1
        mower=new Mower(new Position(1,1, Orientation.E,-1),actions,1);
        mowers=new ArrayList();
        mowers.add(new Mower(new Position(2,4, Orientation.E,-1),actions,0));
        mowers.add(mower);
        assertEquals(MowerUtils.stepToGo(mower, mowers),1);

        //X Y: Test distance=4, i=j, step=2
        mower=new Mower(new Position(1,1, Orientation.E,-1),actions,1);
        mowers=new ArrayList();
        mowers.add(new Mower(new Position(2,5, Orientation.E,-1),actions,0));
        mowers.add(mower);
        assertEquals(MowerUtils.stepToGo(mower, mowers),2);

        //X Y: Test distance=0, i>j, step=1

        mower=new Mower(new Position(2,1, Orientation.E,1),actions,1);
        mowers=new ArrayList();
        mowers.add(new Mower(new Position(3,1, Orientation.E,2),actions,0));
        mowers.add(mower);
        assertEquals(MowerUtils.stepToGo(mower, mowers),1);


        //Y Z: Test distance=0, j=k, step=1
        mower=new Mower(new Position(1,1, Orientation.E,0),actions,0);
        mowers=new ArrayList();
        mowers.add(mower);
        mowers.add(new Mower(new Position(1,2, Orientation.E,0),actions,1));
        assertEquals(MowerUtils.stepToGo(mower, mowers),1);

        //Y Z: Test distance=0, j>k, step=0
        mower=new Mower(new Position(1,1, Orientation.E,1),actions,0);
        mowers=new ArrayList();
        mowers.add(mower);
        mowers.add(new Mower(new Position(1,2, Orientation.E,0),actions,1));
        assertEquals(MowerUtils.stepToGo(mower, mowers),0);

        //Y Z: Test distance=0, j<k, step=0
        mower=new Mower(new Position(1,1, Orientation.E,0),actions,0);
        mowers=new ArrayList();
        mowers.add(mower);
        mowers.add(new Mower(new Position(1,2, Orientation.E,1),actions,1));
        assertEquals(MowerUtils.stepToGo(mower, mowers),0);

        //Y Z: Test distance=3, j=k, step=2
        mower=new Mower(new Position(1,1, Orientation.E,-1),actions,0);
        mowers=new ArrayList();
        mowers.add(mower);
        mowers.add(new Mower(new Position(2,4, Orientation.E,-1),actions,1));
        assertEquals(MowerUtils.stepToGo(mower, mowers),2);

        //Y Z: Test distance=4, j=k, step=2
        mower=new Mower(new Position(1,1, Orientation.E,-1),actions,0);
        mowers=new ArrayList();
        mowers.add(mower);
        mowers.add(new Mower(new Position(2,5, Orientation.E,-1),actions,1));
        assertEquals(MowerUtils.stepToGo(mower, mowers),2);

    }
}
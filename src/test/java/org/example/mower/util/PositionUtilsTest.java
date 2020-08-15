package org.example.mower.util;

import org.example.mower.object.Orientation;
import org.example.mower.object.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionUtilsTest {

    @Test
    void testTurnL() {
        Position position=new Position(1,1, Orientation.E, 0);
        position=PositionUtils.turnL(position);
        assertEquals(position.getO(),Orientation.N);
        position=position.toBuilder().o(Orientation.N).build();
        position=PositionUtils.turnL(position);
        assertEquals(position.getO(),Orientation.W);
        position=position.toBuilder().o(Orientation.S).build();
        position=PositionUtils.turnL(position);
        assertEquals(position.getO(),Orientation.E);
        position=position.toBuilder().o(Orientation.W).build();
        position=PositionUtils.turnL(position);
        assertEquals(position.getO(),Orientation.S);
    }

    @Test
    void testTurnR() {
        Position position=new Position(1,1, Orientation.E, 0);
        position=PositionUtils.turnR(position);
        assertEquals(position.getO(),Orientation.S);
        position=position.toBuilder().o(Orientation.N).build();
        position=PositionUtils.turnR(position);
        assertEquals(position.getO(),Orientation.E);
        position=position.toBuilder().o(Orientation.S).build();
        position=PositionUtils.turnR(position);
        assertEquals(position.getO(),Orientation.W);
        position=position.toBuilder().o(Orientation.W).build();
        position=PositionUtils.turnR(position);
        assertEquals(position.getO(),Orientation.N);
    }

}
package org.example.mower.object;

import lombok.*;

/**
 * Class representing Mower's position in the lawn with its orientation
 */
@Getter
@AllArgsConstructor
@Builder(toBuilder = true, access = AccessLevel.PUBLIC)
public class Position {

    /**
     * Position x
     */
    private int x;

    /**
     * Position y
     */
    private int y;

    /**
     * Orientation o
     */
    private Orientation o;

    /**
     * Count of moved steps
     */
    private int doneSteps = -1;

}

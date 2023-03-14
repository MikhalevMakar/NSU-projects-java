package org.ru.nsu.mikhalev.task3.shape;

import org.ru.nsu.mikhalev.task3.TetrisShape;

@AnnotationShape
public class Ricky extends TetrisShape {
    public Ricky() {
        super(new boolean[][] {
                {true, true},
                {true, false},
                {true, false},
        });
    }
}
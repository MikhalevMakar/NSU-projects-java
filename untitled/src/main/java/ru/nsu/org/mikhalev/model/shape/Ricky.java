package org.ru.nsu.mikhalev.task3.model.shape;

import org.ru.nsu.mikhalev.task3.model.TetrisShape;

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
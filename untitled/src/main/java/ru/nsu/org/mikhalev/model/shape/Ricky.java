package ru.nsu.org.mikhalev.model.shape;

import ru.nsu.org.mikhalev.model.TetrisShape;
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
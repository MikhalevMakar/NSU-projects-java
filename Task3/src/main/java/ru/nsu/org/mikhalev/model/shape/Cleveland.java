package ru.nsu.org.mikhalev.model.shape;

import ru.nsu.org.mikhalev.model.TetrisShape;
@AnnotationShape
public class Cleveland extends TetrisShape {
    public Cleveland() {
        super(new boolean[][] {
                {false, true},
                {true, true},
                {true, false},
        });
    }
}

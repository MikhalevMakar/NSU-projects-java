package org.ru.nsu.mikhalev.task3.model.shape;

import org.ru.nsu.mikhalev.task3.model.TetrisShape;

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

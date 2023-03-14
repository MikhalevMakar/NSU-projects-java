package org.ru.nsu.mikhalev.task3.shape;


import org.ru.nsu.mikhalev.task3.TetrisShape;

@AnnotationShape
public class TeeWee extends TetrisShape {
    public TeeWee() {
        super (new boolean[][]{
                {true, false},
                {true, true},
                {true, false}
        });
    }
}

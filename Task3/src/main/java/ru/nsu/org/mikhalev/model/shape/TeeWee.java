package ru.nsu.org.mikhalev.model.shape;

import ru.nsu.org.mikhalev.model.TetrisShape;
@AnnotationShape
public class TeeWee extends TetrisShape {
    public TeeWee() {
        super(new boolean[][] {
                        {true, false},
                        {true, true},
                        {true, false}
                      });
    }
}

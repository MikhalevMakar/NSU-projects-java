package ru.nsu.org.mikhalev.model.shape;

import ru.nsu.org.mikhalev.model.TetrisShape;
@AnnotationShape
public class SmashBoy extends TetrisShape {
    public SmashBoy() {
        super(new boolean[][]{
                {true,true},
                {true,true}
        });
    }
}

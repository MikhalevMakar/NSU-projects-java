package org.ru.nsu.mikhalev.task3.shape;

import org.ru.nsu.mikhalev.task3.TetrisShape;

@AnnotationShape
public class SmashBoy extends TetrisShape {
    public SmashBoy() {
        super(new boolean[][]{
                {true,true},
                {true,true}
        });
    }
}

package org.ru.nsu.mikhalev.task3.shape;

import org.ru.nsu.mikhalev.task3.TetrisShape;

@AnnotationShape
public class Hero extends TetrisShape {
    public Hero() {
        super(new boolean[][]{
                {true, false},
                {true, false},
                {true, false},
        });
    }
}

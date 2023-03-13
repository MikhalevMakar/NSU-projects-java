package org.ru.nsu.mikhalev.task3.shape;

import org.ru.nsu.mikhalev.task3.TetrisShape;

@AnnotationShape
public class TeeWee implements Shape {
    @Override
    public TetrisShape generateShape(int indexColor) {
        return new TetrisShape(color[random.nextInt (color.length)], new boolean[][]{
                                                                  {true,false},
                                                                  {true,true},
                                                                  {true,false}
                                                                });
    }
}

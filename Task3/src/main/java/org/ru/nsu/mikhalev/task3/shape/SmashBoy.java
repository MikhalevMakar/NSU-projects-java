package org.ru.nsu.mikhalev.task3.shape;

import org.ru.nsu.mikhalev.task3.TetrisShape;
@AnnotationShape
public class SmashBoy  implements Shape{
    @Override
    public TetrisShape generateShape(int indexColor) {
        return new TetrisShape(color[indexColor], new boolean[][]{
                                                                {true,true},
                                                                {true,true}
                                                              });
    }

}

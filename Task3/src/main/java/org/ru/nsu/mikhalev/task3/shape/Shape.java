package org.ru.nsu.mikhalev.task3.shape;
import  org.ru.nsu.mikhalev.task3.TetrisShape;

import java.awt.*;

public interface Shape {
      Color[] color = new Color[]{
                               Color.red,
                               Color.blue,
                               Color.black,
                               Color.yellow,
                               Color.pink,
                               Color.cyan
                              };

     default Color getColor(int index) { return color[index];}
     public TetrisShape generateShape(int indexColor);
}

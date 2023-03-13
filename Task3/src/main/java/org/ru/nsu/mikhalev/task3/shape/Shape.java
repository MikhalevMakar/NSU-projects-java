package org.ru.nsu.mikhalev.task3.shape;
import  org.ru.nsu.mikhalev.task3.TetrisShape;

import java.awt.*;
import java.util.Random;

public interface Shape {
      Color[] color = new Color[]{
                               Color.red,
                               Color.blue,
                               Color.black,
                               Color.yellow,
                               Color.pink,
                               Color.cyan
                              };

      static Random random = new Random();
     public TetrisShape generateShape(int indexColor);
}

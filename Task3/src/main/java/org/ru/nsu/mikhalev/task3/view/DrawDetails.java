package org.ru.nsu.mikhalev.task3.view;

import org.ru.nsu.mikhalev.task3.model.TetrisShape;
import java.awt.*;

public class DrawDetails {
    static public  void drawDetails(Graphics graphics, TetrisShape shape) {
        int height = shape.getHeight();
        int weight = shape.getWidth();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < weight; ++x) {
                if (shape.IsShape(x, y))
                    DrawGivenFigure.draw(graphics,
                                    shape.getColor(),
                                 shape.getX() + x,
                                 shape.getY() + y);
            }
        }
    }
}

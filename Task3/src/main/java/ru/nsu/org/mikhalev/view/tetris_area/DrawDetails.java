package ru.nsu.org.mikhalev.view.tetris_area;

import ru.nsu.org.mikhalev.model.TetrisShape;

import java.awt.*;

public class DrawDetails {
    public static void drawDetails(Graphics graphics, TetrisShape shape) {
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

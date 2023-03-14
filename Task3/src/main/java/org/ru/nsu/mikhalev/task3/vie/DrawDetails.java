package org.ru.nsu.mikhalev.task3.vie;

import org.ru.nsu.mikhalev.task3.Context;
import org.ru.nsu.mikhalev.task3.TetrisShape;
import org.ru.nsu.mikhalev.task3.vie.DrawRectangle;

import java.awt.*;

public class DrawDetails {
     static private void drawGivenFigure(Graphics graphics, Color color, int x, int y, TetrisShape shape) {
        graphics.setColor(color);
        graphics.fillRect(x * Context.getOFFSET_SHARED() + Context.getOFFSET_TABLE_X(),
                       y * Context.getOFFSET_SHARED() + Context.getOFFSET_TABLE_Y(),
                          Context.getOFFSET_SHARED(),
                          Context.getOFFSET_SHARED());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x * Context.getOFFSET_SHARED() + Context.getOFFSET_TABLE_X(),
                        y * Context.getOFFSET_SHARED() + Context.getOFFSET_TABLE_Y(),
                           Context.getOFFSET_SHARED(),
                           Context.getOFFSET_SHARED());
    }
    static public  void drawDetails(Graphics graphics, TetrisShape shape) {
        int height = shape.getHeight();
        int weight = shape.getWidth();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < weight; ++x) {
                if (shape.IsShape(x, y))
                    drawGivenFigure(graphics, shape.getColor(), shape.getX() + x, shape.getY() + y, shape);
            }
        }
    }
    static public  void drawBackGround(Graphics graphics, TetrisShape shape, Color[][] placedShape) {
        Color color;
        for(int y = 0; y < Context.getHEIGHT(); ++y) {
            for(int x = 0; x < Context.getWIDTH(); ++x) {
                color = placedShape[y][x];
                if(color != null)
                    drawGivenFigure(graphics, color, x, y, shape);
            }
        }
    }
    static public void drawGridSquare(Graphics graphics) {
        graphics.setColor(DrawRectangle.color(70,70,65));
        graphics.fillRect(Context.getOFFSET_TABLE_X(),
                          Context.getOFFSET_TABLE_Y(),
                          Context.getREAL_WIDTH(),
                          Context.getREAL_HEIGHT());
        graphics.setColor(Color.BLACK);

        for(int x = 0; x <= Context.getREAL_WIDTH(); x += Context.getSCALE()) {
            graphics.drawLine(x + Context.getOFFSET_TABLE_X(),
                                Context.getOFFSET_TABLE_Y(),
                            x + Context.getOFFSET_TABLE_X(),
                            Context.getREAL_HEIGHT() + Context.getOFFSET_TABLE_Y());
        }
        for(int y = 0; y <= Context.getREAL_HEIGHT(); y += Context.getSCALE()) {
            graphics.drawLine(Context.getOFFSET_TABLE_X(),
                            y + Context.getOFFSET_TABLE_Y(),
                            Context.getREAL_WIDTH() + Context.getOFFSET_TABLE_X(),
                            y + Context.getOFFSET_TABLE_Y());
        }
    }
}

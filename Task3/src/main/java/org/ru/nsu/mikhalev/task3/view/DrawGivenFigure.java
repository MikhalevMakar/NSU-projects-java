package org.ru.nsu.mikhalev.task3.view;

import org.ru.nsu.mikhalev.task3.model.Context;

import java.awt.*;

public class DrawGivenFigure {
    static public void draw(Graphics graphics, Color color, int x, int y) {
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
}

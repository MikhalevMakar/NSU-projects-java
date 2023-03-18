package org.ru.nsu.mikhalev.task3.view;

import org.ru.nsu.mikhalev.task3.model.Context;
import org.ru.nsu.mikhalev.task3.model.TetrisShape;
import java.awt.*;

public class FieldPanel {
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

    static public  void drawBackGround(Graphics graphics,Color[][] placedShape) {
        Color color;
        for(int y = 0; y < Context.getHEIGHT(); ++y) {
            for(int x = 0; x < Context.getWIDTH(); ++x) {
                color = placedShape[y][x];
                if(color != null)
                    DrawGivenFigure.draw(graphics, color, x, y);
            }
        }
    }
}

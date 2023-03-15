package org.ru.nsu.mikhalev.task3.model;

import org.jetbrains.annotations.NotNull;
import java.awt.*;

public class CheckMove {
    static private boolean EmptyColor(@NotNull TetrisShape shape) {
        int row = shape.getHeight() - 1;
        for(int i = 0; i < shape.getWidth(); ++i) {
            if(shape.IsShape(i, row) && shape.getColor() != null)
                return false;
        }
        return true;
    }
    static public boolean checkBarrier(TetrisShape shape, Color[][] placedShape) {
        int w = shape.getWidth();
        int h = shape.getHeight();

        //if(EmptyColor(shape)) return true;
        if(shape.getY() + h >= Context.getHEIGHT() / Context.getRATE_VALUE() - 1)
            return false;

        for (int column = 0; column < w; ++column) {
            for (int row = h - 1; row >= 0; --row) {
                int x = column + shape.getX();
                int y = row + shape.getY();
                if(shape.IsShape(column, row) &&
                   placedShape[y + 1][x] != null
                   )
                    return false;
            }
        }
        return true;
    }
    static public boolean checkMoveLeftShape(TetrisShape shape, Color[][] placedShape) {
        if(shape.getLeftSide() == 0)
            return false;

        int w = shape.getWidth();
        int h = shape.getHeight();
        for (int row = 0; row < h; ++row) {
            for (int column = 0; column < w; ++column) {
                if (shape.IsShape(column, row) &&
                        placedShape[row + shape.getY()][column + shape.getX() - 1] != null)
                    return false;
            }
        }
        return true;
    }
    static public boolean checkMoveRightShape(TetrisShape shape, Color[][] placedShape) {
        if(shape.getRightSide() >= Context.getWIDTH() / Context.getRATE_VALUE())
            return false;

        int w = shape.getWidth();
        int h = shape.getHeight();
        for (int row = 0; row < h; ++row) {
            for (int column = 0; column < w; ++column) {
                if (shape.IsShape(column, row) &&
                        placedShape[row + shape.getY()][column + shape.getX() + 1] != null) {
                    return false;
                }
            }
        }
        return true;
    }
    static public boolean checkMoveRotateShape(TetrisShape shape, Color[][] placedShape) {
        shape.nextRotation();
        if(!checkMoveRightShape(shape, placedShape) ||
           !checkMoveLeftShape(shape, placedShape) ||
           !checkBarrier(shape, placedShape)) {
            shape.previousRotation();
            return false;
        }
        shape.previousRotation();
        return true;
    }
}

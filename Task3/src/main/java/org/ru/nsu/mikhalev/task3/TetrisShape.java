package org.ru.nsu.mikhalev.task3;

import java.awt.*;

public class TetrisShape {
    Color color;
    int x = 0;
    int y = 0;
    private boolean[][] shape;
    public TetrisShape(Color color, boolean[][] shape) {
        this.shape = shape;
        this.color = color;
    }

    public int getWidthShape() {
        return shape.length;
    }

    public int getLengthShape() {
        return shape[0].length;
    }

    public boolean IsShape(int x, int y) {
        return shape[x][y];
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public void moveDown() {++y;}
    public void moveUp() {--y;}
    public void moveLeft() {--x;}
    public void moveRight() {++x;}

}

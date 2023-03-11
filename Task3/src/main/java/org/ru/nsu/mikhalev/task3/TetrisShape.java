package org.ru.nsu.mikhalev.task3;

import java.awt.*;

public class TetrisShape {
    private Color color;
    private int x = 0;
    private int y = 0;
    private final int CNT_ROTATION_SHIP = 4;
    private int indexRotation = 0;
    boolean[][][] rotateShapes;
    private boolean[][] shape;
    public TetrisShape(Color color, boolean[][] shape) {
        this.shape = shape;
        this.color = color;
        generateRotateShapes();
    }

    private void generateRotateShapes() {
        rotateShapes = new boolean[CNT_ROTATION_SHIP][][];
        for(int i = 0, h, w; i < CNT_ROTATION_SHIP; ++i) {
            h = getHeight();
            w = getWidth();
            rotateShapes[i] = new boolean[h][w];
            for(int y = 0; y < h; ++y) {
                for(int x = 0; x < w; ++x) {
                    rotateShapes[i][y][x] = shape[w-x-1][y];
                }
            }
            shape =  rotateShapes[i];
        }
    }
    public int getWidth() {
        return shape.length;
    }
    public int getHeight() {
        return shape[0].length;
    }
    public boolean IsShape(int x, int y) {
        return shape[x][y];
    }

    public Color getColor() {return this.color;}
    public void spawn() {
        indexRotation = 0;
        shape = rotateShapes[indexRotation];
        y = 0;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public void moveDown() {++y;}
    public void moveUp() {--y;}
    public void moveLeft() {--x;}
    public void moveRight() {++x;}
    public void nextRotation() {
        shape = rotateShapes[(++indexRotation) % CNT_ROTATION_SHIP];
    }

    public void previousRotation() {
        shape = rotateShapes[(--indexRotation) % CNT_ROTATION_SHIP];
    }

    public int getLeftSide() {
        return x;
    }

    public int getRightSide() {
        return x+getWidth();
    }
}

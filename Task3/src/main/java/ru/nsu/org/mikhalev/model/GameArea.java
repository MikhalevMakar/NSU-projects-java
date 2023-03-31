package ru.nsu.org.mikhalev.model;


import ru.nsu.org.mikhalev.controller.GameController;
import ru.nsu.org.mikhalev.model.shape.*;
import ru.nsu.org.mikhalev.view.tetris_area.Observer;
import ru.nsu.org.mikhalev.view.tetris_area.PerformanceGameArea;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameArea implements Observable {
    private Random random;
    private Color[][] placedShape = new Color[Context.getHEIGHT()][Context.getWIDTH()];
    private TetrisShape shape;
    private TetrisShape[] shapes;
    private Integer pointPlayer = 0;

    private List<Observer> observers;

    public GameArea() {
        random = new Random();

        observers = new LinkedList<Observer>();

        shapes = new TetrisShape[] {
                             new SmashBoy(),
                             new Ricky(),
                             new Hero(),
                             new TeeWee(),
                             new Cleveland()
                          };
        spawnShape();
    }

    public Integer getPointPlayer() {
        return pointPlayer;
    }

    public void spawnShape() {
        shape = shapes[random.nextInt(shapes.length)];
        shape.rotate();
        shape.setColor();
        shape.spawn();
    }

    private void clearLine(int row) {
        for (int i = 0; i < Context.getWIDTH(); ++i) {
            placedShape[row][i] = null;
        }
    }

    private void shiftDown(int curRow) {
        for (int row = curRow; row > 0; --row) {
            for (int column = 0; column < Context.getWIDTH(); ++column) {
                placedShape[row][column] = placedShape[row - 1][column];
            }
        }
    }

    public TetrisShape getShape() {
        return shape;
    }

    public Color[][] getPlacedShape() {
        return placedShape;
    }

    public boolean isBlockOutOfBounds() {
        return shape.getY() <= 0;
    }

    public void clearLines() {
        int cntStatusField;
        for (int row = 0; row < Context.getHEIGHT(); ++row) {
            cntStatusField = 0;
            for (int column = 0; column < Context.getWIDTH(); ++column) {
                if (placedShape[row][column] != null) ++cntStatusField;
            }
            if (cntStatusField == Context.getNUMBER_CUBES_ROW()) {
                pointPlayer += 100;
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                PerformanceGameArea.Repaint();
            }
        }
    }

    public void updateInitialValues() {
        for (int i = 0; i < Context.getHEIGHT(); ++i) {
            for (int j = 0; j < Context.getWIDTH(); ++j) {
                placedShape[i][j] = null;
            }
        }
        pointPlayer = 0;
        shape.spawn();
    }

    public boolean IsMoveShapeDown(boolean isPaused) {
        if (!CheckMove.checkBarrier(shape, placedShape)) {
            moveShapeToBackGround();
            clearLines();
            PerformanceGameArea.Repaint();
            return false;
        }
        if (!isPaused) shape.moveDown();
        PerformanceGameArea.Repaint();
        return true;
    }

    public void moveShapeDown() {
        while (!CheckMove.checkBarrier(shape, placedShape)) {
            shape.moveDown();
        }
        PerformanceGameArea.Repaint();
    }

    public void moveShapeUp() {
        if (!CheckMove.checkMoveUpShape(shape)) return;
        shape.moveUp();
        PerformanceGameArea.Repaint();
    }

    public void moveShapeLeft() {
        if (!CheckMove.checkMoveLeftShape(shape, placedShape)) return;
        shape.moveLeft();
        PerformanceGameArea.Repaint();
    }

    public void moveShapeRight() {
        if (!CheckMove.checkMoveRightShape(shape, placedShape)) return;
        shape.moveRight();
        PerformanceGameArea.Repaint();
    }

    public void moveShapeRotate() {
        if (!CheckMove.checkMoveRotateShape(shape, placedShape)) return;
        shape.nextRotation();

        PerformanceGameArea.Repaint();
    }

    public void moveShapeToBackGround() {
        if (!GameController.getPaused()) pointPlayer += 10;
        int h = shape.getHeight();
        int w = shape.getWidth();
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (shape.IsShape(x, y))
                    placedShape[y + shape.getY()][x + shape.getX()] = shape.getColor();
            }
        }
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.notification();
        }
    }
}
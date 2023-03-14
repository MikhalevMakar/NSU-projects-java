package org.ru.nsu.mikhalev.task3.controller;

import javax.swing.*;
import org.apache.log4j.*;
import org.ru.nsu.mikhalev.task3.model.CheckMove;
import org.ru.nsu.mikhalev.task3.model.Context;
import org.ru.nsu.mikhalev.task3.model.TetrisShape;
import org.ru.nsu.mikhalev.task3.model.shape.*;
import org.ru.nsu.mikhalev.task3.view.*;
import java.awt.*;
import java.util.Random;

public class GameArea extends JPanel {
    private static final Logger LOGGER = Logger.getLogger (GameArea.class.getName());
    private Random random;
    private Color[][] placedShape = new Color[Context.getHEIGHT()][Context.getWIDTH()];
    private TetrisShape shape;
    private  TetrisShape[] shapes;

    public GameArea() {
        LOGGER.info("GameArea");
        setBounds(getBounds());
        setBackground(DrawRectangle.color(49, 84, 100));
        random = new Random();
        shapes = new TetrisShape[] {
                    new SmashBoy (),
                    new Ricky (),
                    new Hero (),
                    new TeeWee (),
                    new Cleveland ()
                };

        spawnShape();
    }
    public void spawnShape() {
        shape = shapes[random.nextInt(shapes.length)];
        shape.setColor();
        shape.spawn();
    }
    private void clearLine(int row) {
        for (int i = 0; i < Context.getWIDTH(); ++i) {
            placedShape[row][i] = null;
        }
    }

    private void shiftDown(int curRow) {
        for(int row = curRow; row > 0; --row) {
            for(int column = 0; column < Context.getWIDTH(); ++column) {
                placedShape[row][column] = placedShape[row-1][column];
            }
        }
    }
    public boolean isBlockOutOfBounds() {
        return shape.getY() <= 0;
    }
    public void clearLines() {
        int cntStatusField;
        for(int row = 0; row < Context.getHEIGHT(); ++row) {
            cntStatusField = 0;
            for(int column = 0; column < Context.getWIDTH(); ++column) {
                if(placedShape[row][column] != null) ++cntStatusField;
            }
            if(cntStatusField == Context.getNUMBER_CUBES_ROW()) {
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                repaint();
            }
        }
    }
    public boolean IsMoveShapeDown() {
        if(!CheckMove.checkBarrier(shape, placedShape)) {
            moveShapeToBackGround();
            clearLines();
            return false;
        }
        shape.moveDown();
        repaint();
        return true;
    }
    public void moveShapeDown() {
        while(!CheckMove.checkBarrier(shape, placedShape)) {
            shape.moveDown();
        }
        repaint();
    }
    public void moveShapeUp() {
        shape.moveUp();
        repaint();
    }
    public void moveShapeLeft() {
        if(!CheckMove.checkMoveLeftShape(shape, placedShape)) return;
        shape.moveLeft();
        repaint();
    }
    public void moveShapeRight() {
        if(!CheckMove.checkMoveRightShape(shape, placedShape)) return;
        shape.moveRight();
        repaint();
    }
    public void moveShapeRotate() {
        if(!CheckMove.checkMoveRotateShape(shape, placedShape)) return;
        shape.nextRotation();
        repaint();
    }
    public void moveShapeToBackGround() {
        int h = shape.getHeight();
        int w = shape.getWidth();

        for(int y = 0; y < h; ++y) {
            for(int x = 0; x < w; ++x) {
                if (shape.IsShape(x, y))
                    placedShape[y + shape.getY()][x + shape.getX()] = shape.getColor();
            }
        }
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        LOGGER.info("Call paint component");
        FieldPanel.drawGridSquare(graphics);
        DrawRectangle r = new DrawRectangle(Context.getOFFSET_TABLE_X (),
                                            Context.getOFFSET_TABLE_Y(),
                                         500,
                                         800);
        add(r);
        DrawDetails.drawDetails(graphics, shape);
        Image img = new ImageIcon (Context.getLOGO_TETRIS ()).getImage();
        graphics.drawImage(img, 1037, -4, null);
        revalidate();
        FieldPanel.drawBackGround(graphics, shape, placedShape);
    }
}
package org.ru.nsu.mikhalev.task3;

import javax.swing.*;
import org.apache.log4j.*;
import org.ru.nsu.mikhalev.task3.shape.TeeWee;
import java.awt.*;
import java.util.Random;

public class GameArea extends JPanel {
    private static final Logger LOGGER = Logger.getLogger (GameArea.class.getName());
    private TetrisShape shape;
    private Color[][] placedShape = new Color[Context.getHEIGHT()][Context.getWIDTH()];
    private Random random;
    private Color color(int red, int green, int blue){
        return new Color(red, green, blue);
    }
    public GameArea() {
        LOGGER.info("GameArea");
        setBounds(getBounds());
        setBackground(color(49,84,100));
        random = new Random();
        spawnShape();
    }
    public void spawnShape() {
        TeeWee ricky = new TeeWee();
        shape = ricky.generateShape(4);
        shape.spawn();
    }
    private void clearLine(int row) {
        for (int i = 0; i < Context.getWIDTH(); ++i) {
            placedShape[row][i] = null;
        }
    }
    private void shiftDown(int curRow) {
        for(int row = curRow; row > 0; row--) {
            for(int column = 0; column < Context.getWIDTH(); ++column) {
                placedShape[row][column] = placedShape[row-1][column];
            }
        }
    }
    public void clearLines() {
        int cntStatusField;
        for(int row = 0; row < Context.getHEIGHT(); ++row) {
            cntStatusField = 0;
            for(int column = 0; column < Context.getWIDTH(); ++column) {
                if(placedShape[row][column] != null) ++cntStatusField;
            }
            if(cntStatusField == 12) {
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                repaint();
            }
        }
    }
    private boolean checkBarrier() {
        if (shape.getY () + shape.getHeight () >= Context.getHEIGHT() / Context.getRATE_VALUE() - 1)
            return false;

        int w = shape.getWidth();
        int h = shape.getHeight();
        for (int column = 0; column < w; ++column) {
            for (int row = h - 1; row >= 0; --row) {
                int x = column + shape.getX();
                int y = row + shape.getY();
                if(shape.IsShape(column, row) &&
                    placedShape[y + 1][x] != null)
                    return false;
            }
        }
        return true;
    }
    public boolean checkMoveLeftShape() {
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
    public boolean checkMoveRightShape() {
        if(shape.getRightSide() >= Context.getWIDTH() / Context.getRATE_VALUE())
           return false;

        int w = shape.getWidth();
        int h = shape.getHeight();
        for (int row = 0; row < h; ++row) {
            for (int column = 0; column < w; ++column) {
                if (shape.IsShape(column, row) &&
                    placedShape[row + shape.getY()][column + shape.getX()+1] != null) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkMoveRotateShape() {
        shape.nextRotation ();
        if(!checkMoveRightShape() ||
           !checkMoveLeftShape() ||
           !checkBarrier()) {
            shape.previousRotation();
            return false;
        }
        shape.previousRotation();
        return true;
    }
    public boolean IsMoveShapeDown() {
        if(!checkBarrier()) {
            moveShapeToBackGround();
            clearLines();
            return false;
        }
        this.shape.moveDown();
        repaint();
        return true;
    }
    public void moveShapeDown() {
        while(!checkBarrier()) {
            shape.moveDown();
        }
        repaint();
    }
    public void moveShapeUp() {
        shape.moveUp();
        repaint();
    }
    public void moveShapeLeft() {
        if(!checkMoveLeftShape()) return;
        shape.moveLeft();
        repaint();
    }
    public void moveShapeRight() {
        if(!checkMoveRightShape()) return;
        shape.moveRight();
        repaint();
    }
    public void moveShapeRotate() {
        if(!checkMoveRotateShape()) return;
        shape.nextRotation();
        repaint();
    }
    private void drawGivenFigure(Graphics graphics, Color color, int x, int y) {
        graphics.setColor(color);
        graphics.fillRect(x * Context.getOFFSET_SHARED() + Context.getOFFSET_TABLE_X(),
                       y * Context.getOFFSET_SHARED() + Context.getOFFSET_TABLE_Y(),
                          Context.getOFFSET_SHARED(),
                          Context.getOFFSET_SHARED());
        graphics.setColor(Color.black);
        graphics.drawRect(x * Context.getOFFSET_SHARED() + Context.getOFFSET_TABLE_X(),
                        y * Context.getOFFSET_SHARED() + Context.getOFFSET_TABLE_Y(),
                           Context.getOFFSET_SHARED(),
                           Context.getOFFSET_SHARED());
    }
    public void drawDetails(Graphics graphics) {
        int height = shape.getHeight();
        int weight = shape.getWidth();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < weight; ++x) {
                if (shape.IsShape(x, y))
                    drawGivenFigure(graphics, shape.getColor(), shape.getX() + x, shape.getY() + y);
            }
        }
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

    public boolean isBlockOutOfBounds() {
        return shape.getY() <= 0;
    }
    private void drawBackGround(Graphics graphics) {
        Color color;
        for(int y = 0; y < Context.getHEIGHT(); ++y) {
            for(int x = 0; x < Context.getWIDTH(); ++x) {
                color = placedShape[y][x];
                if(color != null)
                    drawGivenFigure(graphics, color, x, y);
            }
        }
    }
    private void drawImage(Graphics graphics) {
        Image img = new ImageIcon(Context.getLOGO_TETRIS ()).getImage();
        graphics.drawImage(img, 1037, -4, null);
        revalidate();
    }
    private void drawGridSquare(Graphics graphics) {
        graphics.setColor(color(70,70,65));
        graphics.fillRect(Context.getOFFSET_TABLE_X(),
                          Context.getOFFSET_TABLE_Y(),
                    Context.getWIDTH() * Context.getSCALE(),
                   Context.getHEIGHT() * Context.getSCALE());
        graphics.setColor(Color.black);

        for(int x = 0; x <= Context.getWIDTH()*Context.getSCALE(); x += Context.getSCALE()) {
            graphics.drawLine(x + Context.getOFFSET_TABLE_X(),
                                Context.getOFFSET_TABLE_Y(),
                            x + Context.getOFFSET_TABLE_X(),
                            Context.getHEIGHT() * Context.getSCALE() + Context.getOFFSET_TABLE_Y());
        }
        for(int y = 0; y <= Context.getHEIGHT()*Context.getSCALE(); y += Context.getSCALE()) {
            graphics.drawLine(Context.getOFFSET_TABLE_X(),
                            y + Context.getOFFSET_TABLE_Y(),
                            Context.getWIDTH()*Context.getSCALE() + Context.getOFFSET_TABLE_X(),
                            y + Context.getOFFSET_TABLE_Y());
        }
    }
    private void drawRectangle() {
        DrawRectangle r = new DrawRectangle(Context.getOFFSET_TABLE_X (),
                                            Context.getOFFSET_TABLE_Y(),
                                         500,
                                         800);
        add(r);
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        LOGGER.info("Call paint component");
        drawGridSquare(graphics);
        drawRectangle();
        drawDetails(graphics);
        drawImage(graphics);
        drawBackGround(graphics);
    }
}
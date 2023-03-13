package org.ru.nsu.mikhalev.task3;

import javax.swing.*;
import java.awt.*;
import org.apache.log4j.*;
import org.ru.nsu.mikhalev.task3.shape.TeeWee;

class DrawRectangle extends JPanel {
    public DrawRectangle(int x, int y, int w, int h) { new Rectangle(x, y, w, h); }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color c = new Color(128, 1, 255);
        g2.setColor(c);
    }
}
public class GameArea extends JPanel {
    private static final Logger LOGGER = Logger.getLogger (GameArea.class.getName ());
    private static final int SCALE = 2;
    private static final int WIDTH = 252;
    private static final int HEIGHT = 420;
    private static final int OFFSET_TABLE_X = 400;
    private static final int OFFSET_TABLE_Y = 50;
    private static final int RATIO_SHAPE_VAlUE = 10;
    private static final int OFFSET_SHARED = HEIGHT / RATIO_SHAPE_VAlUE;
    private TetrisShape shape;
    private String LogoTetris = "../Task3/src/main/resources/LogoTetris.png";
    private Color[][] placedShape = new Color[HEIGHT][WIDTH];
    public Color color(int red, int green, int blue){
        return new Color(red, green, blue);
    }
    public GameArea() {
        LOGGER.info("GameArea");
        this.setBounds(getBounds());
        this.setBackground (color(49,84,100));
        spawnShape();
    }
    public void spawnShape() {
        TeeWee ricky = new TeeWee();
        shape = ricky.generateShape(4);
        shape.spawn();
    }
    private void clearLine(int row) {
        for (int i = 0; i < WIDTH; ++i) {
            placedShape[row][i] = null;
        }
    }
    private void shiftDown(int curRow) {
        for(int row = curRow; row > 0; row--) {
            for(int column = 0; column < WIDTH; ++column) {
                placedShape[row][column] = placedShape[row-1][column];
            }
        }
    }
    public void clearLines() {
        int cntStatusField;
        for(int row = 0; row < HEIGHT; ++row) {
            cntStatusField = 0;
            for(int column = 0; column < WIDTH; ++column) {
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
        if (shape.getY () + shape.getHeight () >= HEIGHT / (RATIO_SHAPE_VAlUE * SCALE) - 1)
            return false;

        int w = shape.getWidth();
        int h = shape.getHeight();
        for (int column = 0; column < w; ++column) {
            for (int row = h - 1; row >= 0; --row) {
                int x = column + shape.getX();
                int y = row + shape.getY();
                if(shape.IsShape (column, row) &&
                    placedShape[y + 1][x] != null)
                    return false;
            }
        }
        return true;
    }
    public boolean checkLeft() {
       if(shape.getLeftSide() == 0)
           return false;

        int w = shape.getWidth();
        int h = shape.getHeight();
        for (int row = 0; row < h; ++row) {
            for (int column = 0; column < w; ++column) {
                if (shape.IsShape(column, row) &&
                    placedShape[row + shape.getY()][column + shape.getX()-1] != null)
                    return false;
            }
        }
        return true;
    }
    public boolean checkRight() {
        if(shape.getRightSide() > WIDTH / (RATIO_SHAPE_VAlUE*SCALE))
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
    public boolean checkRotate() {
        shape.nextRotation ();
        if(!checkRight() || !checkLeft() || !checkBarrier()) {
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
            this.shape.moveDown();
        }
        repaint();
    }
    public void moveShapeUp() {
        this.shape.moveUp();
        repaint();
    }
    public void moveShapeLeft() {
        if(!checkLeft()) return;
        this.shape.moveLeft();
        repaint();
    }
    public void moveShapeRight() {
        if(!checkRight()) return;
        this.shape.moveRight();
        repaint();
    }
    public void moveShapeRotate() {
        if(!checkRotate()) return;
        this.shape.nextRotation();
        repaint();
    }
    private void drawGivenFigure(Graphics graphics, Color color, int x, int y) {
        graphics.setColor(color);
        graphics.fillRect(x*OFFSET_SHARED + OFFSET_TABLE_X,
                y*OFFSET_SHARED + OFFSET_TABLE_Y, OFFSET_SHARED, OFFSET_SHARED);
        graphics.setColor(Color.black);
        graphics.drawRect(x*OFFSET_SHARED + OFFSET_TABLE_X,
                y*OFFSET_SHARED + OFFSET_TABLE_Y, OFFSET_SHARED, OFFSET_SHARED);
    }
    public void drawDetails(Graphics graphics) {
        int height = shape.getHeight ();
        int weight = shape.getWidth ();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < weight; ++x) {
                if (shape.IsShape(x, y))
                    drawGivenFigure (graphics, shape.getColor(), shape.getX () + x, shape.getY () + y);
            }
        }
    }
    public void moveShapeToBackGround() {
        int h = shape.getHeight();
        int w = shape.getWidth();

        for(int y = 0; y < h; ++y) {
            for(int x = 0; x < w; ++x) {
                if (shape.IsShape(x, y)) {
                    placedShape[y + shape.getY()][x + shape.getX()] = shape.getColor();
                }
            }
        }
    }

    public boolean isBlockOutOfBounds() {
        return shape.getY() <= 0;
    }
    private void drawBackGround(Graphics graphics) {
        Color color;
        for(int y = 0; y < HEIGHT; ++y) {
            for(int x = 0; x < WIDTH; ++x) {
                color = placedShape[y][x];
                if(color != null)
                    drawGivenFigure(graphics, color, x, y);
            }
        }
    }
    private void drawImage(Graphics graphics) {
        Image img = new ImageIcon(LogoTetris).getImage();
        graphics.drawImage(img, 1037, -4, null);
        revalidate();
    }
    private void drawGridSquare(Graphics graphics) {
        graphics.setColor(color(70,70,70));
        graphics.fillRect(OFFSET_TABLE_X, OFFSET_TABLE_Y,WIDTH*SCALE, HEIGHT*SCALE);
        graphics.setColor(color(13,13,13));

        for(int x = 0; x <= WIDTH*SCALE; x += SCALE) {
            graphics.drawLine(x + OFFSET_TABLE_X, OFFSET_TABLE_Y,
                    x + OFFSET_TABLE_X, HEIGHT*SCALE + OFFSET_TABLE_Y);
        }
        for(int y = 0; y <= HEIGHT*SCALE; y += SCALE) {
            graphics.drawLine(OFFSET_TABLE_X, y + OFFSET_TABLE_Y,
                    WIDTH*SCALE + OFFSET_TABLE_X, y + OFFSET_TABLE_Y);
        }
    }
    private void drawRectangle() {
        DrawRectangle r = new DrawRectangle(400, 50, 500, 800);
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

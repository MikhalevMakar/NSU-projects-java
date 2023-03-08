package org.ru.nsu.mikhalev.task3;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;


    class DrawRectangle extends JPanel {
    private Rectangle rect;
    public DrawRectangle(int x, int y, int w, int h) {
        rect = new Rectangle(x, y, w, h);
    }
    @Override
    public void paintComponent(Graphics g) {
       Graphics2D g2 = (Graphics2D) g;
        Color c = new Color(128, 1, 255);
        g2.setColor(c);
    }
}

public class GameArea extends JPanel {
    private static final int SCALE = 2;
    private static final int WIDTH = 250;
    private static final int HEIGHT = 400;
    private static final int OFFSET_TABLE_X = 400;
    private static final int OFFSET_TABLE_Y = 50;
    private TetrisShape shape;

    private String LogoTetris = "../Task3/src/main/resources/LogoTetris.png";
    public Color color(int red, int green, int blue){
        return new Color(red, green, blue);
    }
    public GameArea() {
        this.setBounds(getBounds());
        this.setBackground (color(49,84,100));
    }

    public void spawnShape() {
        shape = new TetrisShape (Color.blue, new boolean[][] { {true, false},
                                                               {true, false},
                                                               {true,true}
                                                              });
    }
    public void moveShapeDown() {
        System.out.println ("MOVE " + shape);
        this.shape.moveDown();
        repaint();
    }
    public void drawDetails(Graphics graphics) {
        int height = shape.getLengthShape();
        int weight = shape.getWidthShape();
        int offset = OFFSET_TABLE_X/12;

        System.out.println ("INIT " + shape);
        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < weight; ++x) {
                if (shape.IsShape(x, y)) {
                    graphics.setColor(Color.red);
                    graphics.fillRect((shape.getX() + x)*offset+OFFSET_TABLE_X,
                            (shape.getY() + y)*offset+OFFSET_TABLE_Y, offset, offset);
                    graphics.setColor(Color.black);
                    graphics.drawRect((shape.getX() + x)*offset+OFFSET_TABLE_X,
                                  (shape.getY() + y)*offset+OFFSET_TABLE_Y, offset, offset);
                }
            }
        }
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.setColor(color(70,70,70));
        graphics.fillRect(OFFSET_TABLE_X,OFFSET_TABLE_Y, WIDTH*SCALE, HEIGHT*SCALE);
        graphics.setColor(color(13,13,13));

        for(int x = 0; x <= WIDTH*SCALE; x += SCALE) {
            graphics.drawLine(x + OFFSET_TABLE_X, OFFSET_TABLE_Y, x + OFFSET_TABLE_X, HEIGHT*SCALE + OFFSET_TABLE_Y);
        }
        for(int y = 0; y <= HEIGHT*SCALE; y += SCALE) {
            graphics.drawLine(OFFSET_TABLE_X, y + OFFSET_TABLE_Y,WIDTH*SCALE + OFFSET_TABLE_X, y + OFFSET_TABLE_Y);
        }

        spawnShape();
        DrawRectangle r = new DrawRectangle(400, 50, 500, 800);
        r.paintComponent (graphics);
        add(r);

        drawDetails(graphics);

        Image img = new ImageIcon(LogoTetris).getImage();
        graphics.drawImage(img,  1037, -4   , null);
        revalidate();
    }

}

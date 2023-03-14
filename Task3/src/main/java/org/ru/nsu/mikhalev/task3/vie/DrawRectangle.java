package org.ru.nsu.mikhalev.task3.vie;

import javax.swing.*;
import java.awt.*;

public class DrawRectangle extends JPanel {
    static public Color color(int red, int green, int blue){
        return new Color(red, green, blue);
    }
    public DrawRectangle(int x, int y, int w, int h) { new Rectangle (x, y, w, h); }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color c = new Color(128, 1, 255);
        g2.setColor(c);
    }
}
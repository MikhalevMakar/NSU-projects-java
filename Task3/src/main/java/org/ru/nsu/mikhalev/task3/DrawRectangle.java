package org.ru.nsu.mikhalev.task3;

import javax.swing.*;
import java.awt.*;

class DrawRectangle extends JPanel {
    public DrawRectangle(int x, int y, int w, int h) { new Rectangle (x, y, w, h); }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color c = new Color(128, 1, 255);
        g2.setColor(c);
    }
}
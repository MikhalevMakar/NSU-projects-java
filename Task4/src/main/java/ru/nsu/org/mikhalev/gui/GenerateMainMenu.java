package ru.nsu.org.mikhalev.gui;

import javax.swing.*;
import java.awt.*;

public class GenerateMainMenu extends JFrame {
    public GenerateMainMenu() {

        ImageIcon imageIcon = new ImageIcon
            (
            "/Users/natasamihaleva/NSU_Projects_Java/Task4/src/main/resources/image.gif"
            );
        
        JLabel label = new JLabel (imageIcon);

        add(label, BorderLayout.WEST);
        new SupplierSpeedSlider(this);
    }
}

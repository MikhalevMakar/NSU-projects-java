package org.ru.nsu.mikhalev.task3;

import javax.swing.*;
import java.awt.*;

public class StartGame extends JFrame {
    private GameArea gameArea;
    public  StartGame() {
        gameArea = new GameArea();
        this.add(gameArea);
        //this.runGame();
    }

    public void runGame() {
        new GameThread(gameArea).run();
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater (new Runnable () {
            @Override
            public void run() {
                new StartGame().setVisible(true);
            }
        });
    }
    private javax.swing.JPanel gameAreaPlace = new JPanel ();
}
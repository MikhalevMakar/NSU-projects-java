package org.ru.nsu.mikhalev.task3;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameController {
    private GameArea gameArea;
    private JFrame frame;
    private void creationControls() {
        InputMap inputMap = frame.getRootPane().getInputMap();
        ActionMap actionMap = frame.getRootPane().getActionMap();
        inputMap.put (KeyStroke.getKeyStroke ("UP"), "up");
        inputMap.put (KeyStroke.getKeyStroke ("DOWN"), "down");
        inputMap.put (KeyStroke.getKeyStroke ("LEFT"), "left");
        inputMap.put (KeyStroke.getKeyStroke ("RIGHT"), "right");
        inputMap.put (KeyStroke.getKeyStroke ("SPACE"), "space");
        actionMap.put("up", new AbstractAction () {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveShapeUp();
            }
        });
        actionMap.put("down",  new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveShapeDown();
            }
        });
        actionMap.put("left",  new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveShapeLeft();
            }
        });
        actionMap.put("right",  new AbstractAction () {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveShapeRight();
            }
        });

        actionMap.put("space",  new AbstractAction () {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println ("SPACE");
                gameArea.moveShapeRotate();
            }
        });
    }
    public GameController() {
        frame = new JFrame ();
        gameArea = new GameArea();
        frame.add (gameArea);
        frame.setSize (1500, 840);
        frame.setVisible (true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creationControls();
        runGame();
    }

    private void runGame() {
        new GameThread(gameArea).run();
    }
}
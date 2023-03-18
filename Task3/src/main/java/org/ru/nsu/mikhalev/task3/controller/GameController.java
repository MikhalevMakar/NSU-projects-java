package org.ru.nsu.mikhalev.task3.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameController implements Runnable {
    private GameArea gameArea;
    private JFrame frame;
    private void creationControls() {
        InputMap inputMap = frame.getRootPane().getInputMap();
        ActionMap actionMap = frame.getRootPane().getActionMap();
        inputMap.put (KeyStroke.getKeyStroke("UP"), "up");
        inputMap.put (KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put (KeyStroke.getKeyStroke("LEFT"), "left");
        inputMap.put (KeyStroke.getKeyStroke("RIGHT"), "right");
        inputMap.put (KeyStroke.getKeyStroke("SPACE"), "space");

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
                gameArea.moveShapeRotate();
            }
        });
    }
    public GameController() {
        frame = new JFrame();
        gameArea = new GameArea();
        frame.add(gameArea);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creationControls();
    }

    @Override
    public void run() {
        while(true) {
            while(gameArea.IsMoveShapeDown()) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(gameArea.isBlockOutOfBounds()) {
                System.out.println("Game Over");
                return;
            }
            gameArea.spawnShape();
        }
    }
}

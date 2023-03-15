package org.ru.nsu.mikhalev.task3.controller;

import org.ru.nsu.mikhalev.task3.model.GameThread;
import javax.swing.*;
import java.awt.event.ActionEvent;


//class ButtonActionListener implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String message = "";
//        JOptionPane.showMessageDialog (null, message, "Output", JOptionPane.PLAIN_MESSAGE);
//    }
//}

public class GameController {
    private GameArea gameArea;
    private JFrame frame;
    private JButton button = new JButton ("RULES");
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
        new GameThread (gameArea).run();
    }
}

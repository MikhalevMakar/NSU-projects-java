package ru.nsu.org.mikhalev.view.tetris_area;

import ru.nsu.org.mikhalev.model.GameArea;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DetailSwitchButtons {
    public DetailSwitchButtons(JFrame frame, GameArea gameArea) {
        InputMap inputMap = frame.getRootPane().getInputMap();
        ActionMap actionMap = frame.getRootPane().getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "space");
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
}

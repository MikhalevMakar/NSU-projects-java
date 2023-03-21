package org.ru.nsu.mikhalev.task3.controller;

import org.ru.nsu.mikhalev.task3.model.Context;
import org.ru.nsu.mikhalev.task3.view.HorizontalGradientButton;
import org.ru.nsu.mikhalev.task3.view.SetColor;
import org.ru.nsu.mikhalev.task3.view.generate_menu.LeaderBoard;
import org.ru.nsu.mikhalev.task3.view.generate_menu.GenerateMenu;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameController implements Runnable {
    private GameAreaController gameArea;
    private JFrame frame;
    private static int DELAY = Context.getMIDDLE();
    private JButton buttonMenu, buttonPause, buttonPlay;
    private boolean isPaused = false;
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

    private void createLeaderBoard(String playerName) {
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.addPlayer(playerName, gameArea.getPointPlayer());
        leaderBoard.fillTablePlayer();
    }

    static public void setDELAY(int curDELAY) { DELAY = curDELAY;}
    private void gameOver() {
        String playerName = JOptionPane.showInputDialog("Game Over\n Please, input your name.");
        createLeaderBoard(playerName);
        System.out.println(playerName);
    }
    static public void launchMenu() { new GenerateMenu ();}
    public GameController()  {
        frame = new JFrame();
        gameArea = new GameAreaController(frame);
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
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(gameArea.isBlockOutOfBounds()) {
                System.out.println("Game Over");
                Thread.currentThread().interrupt();
                gameOver();
            }
            if(!gameArea.isPaused())
                gameArea.spawnShape();
        }
    }
}

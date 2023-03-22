package ru.nsu.org.mikhalev.view.tetris_area;

import ru.nsu.org.mikhalev.controller.GameController;
import ru.nsu.org.mikhalev.model.Context;
import ru.nsu.org.mikhalev.model.GameArea;
import ru.nsu.org.mikhalev.view.CreateFrame;
import ru.nsu.org.mikhalev.view.HorizontalGradientButton;
import ru.nsu.org.mikhalev.view.SetColor;
import ru.nsu.org.mikhalev.view.menu_game.GenerateMenu;
import ru.nsu.org.mikhalev.view.menu_game.LeaderBoard;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerformanceGameArea implements Runnable {
    private boolean isPaused = false;
    private static int DELAY = Context.getMIDDLE ();
    CreateFrame frame;
    private JButton buttonMenu, buttonPause, buttonPlay, buttonRestart;
    private String PANEL_GAME_AREA = "PanelGameArea.jpg";
    private GameController gameController;
    private static GameArea gameArea;
    private static JPanel panel;

    public PerformanceGameArea() {
        frame = new CreateFrame ();
        gameArea = new GameArea ();
        panel = new JPanel () {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent (g);
                Image image = new ImageIcon (Context.getPATH_RESOURCES () + PANEL_GAME_AREA).getImage ();
                g.drawImage (image, 0, 0, getWidth (), getHeight (), this);
                FieldPanel.drawGridSquare (g);

                Font font = new Font ("Comic", Font.BOLD, 80);
                g.setFont (font);
                g.setColor (new Color (88, 114, 140));
                g.drawString ("POINT", 1050, 80);

                g.drawString (gameArea.getPointPlayer ().toString (), 1200, 160);

                DrawDetails.drawDetails (g, gameArea.getShape ());
                FieldPanel.drawBackGround (g, gameArea.getPlacedShape ());
            }
        };

        panel.setOpaque (false);
        frame.setContentPane (panel);
        new DetailSwitchButtons (frame, gameArea);
        createButtonMenu ();
        createButtonPause ();
        createButtonRestart ();
        createButtonPlay ();
        gameController = new GameController (gameArea);
    }

    public static void Repaint() {
        panel.repaint ();
    }

    private void createButtonMenu() {
        buttonMenu = new HorizontalGradientButton ("Menu!",
                1100,
                500,
                SetColor.GREEN_START.get (),
                SetColor.GREEN_END.get ());
        buttonMenu.addKeyListener (null);
        buttonMenu.addActionListener (e -> {
            frame.dispose ();
            new GenerateMenu ();
        });
        frame.add (buttonMenu);
    }

    private void createButtonPlay() {
        buttonPlay = new HorizontalGradientButton ("Play",
                100,
                600,
                SetColor.GREEN_START.get (),
                SetColor.GREEN_END.get ());
        buttonPlay.setFocusable (false);
        buttonPlay.addActionListener (n -> {
            ExecutorService newExecutor = Executors.newSingleThreadExecutor();
            newExecutor.shutdown();
            gameController.run();
        });
        frame.add (buttonPlay);
    }

    private void createButtonPause() {
        buttonPause = new HorizontalGradientButton ("Pause",
                100,
                500,
                SetColor.GREEN_START.get (),
                SetColor.GREEN_END.get ());
        buttonPause.setFocusable (false);
        buttonPause.addActionListener (e -> {
            gameController.setPaused (true);
        });
        frame.add (buttonPause);
    }

    private void createButtonRestart() {
        buttonRestart = new HorizontalGradientButton ("Restart",
                1100,
                600,
                SetColor.GREEN_START.get (),
                SetColor.GREEN_END.get ());

        buttonRestart.setFocusable (false);
        buttonRestart.addActionListener (e -> {
            gameController.preparationNewGame ();
        });
        frame.add (buttonRestart);
    }

    private static void createLeaderBoard(String playerName) {
        LeaderBoard leaderBoard = new LeaderBoard ();
        leaderBoard.addPlayer (playerName, gameArea.getPointPlayer ());
        leaderBoard.fillTablePlayer ();
    }

    public static void gameOver() {
        String playerName = JOptionPane.showInputDialog ("Game Over\n Please, input your name.");
        createLeaderBoard (playerName);
    }

    @Override
    public void run() {
        gameController.run ();
    }
}

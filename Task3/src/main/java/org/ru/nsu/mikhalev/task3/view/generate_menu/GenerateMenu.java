package org.ru.nsu.mikhalev.task3.view.generate_menu;

import org.ru.nsu.mikhalev.task3.controller.GameController;
import org.ru.nsu.mikhalev.task3.model.Context;
import org.ru.nsu.mikhalev.task3.view.HorizontalGradientButton;
import org.ru.nsu.mikhalev.task3.view.SetColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GenerateMenu extends JFrame {
    private String MainMenu = "MainMenu.jpg";
    private JButton buttonStart,
                    buttonLevel,
                    buttonScore,
                    buttonRules;
    public GenerateMenu()  {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel () {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent (g);
                Image image = new ImageIcon(Context.getPATH_RESOURCES() + MainMenu).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setOpaque(false);
        setContentPane(panel);
        createButtonStart();
        createButtonLevel();
        createButtonScore();
        createButtonRules();
        setVisible(true);
    }

    private void createButtonStart() {
         buttonStart = new HorizontalGradientButton ("Start game!",
                                                    1050,
                                                    300,
                                                    SetColor.GREEN_START.get(),
                                                    SetColor.GREEN_END.get());
        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ExecutorService newExecutor = Executors.newSingleThreadExecutor();
                newExecutor.execute(new GameController());
                newExecutor.shutdown();
            }
        });
        getContentPane().add(buttonStart);
    }

    private void  createButtonLevel() {
         buttonLevel = new HorizontalGradientButton("Difficulty level",
                                                    250,
                                                    300,
                                                    SetColor.GOLD_START.get(),
                                                    SetColor.GOLD_END.get());
        buttonLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameLevelPanel ();
            }
        });
        getContentPane().add(buttonLevel);
    }
    private void createButtonScore() {
        buttonScore = new HorizontalGradientButton("Table score",
                                               1050,
                                               450,
                                                        SetColor.GREEN_START.get(),
                                                        SetColor.GREEN_END.get());
        buttonScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LeaderBoard leaderBoard = new LeaderBoard();
                leaderBoard.fillTablePlayer();
            }
        });
        getContentPane().add(buttonScore);
    }
    private void createButtonRules() {
        buttonRules = new HorizontalGradientButton("Rules!",
                                                   250,
                                                   450,
                                                   SetColor.GOLD_START.get(),
                                                   SetColor.GOLD_END.get());
        buttonRules.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RulesGame();
            }
        });
        getContentPane().add(buttonRules);
    }
}
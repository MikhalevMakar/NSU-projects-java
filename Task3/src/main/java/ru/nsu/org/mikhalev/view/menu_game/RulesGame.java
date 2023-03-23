package ru.nsu.org.mikhalev.view.menu_game;

import ru.nsu.org.mikhalev.model.Context;

import javax.swing.*;
import java.awt.*;

public class RulesGame extends JFrame {
    public RulesGame() {
        super ("Funny Tetris");

        JPanel panel = new JPanel (new BorderLayout ());

        JLabel descriptionLabelFirst = new JLabel ("<html><h1>Funny Tetris Game</h1>" +
                "<p>Use arrow keys to move the falling blocks and try to fill " +
                "complete rows to score points. The game ends when the blocks " +
                "reach the top of the screen.The main goal of the game is to score as many points as possible <br> <br> " +
                "POINTS can be obtained for arranging pieces +10 and if a line of pieces +100 is built." +
                "The game has 3 game modes: EASILY, MIDDLE, HARD. By default set to MIDDLE." +
                "The game has the following figures:</p></html>");

        panel.add (descriptionLabelFirst, BorderLayout.NORTH);

        JPanel figuresPanel = new JPanel (new GridLayout (1, 5));
        ImageIcon clevelandIcon = new ImageIcon (Context.getPATH_RESOURCES () + "Cleveland.png");
        ImageIcon heroIcon = new ImageIcon (Context.getPATH_RESOURCES () + "Hero.png");
        ImageIcon rickyIcon = new ImageIcon (Context.getPATH_RESOURCES () + "Ricky.png");
        ImageIcon teeWeeIcon = new ImageIcon (Context.getPATH_RESOURCES () + "TeeWee.png");
        ImageIcon smashBoyIcon = new ImageIcon (Context.getPATH_RESOURCES () + "SmashBoy.png");

        figuresPanel.add (new JLabel (clevelandIcon));
        figuresPanel.add (new JLabel (heroIcon));
        figuresPanel.add (new JLabel (rickyIcon));
        figuresPanel.add (new JLabel (teeWeeIcon));
        figuresPanel.add (new JLabel (smashBoyIcon));
        panel.add (figuresPanel, BorderLayout.SOUTH);

        setContentPane (panel);
        pack ();
        setLocationRelativeTo (null);
        setVisible (true);
    }
}
package org.ru.nsu.mikhalev.task3.view;

import org.ru.nsu.mikhalev.task3.controller.GameController;
import org.ru.nsu.mikhalev.task3.model.Context;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameLevelPanel extends JFrame {
    private String pathFile = "../Task3/src/main/resources/PanelLevel.png";
    private JCheckBox EASILY = new JCheckBox ("EASILY");
    private JPanel panel;
    private JCheckBox MIDDLE = new JCheckBox("MIDDLE");
    private JCheckBox HARD = new JCheckBox ("HARD");
    private void checkListener() {
        EASILY.addItemListener(new ItemListener () {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (EASILY.isSelected()) {
                    MIDDLE.setSelected(false);
                    HARD.setSelected(false);
                }
            }
        });

        MIDDLE.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (MIDDLE.isSelected()) {
                    EASILY.setSelected(false);
                    HARD.setSelected(false);
                }
            }
        });

        HARD.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (HARD.isSelected()) {
                    EASILY.setSelected(false);
                    MIDDLE.setSelected(false);
                }
            }
        });

    }

    public GameLevelPanel() {
        super.setBounds(550, 200, 300, 100);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(pathFile).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        createEASILY();
        createMIDDLE();
        createHARD();

        checkListener();
        addActions();
        panel.setOpaque(false);
        setContentPane(panel);
        setVisible(true);
    }

    private void addActions() {
        EASILY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.setDELAY(Context.getEASILY());
            }
        });

        MIDDLE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.setDELAY(Context.getMIDDLE());
            }
        });

        HARD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.setDELAY(Context.getHARD());
            }
        });
    }

    private void createEASILY() {
        EASILY.setSelected(true);
        EASILY.setForeground(Color.WHITE);
        EASILY.setSize(new Dimension(20, 20));
        EASILY.setLocation(50, 50);
        panel.add(EASILY);
    }
    private void createMIDDLE() {
        MIDDLE.setForeground(Color.WHITE);
        MIDDLE.setSize(new Dimension(20, 20));
        MIDDLE.setLocation(50, 100);
        panel.add(MIDDLE);
    }
    private void createHARD() {
        HARD.setForeground(Color.WHITE);
        HARD.setSize(new Dimension(20, 20));
        HARD.setLocation(50, 150);
        panel.add(HARD);
    }
}
package org.ru.nsu.mikhalev.task3.view;

import org.ru.nsu.mikhalev.task3.controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum SetColor {
    GREEN_START(new Color(81, 116, 128)),
    GREEN_END(new Color(88, 128, 140)),
    GOLD_START(new Color(130, 136, 103)),
    GOLD_END(new Color(120, 123, 90));
    private Color color;
    SetColor(Color color) {
        this.color = color;
    }
    public Color get(){ return color;}

}
public class GenerateMenu extends JFrame {
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
                Image image = new ImageIcon("../Task3/src/main/resources/template-5.jpg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setOpaque(false);
        setContentPane(panel);

        createButtonStart();
        createButtonLevel();
        createButtonScore();
        createButtonRules();

        setVisible(true);
    }

    private void createButtonStart() {
         buttonStart = new HorizontalGradientButton("Start game!",
                                                        1050,
                                                       300,
                                                        SetColor.GREEN_START.get(),
                                                        SetColor.GREEN_END.get());
        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                ExecutorService newExecutor = Executors.newSingleThreadExecutor();
                newExecutor.execute(new GameController());
                newExecutor.shutdown();
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
                ExecutorService newExecutor = Executors.newSingleThreadExecutor();
                newExecutor.execute(new GameController());
                newExecutor.shutdown();
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
                ExecutorService newExecutor = Executors.newSingleThreadExecutor();
                newExecutor.execute(new GameController());
                newExecutor.shutdown();
            }
        });
        getContentPane().add(buttonRules);
    }
}

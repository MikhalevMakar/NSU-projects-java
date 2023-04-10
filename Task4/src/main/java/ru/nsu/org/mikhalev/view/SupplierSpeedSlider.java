package ru.nsu.org.mikhalev.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SupplierSpeedSlider implements ChangeListener {
    private JSlider supplier1Slider;
    private JSlider supplier2Slider;
    private JSlider supplier3Slider;

    public SupplierSpeedSlider(JFrame frame) {

        supplier1Slider = createSlider(ContextGUI.OFFSET_X, ContextGUI.OFFSET_Y,
                                       ContextGUI.WIDTH_SUPPLIER, ContextGUI.HEIGHT_SUPPLIER);

        supplier2Slider = createSlider(ContextGUI.OFFSET_X, ContextGUI.OFFSET_Y,
                                       ContextGUI.WIDTH_SUPPLIER, ContextGUI.HEIGHT_SUPPLIER);

        supplier3Slider = createSlider(ContextGUI.OFFSET_X, ContextGUI.OFFSET_Y,
                                       ContextGUI.WIDTH_SUPPLIER, ContextGUI.HEIGHT_SUPPLIER);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(supplier1Slider, BorderLayout.EAST);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(supplier2Slider, BorderLayout.EAST);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(supplier3Slider, BorderLayout.EAST);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        sliderPanel.add(panel1);
        sliderPanel.add(panel2);
        sliderPanel.add(panel3);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(sliderPanel, BorderLayout.CENTER);
        frame.setSize(ContextGUI.WIDTH, ContextGUI.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JSlider createSlider(int x, int y, int width, int height) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);

        slider.setPreferredSize(new Dimension(width, height));
        slider.setBorder(BorderFactory.createEmptyBorder(x, y, width, height));

        return slider;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int supplier1Speed = supplier1Slider.getValue();
        int supplier2Speed = supplier2Slider.getValue();
        int supplier3Speed = supplier3Slider.getValue();

        System.out.println("Supplier 1 Speed: " + supplier1Speed);
        System.out.println("Supplier 2 Speed: " + supplier2Speed);
        System.out.println("Supplier 3 Speed: " + supplier3Speed);
    }
}
package ru.nsu.org.mikhalev.view;

import ru.nsu.org.mikhalev.factory.Factory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SupplierSpeedSlider {
    private JSlider sliderDealer,
                    sliderAccessorySupplier,
                    sliderMotorSupplier,
                    sliderBodySupplier;

    public SupplierSpeedSlider(JFrame frame, Factory factory){
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout (sliderPanel, BoxLayout.Y_AXIS));

        sliderDealer = createSlider();
        sliderDealer.addChangeListener((ChangeEvent e) ->
            factory.setTimeDealer(sliderDealer.getValue()*10));


        sliderAccessorySupplier =  createSlider();
        sliderAccessorySupplier.addChangeListener((ChangeEvent e) ->
            factory.setTimeAccessorySupplier(sliderAccessorySupplier.getValue()*10));

        sliderMotorSupplier = createSlider();
        sliderMotorSupplier.addChangeListener((ChangeEvent e) ->
            factory.setTimeMotorSupplier(sliderMotorSupplier.getValue()*10));


        sliderBodySupplier = createSlider();
        sliderBodySupplier.addChangeListener((ChangeEvent e) ->
            factory.setTimeBodySupplier(sliderBodySupplier.getValue()*10));

        sliderPanel.add(sliderDealer);
        sliderPanel.add(sliderAccessorySupplier);
        sliderPanel.add(sliderMotorSupplier);
        sliderPanel.add(sliderBodySupplier);

        frame.getContentPane().add(sliderPanel, BorderLayout.EAST);
    }

    private JSlider createSlider() {
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL);
        slider.setMajorTickSpacing(ContextGUI.getMAJOR_TICK_SPACING());
        slider.setMinorTickSpacing(ContextGUI.getMINOR_TICK_SPACING());

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.setPreferredSize(new Dimension(ContextGUI.ContextSupplier.getWIDTH(),
                                    ContextGUI.ContextSupplier.getHEIGHT()));

        slider.setBorder(BorderFactory.createEmptyBorder(ContextGUI.ContextSupplier.getTOP(),
                                                         ContextGUI.ContextSupplier.getLEFT(),
                                                         ContextGUI.ContextSupplier.getBOTTOM(),
                                                         ContextGUI.ContextSupplier.getRIGHT()));
        return slider;
    }
}
package ru.nsu.org.mikhalev.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SupplierSpeedSlider implements ChangeListener {
    private ArrayList<JSlider> sliders = new ArrayList<>(ContextGUI.ContextSupplier.getSIZE());

    public SupplierSpeedSlider(JFrame frame){
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout (sliderPanel, BoxLayout.Y_AXIS));

        for(int i = 0; i < ContextGUI.ContextSupplier.getSIZE(); ++i) {
            sliders.add(createSlider(ContextGUI.ContextSupplier.getTOP(), ContextGUI.ContextSupplier.getLEFT(),
                                     ContextGUI.ContextSupplier.getBOTTOM(), ContextGUI.ContextSupplier.getRIGHT()));

            sliderPanel.add(sliders.get(i));
        }

        frame.getContentPane().add(sliderPanel, BorderLayout.EAST);
    }

    private JSlider createSlider(int top, int left, int bottom, int right) {
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL);
        slider.setMajorTickSpacing(ContextGUI.getMAJOR_TICK_SPACING());
        slider.setMinorTickSpacing(ContextGUI.getMINOR_TICK_SPACING());

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);

        slider.setPreferredSize(new Dimension(ContextGUI.ContextSupplier.getWIDTH(),
                                              ContextGUI.ContextSupplier.getHEIGHT()));

        slider.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));

        return slider;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        for(var supplierSpeed : sliders) {
           System.out.println(supplierSpeed.getValue());
        }
    }
}
package ru.nsu.org.mikhalev.view.count_detail_factory;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.storage.MotorStorage;
import ru.nsu.org.mikhalev.view.observer.Observer;

import javax.swing.*;

public class DetailMotorStorage extends JLabel implements Observer  {
    public DetailMotorStorage(@NotNull MotorStorage motorStorage) {
        setText("Motor storage: 0");
        setBounds (1050, 375, 150, 20);
        motorStorage.registerObserver(this);
    }

    @Override
    public void notification(String message,  Integer count){
        setText("Motor storage: " + message);
    }
}

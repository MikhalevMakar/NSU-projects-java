package ru.nsu.org.mikhalev.view.detail_storage;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.storage.MotorStorage;
import ru.nsu.org.mikhalev.view.observer.Observer;

import javax.swing.*;

public class DetailMotorStorage extends JLabel implements Observer  {
    public DetailMotorStorage(@NotNull MotorStorage motorStorage) {
        setText("Motor storage: 0");
        setBounds (1087, 375, 150, 20);
        motorStorage.registerObserver(this);
    }

    @Override
    public void notification(String message){
        setText("Motor storage: " + message);
    }
}

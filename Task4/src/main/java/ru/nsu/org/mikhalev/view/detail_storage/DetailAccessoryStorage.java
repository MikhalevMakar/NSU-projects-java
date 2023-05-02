package ru.nsu.org.mikhalev.view.detail_storage;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.storage.AccessoryStorage;
import ru.nsu.org.mikhalev.view.observer.Observer;

import javax.swing.*;

public class DetailAccessoryStorage extends JLabel implements Observer {
    public DetailAccessoryStorage(@NotNull AccessoryStorage accessoryStorage) {
        setText("Accessory storage: 0");
        setBounds(1050, 400, 150, 20);
        accessoryStorage.registerObserver(this);
    }

    @Override
    public void notification(String message,  Integer count){
        setText("Accessory storage: " + message);
    }
}
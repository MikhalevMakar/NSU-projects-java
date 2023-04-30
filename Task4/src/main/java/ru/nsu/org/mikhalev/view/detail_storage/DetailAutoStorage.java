package ru.nsu.org.mikhalev.view.detail_storage;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.view.observer.Observer;

import javax.swing.*;

public class DetailAutoStorage extends JLabel implements Observer {
    public DetailAutoStorage(@NotNull AutoStorage autoStorage) {
        setText("Auto storage: 0");
        setBounds (1100, 425, 150, 20);
        autoStorage.registerObserver(this);
    }

    @Override
    public void notification(String message){
        setText("Auto storage: " + message);
    }
}

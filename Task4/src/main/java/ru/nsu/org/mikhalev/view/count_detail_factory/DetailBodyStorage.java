package ru.nsu.org.mikhalev.view.count_detail_factory;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.storage.BodyStorage;
import ru.nsu.org.mikhalev.view.observer.Observer;

import javax.swing.*;

public class DetailBodyStorage extends JLabel implements Observer  {
    public DetailBodyStorage(@NotNull BodyStorage bodyStorage) {
        setText("Body storage: 0");
        setBounds (1050, 450, 150, 20);
        bodyStorage.registerObserver(this);
    }

    @Override
    public void notification(String message,  Integer count){
        setText("Body storage: " + message);
    }
}
package ru.nsu.org.mikhalev.view.count_detail_factory;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.ControllerAutoStorage;
import ru.nsu.org.mikhalev.view.observer.Observer;

import javax.swing.*;

public class ViewCountTaskThread extends JLabel implements Observer {
    public ViewCountTaskThread(@NotNull ControllerAutoStorage controllerAutoStorage) {
            setText("Count task in queue: 0");
            setBounds(1050, 350, 150, 20);
            controllerAutoStorage.registerObserver(this);
        }

        @Override
        public void notification(String message,  Integer count){
            System.out.println ("notification " + count);
            setText("Count task in queue " + count);
        }
}

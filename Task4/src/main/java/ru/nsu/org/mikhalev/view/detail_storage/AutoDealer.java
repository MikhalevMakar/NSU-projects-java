package ru.nsu.org.mikhalev.view.detail_storage;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.dealer.Dealer;
import ru.nsu.org.mikhalev.view.observer.Observer;

import javax.swing.*;

public class AutoDealer extends JLabel implements Observer {
    public AutoDealer(@NotNull Dealer dealer) {
        setText("<html><b>Count auto: 0 <b></html>");
        setBounds(1050, 350, 150, 20);
        dealer.registerObserver(this);
    }

    @Override
    public void notification(String message,  Integer count) {
        setText("<html><b>Count auto: </b>" + count + "</html>");
    }
}

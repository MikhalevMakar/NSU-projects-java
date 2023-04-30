package ru.nsu.org.mikhalev.view;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.Factory;
import ru.nsu.org.mikhalev.factory.dealer.Dealer;
import ru.nsu.org.mikhalev.view.detail_storage.DetailAccessoryStorage;
import ru.nsu.org.mikhalev.view.detail_storage.DetailAutoStorage;
import ru.nsu.org.mikhalev.view.detail_storage.DetailBodyStorage;
import ru.nsu.org.mikhalev.view.detail_storage.DetailMotorStorage;

import javax.swing.*;
import java.awt.*;

public class GenerateMainMenu {
    private DetailAutoStorage detailAutoStorage;
    private DetailMotorStorage detailMotorStorage;
    private DetailAccessoryStorage detailAccessoryStorage;
    private DetailBodyStorage detailBodyStorage;

    private JFrame frame;
    public GenerateMainMenu(String link, @NotNull Factory factory) {
        frame = new JFrame("Жигуль да копейка");

        ImageIcon imageIcon = new ImageIcon(link);

        JLabel label = new JLabel(imageIcon);

       frame.add(label, BorderLayout.WEST);

        detailAutoStorage = new DetailAutoStorage(factory.getAutoStorage());
        detailAccessoryStorage = new DetailAccessoryStorage(factory.getAccessoryStorage());
        detailMotorStorage = new DetailMotorStorage(factory.getMotorStorage());
        detailBodyStorage = new DetailBodyStorage(factory.getBodyStorage());

        addDetailAutoStorageFrame();

        this.createNewComponents(factory.getDealer());


        this.sittingFrame();
    }

    private void createNewComponents(@NotNull Dealer dealer) {
        Chat chat = new Chat(frame);
        dealer.registerObserver(chat);
        new SupplierSpeedSlider(frame);
    }

    private void sittingFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(ContextGUI.getWIDTH(), ContextGUI.getHEIGHT());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void addDetailAutoStorageFrame() {
        frame.add(detailAutoStorage, BorderLayout.EAST);
        frame.add(detailAccessoryStorage, BorderLayout.EAST);
        frame.add(detailMotorStorage, BorderLayout.EAST);
        frame.add(detailBodyStorage, BorderLayout.EAST);
    }
}

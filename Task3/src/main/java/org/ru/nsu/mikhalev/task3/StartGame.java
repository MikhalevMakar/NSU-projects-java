package org.ru.nsu.mikhalev.task3;

import org.ru.nsu.mikhalev.task3.controller.GameController;
import org.ru.nsu.mikhalev.task3.model.LeaderBoard;
import javax.swing.*;

public class StartGame extends JFrame {
    public static void main(String[] args) {
        GameController.launchMenu();
    }
}
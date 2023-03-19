package org.ru.nsu.mikhalev.task3.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LeaderBoard extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<String[]> players = new ArrayList<>();
    private String playerStatistics = "../Task3/src/main/resources/PlayerStatistics.txt";
    public LeaderBoard() {
        setTitle("Таблица результатов");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        //Создаем модель таблицы
        model = new DefaultTableModel();
        model.addColumn("Имя");
        model.addColumn("Очки");

        //Добавляем предыдущих игроков в таблицу
        players = getPreviousPlayers();
        for (String[] player : players) {
            model.addRow(player);
        }
        //Создаем таблицу
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible (true);
    }
    private ArrayList<String[]> getPreviousPlayers() {
        return players;
    }
    public void addPlayer(String name, int score)  {
        String[] player = new String[]{name, Integer.toString(score)};
        model.addRow(player);
        players.add(player);
        try(FileOutputStream fileOutputStream = new FileOutputStream(playerStatistics);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream)) {
            oos.writeObject(players);
        } catch (IOException e) {
            System.err.println("Result table not found");
        }
    }
}
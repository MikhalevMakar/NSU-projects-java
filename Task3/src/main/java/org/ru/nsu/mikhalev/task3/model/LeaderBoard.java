package org.ru.nsu.mikhalev.task3.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;

public class LeaderBoard extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<String[]> players;
    private String playerStatistics = "../Task3/src/main/resources/PlayerStatistics.txt";
    public LeaderBoard() {
        setTitle("Таблица результатов");
        setSize(400, 300);
        model = new DefaultTableModel();
        model.addColumn("Имя");
        model.addColumn("Очки");
        players = getPreviousPlayers();
    }
    private ArrayList<String[]> getPreviousPlayers() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream (playerStatistics))) {
            players = (ArrayList<String[]>)ois.readObject();
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return players;
    }
    public void fillTablePlayer() {
        for (String[] player : players) {
            model.addRow(player);
        }
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
    public void addPlayer(String name, int score)  {
        String[] player = new String[]{name, Integer.toString(score)};
        players.add(player);
        players.sort(Comparator.comparing((Function<String[], String>)array-> String.valueOf(Integer.parseInt(array[1]))).reversed());
        try(FileOutputStream fileOutputStream = new FileOutputStream(playerStatistics);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream)) {
            oos.writeObject(players);
        } catch(IOException e) {
            System.err.println("Result table not found");
        }
    }
}
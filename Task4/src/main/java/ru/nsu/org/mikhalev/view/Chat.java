package ru.nsu.org.mikhalev.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Chat extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;

    public Chat() {
        setSize(500, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new BorderLayout ());
        inputField = new JTextField();
        inputField.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                chatArea.append("You: " + message + "\n");
                inputField.setText("");
            }
        });

        inputPanel.add(inputField, BorderLayout.CENTER);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                chatArea.append("You: " + message + "\n");
                inputField.setText("");
            }
        });

        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
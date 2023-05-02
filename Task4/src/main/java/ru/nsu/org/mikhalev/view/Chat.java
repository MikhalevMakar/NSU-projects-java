package ru.nsu.org.mikhalev.view;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.Factory;
import ru.nsu.org.mikhalev.view.observer.Observer;

import javax.swing.*;
import java.awt.*;

public class Chat implements Observer {
    private JTextArea chatArea = new JTextArea();
    private JTextField inputField;
    private String helpMessage = "Bot: 4 splitters set the speed: SUPPLIERS, DEALER\n";

    public Chat(@NotNull JFrame frame, Factory factory) {
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();

        inputField.addActionListener(e -> {
            String message = inputField.getText();
            chatArea.append("You: " + message + "\n");

            if(message.compareTo("help") == 0) {
                chatArea.append (helpMessage);
            } else if(message.compareTo("start") == 0) {
                factory.start();
            } else if(message.compareTo("stop") == 0) {
                factory.stop();
            }
            inputField.setText("");
        });

        createSendButton();

        chatArea = new JTextArea ();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputField.add(scrollPane, BorderLayout.CENTER);

        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setLocation(ContextGUI.getWIDTH() - ContextGUI.ContextChat.getWIDTH(),
                          ContextGUI.ContextChat.getHEIGHT());

        scrollPane.setBounds(ContextGUI.ContextChat.getX(), ContextGUI.ContextChat.getY(),
                             ContextGUI.ContextChat.getWIDTH(), ContextGUI.ContextChat.getHEIGHT());

        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void createSendButton() {
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(e -> {
            String message = inputField.getText();

            chatArea.append("You: " + message + "\n");

            inputField.setText(" ");
        });
    }

    @Override
    public void notification(String message,  Integer count) {
        chatArea.setCaretColor(Color.CYAN);
        chatArea.append(message);
    }
}
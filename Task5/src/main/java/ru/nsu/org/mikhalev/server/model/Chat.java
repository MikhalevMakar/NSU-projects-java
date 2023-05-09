package ru.nsu.org.mikhalev.server.model;

import ru.nsu.org.mikhalev.server.object_serializable.Message;

import java.util.ArrayList;

public class Chat {
    private int INCREASE = 1;

    private ArrayList<Message> historyMessages = new ArrayList<>();




    public void addNewMessage(Message message) {
        historyMessages.add(message);
    }


    public Message getMessageLast() {
        return historyMessages.get(historyMessages.size() - INCREASE);
    }

}

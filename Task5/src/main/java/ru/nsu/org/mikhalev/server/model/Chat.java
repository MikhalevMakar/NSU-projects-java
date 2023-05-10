package ru.nsu.org.mikhalev.server.model;

import lombok.Getter;
import ru.nsu.org.mikhalev.server.object_serializable.Message;

import java.util.ArrayList;

public class Chat {

    private static final int INCREASE = 1;

    @Getter
    private final String nameChat;

    private ArrayList<Message> historyMessages = new ArrayList<>();

    public Chat(String nameChat) {
        this.nameChat = nameChat;
    }

    public void addNewMessage(Message message) {
        historyMessages.add(message);
    }

    public Message getMessageLast() {
        return historyMessages.get(historyMessages.size() - INCREASE);
    }
}

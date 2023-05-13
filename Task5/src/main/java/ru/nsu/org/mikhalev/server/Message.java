package ru.nsu.org.mikhalev.server;



import lombok.Getter;

import java.io.Serializable;

public class Message implements Serializable {

    @Getter
    private String sender;

    @Getter
    private String content;

    public Message(final String sender, final String content) {
        this.sender = sender;
        this.content = content;
    }
}

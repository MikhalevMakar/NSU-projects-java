package ru.nsu.org.mikhalev.server.object_serializable;

import lombok.Getter;

import java.io.Serializable;

public class Message implements Serializable {

    @Getter
    private final String date;

    @Getter
    private final String userName;


    public Message(String date, String userName) {
        this.date = date;
        this.userName = userName;
    }

}

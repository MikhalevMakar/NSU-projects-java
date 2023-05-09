package ru.nsu.org.mikhalev.server.object_serializable;

import lombok.Getter;

import java.io.Serializable;

public class Message implements Serializable {

    @Getter
    private final String date;

    @Getter
    private final String nameCommands;


    public Message(String nameCommands, String date){
        this.date = date;
        this.nameCommands = nameCommands;
    }

}

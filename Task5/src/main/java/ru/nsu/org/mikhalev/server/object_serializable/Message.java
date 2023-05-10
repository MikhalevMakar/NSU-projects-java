package ru.nsu.org.mikhalev.server.object_serializable;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Message implements Serializable {

    @Getter
    private String date;

    @Setter
    @Getter
    private String typeMessage;


    public Message(String typeMessage, String date){
        this.date = date;
        this.typeMessage = typeMessage;
    }
}

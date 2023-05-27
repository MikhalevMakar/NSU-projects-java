package ru.nsu.org.mikhalev.universal_utile_class;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Message<T> implements Serializable {

    @Getter
    private final String typeMessage;

    @Getter
    private final T content;

    @Setter
    @Getter
    private String logIn;

    public Message(String typeMessage, T content) {
        this.content = content;
        this.typeMessage = typeMessage;
    }
}
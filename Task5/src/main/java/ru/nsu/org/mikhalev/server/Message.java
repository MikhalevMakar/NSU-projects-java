package ru.nsu.org.mikhalev.server;


import lombok.Getter;

import java.io.Serializable;

public record Message<T>(@Getter String typeMessage, @Getter T content) implements Serializable {}
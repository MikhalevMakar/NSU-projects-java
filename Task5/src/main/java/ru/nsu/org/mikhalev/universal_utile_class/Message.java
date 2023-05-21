package ru.nsu.org.mikhalev.universal_utile_class;


import lombok.Getter;

import java.io.Serializable;

public record Message<T>(@Getter String typeMessage, @Getter T content) implements Serializable { }
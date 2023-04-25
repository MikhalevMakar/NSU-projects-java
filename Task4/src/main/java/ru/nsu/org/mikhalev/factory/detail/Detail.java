package ru.nsu.org.mikhalev.factory.detail;

import java.util.UUID;

public class Detail {
    protected final UUID id;
    Detail() {
        this.id = UUID.randomUUID();
    }
}

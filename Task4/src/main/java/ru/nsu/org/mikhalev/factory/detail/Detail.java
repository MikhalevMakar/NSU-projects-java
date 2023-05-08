package ru.nsu.org.mikhalev.factory.detail;

import java.util.UUID;

/*
 * { Detail } class that implements all products.
 */

public class Detail {
    protected final UUID id;
    Detail() {
        synchronized(this) {
            this.id = UUID.randomUUID();
        }
    }
}

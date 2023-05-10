package ru.nsu.org.mikhalev.server.file_management;

import lombok.Getter;
public class ServerConfiguration {

    @Getter
    private final int port;

    @Getter
    private final boolean logging;

    @Getter
    private final String format;

    ServerConfiguration(int port, boolean logging, String format) {
        this.port = port;
        this.logging = logging;
        this.format = format;
    }
}

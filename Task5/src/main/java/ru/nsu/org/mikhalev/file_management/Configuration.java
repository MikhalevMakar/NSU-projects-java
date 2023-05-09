package ru.nsu.org.mikhalev.file_management;

import lombok.Getter;
public class Configuration {
    @Getter
    private final int port;

    @Getter
    private final boolean logging;

    @Getter
    private final String format;

    Configuration(int port, boolean logging, String format) {
        this.port = port;
        this.logging = logging;
        this.format = format;
    }
}

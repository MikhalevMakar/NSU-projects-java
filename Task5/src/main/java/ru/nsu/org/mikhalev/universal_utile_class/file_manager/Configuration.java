package ru.nsu.org.mikhalev.universal_utile_class.file_manager;

import lombok.Getter;

public class Configuration {

    @Getter
    private final int port;

    @Getter
    private final boolean logging;

    @Getter
    private final String format;

    @Getter
    private final String host;

    Configuration(final int port, final boolean logging,
                  final String format, final String host) {
        this.port = port;
        this.logging = logging;
        this.format = format;
        this.host = host;
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

module ru.nsu.org.mikhalev {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires log4j;
    requires org.apache.logging.log4j;
    requires org.jetbrains.annotations;
    requires com.google.gson;
    requires commons.cli;

    exports ru.nsu.org.mikhalev.clients.aplication_main;
    exports ru.nsu.org.mikhalev.server.aplication_main;
    exports ru.nsu.org.mikhalev.clients.view;
    exports ru.nsu.org.mikhalev.clients;
    exports ru.nsu.org.mikhalev.universal_utile_class.exceptions;
    exports ru.nsu.org.mikhalev.server;
    exports ru.nsu.org.mikhalev.universal_utile_class;
    exports ru.nsu.org.mikhalev.clients.controller;
    exports ru.nsu.org.mikhalev.clients.file_management;
    exports  ru.nsu.org.mikhalev.server.file_management;

    opens ru.nsu.org.mikhalev.clients.aplication_main to
        javafx.fxml;

    opens ru.nsu.org.mikhalev.universal_utile_class.file_manager to
        com.google.gson;

    opens ru.nsu.org.mikhalev.clients.file_management to
        com.google.gson;

    opens ru.nsu.org.mikhalev.server.file_management to
        com.google.gson;

    opens ru.nsu.org.mikhalev.clients.view to
        javafx.fxml;
}
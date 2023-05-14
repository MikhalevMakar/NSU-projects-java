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
    //requires org.apache.commons;

    exports ru.nsu.org.mikhalev.clients.aplication_main;
    exports ru.nsu.org.mikhalev.server.aplication_main;

    exports ru.nsu.org.mikhalev.clients.view;

    opens ru.nsu.org.mikhalev.clients.aplication_main to
        javafx.fxml;

    opens ru.nsu.org.mikhalev.clients.view to
        javafx.fxml;
}
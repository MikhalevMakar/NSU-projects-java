package ru.nsu.org.mikhalev.factory.storage.auto_storage;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.detail.Auto;
import ru.nsu.org.mikhalev.factory.storage.Storage;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;


@Log4j2
public class AutoStorage extends Storage<Auto> {
    private final ControllerAutoStorage controllerAutoStorage;
    public AutoStorage(ControllerAutoStorage controllerAutoStorage) {
        super(Integer.parseInt(Properties_Value.STORAGE_AUTO_SIZE.getValue()));
        this.controllerAutoStorage = controllerAutoStorage;
    }

    public synchronized void addAuto(Auto auto) {
            notifyObservers(String.valueOf(details.size()), 0);
            details.add(auto);
    }

    public synchronized Auto getAuto() {
        while (details.isEmpty () && Thread.currentThread ().isAlive ()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.warn ("Interrupted Exception in auto storage");
                Thread.currentThread ().interrupt ();
                return null;
            }
        }
        Auto auto = details.remove();
        controllerAutoStorage.distributionTask(details.size());
        notifyObservers(String.valueOf(details.size()), 0);
        return auto;
    }
}
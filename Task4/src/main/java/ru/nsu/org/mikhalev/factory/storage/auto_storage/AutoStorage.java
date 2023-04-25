package ru.nsu.org.mikhalev.factory.storage.auto_storage;

import ru.nsu.org.mikhalev.factory.detail.Auto;
import ru.nsu.org.mikhalev.factory.storage.Storage;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;

public class AutoStorage extends Storage<Auto> {
    public AutoStorage() {
        super(Integer.valueOf(Properties_Value.STORAGE_AUTO_SIZE.getValue()));
    }

    public synchronized void addAuto(Auto auto) throws InterruptedException { //add exception
        details.add(auto);
    }

    public synchronized Auto getAuto() throws InterruptedException {
        Auto auto = (details.size () != 0) ? details.remove(0) : null;

        if(auto == null) {
            this.wait();
            auto = details.remove(0);
        }

        ControllerAutoStorage.isWakesUpWorkers(details.size());
        return auto;
    }
}

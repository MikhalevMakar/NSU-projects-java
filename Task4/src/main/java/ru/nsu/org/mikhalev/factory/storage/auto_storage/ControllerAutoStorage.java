package ru.nsu.org.mikhalev.factory.storage.auto_storage;

import lombok.Setter;

public class ControllerAutoStorage {
    private final AutoStorage autoStorage;
    private  int sizeStorage;
    public ControllerAutoStorage(AutoStorage autoStorage, Integer sizeStorage) {
        this.autoStorage = autoStorage;
        this.sizeStorage = sizeStorage;
    }

    public  void isWakesUpWorkers(int currentCount) {
        synchronized(autoStorage) {
            if (currentCount <= sizeStorage) {
                autoStorage.notifyAll();
            }
        }
    }
}

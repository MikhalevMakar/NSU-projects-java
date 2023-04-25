package ru.nsu.org.mikhalev.factory.storage.auto_storage;

import lombok.Setter;
import ru.nsu.org.mikhalev.factory.worker.Worker;

import java.util.LinkedList;

public class ControllerAutoStorage {
    private static LinkedList<Worker> workers = new LinkedList<>();
    @Setter
    private static int sizeStorage;

    public synchronized static void registrationWorkers(Worker worker) {
        workers.add(worker);
    }

    public static void isWakesUpWorkers(int currentCount){
        if (currentCount == sizeStorage - 1) {
            workers.notifyAll ();
        }
    }
}

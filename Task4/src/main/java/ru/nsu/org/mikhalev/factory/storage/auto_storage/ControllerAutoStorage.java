package ru.nsu.org.mikhalev.factory.storage.auto_storage;

import lombok.Setter;
import ru.nsu.org.mikhalev.factory.worker.Worker;

import java.util.LinkedList;

public class ControllerAutoStorage {
    private static LinkedList<Worker> workers = new LinkedList<>();
    @Setter
    private static int sizeStorage;

    public static void registrationWorkers(Worker worker) {
        synchronized(workers) {
            workers.add(worker);
        }
    }

    public static void isWakesUpWorkers(int currentCount) {
        synchronized(workers) {
            if (currentCount <= sizeStorage) {
                for(var worker : workers) {
                    synchronized(worker) {
                        worker.notify();
                    }
                }
            }
        }
    }
}

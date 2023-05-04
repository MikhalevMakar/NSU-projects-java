package ru.nsu.org.mikhalev.factory.worker;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.detail.*;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

import java.util.LinkedList;

@Log4j2
public class Worker implements Runnable {
    private static LinkedList<DetailStorage<? extends Detail>> storages = new LinkedList<>();
    private LinkedList<Detail> caseDetails = new LinkedList<>();
    private final AutoStorage autoStorage;

    public Worker(AutoStorage autoStorage,
                  DetailStorage<Accessory> accessoryStorage,
                  DetailStorage<Body> bodyStorage,
                  DetailStorage<Motor> motorStorage) {
        this.autoStorage = autoStorage;

        storages.add(accessoryStorage);
        storages.add(motorStorage);
        storages.add(bodyStorage);
    }

    private void requestDetails() throws InterruptedException {
        caseDetails.clear();
        for (var storage : storages) {
            Detail detail = storage.getDetail();
            caseDetails.add(detail);
        }
    }

    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (autoStorage) {
                    while (autoStorage.isFull()) {
                        autoStorage.wait();
                    }
                    requestDetails();
                    autoStorage.addAuto(new Auto(caseDetails));
                }
            } catch (InterruptedException e) {
                log.warn("Exception ExcWorker", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}



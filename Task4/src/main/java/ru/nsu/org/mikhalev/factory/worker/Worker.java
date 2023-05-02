package ru.nsu.org.mikhalev.factory.worker;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.exceptions.ExcWorker;
import ru.nsu.org.mikhalev.factory.detail.*;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.ControllerAutoStorage;

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

        ControllerAutoStorage.registrationWorkers(this);
    }

    private void requestDetails() throws InterruptedException {
        caseDetails.clear();
        for(var storage : storages) {
            Detail detail = storage.getDetail();
            if(detail == null) {
                synchronized(this) {
                    wait();
                }
                detail = storage.getDetail();
            }
            caseDetails.add(detail);
        }
    }


    @Override
    public void run() {
        try {
            while (true) {
                    if (autoStorage.isFull ()) {
                        synchronized(this) {
                            this.wait();
                        }
                    }
                synchronized(caseDetails) {
                    requestDetails();
                    synchronized(autoStorage) {
                        autoStorage.addAuto(new Auto(caseDetails));
                        autoStorage.notify();
                    }
                }
            }
        } catch (InterruptedException e) {
            log.warn("Exception ExcWorker", e);
            Thread.currentThread().interrupt();
            throw new ExcWorker("Interrupted!");
        }
    }
}



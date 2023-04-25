package ru.nsu.org.mikhalev.factory.worker;

import ru.nsu.org.mikhalev.factory.detail.*;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.ControllerAutoStorage;

import java.util.LinkedList;

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

    private synchronized void requestDetails() throws InterruptedException {
        caseDetails.clear();
        for(var storage : storages) {
            Detail detail = storage.getDetail();
            while(detail == null) {
                this.wait();
                detail = storage.getDetail();
            }
            caseDetails.add(detail);
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                requestDetails();
                if(autoStorage.isFull()) {
                    this.wait();
                }
                autoStorage.addAuto(new Auto(caseDetails));
                autoStorage.notify();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

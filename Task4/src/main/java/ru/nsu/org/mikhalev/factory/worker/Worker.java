package ru.nsu.org.mikhalev.factory.worker;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.detail.*;
import ru.nsu.org.mikhalev.factory.storage.MotorStorage;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;
import ru.nsu.org.mikhalev.thread_pool.Task;

import java.util.LinkedList;

@Log4j2
public class Worker implements Task {
    private static final LinkedList<DetailStorage<? extends Detail>> storages = new LinkedList<>();
    private final LinkedList<Detail> caseDetails = new LinkedList<>();
    private final AutoStorage autoStorage;

    public Worker(AutoStorage autoStorage) {
        this.autoStorage = autoStorage;
    }

    public static void registrationStorages(DetailStorage<Accessory> accessoryStorage,
                                            DetailStorage<Body> bodyStorage,
                                            MotorStorage motorStorage) {
        storages.add(accessoryStorage);
        storages.add(bodyStorage);
        storages.add(motorStorage);
    }
    private void requestDetails() throws InterruptedException {
        caseDetails.clear();
        for (var storage : storages) {
            Detail detail = storage.getDetail();
            caseDetails.add(detail);
        }
    }

    @Override
    public void execute() {
        try {
            synchronized (autoStorage) {
                while (autoStorage.isFull ()) {
                    //System.out.println ("autoStorage is full");
                    autoStorage.wait();
                }

                //System.out.println ("autoStorage is not full");
                requestDetails();
                autoStorage.addAuto (new Auto (caseDetails));
            }
        } catch (InterruptedException e) {
            log.warn("Exception ExcWorker", e);
        }
    }
}



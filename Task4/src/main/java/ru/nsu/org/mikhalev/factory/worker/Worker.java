package ru.nsu.org.mikhalev.factory.worker;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.detail.*;
import ru.nsu.org.mikhalev.factory.storage.MotorStorage;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;
import ru.nsu.org.mikhalev.thread_pool.Task;

import java.util.LinkedList;

/*
 * { Worker } class takes parts from the warehouse and assembles the machine.
 *
 * Further makes delivery to the warehouse auto.
 *
 * The class can suspend its work in case there are no parts
 * for production or the vehicle warehouse is full.
 *
 * The class has a dependency with storages and autoStorage.
 */

@Log4j2
public class Worker implements Task {
    private static final LinkedList<DetailStorage<? extends Detail>> storages = new LinkedList<>();
    private final LinkedList<Detail> caseDetails = new LinkedList<>();
    private final AutoStorage autoStorage;

    /*
     * Construct
     *
     * @param AutoStorage
     */
    public Worker(AutoStorage autoStorage) {
        this.autoStorage = autoStorage;
    }

    /*
     * This method registration storages
     *
     * @param DetailStorage<Accessory>, DetailStorage<Body>, MotorStorage
     * @return void
     */
    public static void registrationStorages(DetailStorage<Accessory> accessoryStorage,
                                            DetailStorage<Body> bodyStorage,
                                            MotorStorage motorStorage) {
        storages.add(accessoryStorage);
        storages.add(bodyStorage);
        storages.add(motorStorage);
    }

    /*
     * This method request details
     * @param void
     *  @return void
     */
    private void requestDetails() {
        caseDetails.clear();
        for (var storage : storages) {
            Detail detail = storage.getDetail();
            caseDetails.add(detail);
        }
    }

    /*
     * This method performs tasks that is takes and collects cars
     *
     *@param void
     * @return void
     */
    @Override
    public void execute() {
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
        }
    }
}



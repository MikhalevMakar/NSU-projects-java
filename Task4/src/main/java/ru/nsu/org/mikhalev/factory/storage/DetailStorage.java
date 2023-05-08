package ru.nsu.org.mikhalev.factory.storage;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.detail.Detail;

/*
 * { DetailStorage } class is fundamental to MotorStorage, AccessoryStorage, BodyStorage.
 */


@Log4j2
public class DetailStorage<T extends Detail> extends Storage<T>  {
    public DetailStorage(int sizeStorage) {
        super(sizeStorage);
    }

    /*
     * This method is called by suppliers and they add parts.
     * In case the warehouse is full then the warehouse fills.
     *
     * @param generic T
     * @return void
     */
    public void addDetail(T detail) {
        log.info("add detail: " + detail);

        if(detail == null) {
            log.warn("Detail is null");
            return;
        }

        synchronized (details) {
            while (isFull() && Thread.currentThread().isAlive()) {
                try {
                    log.info("synchronized(details) and call wait");
                    this.details.wait();
                } catch (InterruptedException e) {
                    log.warn("InterruptedException in method addDetail " + this.getClass());
                    return;
                }
            }

            details.add(detail);
            details.notifyAll();
            this.notifyObservers(String.valueOf(this.details.size()), START_SIZE_STORAGE);
        }
    }


    /*
     * This method is invoked by workers.
     * If the warehouse is empty, they fall asleep workers.
     *
     * @param void
     * @return generic T
     */
    public T getDetail() {
        log.info("Call method getDetail");
        T detail = null;

        synchronized (details) {
            while (details.isEmpty()) {
                try {
                    log.info("synchronized(details) and call wait");
                    details.wait();
                } catch (InterruptedException e) {
                    log.warn("InterruptedException in method getDetail " + this.getClass());
                    return detail;
                }
            }

            detail = details.remove();
            details.notifyAll();
        }

        this.notifyObservers(String.valueOf(this.details.size()), START_SIZE_STORAGE);
        return detail;
    }
}

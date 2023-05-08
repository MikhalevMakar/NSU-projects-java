package ru.nsu.org.mikhalev.factory.storage;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.detail.Detail;

@Log4j2
public class DetailStorage<T extends Detail> extends Storage<T>  {
    public DetailStorage(int sizeStorage) {
        super(sizeStorage);
    }

    public void addDetail(T detail) {
        if(detail == null) {
            log.warn("Detail is null");
            return;
        }

        synchronized (details) {
            while (isFull() && Thread.currentThread().isAlive()) {
                try {
                    this.details.wait();
                } catch (InterruptedException e) {
                    log.warn("InterruptedException in method addDetail " + this.getClass());
                    return;
                }
            }

            details.add(detail);
            details.notifyAll();
            this.notifyObservers(String.valueOf(this.details.size()), 0);
        }
    }

    public T getDetail() {
        T detail = null;
        synchronized (details) {
            while (details.isEmpty()) {
                try {
                    details.wait();
                } catch (InterruptedException e) {
                    log.warn("InterruptedException in method getDetail " + this.getClass());
                    return detail;
                }
            }

            detail = details.remove();
            details.notifyAll();
        }
        this.notifyObservers(String.valueOf(this.details.size()), 0);
        return detail;
    }
}

package ru.nsu.org.mikhalev.factory.storage;

import ru.nsu.org.mikhalev.factory.detail.Detail;

public class DetailStorage<T extends Detail> extends Storage<T>  {
    public DetailStorage(int sizeStorage) {
        super(sizeStorage);
    }

    public void addDetail(T detail) {
        synchronized (details) {
            details.notifyAll();
            details.add(detail);
            this.notifyObservers(String.valueOf(this.details.size()), 0);
        }
    }

    public T getDetail() throws InterruptedException{
        T detail;
        synchronized(details) {
            while (details.isEmpty()) {
                details.wait();
            }

            detail = details.removeFirst();


            if (details.size() < this.sizeStorage) {
                details.notifyAll();
            }
        }

        this.notifyObservers(String.valueOf(this.details.size()), 0);
        return detail;
    }
}

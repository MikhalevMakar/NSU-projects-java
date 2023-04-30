package ru.nsu.org.mikhalev.factory.storage;

import ru.nsu.org.mikhalev.factory.detail.Detail;
import ru.nsu.org.mikhalev.factory.observable.Observable;
import ru.nsu.org.mikhalev.factory.suppliers.DetailSupplier;
import ru.nsu.org.mikhalev.view.observer.Observer;

import java.util.ArrayList;
import java.util.LinkedList;

public class DetailStorage<T extends Detail> extends Storage<T>  {
    private ArrayList<DetailSupplier<T>> detailSuppliers;

    public DetailStorage(int sizeStorage, int countSupplier) {
        super(sizeStorage);
        detailSuppliers = new ArrayList<> (countSupplier);
    }

    public synchronized void registrationSupplier(DetailSupplier<T> detailSupplier) {
        detailSuppliers.add(detailSupplier);
    }

    public synchronized void addDetail(T detail) {
        if (!isFull()) {
            details.add(detail);
        }
    }

    public synchronized T getDetail() throws InterruptedException {
        T detail = null;
        synchronized(details) {
            if (details.size() == 0) {
                this.wait();
            }
           detail = details.removeFirst();
        }

        if (this.details.size() <= this.sizeStorage) {
            synchronized(detailSuppliers) {
                for (var supplier : detailSuppliers) {
                    synchronized(supplier) {
                        supplier.notify();
                    }
                }
            }
        }
        this.notifyObservers(String.valueOf(this.details.size()));
        return detail;
    }
}

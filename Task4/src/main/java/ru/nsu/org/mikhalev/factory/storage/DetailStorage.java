package ru.nsu.org.mikhalev.factory.storage;

import ru.nsu.org.mikhalev.factory.detail.Detail;
import ru.nsu.org.mikhalev.factory.suppliers.DetailSupplier;

import java.util.ArrayList;

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
            this.notifyObservers(String.valueOf(this.details.size()), 0);
            details.add(detail);
        }
    }

    public synchronized T getDetail() throws InterruptedException {
        T detail;
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
        this.notifyObservers(String.valueOf(this.details.size()), 0);
        return detail;
    }
}

package ru.nsu.org.mikhalev.factory.storage;

import ru.nsu.org.mikhalev.factory.detail.Detail;
import ru.nsu.org.mikhalev.factory.suppliers.DetailSupplier;

import java.util.ArrayList;

public class DetailStorage<T extends Detail> extends Storage<T> {
    private ArrayList<DetailSupplier<T>> detailSuppliers;

    public DetailStorage(int sizeStorage, int countSupplier) {
        super (sizeStorage);
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

    public synchronized T getDetail() throws InterruptedException{
            if (details.size () == 0)
                this.wait();

            T detail = details.remove (0);

            if (details.size () == sizeStorage - 1)
                synchronized(detailSuppliers) {
                    detailSuppliers.notifyAll();
                }
            return detail;
    }
}

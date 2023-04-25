package ru.nsu.org.mikhalev.storage;

import ru.nsu.org.mikhalev.detail.Detail;
import ru.nsu.org.mikhalev.suppliers.DetailSupplier;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DetailStorage<T extends Detail> {
    private final int sizeStorage;
    private ArrayList<DetailSupplier<T>> detailSuppliers;
    private List<T> details = new LinkedList<>();

    public DetailStorage(int sizeStorage, int countSupplier) {
        this.sizeStorage = sizeStorage;
        detailSuppliers = new ArrayList<>(countSupplier);
    }

    public void addSupplier(DetailSupplier<T> detailSupplier) {
        detailSuppliers.add(detailSupplier);
    }
    public synchronized T getDetail() {
        return (details.size() != 0)  ? details.remove(0) : null;
    }

    public synchronized void addDetail(T detail) {
        if(!isFull()) {
            details.add(detail);
        }
        detailSuppliers.notifyAll();
    }

    public synchronized boolean isFull() {
        return details.size() >= sizeStorage;
    }
}

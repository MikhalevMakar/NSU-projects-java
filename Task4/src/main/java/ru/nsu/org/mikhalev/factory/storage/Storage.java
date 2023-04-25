package ru.nsu.org.mikhalev.factory.storage;

import ru.nsu.org.mikhalev.factory.detail.Detail;
import ru.nsu.org.mikhalev.factory.worker.Worker;

import java.util.LinkedList;
import java.util.List;

public class Storage<T extends Detail> {
    protected final int sizeStorage;
    protected List<T> details = new LinkedList<>();

    public Storage(int sizeStorage) {
        this.sizeStorage = sizeStorage;
    }

    public synchronized boolean isFull() {
        return details.size() >= sizeStorage;
    }
}

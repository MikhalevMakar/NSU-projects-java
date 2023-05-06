package ru.nsu.org.mikhalev.factory.suppliers;

import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.detail.Detail;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

public class DetailSupplier <T extends Detail> implements Runnable {
    @Setter
    private Integer time = 50;
    private final @NotNull Class<T> clazz;
    private final DetailStorage<T> detailStorage;

    public DetailSupplier(DetailStorage<T> detailStorage, Class<T> clazz)  {
        this.detailStorage = detailStorage;
        this.clazz = clazz;
    }

    @SneakyThrows
    public synchronized T create() {
        return clazz.getDeclaredConstructor().newInstance();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (detailStorage) {
                    while (detailStorage.isFull()) {
                        detailStorage.wait();
                    }
                    detailStorage.addDetail(create());
                    detailStorage.notifyAll();
                }

                Thread.sleep(time);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
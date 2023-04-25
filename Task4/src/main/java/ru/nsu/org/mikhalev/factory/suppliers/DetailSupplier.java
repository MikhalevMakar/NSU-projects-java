package ru.nsu.org.mikhalev.factory.suppliers;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.detail.Detail;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

public class DetailSupplier <T extends Detail> implements Runnable {
    private final Integer time = 10;
    private @NotNull Class<T> clazz;
    private final DetailStorage<T> detailStorage;

    public DetailSupplier(DetailStorage<T> detailStorage, Class<T> clazz)  {
        this.detailStorage = detailStorage;
        this.clazz = clazz;
    }

    @SneakyThrows
    synchronized public T create() {
        return clazz.getDeclaredConstructor().newInstance();
    }

    @Override
    public synchronized void run() {
        synchronized(detailStorage) {
            detailStorage.registrationSupplier(this);

            while (true) {
                try {

                    if (!detailStorage.isFull ()) {
                        detailStorage.addDetail (create ());
                        detailStorage.notify ();
                    } else {
                        this.wait ();
                    }

                    Thread.sleep (time);

                } catch (InterruptedException e) {
                    throw new RuntimeException (e);
                }
            }
        }
    }
}
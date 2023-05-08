package ru.nsu.org.mikhalev.factory.suppliers;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.factory.detail.Detail;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

import java.lang.reflect.InvocationTargetException;

@Log4j2
public class DetailSupplier <T extends Detail> implements Runnable {
    @Setter
    private Integer time = 50;
    private final @NotNull Class<T> clazz;
    private final DetailStorage<T> detailStorage;

    public DetailSupplier(DetailStorage<T> detailStorage, Class<T> clazz)  {
        this.detailStorage = detailStorage;
        this.clazz = clazz;
    }


    public synchronized T create() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return clazz.getDeclaredConstructor().newInstance();
    }

    @Override
    public void run(){
        while (!Thread.currentThread ().isInterrupted ()) {
            try {
                synchronized (detailStorage) {
                    try {
                        detailStorage.addDetail(create());
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException e) {
                        log.info("Exception in " + this.getClass() + " method run().");
                        return;
                    }
                    detailStorage.notifyAll();
                }

                Thread.sleep(time);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
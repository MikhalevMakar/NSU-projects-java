package ru.nsu.org.mikhalev.factory.suppliers;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.exceptions.ExcNewInstance;
import ru.nsu.org.mikhalev.factory.detail.Detail;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

import java.lang.reflect.InvocationTargetException;


/*
 * { DetailSupplier } class creates parts and sends them to the storages
 *
 * The class has dependencies with storages and details
 */

@Log4j2
public class DetailSupplier <T extends Detail> implements Runnable {
    @Setter
    private Integer time = 50;
    private final @NotNull Class<T> clazz;
    private final DetailStorage<T> detailStorage;

    /*
     * Construct
     *
     * @param DetailStorage<T>, Class<T>
     */
    public DetailSupplier(DetailStorage<T> detailStorage, Class<T> clazz)  {
        this.detailStorage = detailStorage;
        this.clazz = clazz;
    }

    /*
     * Create new object
     *
     * @param void
     * @return generic T extends Detail
     */
    public synchronized T create()  {
        log.info("Create new object");
        try {

            return clazz.getDeclaredConstructor().newInstance();

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.info("Exception in " + this.getClass() + " method create()");
            throw new ExcNewInstance("Exception create object");
        }
    }

    /*
     * Parts delivery at the storage facility
     *
     * @param void
     * @return void
     */
    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (detailStorage) {
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
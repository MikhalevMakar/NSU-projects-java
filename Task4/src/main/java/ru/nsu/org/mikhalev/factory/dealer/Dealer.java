package ru.nsu.org.mikhalev.factory.dealer;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.observable.Observable;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;
import ru.nsu.org.mikhalev.view.observer.Observer;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Boolean.*;


/*
 * Dealer do pick up cars from the warehouse of cars.
 *
 * Also implements two interfaces
 *
 * Dealer has dependencies with autoStorage and manages it
 */


@Log4j2
public class Dealer implements Runnable, Observable {

    private final LinkedList<Observer> observers = new LinkedList<>();

    private final AutoStorage autoStorage;

    /*
     * Initial speed of the dealer.
    */

    @Setter
    private int time = 50;

    private final AtomicInteger countFinishedAuto = new AtomicInteger();

    /*
     * Construct initialize auto storage.
     *
     * @param AutoStorage
     */

    public Dealer(AutoStorage autoStorage) {
        this.autoStorage = autoStorage;
    }

    /*
     * Every once in a while a request for automobiles occurs.
     * In the case of an interrupt there is an exit from the function,
     * this is the only way to exit the function.
     *
     * @param void
     * @return void
     */

    @Override
    public void run() {
        log.info("Start work dealer, method run");
        String message;
        while (Thread.currentThread().isAlive()) {
            try {
                synchronized (autoStorage) {
                     message = String.valueOf(autoStorage.getAuto().getId());
                }

                notifyObservers(message + "\n", countFinishedAuto.incrementAndGet());

                if (TRUE.equals(valueOf(Properties_Value.LOG_SALE.getValue()))) {
                    log.info(message);
                }

                Thread.sleep(time);
            } catch (InterruptedException e) {
                log.warn("Interrupted Exception in auto storage\n");
                return;
            }
        }
    }

    /*
     * Register objects for further updates.
     *
     * @param Observer
     * @return void
     */

    @Override
    public void registerObserver(Observer o){
        observers.add(o);
    }


    /*
     * Updating values on the screen.
     *
     * @param String, Integer
     * @return void
     */

    @Override
    public void notifyObservers(String message, Integer count) {
        for(Observer observer : observers) {
                observer.notification(message, count);
        }
    }
}


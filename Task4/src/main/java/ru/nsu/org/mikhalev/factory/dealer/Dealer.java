package ru.nsu.org.mikhalev.factory.dealer;

import ru.nsu.org.mikhalev.factory.observable.Observable;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.view.observer.Observer;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Dealer implements Runnable, Observable {
    private LinkedList<Observer> observers = new LinkedList<>();
    private final AutoStorage autoStorage;
    private final int time = 0;
    private AtomicInteger countFinishedAuto = new AtomicInteger();
    public Dealer(AutoStorage autoStorage) {
        this.autoStorage = autoStorage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (autoStorage) {
                    String message  = String.valueOf(autoStorage.getAuto().getId());
                    notifyObservers("Auto ID"  + message + "\n");
                    //System.out.println("Auto ID - " + message + " " + countFinishedAuto.incrementAndGet ());
                }
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void registerObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void notifyObservers(String message){
        for(Observer observer : observers) {
            observer.notification(message);
        }
    }
}


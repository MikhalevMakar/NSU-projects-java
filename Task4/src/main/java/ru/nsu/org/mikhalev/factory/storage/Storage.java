package ru.nsu.org.mikhalev.factory.storage;

import ru.nsu.org.mikhalev.factory.detail.Detail;
import ru.nsu.org.mikhalev.factory.observable.Observable;
import ru.nsu.org.mikhalev.view.observer.Observer;

import java.util.LinkedList;

/*
 * The warehouse class implements the Observable interface.
 * This class will inherit AccessoryStorage, BodyStorage, MotorStorage, DetailStorage.
 *
 */

public class Storage<T extends Detail> implements Observable {

    private final LinkedList<Observer> observers = new LinkedList<>();

    protected final int sizeStorage;

    protected final LinkedList<T> details = new LinkedList<>();

    protected static final int START_SIZE_STORAGE = 0;


    public Storage(int sizeStorage) {
        this.sizeStorage = sizeStorage;
    }

    public boolean isFull() {
        synchronized (details) {
            return details.size () >= sizeStorage;
        }
    }

    @Override
    public void registerObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void notifyObservers(String message, Integer count){
        for(Observer observer : observers) {
            observer.notification(message, START_SIZE_STORAGE);
        }
    }
}

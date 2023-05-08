package ru.nsu.org.mikhalev.factory.observable;

import ru.nsu.org.mikhalev.view.observer.Observer;

/*
 *  interface Observable is used to implement the guy's: Observable, Observer.
 */

public interface Observable {
    void registerObserver(Observer o);
    void notifyObservers(String message, Integer count);
}

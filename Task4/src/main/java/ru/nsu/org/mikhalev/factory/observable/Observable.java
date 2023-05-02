package ru.nsu.org.mikhalev.factory.observable;

import ru.nsu.org.mikhalev.view.observer.Observer;

public interface Observable {
    void registerObserver(Observer o);
    void notifyObservers(String message, Integer count);
}

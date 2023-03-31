package ru.nsu.org.mikhalev.model;

import ru.nsu.org.mikhalev.view.tetris_area.Observer;

public interface Observable {
    void registerObserver(Observer o);
    void notifyObservers();
}

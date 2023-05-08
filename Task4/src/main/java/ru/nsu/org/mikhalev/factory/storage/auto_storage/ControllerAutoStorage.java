package ru.nsu.org.mikhalev.factory.storage.auto_storage;

import lombok.Getter;
import ru.nsu.org.mikhalev.factory.observable.Observable;
import ru.nsu.org.mikhalev.factory.worker.Worker;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;
import ru.nsu.org.mikhalev.thread_pool.*;
import ru.nsu.org.mikhalev.view.observer.Observer;

import java.util.LinkedList;


/*
 * { ControllerAutoStorage } class manages the auto storage.
 * Transmits information to the workers.
 */

public class ControllerAutoStorage implements Observable {

    private final LinkedList<Observer> observers = new LinkedList<>();

    @Getter
    private final AutoStorage autoStorage;

    private final ThreadPool threadPool = new ThreadPool();

    private  final int sizeStorage;

    private static final double FILLING_PERCENTAGE = 0.8;


    public ControllerAutoStorage(Integer sizeStorage) {
        this.autoStorage = new AutoStorage(this);
        this.sizeStorage = sizeStorage;

        createThreadPool();
    }

    private void createThreadPool() {
        for(int i = 0; i < Integer.parseInt(Properties_Value.WORKERS.getValue()); ++i) {
            threadPool.addTaskExecute(new TaskExecute());
        }
    }


    /*
     * Launch  ThreadPool
     *
     * @param void
     * @return void
     */
    public void start() {
        threadPool.start();
        int START_SIZE_QUEUE = 0;
        distributionTask(START_SIZE_QUEUE);
    }

    public void end() {
        threadPool.end();
    }

    private void addNewTask() {
        synchronized (autoStorage) {
            threadPool.addTask(new Worker(autoStorage));
        }
    }


    /*
     * This method add new task to queue.
     *
     * @param int
     * @return void
     */
    public void distributionTask(int currentCount) {
        notifyObservers("", threadPool.getSizeQueueTask());
        synchronized(autoStorage) {
            autoStorage.notifyAll();

            if (currentCount < sizeStorage) {
                int currentSizeAutoStorage = threadPool.getSizeQueueTask();
                int sizeFillingStorage = (int) (FILLING_PERCENTAGE * sizeStorage);
                for(int i = 0; i < sizeFillingStorage - currentSizeAutoStorage - currentCount; ++i) {
                    addNewTask ();
                }
            }
        }
    }

    public void registerObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void notifyObservers(String message, Integer count) {
        for(Observer observer : observers) {
            observer.notification(message, count);
        }
    }
}
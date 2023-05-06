package ru.nsu.org.mikhalev.factory.storage.auto_storage;

import ru.nsu.org.mikhalev.factory.worker.Worker;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;
import ru.nsu.org.mikhalev.thread_pool.*;


public class ControllerAutoStorage {
    private final AutoStorage autoStorage;
    private final ThreadPool threadPool = new ThreadPool();
    private  final int sizeStorage;
    private static final double fillingPercentage = 0.8;
    public ControllerAutoStorage(AutoStorage autoStorage,
                                 Integer sizeStorage) {
        this.autoStorage = autoStorage;
        this.sizeStorage = sizeStorage;

        createThreadPool();
    }
    private void createThreadPool() {
        for(int i = 0; i < Integer.parseInt(Properties_Value.WORKERS.getValue()); ++i) {
            threadPool.addTaskExecute(new TaskExecute());
        }
    }

    public void start() {
        threadPool.start();
        distributionTask(0);
    }

    public void end() {
        threadPool.end();
    }

    private void addNewTask() {
        synchronized (autoStorage) {
            threadPool.addTask(new Worker(autoStorage));
        }
    }

    public void distributionTask(int currentCount) {
        synchronized(autoStorage) {
            if (currentCount < sizeStorage) {

                autoStorage.notifyAll ();

                //distributionTask(currentCount);
                int currentSizeAutoStorage = threadPool.getSizeQueueTask ();
                for (int i = 0; i < sizeStorage - currentSizeAutoStorage - currentCount; ++i) {
                    addNewTask();
                }
            }
        }
    }

//    private void distributionTask(int currentCount) {
//        synchronized(autoStorage) {
//            int currentSizeAutoStorage = threadPool.getSizeQueueTask();
//            int sizeFillingStorage = (int) (fillingPercentage * sizeStorage);
//
//            if(currentCount + currentSizeAutoStorage < sizeFillingStorage) {
//                for(int i = 0; i < sizeFillingStorage - currentSizeAutoStorage - currentCount; ++i) {
//                    addNewTask();
//                }
//            }
//        }
//    }

}
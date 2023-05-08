package ru.nsu.org.mikhalev.thread_pool;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;


/*
 * { TaskExecute } class receives tasks and takes
 *  them in order from the queue and executes them.
 */

@Log4j2
public class TaskExecute extends Thread {
    @Setter
    private ThreadPool threadPool;

    @Override
    public void run() {
        while (isAlive()) {
            Task task;
            try {
                task = threadPool.getTask();
            } catch (InterruptedException e) {
                log.warn("InterruptedException");
                return;
            }
            task.execute();
        }
    }
}

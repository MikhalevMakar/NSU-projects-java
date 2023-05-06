package ru.nsu.org.mikhalev.thread_pool;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.LinkedBlockingQueue;

@Log4j2
public class TaskExecute extends Thread {
    @Setter
    LinkedBlockingQueue<Task> queueTask = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (isAlive()) {
            if (queueTask == null) {
                log.warn("QueueTask is null");
                continue;
            }

            Task task;
            try {
                task = queueTask.take();
            } catch (InterruptedException e) {
                log.warn("InterruptedException");
                return;
            }
            task.execute();
        }
    }
}

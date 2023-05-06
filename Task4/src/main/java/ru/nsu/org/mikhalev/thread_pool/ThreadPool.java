package ru.nsu.org.mikhalev.thread_pool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private List<TaskExecute> listTaskExecute = new LinkedList<>();
    private LinkedBlockingQueue<Task> queueTasks = new LinkedBlockingQueue<>();
    public void start() {
        for(var thread : listTaskExecute) {
            thread.start();
        }
    }

    public int getSizeQueueTask() {
        return queueTasks.size();
    }

    public void addTask(Task task) {
        queueTasks.add(task);
    }

    public void addTaskExecute(TaskExecute task) {
        task.setQueueTask(queueTasks);
        listTaskExecute.add(task);
    }

    public void end() {
        for (var thread : listTaskExecute) {
            thread.interrupt();
        }
    }
}
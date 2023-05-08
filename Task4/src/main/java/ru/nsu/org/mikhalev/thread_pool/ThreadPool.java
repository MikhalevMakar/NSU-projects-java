package ru.nsu.org.mikhalev.thread_pool;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Log4j2
public class ThreadPool {
    private final List<TaskExecute> listTaskExecute = new LinkedList<>();
    private final BlockingQueue<Task> queueTasks = new LinkedBlockingQueue<>();

    public void start() {
        for (var thread : listTaskExecute) {
            thread.start();
        }
    }

    public int getSizeQueueTask() {
        return queueTasks.size();
    }

    public void addTask(Task task) {
        try {
            queueTasks.add(task);
        } catch (NullPointerException ex) {
            throw new NullPointerException();
        }
    }

    public void addTaskExecute(@NotNull TaskExecute taskExecute) {
        taskExecute.setThreadPool(this);
        listTaskExecute.add(taskExecute);
    }

    public Task getTask() throws InterruptedException, NoSuchElementException {
        Task task = queueTasks.take();
        return task;
    }
    public void end() {
        for (var thread : listTaskExecute) {
            thread.interrupt();
        }
    }
}

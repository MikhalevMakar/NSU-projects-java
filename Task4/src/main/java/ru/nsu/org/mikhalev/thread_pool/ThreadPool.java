package ru.nsu.org.mikhalev.thread_pool;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadPool<T extends Runnable> {

    List<Thread> threads;
    public ThreadPool(int threadCount, T clazz) {
        threads = Stream.generate(() -> new Thread(clazz))
                                                .limit(threadCount)
                                                .collect(Collectors.toList());
    }

    public void start() {
        for(var thread : threads) {
            thread.start();
        }
    }

    public void end() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
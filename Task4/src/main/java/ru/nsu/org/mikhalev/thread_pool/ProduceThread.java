package ru.nsu.org.mikhalev.thread_pool;

import java.util.List;
import java.util.stream.Stream;

public class ProduceThread<T extends Runnable> { List<Thread> threads;
    public ProduceThread(int threadCount, T clazz) {
        threads = Stream.generate(() -> new Thread(clazz))
                                                .limit(threadCount)
                                                .toList();
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
package ru.nsu.org.mikhalev.thread_pool;

import java.util.List;
import java.util.stream.Stream;

/*
 * { ProduceThread } class creates threads for suppliers, dealers.
 */

public class ProduceThread<T extends Runnable> { 
    List<Thread> threads;
    
    public ProduceThread(int threadCount, T clazz) {
        threads = Stream.generate(() -> new Thread(clazz))
                                                .limit(threadCount)
                                                .toList();
    }

    /*
     * Start execute task class worker
     *
     * @param void
     * @return void
     */
    public void start() {
        for(var thread : threads) {
            thread.start();
        }
    }

    /*
     * End execute task class worker
     *
     * @param void
     * @return void
     */
    public void end() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}

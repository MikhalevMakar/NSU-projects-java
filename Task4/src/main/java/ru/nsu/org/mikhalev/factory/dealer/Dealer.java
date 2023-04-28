package ru.nsu.org.mikhalev.factory.dealer;

import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;

public class Dealer implements Runnable {
    private final AutoStorage autoStorage;
    private final int time = 10;
    private int countFinishedAuto = 1 - Integer.valueOf(Properties_Value.DEALERS.getValue());
    private final Object lock = new Object(); // создаем объект-монитор

    public Dealer(AutoStorage autoStorage) {
        this.autoStorage = autoStorage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    ++countFinishedAuto;
                }
                System.out.println ("Auto ID - " + autoStorage.getAuto ().getId () + " " + countFinishedAuto);
                Thread.sleep (time);
            } catch (InterruptedException e) {
                throw new RuntimeException (e);
            }
        }
    }
}


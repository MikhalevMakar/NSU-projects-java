package ru.nsu.org.mikhalev.factory.dealer;

import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;

public class Dealer implements Runnable {
    private final AutoStorage autoStorage;
    private final int time = 10;
    private int countFinishedAuto = 0;
    public Dealer(AutoStorage autoStorage) {
        this.autoStorage = autoStorage;
    }

    @Override
    public void run() {
        while(true) {
            try {
                ++countFinishedAuto;
                System.out.println("Auto ID - "  + autoStorage.getAuto().getId() + " " + countFinishedAuto);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

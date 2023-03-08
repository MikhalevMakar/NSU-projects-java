package org.ru.nsu.mikhalev.task3;

public class GameThread extends Thread {
    private GameArea gameArea;
    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;
    }
    @Override
    public void run() {
        while (true) {
            try {
            gameArea.moveShapeDown();

                Thread.sleep (1000);
            } catch (InterruptedException e) {
                throw new RuntimeException (e);
            }
        }
    }
}

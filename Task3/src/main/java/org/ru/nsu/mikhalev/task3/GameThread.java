package org.ru.nsu.mikhalev.task3;

public class GameThread extends Thread {
    private GameArea gameArea;
    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;
    }
    @Override
    public void run() {
        while(true) {
            while (gameArea.IsMoveShapeDown()) {
                try {
                    Thread.sleep (200);
                } catch (InterruptedException e) {
                    throw new RuntimeException (e);
                }
            }
            if(gameArea.isBlockOutOfBounds()) {
                System.out.println ("Game Over");
                return;
            }
            gameArea.spawnShape();
        }
    }
}

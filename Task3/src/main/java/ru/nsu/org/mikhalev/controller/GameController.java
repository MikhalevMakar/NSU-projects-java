package ru.nsu.org.mikhalev.controller;

import ru.nsu.org.mikhalev.model.Context;
import ru.nsu.org.mikhalev.model.GameArea;
import ru.nsu.org.mikhalev.view.tetris_area.PerformanceGameArea;

public class GameController {
    private static boolean isPaused = false;
    private static int DELAY = Context.getMIDDLE();
    private GameArea gameArea;

    public GameController(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public static boolean getPaused() { return isPaused; }

    public static void setDELAY(int curDELAY) {
        DELAY = curDELAY;
    }

    public void preparationNewGame() {
        gameArea.updateInitialValues();
    }

    public void run() {
        while(true) {
            while (gameArea.IsMoveShapeDown(isPaused)) {
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!isPaused && gameArea.isBlockOutOfBounds()) {
                System.out.println("Game Over");
                Thread.currentThread().interrupt();
                PerformanceGameArea.gameOver();
                isPaused = true;
            }
            if (!isPaused)
                gameArea.spawnShape();
        }
    }
}

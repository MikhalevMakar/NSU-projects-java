package ru.nsu.org.mikhalev.view.tetris_area;

import ru.nsu.org.mikhalev.controller.GameController;
import ru.nsu.org.mikhalev.model.GameArea;

public class TetrisAreaView implements Runnable {
    private final GameArea gameArea;
    private final GameController gameController;
    public TetrisAreaView() {
        gameArea = new GameArea();
        gameController = new GameController(gameArea);
        PerformanceGameArea performanceGameArea = new PerformanceGameArea(gameController);
        gameArea.registerObserver(performanceGameArea);
    }

    @Override
    public void run() {
        gameController.run();
    }
}

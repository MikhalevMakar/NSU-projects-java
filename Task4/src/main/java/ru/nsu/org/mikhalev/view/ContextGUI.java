package ru.nsu.org.mikhalev.view;

import lombok.Getter;

public class ContextGUI {
    private ContextGUI() {
        throw new IllegalStateException("Utility  ContextGUI class");
    }
    @Getter
    private static final int OFFSET_X = 200;
    @Getter
    private static final int OFFSET_Y = 30;
    public static class ContextSupplier {
        @Getter
        private static final int  SIZE = 4;
        @Getter
        private static final int WIDTH = 150;
        @Getter
        private static final int HEIGHT = 10;
        @Getter
        private static final int TOP = 5;
        @Getter
        private static final int LEFT = 5;
        @Getter
        private static final int RIGHT = 5;
        @Getter
        private static final int BOTTOM = 10;
    }
    @Getter
    private static final int WIDTH = 1200;
    @Getter
    private static final int HEIGHT = 580;
    @Getter
    private static final int MAJOR_TICK_SPACING = 50;
    @Getter
    private static final int MINOR_TICK_SPACING = 10;

    public static class ContextChat {
        @Getter
        private static final int X = 734;
        @Getter
        private static final int Y = 250;
        @Getter
        private static final int WIDTH = 300;
        @Getter
        private static final int HEIGHT = 330;
    }
}

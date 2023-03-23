package ru.nsu.org.mikhalev.view;

import java.awt.*;

public enum SetColor {
    GREEN_START (new Color (81, 116, 128)),
    GREEN_END (new Color (88, 128, 140)),
    GOLD_START (new Color (130, 136, 103)),
    GOLD_END (new Color (120, 123, 90));
    private Color color;

    SetColor(Color color) {
        this.color = color;
    }

    public Color get() {
        return color;
    }
}

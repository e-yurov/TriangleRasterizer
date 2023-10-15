package ru.vsu.cg.trianglerasterizer.triangle;

import javafx.scene.paint.Color;

public class TrianglePoint {
    final int x;
    final int y;
    final Color color;

    public TrianglePoint(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}

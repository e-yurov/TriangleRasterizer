package ru.vsu.cs.cg.rasterizerdemo.rasterizer;

import javafx.scene.paint.Color;

public class TrianglePoint {
    int x;
    int y;
    private Color color;

    public TrianglePoint(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public TrianglePoint(int x, int y) {
        this(x, y, Color.BLACK);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

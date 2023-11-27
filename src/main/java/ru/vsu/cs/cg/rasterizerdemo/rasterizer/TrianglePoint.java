package ru.vsu.cs.cg.rasterizerdemo.rasterizer;

import javafx.scene.paint.Color;

public class TrianglePoint {
    float x;
    float y;
    private Color color;

    public TrianglePoint(float x, float y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public TrianglePoint(int x, int y) {
        this(x, y, Color.BLACK);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

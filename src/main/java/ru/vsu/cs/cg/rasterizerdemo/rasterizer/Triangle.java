package ru.vsu.cs.cg.rasterizerdemo.rasterizer;

import javafx.scene.paint.Color;

public class Triangle {
    private TrianglePoint p1;
    private TrianglePoint p2;
    private TrianglePoint p3;

    public Triangle(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(float x1, float y1, Color color1,
                    float x2, float y2, Color color2,
                    float x3, float y3, Color color3) {
        this(
                new TrianglePoint(x1, y1, color1),
                new TrianglePoint(x2, y2, color2),
                new TrianglePoint(x3, y3, color3)
        );
    }

    public static Triangle withRandomColor(float x1, float y1, float x2, float y2, float x3, float y3) {
        return new Triangle(
                x1, y1, Utils.getRandomColor(),
                x2, y2, Utils.getRandomColor(),
                x3, y3, Utils.getRandomColor()
        );
    }

    public static Triangle rgb(float x1, float y1, float x2, float y2, float x3, float y3) {
        return new Triangle(
                x1, y1, Color.RED,
                x2, y2, Color.GREEN,
                x3, y3, Color.BLUE
        );
    }

    public TrianglePoint getP1() {
        return p1;
    }

    public void setP1(TrianglePoint p1) {
        this.p1 = p1;
    }

    public TrianglePoint getP2() {
        return p2;
    }

    public void setP2(TrianglePoint p2) {
        this.p2 = p2;
    }

    public TrianglePoint getP3() {
        return p3;
    }

    public void setP3(TrianglePoint p3) {
        this.p3 = p3;
    }

    public TrianglePoint[] getPoints() {
        return new TrianglePoint[]{p1, p2, p3};
    }
}

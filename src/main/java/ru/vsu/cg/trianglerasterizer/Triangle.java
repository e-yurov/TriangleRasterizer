package ru.vsu.cg.trianglerasterizer;

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

    public Triangle(int x1, int y1, Color color1,
                    int x2, int y2, Color color2,
                    int x3, int y3, Color color3) {
        this(
                new TrianglePoint(x1, y1, color1),
                new TrianglePoint(x2, y2, color2),
                new TrianglePoint(x3, y3, color3)
        );
    }

    public void sortPointsByY() {
        TrianglePoint temporal = p1;

        if (p2.y < p1.y) {
            p1 = p2;
            p2 = temporal;
        }

        if (p3.y < p1.y) {
            p1 = p3;
            p3 = temporal;
        }

        if (p3.y < p2.y) {
            temporal = p2;
            p2 = p3;
            p3 = temporal;
        }
    }

    public TrianglePoint getP1() {
        return p1;
    }

    public TrianglePoint getP2() {
        return p2;
    }

    public TrianglePoint getP3() {
        return p3;
    }
}

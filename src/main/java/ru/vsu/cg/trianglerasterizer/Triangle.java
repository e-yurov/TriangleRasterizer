package ru.vsu.cg.trianglerasterizer;

import java.awt.*;

public class Triangle {
    private Point[] points;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        points = new Point[3];
        points[0] = new Point(x1, y1);
        points[1] = new Point(x2, y2);
        points[2] = new Point(x3, y3);
    }

    public Point[] getPoints() {
        return points;
    }
}

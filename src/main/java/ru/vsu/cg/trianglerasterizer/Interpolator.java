package ru.vsu.cg.trianglerasterizer;

import javafx.scene.paint.Color;

import java.awt.*;

public class Interpolator {
    private double alpha;
    private double beta;
    private double gamma;

    private Point p;

    private Point p1;
    private Point p2;
    private Point p3;

    private Color color1;
    private Color color2;
    private Color color3;

    public Color getPixelColor(Point[] trianglePoints, Color color1, Color color2, Color color3, Point point) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;

        this.p = point;

        p1 = trianglePoints[0];
        p2 = trianglePoints[1];
        p3 = trianglePoints[2];

        calcBarycentric();
        return interpolate();
    }

    private Color interpolate() {
        double red = alpha * color1.getRed() + beta * color2.getRed() + gamma * color3.getRed();
        double green = alpha * color1.getGreen() + beta * color2.getGreen() + gamma * color3.getGreen();
        double blue = alpha * color1.getBlue() + beta * color2.getBlue() + gamma * color3.getBlue();

        red = (red < 0) ? 0.0D : red;
        green = (green < 0) ? 0.0D : green;
        blue = (blue < 0) ? 0.0D : blue;

        return Color.color(red, green, blue);
    }

    private void calcBarycentric() {
        alpha = (double) ((p2.y - p3.y) * (p.x - p3.x) + (p3.x - p2.x) * (p.y - p3.y)) /
                ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        beta = (double) ((p3.y - p1.y) * (p.x - p3.x) + (p1.x - p3.x) * (p.y - p3.y)) /
                ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        gamma = 1.0D - alpha - beta;
    }
}

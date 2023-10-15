package ru.vsu.cg.trianglerasterizer.triangle;

import javafx.scene.paint.Color;

import java.awt.*;

public class Interpolator {
    /*private double alpha;
    private double beta;
    private double gamma;

    //private Point p;

    private Point p1;
    private Point p2;
    private Point p3;

    private Color color1;
    private Color color2;
    private Color color3;*/

    /*public Color getPixelColor(Point[] trianglePoints, Color color1, Color color2, Color color3, Point point) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;

        this.p = point;

        p1 = trianglePoints[0];
        p2 = trianglePoints[1];
        p3 = trianglePoints[2];

        calculateBarycentric();
        return interpolate();
    }*/

    public void drawPixel() {

    }

    public Color getPixelColor(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int x, int y) {
        double[] barycentric = calculateBarycentric(p1, p2, p3, x, y);
        return interpolate(barycentric[0], barycentric[1], barycentric[2],
                p1.getColor(), p2.getColor(), p3.getColor());
    }

    private Color interpolate(double alpha, double beta, double gamma,
                              Color color1, Color color2, Color color3) {
        double EPS = 1E-3;

        double red = alpha * color1.getRed() + beta * color2.getRed() + gamma * color3.getRed();
        red -= (red - 0.5D) * 0.005D;
        double green = alpha * color1.getGreen() + beta * color2.getGreen() + gamma * color3.getGreen();
        green -= (green - 0.5D) * 0.005D;
        double blue = alpha * color1.getBlue() + beta * color2.getBlue() + gamma * color3.getBlue();
        blue -= (blue - 0.5D) * 0.005D;


        /*double red = 0.5D;
        double green = 0.5D;
        double blue = 0.5D;*/

        /*
        if (red < 0) {
            red = 0.0D;
        }
        if (green < 0) {
            green = 0.0D;
        }
        if (blue < 0) {
            blue = 0.0D;
        }*/

        //System.out.println(red + " " + green + " " + blue);

        return new Color(red, green, blue, 1.0);
    }

    private double[] calculateBarycentric(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int x, int y) {
        double[] coordinates = new double[3];
        coordinates[0] = (double) ((p2.y - p3.y) * (x - p3.x) + (p3.x - p2.x) * (y - p3.y)) /
                ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        coordinates[1] = (double) ((p3.y - p1.y) * (x - p3.x) + (p1.x - p3.x) * (y - p3.y)) /
                ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        coordinates[2] = 1.0D - coordinates[0] - coordinates[1];

        //if (coordinates[0] < 0 || coordinates[1] < 0 || coordinates[2] < 0)
            //System.out.println();

        return coordinates;
    }
}

package ru.vsu.cg.trianglerasterizer.triangle;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.awt.*;

public class TriangleDrawer {
    private final PixelWriter pixelWriter;

    /*private Point p1;
    private Point p2;
    private Point p3;*/

    /*private double dx12;
    private double dx13;
    private double dx23;*/

    private double wx1;
    private double wx2;

    private double dxLeft;
    private double dxRight;

    private final Interpolator interpolator;



    /*public TriangleDrawer(TriangleOld triangle, GraphicsContext graphicsContext,
                          Color color1, Color color2, Color color3) {
        this.pixelWriter = graphicsContext.getPixelWriter();
        this.triangle = triangle;

        Point[] points = triangle.getPoints();
        p1 = points[0];
        p2 = points[1];
        p3 = points[2];

        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;

        this.graphicsContext = graphicsContext;
        interpolator = new Interpolator();
    }*/

    public TriangleDrawer(PixelWriter pixelWriter) {
        this.pixelWriter = pixelWriter;
        interpolator = new Interpolator();
    }

    public void draw(Triangle triangle) {
        triangle.sortPointsByY();

        TrianglePoint p1 = triangle.getP1();
        TrianglePoint p2 = triangle.getP2();
        TrianglePoint p3 = triangle.getP3();

        double dx12 = calcPointDiff(p1, p2);
        double dx13 = calcPointDiff(p1, p3);
        double dx23 = calcPointDiff(p2, p3);

        calculatePreUpper(dx12, dx13, p1.x);
        drawUpper(triangle.getP1(), triangle.getP2(), triangle.getP3());

        calculatePreLower(dx13, dx23, p1, p2);
        drawLower(triangle.getP1(), triangle.getP2(), triangle.getP3());
    }

    /*private void swapPoints(Point first, Point second) {
        int x1 = first.x;
        int y1 = first.y;

        first.x = second.x;
        first.y = second.y;
        second.x = x1;
        second.y = y1;
    }

    private void sortPoints() {
        if (p2.y < p1.y) {
            swapPoints(p1, p2);
        }

        if (p3.y < p1.y) {
            swapPoints(p1, p3);
        }

        if (p3.y < p2.y) {
            swapPoints(p2, p3);
        }
    }*/

    private void calculatePreUpper(double dx12, double dx13, int wx) {
        wx1 = wx;
        wx2 = wx1;

        //calcSideDiff(dx12, dx13);

        dxLeft = dx12;
        dxRight = dx13;

        if (dx13 < dx12) {
            dxLeft = dx13;
            dxRight = dx12;
        }
    }

    private void calculatePreLower(double dx13, double dx23, TrianglePoint p1, TrianglePoint p2) {
        if (p1.y == p2.y) {
            wx1 = p1.x;
            wx2 = p2.x;
        }

        //calcSideDiff(dx13, dx23);
        dxLeft = dx13;
        dxRight = dx23;

        if (dx13 < dx23) {
            dxLeft = dx23;
            dxRight = dx13;
        }
    }

    private void calcSideDiff(double dxFirst, double dxSecond) {
        dxLeft = dxFirst;
        dxRight = dxSecond;

        if (dxSecond < dxFirst) {
            dxLeft = dxSecond;
            dxRight = dxFirst;
        }
    }

    private double calcPointDiff(TrianglePoint first, TrianglePoint second) {
        return (first.y == second.y) ? 0.0D : (double) (second.x - first.x) / (second.y - first.y);
    }

    private void drawUpper(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3) {
        for (int y = p1.y; y < p2.y; y++, wx1 += dxLeft, wx2 += dxRight) {
            drawLine(p1, p2, p3, y);
            /*for (int j = (int) Math.round(wx1); j <= (int) Math.round(wx2); j ++) {
                Color color = interpolator.getPixelColor(p1, p2, p3, j, y);
                pixelWriter.setColor(j, i, color);
            }*/

        }
    }

    private void drawLower(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3) {
        for (int y = p2.y; y <= p3.y; y++, wx1 += dxLeft, wx2 += dxRight) {
            drawLine(p1, p2, p3, y);
        }
    }

    private void drawLine(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int y) {
        for (int x = (int) Math.round(wx1); x <= (int) Math.round(wx2); x++) {
            Color color = interpolator.getPixelColor(p1, p2, p3, x, y);
            pixelWriter.setColor(x, y, color);
        }
    }
}

package ru.vsu.cg.trianglerasterizer;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.awt.*;

public class TriangleDrawer {
    private PixelWriter pixelWriter;
    private Triangle triangle;
    private GraphicsContext graphicsContext;

    private Point p1;
    private Point p2;
    private Point p3;

    private double dx12;
    private double dx13;
    private double dx23;

    private double wx1;
    private double wx2;

    /*private double dx1left;
    private double dx1right;*/

    public TriangleDrawer(Triangle triangle, GraphicsContext graphicsContext) {
        this.pixelWriter = graphicsContext.getPixelWriter();
        this.triangle = triangle;

        Point[] points = triangle.getPoints();
        p1 = points[0];
        p2 = points[1];
        p3 = points[2];

        this.graphicsContext = graphicsContext;
    }

    public void draw() {
        sortPoints();
        calcDiff();
        drawUpper();
        drawLower();
    }

    private void swapPoints(Point first, Point second) {
        int x1 = first.x;
        int y1 = first.y;

        first.x = second.x;
        first.y = second.y;
        second.x = x1;
        second.y = y1;
    }

    private void sortPoints() {
        /*Point p1 = points[0];
        Point p2 = points[1];
        Point p3 = points[2];*/

        if (p2.y < p1.y) {
            swapPoints(p1, p2);
        }

        if (p3.y < p1.y) {
            swapPoints(p1, p3);
        }

        if (p3.y < p2.y) {
            swapPoints(p2, p3);
        }
    }

    private void calcDiff() {
        dx12 = calcPointDiff(p1, p2);
        dx13 = calcPointDiff(p1, p3);
        dx23 = calcPointDiff(p2, p3);
    }

    private double calcPointDiff(Point first, Point second) {
        return (first.y == second.y) ? 0D : (double) (second.x - first.x) / (second.y - first.y);
    }

    private void drawUpper() {
        wx1 = p1.x;
        wx2 = wx1;

        double dxLeft = dx12;
        double dxRight = dx13;

        if (dx13 < dx12) {
            dxLeft = dx13;
            dxRight = dx12;
        }

        for (int i = p1.y; i < p2.y; i ++) {
            for (int j = (int) Math.round(wx1); j <= (int) Math.round(wx2); j ++) {
                pixelWriter.setColor(j, i, Color.BLACK);
            }

            wx1 += dxLeft;
            wx2 += dxRight;
        }
    }

    private void drawLower() {
        if (p1.y == p2.y) {
            wx1 = p1.x;
            wx2 = p2.x;
        }

        double dxLeft = dx13;
        double dxRight = dx23;

        if (dx13 < dx23) {
            dxLeft = dx23;
            dxRight = dx13;
        }

        for (int i = p2.y; i <= p3.y; i++) {
            for (int j = (int) Math.round(wx1); j <= (int) Math.round(wx2); j++) {
                pixelWriter.setColor(j, i, Color.BLACK);
            }

            wx1 += dxLeft;
            wx2 += dxRight;
        }
    }
}

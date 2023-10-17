package ru.vsu.cs.cg.rasterizerdemo.rasterizer;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class TriangleRasterizer {
    private final Interpolator interpolator;

    private double wx1;
    private double wx2;

    private double dxLeft;
    private double dxRight;

    public TriangleRasterizer(PixelWriter pixelWriter) {
        interpolator = new Interpolator(pixelWriter);
    }

    public void rasterize(Triangle triangle) {
        Utils.sortTrianglePointsByY(triangle);

        TrianglePoint p1 = triangle.getP1();
        TrianglePoint p2 = triangle.getP2();
        TrianglePoint p3 = triangle.getP3();

        double dx12 = calculateSideXIncrement(p1, p2);
        double dx13 = calculateSideXIncrement(p1, p3);
        double dx23 = calculateSideXIncrement(p2, p3);

        computeBeforeUpperPart(dx12, dx13, p1.x);
        drawPart(p1, p2, p3, p1.y, p2.y - 1);

        computeBeforeLowerPart(dx13, dx23, p1, p2);
        drawPart(p1, p2, p3, p2.y, p3.y);
    }

    public void rasterize(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3) {
        Triangle triangle = new Triangle(p1, p2, p3);
        this.rasterize(triangle);
    }

    public void rasterize(int x1, int y1, Color color1,
                          int x2, int y2, Color color2,
                          int x3, int y3, Color color3) {
        Triangle triangle = new Triangle(
                x1, y1, color1,
                x2, y2, color2,
                x3, y3, color3
        );
        this.rasterize(triangle);
    }

    private void computeBeforeUpperPart(double dx12, double dx13, int wx) {
        wx1 = wx;
        wx2 = wx1;

        dxLeft = dx12;
        dxRight = dx13;
        if (dx13 < dx12) {
            dxLeft = dx13;
            dxRight = dx12;
        }
    }

    private void computeBeforeLowerPart(double dx13, double dx23, TrianglePoint p1, TrianglePoint p2) {
        if (p1.y == p2.y) {
            wx1 = p1.x;
            wx2 = p2.x;
        }

        dxLeft = dx13;
        dxRight = dx23;
        if (dx13 < dx23) {
            dxLeft = dx23;
            dxRight = dx13;
        }
    }

    private double calculateSideXIncrement(TrianglePoint first, TrianglePoint second) {
        return (first.y == second.y) ? 0.0D : (double) (second.x - first.x) / (second.y - first.y);
    }

    private void drawPart(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int leftY, int rightY) {
        for (int y = leftY; y <= rightY; y++, wx1 += dxLeft, wx2 += dxRight) {
            drawLine(p1, p2, p3, y);
        }
    }

    private void drawLine(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int y) {
        for (int x = (int) Math.round(wx1); x <= (int) Math.round(wx2); x++) {
            interpolator.drawPixel(p1, p2, p3, x, y);
        }
    }
}

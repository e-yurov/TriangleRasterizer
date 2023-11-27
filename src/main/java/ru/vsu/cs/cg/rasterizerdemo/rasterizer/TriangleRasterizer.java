package ru.vsu.cs.cg.rasterizerdemo.rasterizer;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class TriangleRasterizer {
    private final Interpolator interpolator;

    private float wx1;
    private float wx2;

    private float dxLeft;
    private float dxRight;

    public TriangleRasterizer(PixelWriter pixelWriter) {
        interpolator = new Interpolator(pixelWriter);
    }

    public void rasterize(Triangle triangle) {
        Utils.sortTrianglePointsByY(triangle);

        TrianglePoint p1 = triangle.getP1();
        TrianglePoint p2 = triangle.getP2();
        TrianglePoint p3 = triangle.getP3();

        int x1 = (int) p1.x;
        int y1 = (int) p1.y;
        int x2 = (int) p2.x;
        int y2 = (int) p2.y;
        int x3 = (int) p3.x;
        int y3 = (int) p3.y;

        float dx12 = calculateSideXIncrement(x1, y1, x2, y2);
        float dx13 = calculateSideXIncrement(x1, y1, x3, y3);
        float dx23 = calculateSideXIncrement(x2, y2, x3, y3);

        computeBeforeUpperPart(dx12, dx13, x1);
        drawPart(p1, p2, p3, y1, y2 - 1);

        computeBeforeLowerPart(dx13, dx23, x1, y1, x2, y2);
        drawPart(p1, p2, p3, y2, y3);
    }

    public void rasterize(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3) {
        Triangle triangle = new Triangle(p1, p2, p3);
        this.rasterize(triangle);
    }

    public void rasterize(float x1, float y1, Color color1,
                          float x2, float y2, Color color2,
                          float x3, float y3, Color color3) {
        Triangle triangle = new Triangle(
                x1, y1, color1,
                x2, y2, color2,
                x3, y3, color3
        );
        this.rasterize(triangle);
    }

    private void computeBeforeUpperPart(float dx12, float dx13, float wx) {
        wx1 = wx;
        wx2 = wx1;

        if (dx13 < dx12) {
            dxLeft = dx13;
            dxRight = dx12;
            return;
        }
        dxLeft = dx12;
        dxRight = dx13;
    }

    private void computeBeforeLowerPart(float dx13, float dx23, int x1, int y1, int x2, int y2) {
        if (y1 == y2) {
            wx1 = x1;
            wx2 = x2;
            if (wx1 > wx2) {
                wx1 = x2;
                wx2 = x1;
            }
        }

        if (dx13 < dx23) {
            dxLeft = dx23;
            dxRight = dx13;
            return;
        }
        dxLeft = dx13;
        dxRight = dx23;
    }

    private float calculateSideXIncrement(int x1, int y1, int x2, int y2) {
        return (y1 == y2) ? 0.0F : (float) (x2 - x1) / (y2 - y1);
    }

    private void drawPart(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int leftY, int rightY) {
        for (int y = leftY; y <= rightY; y++, wx1 += dxLeft, wx2 += dxRight) {
            drawLine(p1, p2, p3, y);
        }
    }

    private void drawLine(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int y) {
        for (int x = (int)(wx1); x <= (int)(wx2); x++) {
            interpolator.drawPixel(p1, p2, p3, x, y);
        }
    }
}

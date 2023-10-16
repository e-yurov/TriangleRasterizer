package ru.vsu.cg.trianglerasterizer;

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
        triangle.sortPointsByY();

        TrianglePoint p1 = triangle.getP1();
        TrianglePoint p2 = triangle.getP2();
        TrianglePoint p3 = triangle.getP3();

        double dx12 = calcPointDiff(p1, p2);
        double dx13 = calcPointDiff(p1, p3);
        double dx23 = calcPointDiff(p2, p3);

        calculatePreUpper(dx12, dx13, p1.x);
        //drawTopPart(triangle.getP1(), triangle.getP2(), triangle.getP3());
        drawPart(p1, p2, p3, p1.y, p2.y - 1);

        calculatePreLower(dx13, dx23, p1, p2);
        drawPart(p1, p2, p3, p2.y, p3.y);
        //drawBottomPart(triangle.getP1(), triangle.getP2(), triangle.getP3());
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

    private void calculatePreUpper(double dx12, double dx13, int wx) {
        wx1 = wx;
        wx2 = wx1;

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


        dxLeft = dx13;
        dxRight = dx23;
        if (dx13 < dx23) {
            dxLeft = dx23;
            dxRight = dx13;
        }
    }

    private double calcPointDiff(TrianglePoint first, TrianglePoint second) {
        return (first.y == second.y) ? 0.0D : (double) (second.x - first.x) / (second.y - first.y);
    }

    /*private void drawTopPart(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3) {
        for (int y = p1.y; y < p2.y; y++, wx1 += dxLeft, wx2 += dxRight) {
            drawLine(p1, p2, p3, y);
        }
    }

    private void drawBottomPart(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3) {
        for (int y = p2.y; y <= p3.y; y++, wx1 += dxLeft, wx2 += dxRight) {
            drawLine(p1, p2, p3, y);
        }
    }*/

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

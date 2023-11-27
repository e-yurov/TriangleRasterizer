package ru.vsu.cs.cg.rasterizerdemo.rasterizer;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Interpolator {
    private final PixelWriter pixelWriter;

    public Interpolator(PixelWriter pixelWriter) {
        this.pixelWriter = pixelWriter;
    }

    public void drawPixel(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int x, int y) {
        float[] barycentric = calculateBarycentric(p1, p2, p3, x, y);
        interpolate(barycentric[0], barycentric[1], barycentric[2],
                p1.getColor(), p2.getColor(), p3.getColor(),
                x, y);
    }

    private void interpolate(float alpha, float beta, float gamma,
                             Color color1, Color color2, Color color3,
                             int x, int y) {
        double red = alpha * color1.getRed() + beta * color2.getRed() + gamma * color3.getRed();
        double green = alpha * color1.getGreen() + beta * color2.getGreen() + gamma * color3.getGreen();
        double blue = alpha * color1.getBlue() + beta * color2.getBlue() + gamma * color3.getBlue();

        int argb = (255 << 24) + (clampColor(red) << 16) + (clampColor(green) << 8) + clampColor(blue);
        pixelWriter.setArgb(x, y, argb);
    }

    private int clampColor(double color) {
        int intColor = (int) (color * 255);
        int signMask = ~(intColor >>> 24);
        intColor &= signMask;
        int clampMask = ~((intColor >> 8) * 255);
        intColor &= clampMask;
        intColor &= 255;
        return intColor;
    }

    public static float[] calculateBarycentric(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int x, int y) {
        float[] coordinates = new float[3];
        float denominator = (p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y);
        coordinates[0] = ((p2.y - p3.y) * (x - p3.x) + (p3.x - p2.x) * (y - p3.y)) / denominator;
        coordinates[1] =((p3.y - p1.y) * (x - p3.x) + (p1.x - p3.x) * (y - p3.y)) / denominator;
        coordinates[2] = 1.0F - coordinates[0] - coordinates[1];

        return coordinates;
    }

    public static float[] calculateBarycentric(int x1, int y1,
                                               int x2, int y2,
                                               int x3, int y3,
                                               int x, int y) {
        return calculateBarycentric(
                new TrianglePoint(x1, y1),
                new TrianglePoint(x2, y2),
                new TrianglePoint(x3, y3),
                x, y
        );
    }
}

package ru.vsu.cg.trianglerasterizer;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Interpolator {
    private final PixelWriter pixelWriter;

    public Interpolator(PixelWriter pixelWriter) {
        this.pixelWriter = pixelWriter;
    }

    public void drawPixel(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int x, int y) {
        double[] barycentric = calculateBarycentric(p1, p2, p3, x, y);
        interpolate(barycentric[0], barycentric[1], barycentric[2],
                p1.getColor(), p2.getColor(), p3.getColor(),
                x, y);
    }

    private void interpolate(double alpha, double beta, double gamma,
                             Color color1, Color color2, Color color3,
                             int x, int y) {
        double red = alpha * color1.getRed() + beta * color2.getRed() + gamma * color3.getRed();
        red -= (red - 0.5D) * 0.005D;
        double green = alpha * color1.getGreen() + beta * color2.getGreen() + gamma * color3.getGreen();
        green -= (green - 0.5D) * 0.005D;
        double blue = alpha * color1.getBlue() + beta * color2.getBlue() + gamma * color3.getBlue();
        blue -= (blue - 0.5D) * 0.005D;

        int argb = (255 << 24) + ((int) (red * 255) << 16) + ((int) (green * 255) << 8) + (int) (blue * 255);
        pixelWriter.setArgb(x, y, argb);
    }

    private double[] calculateBarycentric(TrianglePoint p1, TrianglePoint p2, TrianglePoint p3, int x, int y) {
        double[] coordinates = new double[3];
        int denominator = (p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y);
        coordinates[0] = (double) ((p2.y - p3.y) * (x - p3.x) + (p3.x - p2.x) * (y - p3.y)) / denominator;
        coordinates[1] = (double) ((p3.y - p1.y) * (x - p3.x) + (p1.x - p3.x) * (y - p3.y)) / denominator;
        coordinates[2] = 1.0D - coordinates[0] - coordinates[1];

        return coordinates;
    }
}

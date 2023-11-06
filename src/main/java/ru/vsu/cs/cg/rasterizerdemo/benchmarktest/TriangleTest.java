package ru.vsu.cs.cg.rasterizerdemo.benchmarktest;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import ru.vsu.cs.cg.rasterizerdemo.rasterizer.Triangle;
import ru.vsu.cs.cg.rasterizerdemo.rasterizer.TriangleRasterizer;

public class TriangleTest {
    public static void main(String[] args) {
        TriangleTest triangleTest = new TriangleTest();
        triangleTest.test(new Canvas().getGraphicsContext2D().getPixelWriter());
    }

    public void test(PixelWriter pixelWriter) {
        TriangleRasterizer drawer = new TriangleRasterizer(pixelWriter);
        Triangle triangle1 = new Triangle(
                100, 300, Color.RED,
                250, 100, Color.BLUE,
                400, 400, Color.LIME
        );
        Triangle triangle2 = new Triangle(
                100, 120, Color.RED,
                150, 100, Color.BLUE,
                200, 120, Color.LIME
        );
        Triangle pixel = new Triangle(
                100, 100, Color.RED,
                100, 100, Color.BLUE,
                100, 100, Color.LIME
        );
        long iterations = 10_000L;
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < iterations; i++) {
            drawer.rasterize(pixel);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(iterations + " triangles drawing time: " + (endTime - startTime));
    }
}

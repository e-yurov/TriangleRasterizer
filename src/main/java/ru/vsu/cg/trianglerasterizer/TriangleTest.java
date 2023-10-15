package ru.vsu.cg.trianglerasterizer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import ru.vsu.cg.trianglerasterizer.triangle.Triangle;
import ru.vsu.cg.trianglerasterizer.triangle.TriangleDrawer;

public class TriangleTest {
    public static void main(String[] args) {
        TriangleTest triangleTest = new TriangleTest();
        triangleTest.test(new Canvas().getGraphicsContext2D().getPixelWriter());
    }

    public void test(PixelWriter pixelWriter) {
        TriangleDrawer drawer = new TriangleDrawer(pixelWriter);
        Triangle triangle1 = new Triangle(
                100, 300, Color.RED,
                250, 100, Color.BLUE,
                400, 400, Color.LIME
        );

        long iterations = 500L;
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < iterations; i++) {
            drawer.draw(triangle1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}

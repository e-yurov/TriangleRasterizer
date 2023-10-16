package ru.vsu.cg.graphics;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import ru.vsu.cg.rasterizer.Triangle;
import ru.vsu.cg.rasterizer.TrianglePoint;
import ru.vsu.cg.rasterizer.TriangleRasterizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriangleSamplesDrawer {
    public void drawTriangles(PixelWriter pixelWriter, int leftXIndent, int topYIndent) {
        TriangleRasterizer rasterizer = new TriangleRasterizer(pixelWriter);
        for (Triangle triangle : generateTriangles()) {
            for (TrianglePoint point : triangle.getPoints()) {
                point.setX(point.getX() + leftXIndent);
                point.setY(point.getY() + topYIndent);
            }

            rasterizer.rasterize(triangle);
        }
    }

    private List<Triangle> generateTriangles() {
        Random random = new Random();
        List<Triangle> resultList = new ArrayList<>();

        Triangle noTopTriangle0 = new Triangle(
                100, 100, getRandomColor(random),
                200, 100, getRandomColor(random),
                100, 200, getRandomColor(random)
        );
        resultList.add(noTopTriangle0);
        Triangle noTopTriangle1 = new Triangle(
                300, 100, getRandomColor(random),
                400, 100, getRandomColor(random),
                400, 200, getRandomColor(random)
        );
        resultList.add(noTopTriangle1);
        Triangle noTopTriangle2 = new Triangle(
                500, 100, getRandomColor(random),
                600, 100, getRandomColor(random),
                550, 200, getRandomColor(random)
        );
        resultList.add(noTopTriangle2);
        Triangle noTopTriangle3 = new Triangle(
                700, 100, getRandomColor(random),
                800, 100, getRandomColor(random),
                650, 200, getRandomColor(random)
        );
        resultList.add(noTopTriangle3);
        Triangle noTopTriangle4 = new Triangle(
                900, 100, getRandomColor(random),
                1000, 100, getRandomColor(random),
                1050, 200, getRandomColor(random)
        );
        resultList.add(noTopTriangle4);
        Triangle horizontalLine = new Triangle(
                1100, 100, getRandomColor(random),
                1200, 100, getRandomColor(random),
                1150, 100, getRandomColor(random)
        );
        resultList.add(horizontalLine);
        Triangle verticalLine = new Triangle(
                1300, 100, getRandomColor(random),
                1300, 150, getRandomColor(random),
                1300, 200, getRandomColor(random)
        );
        resultList.add(verticalLine);
        return resultList;
    }

    private Color getRandomColor(Random random) {
        return Color.color(random.nextDouble(0.0D, 1.0D),
                random.nextDouble(0.0D, 1.0D), random.nextDouble(0.0D, 1.0D));
    }
}

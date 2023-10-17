package ru.vsu.cg.rasterizerdemo.graphics.drawers;

import javafx.scene.image.PixelWriter;
import ru.vsu.cg.rasterizerdemo.rasterizer.Triangle;
import ru.vsu.cg.rasterizerdemo.rasterizer.TrianglePoint;
import ru.vsu.cg.rasterizerdemo.rasterizer.TriangleRasterizer;

import java.util.ArrayList;
import java.util.List;

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
        List<Triangle> resultList = new ArrayList<>();

        resultList.addAll(generateFirstRow());
        resultList.addAll(generateSecondRow());
        resultList.addAll(generateThirdRow());

        return resultList;
    }

    private List<Triangle> generateFirstRow() {
        List<Triangle> firstRow = new ArrayList<>();

        Triangle noTopTriangle0 = Triangle.withRandomColor(
                100, 100,
                200, 100,
                100, 200);
        firstRow.add(noTopTriangle0);
        Triangle noTopTriangle1 = Triangle.withRandomColor(
                300, 100,
                400, 100,
                400, 200
        );
        firstRow.add(noTopTriangle1);
        Triangle noTopTriangle2 = Triangle.withRandomColor(
                500, 100,
                600, 100,
                550, 200
        );
        firstRow.add(noTopTriangle2);
        Triangle noTopTriangle3 = Triangle.withRandomColor(
                700, 100,
                800, 100,
                650, 200
        );
        firstRow.add(noTopTriangle3);
        Triangle noTopTriangle4 = Triangle.withRandomColor(
                900, 100,
                1000, 100,
                1050, 200
        );
        firstRow.add(noTopTriangle4);
        Triangle horizontalLine = Triangle.withRandomColor(
                1100, 100,
                1200, 100,
                1150, 100
        );
        firstRow.add(horizontalLine);
        Triangle verticalLine = Triangle.withRandomColor(
                1300, 100,
                1300, 150,
                1300, 200
        );
        firstRow.add(verticalLine);

        return firstRow;
    }

    private List<Triangle> generateSecondRow() {
        List<Triangle> secondRow = new ArrayList<>();

        Triangle noBottomTriangle0 = Triangle.withRandomColor(
                100, 300,
                100, 400,
                200, 400
        );
        secondRow.add(noBottomTriangle0);
        Triangle noBottomTriangle1 = Triangle.withRandomColor(
                400, 300,
                400, 400,
                300, 400
        );
        secondRow.add(noBottomTriangle1);
        Triangle noBottomTriangle2 = Triangle.withRandomColor(
                500, 400,
                600, 400,
                550, 300
        );
        secondRow.add(noBottomTriangle2);
        Triangle noBottomTriangle3 = Triangle.withRandomColor(
                700, 400,
                800, 400,
                650, 300
        );
        secondRow.add(noBottomTriangle3);
        Triangle noBottomTriangle4 = Triangle.withRandomColor(
                900, 400,
                1000, 400,
                1050, 300
        );
        secondRow.add(noBottomTriangle4);
        Triangle noLeftSideTriangle0 = Triangle.withRandomColor(
                1100, 300,
                1100, 400,
                1200, 350
        );
        secondRow.add(noLeftSideTriangle0);
        Triangle noRightSideTriangle0 = Triangle.withRandomColor(
                1400, 400,
                1300, 350,
                1400, 300
        );
        secondRow.add(noRightSideTriangle0);

        return secondRow;
    }

    private List<Triangle> generateThirdRow() {
        List<Triangle> thirdRow = new ArrayList<>();

        Triangle triangle0 = Triangle.withRandomColor(
                150, 500,
                100, 550,
                200, 600
        );
        thirdRow.add(triangle0);
        Triangle triangle1 = Triangle.withRandomColor(
                350, 500,
                400, 550,
                300, 600
        );
        thirdRow.add(triangle1);
        Triangle triangle2 = Triangle.withRandomColor(
                600, 500,
                590, 550,
                500, 590
        );
        thirdRow.add(triangle2);
        Triangle triangle3 = Triangle.withRandomColor(
                700, 500,
                710, 550,
                800, 590
        );
        thirdRow.add(triangle3);

        return thirdRow;
    }
}

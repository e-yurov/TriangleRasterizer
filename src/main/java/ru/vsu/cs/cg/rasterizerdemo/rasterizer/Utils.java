package ru.vsu.cs.cg.rasterizerdemo.rasterizer;

import javafx.scene.paint.Color;

import java.util.Random;

public class Utils {
    public static void sortTrianglePointsByY(Triangle triangle) {
        TrianglePoint temporal;

        if (triangle.getP2().getY() < triangle.getP1().getY()) {
            temporal = triangle.getP1();
            triangle.setP1(triangle.getP2());
            triangle.setP2(temporal);
        }

        if (triangle.getP3().getY() < triangle.getP1().getY()) {
            temporal = triangle.getP1();
            triangle.setP1(triangle.getP3());
            triangle.setP3(temporal);
        }

        if (triangle.getP3().getY() < triangle.getP2().getY()) {
            temporal = triangle.getP2();
            triangle.setP2(triangle.getP3());
            triangle.setP3(temporal);
        }
    }

    public static Color getRandomColor() {
        Random random = new Random();
        return Color.color(random.nextDouble(0.0D, 1.0D),
                random.nextDouble(0.0D, 1.0D), random.nextDouble(0.0D, 1.0D));
    }
}

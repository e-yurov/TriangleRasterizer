package ru.vsu.cg.trianglerasterizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.vsu.cg.trianglerasterizer.triangle.Triangle;
import ru.vsu.cg.trianglerasterizer.triangle.TriangleDrawer;

import java.io.IOException;
import java.util.Date;

public class HelloApplication extends Application {
    Canvas canvas;
    @Override
    public void start(Stage stage) throws IOException {
        long start = new Date().getTime();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        canvas = new Canvas(500, 500);

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, 500, 500);

        //inliningTest();

        /*TriangleOld triangle0 = new TriangleOld(100, 100, 200, 100, 300, 100);
        TriangleOld triangle1 = new TriangleOld(100, 300, 250, 100, 450, 300);
        TriangleOld triangle2 = new TriangleOld(100, 300, 250, 400, 450, 250);
        TriangleOld triangle3 = new TriangleOld(200, 100, 100, 101, 300, 102);
        TriangleOld triangle4 = new TriangleOld(250, 100, 100, 400, 450, 250);
        TriangleDrawer drawer = new TriangleDrawer(triangle1, canvas.getGraphicsContext2D(),
                Color.RED, Color.LIME, Color.BLUE);*/

        TriangleDrawer drawer = new TriangleDrawer(canvas.getGraphicsContext2D().getPixelWriter());
        Triangle triangle0 = new Triangle(
                100, 400, Color.RED,
                250, 100, Color.BLUE,
                400, 400, Color.LIME
        );
        Triangle triangle1 = new Triangle(
                100, 300, Color.RED,
                250, 100, Color.BLUE,
                400, 400, Color.LIME
        );
        drawer.draw(triangle1);

        //canvas.getGraphicsContext2D().strokeLine(100, 100, 200, 200);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //drawer.draw();
        drawCoordinates(canvas.getGraphicsContext2D(), 500, 500);
        //System.out.println(new Date().getTime() - start);
    }

    private void drawCoordinates(GraphicsContext graphicsContext, int width, int height) {
        graphicsContext.setFill(Color.RED);
        for (int i = 0; i < width; i += 100) {
            for (int j = 0; j < height; j += 100) {
                graphicsContext.fillOval(i - 2, j - 2, 4, 4);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

    /*private void inliningTest() {
        long start = System.currentTimeMillis();
        TriangleOld triangle = new TriangleOld(100, 300, 250, 100, 450, 300);

        TriangleDrawer drawer = new TriangleDrawer(triangle, canvas.getGraphicsContext2D(),
                Color.RED, Color.LIME, Color.BLUE);

        for (long i = 0; i < 1000L; i++) {
            drawer.draw();
        }
        long end = System.currentTimeMillis();
        System.out.println("Draw:" + (end - start));
    }*/
}
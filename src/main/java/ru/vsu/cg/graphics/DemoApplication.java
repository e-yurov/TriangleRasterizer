package ru.vsu.cg.graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.vsu.cg.rasterizer.Triangle;
import ru.vsu.cg.rasterizer.TriangleRasterizer;

import java.io.IOException;
import java.util.Date;

public class DemoApplication extends Application {
    Canvas canvas;
    @Override
    public void start(Stage stage) throws IOException {
        long start = new Date().getTime();
        FXMLLoader fxmlLoader = new FXMLLoader(DemoApplication.class.getResource("demo-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        canvas = new Canvas(500, 500);

        Pane root = new Pane(canvas);
        Scene scene = new Scene(fxmlLoader.load(), 1760, 990);

        //inliningTest();

        /*TriangleOld triangle0 = new TriangleOld(100, 100, 200, 100, 300, 100);
        TriangleOld triangle1 = new TriangleOld(100, 300, 250, 100, 450, 300);
        TriangleOld triangle2 = new TriangleOld(100, 300, 250, 400, 450, 250);
        TriangleOld triangle3 = new TriangleOld(200, 100, 100, 101, 300, 102);
        TriangleOld triangle4 = new TriangleOld(250, 100, 100, 400, 450, 250);
        TriangleDrawer drawer = new TriangleDrawer(triangle1, canvas.getGraphicsContext2D(),
                Color.RED, Color.LIME, Color.BLUE);*/

        TriangleRasterizer drawer = new TriangleRasterizer(canvas.getGraphicsContext2D().getPixelWriter());
        Triangle triangle0 = new Triangle(
                100, 400, Color.RED,
                250, 100, Color.BLUE,
                400, 400, Color.LIME
        );
        Triangle triangle1 = new Triangle(
                100, 300, Color.color(1.0D, 0.0D, 0.0D),
                250, 100, Color.color(1.0D, 0.2D, 0.5D),
                400, 400, Color.color(1.0D, 0.4D, 1.D)
        );
        Triangle triangleSmall = new Triangle(
                100, 300, Color.RED,
                150, 250, Color.BLUE,
                150, 300, Color.LIME
        );
        drawer.rasterize(triangle1);

        //canvas.getGraphicsContext2D().strokeLine(100, 100, 200, 200);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //drawer.draw();
        //drawCoordinates(canvas.getGraphicsContext2D(), 500, 500);
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
}
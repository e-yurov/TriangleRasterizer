package ru.vsu.cg.trianglerasterizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Canvas canvas = new Canvas(500, 500);

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, 500, 500);

        Triangle triangle0 = new Triangle(100, 100, 200, 100, 300, 100);
        Triangle triangle1 = new Triangle(100, 300, 250, 100, 450, 400);
        TriangleDrawer drawer = new TriangleDrawer(triangle1, canvas.getGraphicsContext2D());

        //canvas.getGraphicsContext2D().strokeLine(100, 100, 200, 200);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        drawer.draw();
        drawCoordinates(canvas.getGraphicsContext2D(), 500, 500);
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
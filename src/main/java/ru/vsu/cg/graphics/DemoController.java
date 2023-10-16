package ru.vsu.cg.graphics;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.vsu.cg.rasterizer.Triangle;
import ru.vsu.cg.rasterizer.TrianglePoint;
import ru.vsu.cg.rasterizer.TriangleRasterizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DemoController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue());
            //drawCoordinates();
        });
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue());
            //drawCoordinates();
        });

        CoordinatesDrawer coordinatesDrawer = new CoordinatesDrawer(
                canvas.getGraphicsContext2D(), (int) canvas.getWidth(), (int) canvas.getHeight());
        coordinatesDrawer.drawCoordinates();
        //drawCoordinates();
        TriangleSamplesDrawer triangleSamplesDrawer = new TriangleSamplesDrawer();
        triangleSamplesDrawer.drawTriangles(canvas.getGraphicsContext2D().getPixelWriter(), 50, 50);
        //drawTriangles(50, 50);
    }
}
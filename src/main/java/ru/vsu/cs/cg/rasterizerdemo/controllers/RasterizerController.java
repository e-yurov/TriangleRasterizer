package ru.vsu.cs.cg.rasterizerdemo.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import ru.vsu.cs.cg.rasterizerdemo.rasterizer.Triangle;
import ru.vsu.cs.cg.rasterizerdemo.rasterizer.TriangleRasterizer;

public class RasterizerController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        TriangleRasterizer rasterizer = new TriangleRasterizer(canvas.getGraphicsContext2D().getPixelWriter());
        rasterizer.rasterize(Triangle.withRandomColor(
                700, 100,
                400, 500 ,
                1100 ,700
        ));
    }
}

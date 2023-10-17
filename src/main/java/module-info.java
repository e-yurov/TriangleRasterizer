module ru.vsu.cg.trianglerasterizer {
    requires javafx.controls;
    requires javafx.fxml;

    exports ru.vsu.cg.rasterizerdemo.graphics;
    opens ru.vsu.cg.rasterizerdemo.graphics to javafx.fxml;
    exports ru.vsu.cg.rasterizerdemo.benchmarktest;
    opens ru.vsu.cg.rasterizerdemo.benchmarktest to javafx.fxml;
    exports ru.vsu.cg.rasterizerdemo.graphics.drawers;
    opens ru.vsu.cg.rasterizerdemo.graphics.drawers to javafx.fxml;
}
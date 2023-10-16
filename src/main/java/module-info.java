module ru.vsu.cg.trianglerasterizer {
    requires javafx.controls;
    requires javafx.fxml;

    exports ru.vsu.cg.graphics;
    opens ru.vsu.cg.graphics to javafx.fxml;
}
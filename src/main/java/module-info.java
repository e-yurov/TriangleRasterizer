module ru.vsu.cg.trianglerasterizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports ru.vsu.cg.graphics;
    opens ru.vsu.cg.graphics to javafx.fxml;
    exports ru.vsu.cg.trianglerasterizer;
    opens ru.vsu.cg.trianglerasterizer to javafx.fxml;
}
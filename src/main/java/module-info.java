module ru.vsu.cg.trianglerasterizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ru.vsu.cg.trianglerasterizer to javafx.fxml;
    exports ru.vsu.cg.trianglerasterizer;
    exports ru.vsu.cg.trianglerasterizer.triangle;
    opens ru.vsu.cg.trianglerasterizer.triangle to javafx.fxml;
}
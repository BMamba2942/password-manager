package passwordmanager.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class PasswordScene extends ViewScene {
    @Override
    public Scene getScene() {
        FlowPane root = new FlowPane();
        Label l = new Label("Label");
        root.getChildren().add(l);
        return new Scene(root, 600, 600);
    }
}

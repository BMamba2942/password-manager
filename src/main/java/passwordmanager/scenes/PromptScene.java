package passwordmanager.scenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import passwordmanager.controllers.SceneController;


public class PromptScene extends ViewScene {

    public PromptScene(SceneController controller) {
        this.controller = controller;
    }

    @Override
    public Scene getScene() {
        FlowPane root = new FlowPane();
        Label passLabel = new Label("Password");
        JFXPasswordField passwordField = new JFXPasswordField();
        Label confirmLabel = new Label("Confirm Password");
        JFXPasswordField confirmField = new JFXPasswordField();
        JFXButton butt = new JFXButton("Enter");
        butt.disableProperty().bind(passwordField.textProperty().isNotEmpty().and(passwordField.textProperty().isNotEqualTo(confirmField.textProperty())));
        butt.setOnAction((event) -> this.controller.nextScene());
        root.getChildren().addAll(butt, passLabel, passwordField, confirmLabel, confirmField);
        return new Scene(root, 800, 800);
    }
}

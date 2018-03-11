package passwordmanager.scenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import passwordmanager.controllers.SceneController;
import passwordmanager.models.Password;

import java.sql.SQLException;
import java.util.List;

public class MainScene extends ViewScene {
    public MainScene(SceneController controller) {
        this.controller = controller;
    }

    @Override
    public Scene getScene() {
        FlowPane root = new FlowPane();
        JFXButton but = new JFXButton("NEXT SCENE");
        JFXComboBox<Password> passwordsBox = new JFXComboBox<>();
        try {
            List<Password> passwords = this.controller.getDb().getPasswords();
            passwordsBox.setItems(FXCollections.observableList(passwords));
        } catch (SQLException e) {

        }
        but.setOnAction((event) -> {
            this.controller.nextScene();
        });
        root.getChildren().add(but);
        return new Scene(root, 800, 800);
    }
}

package passwordmanager.scenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import passwordmanager.controllers.SceneController;
import passwordmanager.models.Password;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainScene extends ViewScene {
    public MainScene(SceneController controller) {
        this.controller = controller;
    }

    @Override
    public Scene getScene() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        } catch (IOException e) {
            System.exit(-1);
        }
//        JFXTabPane tabPane = new JFXTabPane();
//        tabPane.setPrefSize(800, 800);
//        Tab tab = new Tab();
//        tab.setText("TEST");
//        tab.setContent(new Label("Content"));
//        Tab tab1 = new Tab();
//        tab1.setText("TEST");
//        tab1.setContent(new Label("Content"));
//        tabPane.getTabs().addAll(tab, tab1);
        return new Scene(root, 800, 800);
    }
}

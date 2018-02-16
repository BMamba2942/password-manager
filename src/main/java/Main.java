import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Password Manager");
        GridPane root = new GridPane();
        Label passwordLabel = new Label("Password");
        passwordLabel.setPadding(new Insets(0, 10, 0, 10));
        passwordLabel.setAlignment(Pos.CENTER);

        JFXPasswordField passwordField = new JFXPasswordField();
        passwordField.setAlignment(Pos.CENTER);

        root.add(passwordLabel, 0, 0);
        root.add(passwordField, 1, 0);
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

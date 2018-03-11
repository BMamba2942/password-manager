import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import passwordmanager.controllers.SceneController;
import passwordmanager.models.Password;
import passwordmanager.scenes.ViewScene;
import passwordmanager.scenes.PromptScene;
import passwordmanager.util.DBManager;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Main extends Application implements Observer {
    private Stage primaryStage;
    private SceneController sc;
    public static final String dbFile = "test.db";

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        DBManager db = new DBManager(dbFile);
        sc = new SceneController(db);
        List<Password> passwords = db.getPasswords();
        if (!passwords.isEmpty()) {
            sc.setCurrentScene(new PromptScene(sc));
        }
        this.primaryStage.setTitle("Password Manager");
        sc.addObserver(this);
        this.primaryStage.setScene(sc.getCurrentScene());
        this.primaryStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        ViewScene scene = (ViewScene)arg;
        this.primaryStage.setScene(scene.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

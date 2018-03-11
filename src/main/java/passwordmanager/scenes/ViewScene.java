package passwordmanager.scenes;

import javafx.scene.Scene;
import passwordmanager.controllers.SceneController;

public abstract class ViewScene {
    protected SceneController controller;
    public abstract Scene getScene();
}

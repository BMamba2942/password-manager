package passwordmanager.controllers;

import javafx.scene.Scene;
import passwordmanager.scenes.MainScene;
import passwordmanager.scenes.PromptScene;
import passwordmanager.scenes.ViewScene;
import passwordmanager.util.DBManager;

import java.util.Observable;

public class SceneController extends Observable {
    private ViewScene currentScene;
    private DBManager db;

    public SceneController(DBManager db) {
        this.currentScene = new MainScene(this);
        this.db = db;
    }

    public Scene getCurrentScene() {
        return this.currentScene.getScene();
    }

    public void setCurrentScene(ViewScene scene) {
        this.currentScene = scene;
    }

    public DBManager getDb() {
        return this.db;
    }

    public void nextScene() {
        if (currentScene instanceof MainScene) {
            this.currentScene = new PromptScene(this);
        } else if (currentScene instanceof PromptScene){
            this.currentScene = new MainScene(this);
        }
        setChanged();
        notifyObservers(this.currentScene);
    }
}

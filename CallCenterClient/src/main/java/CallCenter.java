/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class CallCenter extends Application {
    private static Stage stage;
    private static Scene rootScene;

    @Override
    public void start(final Stage s) throws Exception {
        CallCenter.stage = s;
        Parent root = FXMLLoader.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("rootSceneCallCenter.fxml")));

        rootScene = new Scene(root);
        rootScene.getStylesheets().add(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("style.css")).toString());

        stage.setTitle("Trasporti 116-117 ");

        setScene(SceneTypeCallCenter.ROOT_SCENE);
    }

    public static void setScene(final SceneTypeCallCenter type) {
        switch (type) {
            default:
                stage.setScene(rootScene);
                break;
        }
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }

}

package io.dbc.github.sninventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class SNApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                Screen.getPrimary().getBounds().getMinX(),
                Screen.getPrimary().getBounds().getMinY()
        );
        stage.setTitle("SN_INVENTORY !");
        stage.setScene(scene);
        stage.show();
    }
}
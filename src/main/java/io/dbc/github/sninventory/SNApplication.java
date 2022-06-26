package io.dbc.github.sninventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SNApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("mainWindow-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
//                Screen.getPrimary().getBounds().getMinX(),
//                Screen.getPrimary().getBounds().getMinY()
                600.0,400.0
        );
        stage.setTitle("SNInventory");
        stage.setScene(scene);
        stage.show();
    }
}
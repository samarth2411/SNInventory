package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MajorController {

    @FXML
    public Button expiredProductButton;

    @FXML
    public Button nearExpiryProductButton;

    @FXML
    public Button dumpStockButton;

    public void onExpiredProductButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("expiredProducts-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Screen.getPrimary().getBounds().getMinX(), Screen.getPrimary().getBounds().getMinY());
        stage.setTitle("Expired Products");
        stage.setScene(scene);
        stage.show();

    }

    public void onNearExpiryProductButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("nearExpiryProducts-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Screen.getPrimary().getBounds().getMinX(), Screen.getPrimary().getBounds().getMinY());
        stage.setTitle("Near Expiry Products");
        stage.setScene(scene);
        stage.show();

    }

    public void onDumpStockButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("dumpStock-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Screen.getPrimary().getBounds().getMinX(), Screen.getPrimary().getBounds().getMinY());
        stage.setTitle("Dump Stock");
        stage.setScene(scene);
        stage.show();

    }


}

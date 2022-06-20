package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class CreditController {
    @FXML
    public Button creditButton;
    @FXML
    public Button outstandingButton;

    public void onCreditButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("showCredits-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Screen.getPrimary().getBounds().getMinX(), Screen.getPrimary().getBounds().getMinY());
        stage.setTitle("Previous Credits ");
        stage.setScene(scene);
        stage.show();

    }

    public void onOutstandingButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("showOutstanding-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Screen.getPrimary().getBounds().getMinX(), Screen.getPrimary().getBounds().getMinY());
        stage.setTitle("Outstanding");
        stage.setScene(scene);
        stage.show();

    }
}

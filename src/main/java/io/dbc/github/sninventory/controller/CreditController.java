package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CreditController {
    @FXML
    public Button creditButton;
    @FXML
    public Button outstandingButton;
    public Button backButton;

    public void onCreditButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("showCredits-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650.0,400.0);
        stage.setTitle("Previous Credits ");
        stage.setScene(scene);
        stage.show();
        stage = (Stage)creditButton.getScene().getWindow();
        stage.close();

    }

    public void onOutstandingButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("showOutstanding-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),650.0,400.0);
        stage.setTitle("Outstanding");
        stage.setScene(scene);
        stage.show();
        stage = (Stage)outstandingButton.getScene().getWindow();
        stage.close();

    }

    public void onBackButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("mainWindow-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650.0,400.0);
        stage.setTitle("SNInventory");
        stage.setScene(scene);
        stage.show();
        stage = (Stage)backButton.getScene().getWindow();
        stage.close();
    }
}

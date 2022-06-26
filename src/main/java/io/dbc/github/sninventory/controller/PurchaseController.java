package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseController {


    @FXML
    public Button addNewPurchaseButton;

    @FXML
    public Button showPreviousPurchaseButton;
    @FXML
    public Button backButton;

    public void onAddNewPurchaseButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("addNewPurchase-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 400);
        stage.setTitle("Add New Purchase");
        stage.setScene(scene);
        stage.show();
        stage = (Stage) addNewPurchaseButton.getScene().getWindow();
        stage.close();
    }


    public void onshowPreviousPurchaseButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("showPreviousPurchase-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 400);
        stage.setTitle("Show Previous Purchase");
        stage.setScene(scene);
        stage.show();
        stage = (Stage) showPreviousPurchaseButton.getScene().getWindow();
        stage.close();
    }

    public void onBackButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("mainWindow-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650.0, 400.0);
        stage.setTitle("Product Details");
        stage.setScene(scene);
        stage.show();
        stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}

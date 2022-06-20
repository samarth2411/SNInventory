package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseController {


    @FXML
    public Button addNewPurchaseButton;

    @FXML
    public Button showPreviousPurchaseButton;

    public void onAddNewPurchaseButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("addNewPurchase-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Screen.getPrimary().getBounds().getMinX(), Screen.getPrimary().getBounds().getMinY());
        stage.setTitle("Add New Purchase");
        stage.setScene(scene);
        stage.show();
    }


    public void onshowPreviousPurchaseButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("showPreviousPurchase-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Screen.getPrimary().getBounds().getMinX(), Screen.getPrimary().getBounds().getMinY());
        stage.setTitle("Show Previous Purchase");
        stage.setScene(scene);
        stage.show();
    }
}

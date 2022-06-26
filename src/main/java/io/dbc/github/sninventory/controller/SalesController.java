package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SalesController {
    @FXML
    public Button addNewSaleButton;
    @FXML
    public Button showPreviousSalesButton;
    public Button backButton;

    public void onAddNewSaleButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("addNewSale-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),650.0,400.0);
        stage.setTitle("Add New Sale");
        stage.setScene(scene);
        stage.show();
        stage = (Stage)addNewSaleButton.getScene().getWindow();
        stage.close();
    }

    public void onShowPreviousSalesButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("showPreviousSales-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),650.0,400.0);
        stage.setTitle("Previous Sales");
        stage.setScene(scene);
        stage.show();
        stage = (Stage)showPreviousSalesButton.getScene().getWindow();
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

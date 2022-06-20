package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    @FXML
    public Button currentStockButton;
    @FXML
    public Button productDetailsButton;
    @FXML
    public Button purchaseButton;
    @FXML
    public Button salesButton;
    @FXML
    public Button creditButton;
    @FXML
    public Button debitButton;
    @FXML
    public Button profitButton;
    @FXML
    public Button majorButton;

    public void onCurrentStockButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("currentStock-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                Screen.getPrimary().getBounds().getMinX(),
                Screen.getPrimary().getBounds().getMinY()
        );
        stage.setTitle("Current Stock");
        stage.setScene(scene);
        stage.show();
    }

    public void onProductDetailsButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("productDetails-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                Screen.getPrimary().getBounds().getMinX(),
                Screen.getPrimary().getBounds().getMinY()
        );
        stage.setTitle("Product Details");
        stage.setScene(scene);
        stage.show();


    }

    public void onPurchaseButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("purchase-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                Screen.getPrimary().getBounds().getMinX(),
                Screen.getPrimary().getBounds().getMinY()
        );
        stage.setTitle("Purchase");
        stage.setScene(scene);
        stage.show();
    }

    public void onSalesButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("sales-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                Screen.getPrimary().getBounds().getMinX(),
                Screen.getPrimary().getBounds().getMinY()
        );
        stage.setTitle("Sales");
        stage.setScene(scene);
        stage.show();
    }

    public void onCreditButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("credit-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                Screen.getPrimary().getBounds().getMinX(),
                Screen.getPrimary().getBounds().getMinY()
        );
        stage.setTitle("Credit");
        stage.setScene(scene);
        stage.show();
    }

    public void onDebitButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("debit-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                Screen.getPrimary().getBounds().getMinX(),
                Screen.getPrimary().getBounds().getMinY()
        );
        stage.setTitle("Debit");
        stage.setScene(scene);
        stage.show();
    }

    public void onProfitButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("profit-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                Screen.getPrimary().getBounds().getMinX(),
                Screen.getPrimary().getBounds().getMinY()
        );
        stage.setTitle("Profit");
        stage.setScene(scene);
        stage.show();
    }

    public void onMajorButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("major-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                Screen.getPrimary().getBounds().getMinX(),
                Screen.getPrimary().getBounds().getMinY()
        );
        stage.setTitle("Major");
        stage.setScene(scene);
        stage.show();
    }
}
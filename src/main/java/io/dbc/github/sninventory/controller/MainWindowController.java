package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.service.FXMLloader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("currentStock-view.fxml","Current Stock");
        fxmLloader.close(currentStockButton);

    }

    public void onProductDetailsButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("productDetails-view.fxml","Product Details");
        fxmLloader.close(productDetailsButton);

    }

    public void onPurchaseButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("purchase-view.fxml","Purchase");
        fxmLloader.close(purchaseButton);
    }

    public void onSalesButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("sales-view.fxml","Sales");
        fxmLloader.close(salesButton);
    }

    public void onCreditButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("credit-view.fxml","Credit");
        fxmLloader.close(creditButton);
    }

    public void onDebitButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("debit-view.fxml","Debit");
        fxmLloader.close(debitButton);
    }

    public void onProfitButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("profit-view.fxml","Profit");
        fxmLloader.close(profitButton);
    }

    public void onMajorButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("major-view.fxml","Major");
        fxmLloader.close(majorButton);
    }


}

package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.service.FXMLloader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PurchaseController {


    @FXML
    public Button addNewPurchaseButton;

    @FXML
    public Button showPreviousPurchaseButton;
    @FXML
    public Button backButton;

    public void onAddNewPurchaseButtonClick() throws IOException {

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("addNewPurchase-view.fxml","Add New Purchase");
        fxmLloader.close(addNewPurchaseButton);
    }


    public void onshowPreviousPurchaseButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("showPreviousPurchase-view.fxml","Show Previous Purchase");
        fxmLloader.close(showPreviousPurchaseButton);
    }

    public void onBackButtonClick() throws IOException {

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("mainWindow-view.fxml","Stock Management System");
        fxmLloader.close(backButton);
    }
}

package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.service.FXMLloader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class CreditController {

    @FXML
    public Button creditButton;
    @FXML
    public Button outstandingButton;
    @FXML
    public Button backButton;

    public void onCreditButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("showCredits-view.fxml","Credit");
        fxmLloader.close(creditButton);
    }

    public void onOutstandingButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("showOutstanding-view.fxml","Outstanding");
        fxmLloader.close(outstandingButton);

    }

    public void onBackButtonClick() throws IOException {

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("mainWindow-view.fxml","Stock Management System");
        fxmLloader.close(backButton);
    }
}

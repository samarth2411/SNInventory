package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.service.FXMLloader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SalesController {


    @FXML
    public Button addNewSalesButton;

    @FXML
    public Button showPreviousSalesButton;
    @FXML
    public Button backButton;

    public void onaddNewSalesButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("addNewSale-view.fxml","Add New Sale");
        fxmLloader.close(addNewSalesButton);

    }

    public void onshowPreviousSalesButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("showPreviousSales-view.fxml","Previous Sales");
        fxmLloader.close(showPreviousSalesButton);

    }

    public void onBackButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("mainWindow-view.fxml","Product Details");
        fxmLloader.close(backButton);

    }
}

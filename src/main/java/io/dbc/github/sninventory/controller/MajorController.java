package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.service.FXMLloader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MajorController {

    @FXML
    public Button expiredProductButton;

    @FXML
    public Button nearExpiryProductButton;

    @FXML
    public Button dumpStockButton;
    @FXML
    public Button backButton;

    public void onExpiredProductButtonClick() throws IOException {

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("expiredProducts-view.fxml","Expired Products");
        fxmLloader.close(expiredProductButton);
    }

    public void onNearExpiryProductButtonClick() throws IOException {

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("nearExpiryProducts-view.fxml","Near Expiry Products");
        fxmLloader.close(nearExpiryProductButton);
    }

    public void onDumpStockButtonClick() throws IOException {

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("dumpStock-view.fxml","Dump Stock");
        fxmLloader.close(dumpStockButton);

    }
    public void onBackButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("mainWindow-view.fxml","Product Details");
        fxmLloader.close(backButton);
    }
}

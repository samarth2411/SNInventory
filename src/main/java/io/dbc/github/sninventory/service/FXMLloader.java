package io.dbc.github.sninventory.service;

import io.dbc.github.sninventory.SNApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLloader {
//   public Button button;
//   public String resource;
//   public String resourceTitle;
//
//    public FXMLloader(Button button, String resource, String resourceTitle) {
//        this.button = button;
//        this.resource = resource;
//        this.resourceTitle = resourceTitle;
//    }
    public Stage stage=new Stage();
    public void load(String resource,String resourceTitle){
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource(resource));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 650.0, 400.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(resourceTitle);
        stage.setScene(scene);
        stage.show();

    }
    public void close(Button button){
        stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}

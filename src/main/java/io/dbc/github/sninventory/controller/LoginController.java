package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.service.FXMLloader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    // button
    @FXML
    public Button loginButton;

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField usernameTextField;

    // a method to control the button
    @FXML
    public void onLoginButtonClick() throws IOException {

        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("empty fields");
            alert.setContentText("Please make sure that the username and password fields are not empty");
            alert.showAndWait();
        } else if ("samarth".equals(username) && "password123".equals(password)) {


            FXMLloader fxmLloader=new FXMLloader();
            fxmLloader.load("mainWindow-view.fxml","Stock Management System");
            fxmLloader.close(loginButton);


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WRONG DETAILS");
            alert.setContentText("Please make sure that the username and password fields are correct");
            alert.showAndWait();
        }

    }


    // JDBC
    // create connection to database
    // write a sql query to get values from the database in the controller
    // check the values from user with the database values
    // correct, then open main windows, and call stage.close();
    // open the main window
}

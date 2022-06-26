package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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


            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("mainWindow-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650.0, 400.0);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

            stage = (Stage) loginButton.getScene().getWindow();
            stage.close();


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

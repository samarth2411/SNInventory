package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import io.dbc.github.sninventory.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewProductController {
    @FXML
    public TextField productNameTextField;
    @FXML
    public TextField sellingPriceTextField;
    @FXML
    public TextField descriptionTextField;
    @FXML
    public Button addButton;
    @FXML
    public Button backButton;

    public void addNewProductClick() throws SQLException, ClassNotFoundException, IOException {

        String productName = productNameTextField.getText();

        double productPrice = Double.parseDouble(sellingPriceTextField.getText());

        String description = descriptionTextField.getText();

        Connection connection = DatabaseConnection.addConnection();

        String insertQuery =
                "INSERT INTO PRODUCT_DETAILS VALUES (?,?,?,?);";


        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, productName);
        preparedStatement.setDouble(2, productPrice);
        preparedStatement.setInt(3, 0);
        preparedStatement.setString(4, description);

        preparedStatement.executeUpdate();


        JOptionPane.showMessageDialog(
                null,
                productName + " added to the database",
                "Query Executed Successfully",
                JOptionPane.INFORMATION_MESSAGE
        );
        Stage stage = new Stage();
        stage = (Stage)addButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("productDetails-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650.0,400.0);

        stage.setTitle("Product Details");
        stage.setScene(scene);
        stage.show();



    }

    public void onBackButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("productDetails-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650.0,400.0);
        stage.setTitle("Product Details ");
        stage.setScene(scene);
        stage.show();
        stage = (Stage)backButton.getScene().getWindow();
        stage.close();
    }
}









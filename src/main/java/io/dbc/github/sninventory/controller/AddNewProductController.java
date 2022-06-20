package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
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

    public void onAddButtonClick() throws SQLException, ClassNotFoundException {


        String productName = productNameTextField.getText();

        double productPrice = Double.parseDouble(sellingPriceTextField.getText());

        String description = descriptionTextField.getText();


        Connection connection = DatabaseConnection.addConnection();

        String insertQuery = "INSERT into product_details values (?,?,?,?);";


        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, productName);
        preparedStatement.setDouble(2, productPrice);
        preparedStatement.setInt(3, 0);
        preparedStatement.setString(4, description);

        preparedStatement.execute();

        JOptionPane.showMessageDialog(null, productName + "Product Added to the System", "Successfully Added", JOptionPane.INFORMATION_MESSAGE);

    }
}




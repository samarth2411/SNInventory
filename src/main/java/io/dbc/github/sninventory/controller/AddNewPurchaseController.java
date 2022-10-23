package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.database.DatabaseConnection;
import io.dbc.github.sninventory.service.FXMLloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddNewPurchaseController implements Initializable {

    @FXML
    public TextField quantityTextField;

    @FXML
    public TextField priceOfProductTextField;

    @FXML
    public ChoiceBox<String> productNameChoiceBox;
    @FXML
    public Button addButton;
    @FXML
    public DatePicker expiryDatePicker;
    @FXML
    public Button backButton;

    ObservableList<String> list = FXCollections.observableArrayList();
    Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT ProductName FROM product_details";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);


            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            productNameChoiceBox.getItems().addAll(list);

        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void onAddButtonClick() throws SQLException, ClassNotFoundException, IOException {
        try{
        connection = DatabaseConnection.addConnection();
        String productName = productNameChoiceBox.getSelectionModel().getSelectedItem();
        int productQuantity = Integer.parseInt(quantityTextField.getText());
        double purchasePrice = Double.parseDouble(priceOfProductTextField.getText());
        Date expiryDate = Date.valueOf(expiryDatePicker.getValue());

        String insertQuery =
                "INSERT Into purchase(productname, quantity, purchaseprice, purchasedate , ExpiryDate)\n" +
                        " VALUES (?,?,?,?,?)";

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, productName);
        preparedStatement.setInt(2, productQuantity);
        preparedStatement.setDouble(3, purchasePrice);
        preparedStatement.setDate(4, date);
        preparedStatement.setDate(5, expiryDate);


        preparedStatement.executeUpdate();

        String updateQuery =
                "UPDATE product_details set Quantity=Quantity+? where ProductName=?";
        preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setInt(1, productQuantity);
        preparedStatement.setString(2, productName);
        preparedStatement.executeUpdate();


        JOptionPane.showMessageDialog(
                null,
                productQuantity + productName + "Purchased",
                "Successfully Purchased",
                JOptionPane.INFORMATION_MESSAGE
        );

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.close(addButton);
        fxmLloader.load("showPreviousPurchase-view.fxml","Previous Purchase.");
        }  catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WRONG INPUT");
            alert.setContentText("Please give the valid inputs");
            alert.showAndWait();
        }  catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("EMPTY FIELDS");
            alert.setContentText("Please Fill All The Fields");
            alert.showAndWait();
        }
    }

    public void onBackButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("purchase-view.fxml","Product Details");
        fxmLloader.close(backButton);
    }
}

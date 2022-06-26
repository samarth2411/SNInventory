package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import io.dbc.github.sninventory.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddNewSaleController implements Initializable {
    @FXML
    public ChoiceBox<String> productNameChoiceBox;
    @FXML
    public TextField quantityTextField;
    @FXML
    public TextField priceOfProductTextField;
    @FXML
    public ChoiceBox<Boolean> billPaidChoiceBox;
    @FXML
    public Button addButton;
    @FXML
    public Button backButton;


    ObservableList<String> list = FXCollections.observableArrayList();
    Connection connection;
    Stage stage=new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT ProductName FROM product_details where Quantity>0";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            productNameChoiceBox.getItems().addAll(list);
            billPaidChoiceBox.getItems().addAll(Boolean.TRUE, Boolean.FALSE);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void onAddButtonClick() throws SQLException, ClassNotFoundException, IOException {
        connection = DatabaseConnection.addConnection();
        String productName = productNameChoiceBox.getSelectionModel().getSelectedItem();
        int productQuantity = Integer.parseInt(quantityTextField.getText());
        double purchasePrice = Double.parseDouble(priceOfProductTextField.getText());
        boolean billPaid = billPaidChoiceBox.getSelectionModel().getSelectedItem();

        String selectQuery1 = "SELECT Quantity From product_details where ProductName='" + productName + "' ";
        PreparedStatement preparedStatement1 = connection.prepareStatement(selectQuery1);
        ResultSet resultSet1 = preparedStatement1.executeQuery(selectQuery1);
        resultSet1.next();
        int currentQuantity = resultSet1.getInt(1);
        if (currentQuantity >= productQuantity) {
            String insertQuery =
                    "INSERT Into sales(productname, quantitysold, sellingprice, dateofsale, billpaid)\n" +
                            " VALUES (?,?,?,?,?)";


            Date date = new Date(Calendar.getInstance().getTime().getTime());

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, productQuantity);
            preparedStatement.setDouble(3, purchasePrice);
            preparedStatement.setDate(4, date);
            preparedStatement.setBoolean(5, billPaid);
            preparedStatement.executeUpdate();


            String updateQuery = "" +
                    "UPDATE product_details set Quantity=product_details.Quantity-? where ProductName=?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, productQuantity);
            preparedStatement.setString(2, productName);
            preparedStatement.executeUpdate();


            JOptionPane.showMessageDialog(
                    null,
                    productName + " added to the database",
                    "Query Executed Successfully",
                    JOptionPane.INFORMATION_MESSAGE
            );

            stage = (Stage)addButton.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("showPreviousSales-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650.0,400.0);

            stage.setTitle("Previous Sales.");
            stage.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LESS PRODUCT AVAILABLE");
            alert.setContentText("The available quantity of" + productName + " is " + currentQuantity);
            alert.showAndWait();
            stage = (Stage)addButton.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("addNewSale-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650.0,400.0);

            stage.setTitle(" Add New Sale.");
            stage.setScene(scene);

        }
        stage.show();

    }

    public void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("sales-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                650.0,400.0
        );
        stage.setTitle("Sales");
        stage.setScene(scene);
        stage.show();
        stage = (Stage)backButton.getScene().getWindow();
        stage.close();
    }
}

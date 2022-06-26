package io.dbc.github.sninventory.controller;


import io.dbc.github.sninventory.SNApplication;
import io.dbc.github.sninventory.database.DatabaseConnection;
import io.dbc.github.sninventory.model.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;


public class ExpiredProductsController implements Initializable {
    @FXML
    public TableColumn<Purchase, String> productNameColumn;
    @FXML
    public TableColumn<Purchase, Integer> quantityColumn;
    @FXML
    public TableColumn<Purchase, Date> expiryDateColumn;
    @FXML
    public TableColumn<Purchase, Integer> purchaseIDColumn;
    @FXML
    public TableView<Purchase> expiredProductTable;
    @FXML
    public Button backButton;
    @FXML
    public Button removeAllProductsButton;


    ObservableList<Purchase> list = FXCollections.observableArrayList();

    ObservableList<Purchase> list1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        purchaseIDColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityPurchased"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        try {
            Connection connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT ProductName,Quantity FROM product_details where Quantity>0";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);

            while (resultSet.next()) {
                String product = resultSet.getString(1);
                int currentQuantity = resultSet.getInt(2);
                String selectQuery1 = "Select PurchaseID,ProductName,Quantity,ExpiryDate from purchase where ProductName= '" + product + "'";
                PreparedStatement preparedStatement1 = connection.prepareStatement(selectQuery1);
                ResultSet resultSet1 = preparedStatement1.executeQuery(selectQuery1);


                while (resultSet1.next()) {
                    list.add(new Purchase(resultSet1.getInt(1),
                            resultSet1.getString(2),
                            resultSet1.getInt(3),
                            resultSet1.getDate(4)
                    ));
                }

                FXCollections.reverse(list);

                while (currentQuantity > 0) {
                    for (Purchase purchase : list) {
                        if (!purchase.getExpiryDate().before(new Date(Calendar.getInstance().getTime().getTime()))) {
                            currentQuantity -= purchase.getQuantityPurchased();
                        }
                        else if(currentQuantity > 0)
                        {
                            if (currentQuantity >=purchase.getQuantityPurchased()) {
                                list1.add(purchase);
                                currentQuantity -= purchase.getQuantityPurchased();
                            }
                            else  {
                                list1.add(new Purchase(purchase.getPurchaseID(), purchase.getProductName(),
                                        currentQuantity, purchase.getExpiryDate()));
                                currentQuantity = 0;
                                break;
                            }
                        }
                    }

                    expiredProductTable.setItems(list1);
                    }
                }

        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();

        }
    }

    public void onRemoveAllProductsButtonClick() throws SQLException, ClassNotFoundException {
        for (Purchase purchase:list1) {
            Connection connection=DatabaseConnection.addConnection();
            String updateQuery="UPDATE product_details set Quantity=product_details.Quantity-? where ProductName=?";
            PreparedStatement preparedStatement=connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1,purchase.getQuantityPurchased());
            preparedStatement.setString(2,purchase.getProductName());
            preparedStatement.executeUpdate();
        }
    }

    public void onBackButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("major-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650.0,400.0);
        stage.setTitle("Major");
        stage.setScene(scene);
        stage.show();
        stage = (Stage)backButton.getScene().getWindow();
        stage.close();

    }
}
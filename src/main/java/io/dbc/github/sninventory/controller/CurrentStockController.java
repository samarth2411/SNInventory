package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.database.DatabaseConnection;
import io.dbc.github.sninventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CurrentStockController implements Initializable {
    @FXML
    public TableColumn<Product, String> productNameColumn;
    @FXML
    public TableColumn<Product, Integer> quantityColumn;
    @FXML
    public TableColumn<Product, Double> priceColumn;
    @FXML
    public TableView<Product> currentStockTable;

    ObservableList<Product> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));


        try {
            Connection connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT productName,quantity,productPrice FROM product_details where Quantity>0";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);

            while (resultSet.next()) {
                list.add(new Product(
                                resultSet.getString(1),
                                resultSet.getInt(2),
                                resultSet.getDouble(3)
                        )
                );
            }
            currentStockTable.setItems(list);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}

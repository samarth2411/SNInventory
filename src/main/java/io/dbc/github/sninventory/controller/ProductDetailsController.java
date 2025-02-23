package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.database.DatabaseConnection;
import io.dbc.github.sninventory.model.Product;
import io.dbc.github.sninventory.service.FXMLloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ProductDetailsController implements Initializable {


    @FXML
    public Button addNewProductButton;

    @FXML
    public TableColumn<Product, String> productNameColumn;
    @FXML
    public TableColumn<Product, Integer> quantityColumn;
    @FXML
    public TableColumn<Product, Double> priceColumn;
    @FXML
    public TableColumn<Product, String> descriptionColumn;
    @FXML
    public TableView<Product> productDetailsTable;
    @FXML
    public Button backButton;

    ObservableList<Product> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {

            Connection connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT * FROM product_details";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);

            while (resultSet.next()) {
                list.add(new Product(resultSet.getString(1),
                        resultSet.getDouble(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)));
            }
            productDetailsTable.setItems(list);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }


    public void onAddNewProductButtonClick() throws IOException {

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("addNewProduct-view.fxml","Add New Product");
        fxmLloader.close(addNewProductButton);
    }

    public void onBackButtonClick() throws IOException {

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("mainWindow-view.fxml","Product Details");
        fxmLloader.close(backButton);
    }
}

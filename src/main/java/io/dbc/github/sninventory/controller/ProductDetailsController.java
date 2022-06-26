package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import io.dbc.github.sninventory.database.DatabaseConnection;
import io.dbc.github.sninventory.model.Product;
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
        Stage stage = new Stage();
        FXMLLoader fxmlLoader =
                new FXMLLoader(SNApplication.class.getResource("addNewProduct-view.fxml"));
        Scene scene = new Scene(
                fxmlLoader.load(),
                650, 400
        );
        stage.setTitle("Add New Product");
        stage.setScene(scene);
        stage.show();
        stage = (Stage) addNewProductButton.getScene().getWindow();
        stage.close();

    }

    public void onBackButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("mainWindow-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650.0, 400.0);
        stage.setTitle("Product Details");
        stage.setScene(scene);
        stage.show();
        stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}

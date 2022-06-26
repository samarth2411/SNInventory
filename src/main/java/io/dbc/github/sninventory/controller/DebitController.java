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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DebitController implements Initializable {
    @FXML
    public TableColumn<Purchase, String> productNameColumn;
    @FXML
    public TableColumn<Purchase, Integer> purchaseIdColumn;
    @FXML
    public TableColumn<Purchase, Integer> quantityColumn;
    @FXML
    public TableColumn<Purchase, Date> purchaseDateColumn;
    @FXML
    public TableColumn<Purchase, Double> purchasePriceColumn;
    @FXML
    public TableColumn<Purchase, Double> totalAmountColumn;
    @FXML
    public TableView<Purchase> debitTable;
    @FXML
    public Button backButton;

    ObservableList<Purchase> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        purchaseIdColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityPurchased"));
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        try {
            Connection connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT * FROM purchase";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);

            while (resultSet.next()) {
                list.add(new Purchase(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),

                                resultSet.getDouble(4),
                                resultSet.getDate(5),
                                (resultSet.getInt(3) * resultSet.getDouble(4))
                        )
                );
            }
            debitTable.setItems(list);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
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

package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.SNApplication;
import io.dbc.github.sninventory.database.DatabaseConnection;
import io.dbc.github.sninventory.model.Sale;
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

public class ShowCreditsController implements Initializable {
    @FXML
    public TableColumn<Sale, Integer> saleIDColumn;
    @FXML
    public TableColumn<Sale, String> productNameColumn;
    @FXML
    public TableColumn<Sale, Date> dateOfSaleColumn;
    @FXML
    public TableColumn<Sale, Integer> quantityColumn;
    @FXML
    public TableColumn<Sale, Double> sellingPriceColumn;
    @FXML
    public TableColumn<Sale, Double> totalAmountColumn;
    @FXML
    public TableView<Sale> showCreditTable;
    @FXML
    public Button backButton;

    ObservableList<Sale> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saleIDColumn.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantitySold"));
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        dateOfSaleColumn.setCellValueFactory(new PropertyValueFactory<>("DateOfSale"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        try {
            Connection connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT * FROM sales where BillPaid=true";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);

            while (resultSet.next()) {
                list.add(new Sale(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getDouble(4),
                                resultSet.getDate(5),
                                (resultSet.getDouble(4) * resultSet.getInt(3))
                        )
                );
            }

            showCreditTable.setItems(list);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void onBackButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SNApplication.class.getResource("credit-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650.0, 400.0);
        stage.setTitle("Product Details");
        stage.setScene(scene);
        stage.show();
        stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}

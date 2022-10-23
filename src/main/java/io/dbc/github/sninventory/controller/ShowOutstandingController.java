package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.database.DatabaseConnection;
import io.dbc.github.sninventory.model.Sale;
import io.dbc.github.sninventory.service.FXMLloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ShowOutstandingController implements Initializable {
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
    public TableView<Sale> showOutstandingTable;
    @FXML
    public Button backButton;
    @FXML
    public Button creditedButton;
    @FXML
    public TextField saleIdTextField;

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

            String selectQuery = "SELECT * FROM sales where BillPaid=false";
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

            showOutstandingTable.setItems(list);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void onCreditedButtonClick() throws SQLException, ClassNotFoundException, IOException {
        if (!saleIdTextField.getText().isBlank()) {
            int enterSaleId = Integer.parseInt(saleIdTextField.getText());
            Connection connection = DatabaseConnection.addConnection();
            String updateQuery = "UPDATE sales set BillPaid=true WHERE SalesID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, enterSaleId);
            preparedStatement.executeUpdate();
        } else {
            System.err.println("Text Field Empty");
        }

        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.close(creditedButton);
        fxmLloader.load("showOutstanding-view.fxml","Outstanding");

    }


    public void onBackButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("credit-view.fxml","Credit");
        fxmLloader.close(backButton);
    }
}

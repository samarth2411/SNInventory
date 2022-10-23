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
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class ShowPreviousSalesController implements Initializable {
    @FXML
    public TableColumn<Sale, Integer> saleIdColumn;
    @FXML
    public TableColumn<Sale, String> productNameColumn;
    @FXML
    public TableColumn<Sale, Integer> quantityColumn;
    @FXML
    public TableColumn<Sale, Double> sellingPriceColumn;
    @FXML
    public TableColumn<Sale, Date> dateOfSaleColumn;
    @FXML
    public TableColumn<Sale, Boolean> billpaidColumn;
    @FXML
    public TableView<Sale> showPreviousSalesTable;
    @FXML
    public Button backButton;

    ObservableList<Sale> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saleIdColumn.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantitySold"));
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        dateOfSaleColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));
        billpaidColumn.setCellValueFactory(new PropertyValueFactory<>("billPaid"));


        try {
            Connection connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT * FROM sales";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);

            while (resultSet.next()) {
                list.add(new Sale(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getDouble(4),
                                resultSet.getDate(5),
                                resultSet.getBoolean(6)

                        )
                );
            }
            showPreviousSalesTable.setItems(list);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }


    public void onBackButtonClick()  {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("sales-view.fxml","Sales");
        fxmLloader.close(backButton);
            }
}

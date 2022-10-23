package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.database.DatabaseConnection;
import io.dbc.github.sninventory.model.Purchase;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public TableColumn<Purchase, Integer> purchaseIdColumn;
    @FXML
    public TableView<Purchase> expiredProductTable;
    @FXML
    public Button backButton;



    ObservableList<Purchase> list = FXCollections.observableArrayList();
    ObservableList<Purchase> list1 = FXCollections.observableArrayList();
    ObservableList<Purchase> list2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        purchaseIdColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityPurchased"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        try {
            Connection connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT ProductName,Quantity FROM product_details";
            PreparedStatement preparedStatement3 = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement3.executeQuery(selectQuery);

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
                        } else {
                            if (currentQuantity > purchase.getQuantityPurchased()) {
                                list1.add(purchase);
                                currentQuantity -= purchase.getQuantityPurchased();
                            } else if (currentQuantity > 0 && currentQuantity < purchase.getQuantityPurchased()) {
                                list1.add(new Purchase(purchase.getPurchaseID(), purchase.getProductName(),
                                        currentQuantity, purchase.getExpiryDate()));
                                currentQuantity = 0;
                                break;
                            }
                        }
                    }
                }

            }
                    for(Purchase purchase1:list1){
                  String insertQuery = "INSERT into expired_products values (?,?,?,?);";

                    PreparedStatement preparedStatement2 = connection.prepareStatement(insertQuery);
                    preparedStatement2.setInt(1, purchase1.getPurchaseID());
                    preparedStatement2.setString(2, purchase1.getProductName());
                    preparedStatement2.setInt(3, purchase1.getQuantityPurchased());
                    preparedStatement2.setDate(4,purchase1.getExpiryDate());

                    preparedStatement2.execute();

                        String updateQuery =
                                "UPDATE product_details set Quantity=product_details.Quantity-? where ProductName=?";
                        preparedStatement3 = connection.prepareStatement(updateQuery);
                        preparedStatement3.setInt(1, purchase1.getQuantityPurchased());
                        preparedStatement3.setString(2, purchase1.getProductName());
                        preparedStatement3.executeUpdate();
                }
                    String selectQuery2 = "SELECT * FROM expired_products";
                    PreparedStatement preparedStatement4 = connection.prepareStatement(selectQuery2);
                    ResultSet finalResultSet = preparedStatement4.executeQuery(selectQuery2);

                    while (finalResultSet.next()) {
                        list2.add(new Purchase(finalResultSet.getInt(1),
                                finalResultSet.getString(2),
                                finalResultSet.getInt(3),
                                finalResultSet.getDate(4)
                        ));
                    }
                    expiredProductTable.setItems(list2);

        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void onBackButtonClick() throws IOException {
        FXMLloader fxmLloader=new FXMLloader();
        fxmLloader.load("major-view.fxml","Product Details");
        fxmLloader.close(backButton);
    }
}

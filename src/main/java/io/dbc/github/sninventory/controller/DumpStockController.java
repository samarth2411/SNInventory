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
import java.util.Calendar;
import java.util.ResourceBundle;

public class DumpStockController implements Initializable {
    @FXML
    public TableColumn<Purchase , Integer> purchaseIdColumn;
    @FXML
    public TableColumn<Purchase , String> productNameColumn;
    @FXML
    public TableColumn<Purchase , Integer> quantityColumn;
    @FXML
    public TableColumn<Purchase , Date> purchaseDateColumn;
    @FXML
    public TableView<Purchase> dumpStockTable;
    public Button backButton;

    ObservableList<Purchase> list = FXCollections.observableArrayList();
    ObservableList<Purchase> list1 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        purchaseIdColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityPurchased"));
        purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        try {
            Connection connection = DatabaseConnection.addConnection();

            String selectQuery = "SELECT ProductName,Quantity  FROM product_details where Quantity>0";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);
            Date lastSaleDate = null;

            while(resultSet.next()){
                String product = resultSet.getString(1);
                int currentQuantity=resultSet.getInt(2);
                String selectQuery1 = "Select PurchaseID,ProductName,PurchaseDate,Quantity from purchase where ProductName= '" + product + "'";
                PreparedStatement preparedStatement1 = connection.prepareStatement(selectQuery1);
                ResultSet resultSet1 = preparedStatement1.executeQuery(selectQuery1);


                while (resultSet1.next()) {
                    list.add(new Purchase(
                            resultSet1.getInt(1),
                            resultSet1.getString(2),
                            resultSet1.getDate(3),
                            resultSet1.getInt(4)
                    ));



                    FXCollections.reverse(list);
                    String selectQuery2="SELECT DateOfSale from sales where ProductName= '" + product + "'";
                    PreparedStatement preparedStatement2=connection.prepareStatement(selectQuery2);
                    ResultSet resultSet2=preparedStatement2.executeQuery(selectQuery2);

                    while (resultSet2.next()){
                        lastSaleDate=resultSet2.getDate(1);
                    }


                    float daysDifference = (( new Date(Calendar.getInstance().getTime().getTime()).getTime()-lastSaleDate.getTime()) / (1000 * 60 * 60 * 24));

                    if(daysDifference>=365.0){
                        Purchase purchase =list.get(0);
                        if(currentQuantity>=purchase.getQuantityPurchased()){
                            list1.add(purchase);
                            currentQuantity-=purchase.getQuantityPurchased();
                        }
                        else {
                        list1.add(new Purchase(purchase.getPurchaseID(), purchase.getProductName(),purchase.getPurchaseDate(),currentQuantity));
                    }}



            }
            dumpStockTable.setItems(list1);
        }}
        catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
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
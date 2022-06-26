package io.dbc.github.sninventory.controller;

import io.dbc.github.sninventory.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ProfitController implements Initializable {
    @FXML
    public AnchorPane profitAnchorPane;
    @FXML
    public LineChart profitLineChart;
    @FXML
    public TextField profitTextField;
    @FXML
    public AnchorPane lossAnchorPane;
    @FXML
    public LineChart lossLineChart;
    @FXML
    public TextField lossTextField;


    ObservableList<Double> list1 = FXCollections.observableArrayList();
    ObservableList<Double> list2 = FXCollections.observableArrayList();
    Connection connection;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            connection = DatabaseConnection.addConnection();

            String selectQuery1 = "SELECT Quantity , PurchasePrice FROM purchase";
            PreparedStatement preparedStatement1 = connection.prepareStatement(selectQuery1);
            ResultSet resultSet1 = preparedStatement1.executeQuery(selectQuery1);

            String selectQuery2 = "SELECT QuantitySold , SellingPrice FROM sales";
            PreparedStatement preparedStatement2 = connection.prepareStatement(selectQuery2);
            ResultSet resultSet2 = preparedStatement2.executeQuery(selectQuery2);


            while (resultSet1.next()) {
                list1.add(resultSet1.getDouble(1) * resultSet1.getDouble(2));
            }

            while (resultSet1.next()) {
                list2.add(resultSet2.getDouble(1) * resultSet2.getDouble(2));
            }
            double debit = 0;
            double credit = 0;
            for (int i = 1; i < list1.size(); i++) {
                debit = debit + list1.get(i);
            }

            for (int i = 1; i < list2.size(); i++) {
                credit = credit + list2.get(i);
            }
            if (credit > debit) {
                profitTextField.setTranslateX(credit - debit);          // profit ki text field mei show ho jae credit - debit
            }
            if (credit < debit) {
                lossTextField.setTranslateX(debit - credit);             // loss ki text field mei show ho jae debit - credit
            }


        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }

    }
}

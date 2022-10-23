
module io.dbc.github.sninventory{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;

    opens io.dbc.github.sninventory.model to javafx.fxml;
    exports io.dbc.github.sninventory.model;
    opens io.dbc.github.sninventory.controller to javafx.fxml;
    exports io.dbc.github.sninventory.controller;
    opens io.dbc.github.sninventory to javafx.fxml;
    exports io.dbc.github.sninventory;
}
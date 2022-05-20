module io.dbc.github.sninventory {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.dbc.github.sninventory to javafx.fxml;
    exports io.dbc.github.sninventory;
    exports io.dbc.github.sninventory.controller;
    opens io.dbc.github.sninventory.controller to javafx.fxml;
}
module crudhotel {
    requires javafx.controls;
    requires java.sql;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.base;
    
    opens application to javafx.graphics, javafx.fxml, javafx.base;
}

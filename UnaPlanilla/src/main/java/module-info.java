module cr.ac.una.unaplanilla {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.logging;
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;
    requires java.sql;
    requires java.base;
    requires jakarta.json;

    
    //TODO
    
    opens cr.ac.una.unaplanilla to javafx.fxml,javafx.graphics;
    opens cr.ac.una.unaplanilla.controller to java.base,javafx.fxml,javafx.base,com.jfoenix;
    opens cr.ac.una.unaplanilla.util to java.base,javafx.fxml,javafx.base,com.jfoenix;
    
    exports cr.ac.una.unaplanilla.model;
}

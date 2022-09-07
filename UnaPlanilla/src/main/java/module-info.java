module cr.ac.una.unaplanilla {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.logging;
    
    //TODO
    
    opens cr.ac.una.unaplanilla to javafx.fxml;
    opens cr.ac.una.unaplanilla.controller to java.base,javafx.fxml,javafx.base,com.jfoenix;
    opens cr.ac.una.unaplanilla.util to java.base,javafx.fxml,javafx.base,com.jfoenix;
    
}

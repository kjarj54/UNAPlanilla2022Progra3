/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import cr.ac.una.unaplanilla.util.FlowController;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class Principal2Controller implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private JFXButton btnEmpleados;
    @FXML
    private JFXButton btnPlanillas;
    @FXML
    private JFXHamburger btnMenuLateral;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblNombreU;
    @FXML
    private JFXButton btnCerrarSesion;
    @FXML
    private JFXButton btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ingresarEmpleados(ActionEvent event) {
        FlowController.getInstance().goView("Empleados");
    }

    @FXML
    private void ingresarPlanillas(ActionEvent event) {
        FlowController.getInstance().goView("TipoPlanillas");
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        FlowController.getInstance().initialize();
        FlowController.getInstance().goViewInWindow("LogIng2");
        ((Stage) btnCerrarSesion.getScene().getWindow()).close();
    }

    @FXML
    private void salir(ActionEvent event) {
        FlowController.getInstance().salir();
    }
}

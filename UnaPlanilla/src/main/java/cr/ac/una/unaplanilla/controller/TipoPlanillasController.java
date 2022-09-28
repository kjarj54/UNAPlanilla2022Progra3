/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import cr.ac.una.unaplanilla.model.EmpleadoDto;
import cr.ac.una.unaplanilla.model.TipoPlanillaDto;
import cr.ac.una.unaplanilla.service.EmpleadoService;
import cr.ac.una.unaplanilla.service.TipoPlanillaService;
import cr.ac.una.unaplanilla.util.FlowController;
import cr.ac.una.unaplanilla.util.Formato;
import cr.ac.una.unaplanilla.util.Mensaje;
import cr.ac.una.unaplanilla.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TipoPlanillasController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Tab tbpTipoPlanillas;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXCheckBox chkActivo;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescripcion;
    @FXML
    private JFXTextField txtPlanillasMes;
    @FXML
    private Tab tbpInclusionEmpleados;
    @FXML
    private JFXTextField txtIdEmpleado;
    @FXML
    private JFXTextField txtNombreEmpleado;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private TableView<EmpleadoDto> tbvEmpleados;
    @FXML
    private TableColumn<EmpleadoDto, String> tbcCodigo;
    @FXML
    private TableColumn<EmpleadoDto, String> tbcNombre;
    @FXML
    private TableColumn<EmpleadoDto, Boolean> tbcEliminar;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnGuardar;

    private TipoPlanillaDto tipoPlanilla;
    private EmpleadoDto empleado;

    List<Node> requeridosTipoPlanilla = new ArrayList<>();
    @FXML
    private TabPane tbpTipoPlanilla;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtCodigo.setTextFormatter(Formato.getInstance().maxLengthFormat(4));
        txtDescripcion.setTextFormatter(Formato.getInstance().letrasFormat(40));
        txtPlanillasMes.setTextFormatter(Formato.getInstance().integerFormat());
        tipoPlanilla = new TipoPlanillaDto();
        nuevoTipoPlanilla();
        indicarRequeridos();

        empleado = new EmpleadoDto();
        nuevoEmpleado();

        txtIdEmpleado.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombreEmpleado.setTextFormatter(Formato.getInstance().maxLengthFormat(40));
        tbcCodigo.setCellValueFactory(cd -> cd.getValue().empId);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().empNombre);
        
        tbvEmpleados.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends EmpleadoDto> observable, EmpleadoDto oldValue, EmpleadoDto newValue) -> {
            unbindEmpleado();
            if (newValue != null) {
                empleado = newValue;
                bindEmpleado(false);
            }
        });

        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<EmpleadoDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));

        //Adding the Button to the cell
        tbcEliminar.setCellFactory((TableColumn<EmpleadoDto, Boolean> p) -> new ButtonCell());

    }

    public void indicarRequeridos() {
        requeridosTipoPlanilla.clear();
        requeridosTipoPlanilla.addAll(Arrays.asList(txtCodigo, txtDescripcion, txtPlanillasMes));
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridosTipoPlanilla) {
            if (node instanceof JFXTextField && !((JFXTextField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && !((JFXPasswordField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }

    private void bindTipoPlanilla(Boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bind(tipoPlanilla.tplaId);
        }
        txtCodigo.textProperty().bindBidirectional(tipoPlanilla.tplaCodigo);
        txtDescripcion.textProperty().bindBidirectional(tipoPlanilla.tplaDescripcion);
        txtPlanillasMes.textProperty().bindBidirectional(tipoPlanilla.tplaPlaxmes);
        chkActivo.selectedProperty().bindBidirectional(tipoPlanilla.tplaEstado);
    }

    private void unbindTipoPlanilla() {
        txtId.textProperty().unbind();
        txtCodigo.textProperty().unbindBidirectional(tipoPlanilla.tplaCodigo);
        txtDescripcion.textProperty().unbindBidirectional(tipoPlanilla.tplaDescripcion);
        txtPlanillasMes.textProperty().unbindBidirectional(tipoPlanilla.tplaPlaxmes);
        chkActivo.selectedProperty().unbindBidirectional(tipoPlanilla.tplaEstado);
    }

    private void nuevoTipoPlanilla() {
        unbindTipoPlanilla();
        tipoPlanilla = new TipoPlanillaDto();
        bindTipoPlanilla(true);
        txtId.clear();
        txtId.requestFocus();
    }

    private void bindEmpleado(Boolean nuevo) {
        if (!nuevo) {
            txtIdEmpleado.textProperty().bind(this.empleado.empId);
        }
        txtNombreEmpleado.textProperty().bindBidirectional(this.empleado.empNombre);
    }

    private void unbindEmpleado() {
        txtIdEmpleado.textProperty().unbind();
        txtNombreEmpleado.textProperty().unbindBidirectional(empleado.empNombre);
    }

    private void cargarEmpleados() {
        tbvEmpleados.getItems().clear();
        tbvEmpleados.setItems(tipoPlanilla.getEmpleados());
        tbvEmpleados.refresh();
    }

    private void nuevoEmpleado() {
        unbindEmpleado();
        tbvEmpleados.getSelectionModel().select(null);
        empleado = new EmpleadoDto();
        bindEmpleado(true);
        txtIdEmpleado.clear();
        txtIdEmpleado.requestFocus();
    }

    @FXML
    private void buscarTipoPlanilla(ActionEvent event) {
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaPlanillas();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(),true);
        TipoPlanillaDto tipoPlanillasDto = (TipoPlanillaDto) busquedaController.getResultado();
        if (tipoPlanillasDto != null) {
            cargarTipoPlanilla(tipoPlanillasDto.getTplaId());
        }
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void nuevoRegistro(ActionEvent event) {
        if (tbpInclusionEmpleados.isSelected()) {
            nuevoEmpleado();
        } else if (tbpTipoPlanillas.isSelected()) {
            if (new Mensaje().showConfirmation("Limpiar tipo planilla", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
                nuevoTipoPlanilla();
            }
        }
    }

    @FXML
    private void agregarEmpleado(ActionEvent event) {
        if (empleado.getEmpId() == null || empleado.getEmpNombre().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar empleado", getStage(), "Es necesario cargar un empleado para agregarlo a la lista.");
        } else if (tbvEmpleados.getItems() == null || !tbvEmpleados.getItems().stream().anyMatch(a -> a == empleado)) {
            empleado.setModificado(true);
            tbvEmpleados.getItems().add(empleado);
            tbvEmpleados.refresh();
        }

        nuevoEmpleado();
    }

    @FXML
    private void keyPressIdTipoPlanilla(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && txtId.getText() != null && !txtId.getText().isEmpty()) {
            cargarTipoPlanilla(Long.valueOf(txtId.getText()));
        }
    }

    private void cargarTipoPlanilla(Long id) {
        TipoPlanillaService service = new TipoPlanillaService();
        Respuesta respuesta = service.getTipoPlanilla(id);

        if (respuesta.getEstado()) {
            unbindTipoPlanilla();
            tipoPlanilla = (TipoPlanillaDto) respuesta.getResultado("TipoPlanilla");
            bindTipoPlanilla(false);
            validarRequeridos();
            cargarEmpleados();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar tipo planilla", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    private void eliminarRegistro(ActionEvent event) {
        try {
            if (tipoPlanilla.getTplaId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), "Debe cargar el tipo de planilla que desea eliminar.");
            } else {

                TipoPlanillaService service = new TipoPlanillaService();
                Respuesta respuesta = service.eliminarTipoPlanilla(tipoPlanilla.getTplaId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar tipo planilla", getStage(), "Tipo planilla eliminado correctamente.");
                    nuevoTipoPlanilla();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, "Error eliminando el tipo planilla.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), "Ocurrio un error eliminando el tipo planilla.");
        }
    }

    @FXML
    private void guardarRegistro(ActionEvent event) {
        try {
                      
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), invalidos);
            } else {

                TipoPlanillaService service = new TipoPlanillaService();
                Respuesta respuesta = service.guardarTipoPlanilla(tipoPlanilla);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), respuesta.getMensaje());
                } else {
                    unbindTipoPlanilla();
                    tipoPlanilla = (TipoPlanillaDto) respuesta.getResultado("TipoPlanilla");
                    bindTipoPlanilla(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar tipo planilla", getStage(), "Tipo planilla actualizado correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, "Error guardando el tipo de planilla.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), "Ocurrio un error guardando el tipo de planilla.");
        }
    }

    @FXML
    private void selectionChangeTabEmp(Event event) {
        if (tbpInclusionEmpleados.isSelected()) {
            if (tipoPlanilla.getTplaId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Tipo planilla", getStage(), "Debe cargar el tipo de planilla al que desea agregar empleados.");
                tbpTipoPlanilla.getSelectionModel().select(tbpTipoPlanillas);
            }
        } else if (tbpTipoPlanillas.isSelected()) {

        }
    }

    @FXML
    private void keyPressIdEmpleado(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtId.getText().isEmpty()) {
            cargarEmpleado(Long.valueOf(txtIdEmpleado.getText()));
        }
    }

    private void cargarEmpleado(Long id) {
        EmpleadoService service = new EmpleadoService();
        Respuesta respuesta = service.getEmpleado(id);

        if (respuesta.getEstado()) {
            unbindEmpleado();
            empleado = (EmpleadoDto) respuesta.getResultado("Empleado");
            bindEmpleado(false);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empleado", getStage(), respuesta.getMensaje());
        }
    }

    private class ButtonCell extends TableCell<EmpleadoDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");

            cellButton.setOnAction((ActionEvent t) -> {
                EmpleadoDto emp = (EmpleadoDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                tipoPlanilla.getEmpleadosEliminados().add(emp);
                tbvEmpleados.getItems().remove(emp);
                tbvEmpleados.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

}

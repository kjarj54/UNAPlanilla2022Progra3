/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Carlos
 */
public class EmpleadoDto {

    public SimpleStringProperty empId;
    public SimpleStringProperty empNombre;
    public SimpleStringProperty empPapellido;
    public SimpleStringProperty empSapellido;
    public SimpleStringProperty empCedula;
    public ObjectProperty<String> empGenero;
    public SimpleStringProperty empCorreo;
    public SimpleBooleanProperty empAdministrador;
    public SimpleStringProperty empUsuario;
    public SimpleStringProperty empClave;
    public ObjectProperty<LocalDate> empFingreso;
    public ObjectProperty<LocalDate> empFsalida;
    public SimpleBooleanProperty empEstado;
    
    private String token;
    private Boolean modificado;

    public EmpleadoDto() {
        this.modificado = false;
        this.empId = new SimpleStringProperty();
        this.empNombre = new SimpleStringProperty();
        this.empPapellido = new SimpleStringProperty();
        this.empSapellido = new SimpleStringProperty();
        this.empCedula = new SimpleStringProperty();
        this.empGenero = new SimpleObjectProperty("M");
        this.empCorreo = new SimpleStringProperty();
        this.empAdministrador = new SimpleBooleanProperty(false);
        this.empUsuario = new SimpleStringProperty();
        this.empClave = new SimpleStringProperty();
        this.empFingreso = new SimpleObjectProperty();
        this.empFsalida = new SimpleObjectProperty();
        this.empEstado = new SimpleBooleanProperty(true);
    }

    public Long getEmpId() {
        if(empId.get()!=null && !empId.get().isEmpty())
            return Long.valueOf(empId.get());
        else
            return null;
    }

    public void setEmpId(Long empId) {
        this.empId.set(empId.toString());
    }

    public String getEmpNombre() {
        return empNombre.get();
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre.set(empNombre);
    }

    public String getEmpPapellido() {
        return empPapellido.get();
    }

    public void setEmpPapellido(String empPapellido) {
        this.empPapellido.set(empPapellido);
    }

    public String getEmpSapellido() {
        return empSapellido.get();
    }

    public void setEmpSapellido(String empSapellido) {
        this.empSapellido.set(empSapellido);
    }

    public String getEmpCedula() {
        return empCedula.get();
    }

    public void setEmpCedula(String empCedula) {
        this.empCedula.set(empCedula);
    }

    public String getEmpGenero() {
        return empGenero.get();
    }

    public void setEmpGenero(String empGenero) {
        this.empGenero.set(empGenero);
    }

    public String getEmpCorreo() {
        return empCorreo.get();
    }

    public void setEmpCorreo(String empCorreo) {
        this.empCorreo.set(empCorreo);
    }

    public String getEmpAdministrador() {
        return empAdministrador.getValue() ? "S" : "N";
    }

    public void setEmpAdministrador(String empAdministrador) {
        this.empAdministrador.setValue(empAdministrador.equalsIgnoreCase("S"));
    }

    public String getEmpUsuario() {
        return empUsuario.get();
    }

    public void setEmpUsuario(String empUsuario) {
        this.empUsuario.set(empUsuario);
    }

    public String getEmpClave() {
        return empClave.get();
    }

    public void setEmpClave(String empClave) {
        this.empClave.set(empClave);
    }

    public LocalDate getEmpFingreso() {
        return empFingreso.get();
    }

    public void setEmpFingreso(LocalDate empFingreso) {
        this.empFingreso.set(empFingreso);
    }

    public LocalDate getEmpFsalida() {
        return empFsalida.get();
    }

    public void setEmpFsalida(LocalDate empFsalida) {
        this.empFsalida.set(empFsalida);
    }

    public String getEmpEstado() {
        return empEstado.getValue()?"A":"I";
    }

    public void setEmpEstado(String empEstado) {
        this.empEstado.setValue(empEstado.equalsIgnoreCase("A"));
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "EmpleadoDto{" + "empId=" + empId + ", empNombre=" + empNombre + ", empPapellido=" + empPapellido + ", empSapellido=" + empSapellido + ", empCedula=" + empCedula + ", empGenero=" + empGenero + ", empCorreo=" + empCorreo + ", empAdministrador=" + empAdministrador + ", empUsuario=" + empUsuario + ", empClave=" + empClave + ", empFingreso=" + empFingreso + ", empFsalida=" + empFsalida + ", empEstado=" + empEstado + '}';
    }

}

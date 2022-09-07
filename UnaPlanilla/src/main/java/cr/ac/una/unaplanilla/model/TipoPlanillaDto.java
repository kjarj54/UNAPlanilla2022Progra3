/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cbcar
 */
public class TipoPlanillaDto {
    public SimpleStringProperty tplaId;
    public SimpleStringProperty tplaCodigo;
    public SimpleStringProperty tplaDescripcion;
    public SimpleStringProperty tplaPlaxmes;
    private Integer tplaAnoultpla;
    private Integer tplaMesultpla;
    private Integer tplaNumultpla;
    public SimpleBooleanProperty tplaEstado;
    private Boolean modificado;
    ObservableList<EmpleadoDto> empleados;
    List<EmpleadoDto> empleadosEliminados;

    public TipoPlanillaDto() {
        this.modificado = false;
        this.tplaId = new SimpleStringProperty();
        this.tplaCodigo = new SimpleStringProperty();
        this.tplaDescripcion = new SimpleStringProperty();
        this.tplaPlaxmes = new SimpleStringProperty();
        this.tplaEstado = new SimpleBooleanProperty(true);
        empleados = FXCollections.observableArrayList();
        empleadosEliminados = new ArrayList<>();
    }

    public Long getTplaId() {
        if(tplaId.get()!=null && !tplaId.get().isEmpty())
            return Long.valueOf(tplaId.get());
        else
            return null;
    }

    public void setTplaId(Long tplaId) {
        this.tplaId.set(tplaId.toString());
    }

    public String getTplaCodigo() {
        return tplaCodigo.get();
    }

    public void setTplaCodigo(String tplaCodigo) {
        this.tplaCodigo.set(tplaCodigo);
    }

    public String getTplaDescripcion() {
        return tplaDescripcion.get();
    }

    public void setTplaDescripcion(String tplaDescripcion) {
        this.tplaDescripcion.set(tplaDescripcion);
    }

    public Integer getTplaPlaxmes() {
        if(tplaPlaxmes.get()!=null && !tplaPlaxmes.get().isEmpty())
            return Integer.valueOf(tplaPlaxmes.get());
        else
            return null;
    }

    public void setTplaPlaxmes(Integer tplaPlaxmes) {
        this.tplaPlaxmes.set(tplaPlaxmes.toString());
    }

    public Integer getTplaAnoultpla() {
        return tplaAnoultpla;
    }

    public void setTplaAnoultpla(Integer tplaAnoultpla) {
        this.tplaAnoultpla = tplaAnoultpla;
    }

    public Integer getTplaMesultpla() {
        return tplaMesultpla;
    }

    public void setTplaMesultpla(Integer tplaMesultpla) {
        this.tplaMesultpla = tplaMesultpla;
    }

    public Integer getTplaNumultpla() {
        return tplaNumultpla;
    }

    public void setTplaNumultpla(Integer tplaNumultpla) {
        this.tplaNumultpla = tplaNumultpla;
    }

    public String getTplaEstado() {
        return tplaEstado.getValue()?"A":"I";
    }

    public void setTplaEstado(String tplaEstado) {
        this.tplaEstado.setValue(tplaEstado.equalsIgnoreCase("A"));
    }
    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public ObservableList<EmpleadoDto> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ObservableList<EmpleadoDto> empleados) {
        this.empleados = empleados;
    }
    
    public void setEmpleados(List<EmpleadoDto> empleados) {
        this.empleados = FXCollections.observableArrayList(empleados);
    }

    public List<EmpleadoDto> getEmpleadosEliminados() {
        return empleadosEliminados;
    }

    public void setEmpleadosEliminados(List<EmpleadoDto> empleadosEliminados) {
        this.empleadosEliminados = empleadosEliminados;
    }

    @Override
    public String toString() {
        return "TipoPlanillaDto{" + "tplaId=" + tplaId.get() + ", tplaCodigo=" + tplaCodigo.getName() + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanillaws.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cbcar
 */
public class TipoPlanillaDto {

    private Long tplaId;
    public String tplaCodigo;
    public String tplaDescripcion;
    public Integer tplaPlaxmes;
    private Integer tplaAnoultpla;
    private Integer tplaMesultpla;
    private Integer tplaNumultpla;
    public String tplaEstado;
    private Boolean modificado;
    List<EmpleadoDto> empleados;
    List<EmpleadoDto> empleadosEliminados;

    public TipoPlanillaDto() {
        empleados = new ArrayList<>();
        empleadosEliminados = new ArrayList<>();
    }

    public TipoPlanillaDto(TipoPlanilla tipoPlanilla) {
        this();
        this.tplaId = tipoPlanilla.getTplaId();
        this.tplaCodigo = tipoPlanilla.getTplaCodigo();
        this.tplaDescripcion = tipoPlanilla.getTplaDescripcion();
        this.tplaPlaxmes = tipoPlanilla.getTplaPlaxmes();
        this.tplaAnoultpla = tipoPlanilla.getTplaAnoultpla();
        this.tplaMesultpla = tipoPlanilla.getTplaMesultpla();
        this.tplaNumultpla = tipoPlanilla.getTplaNumultpla();
        this.tplaEstado = tipoPlanilla.getTplaEstado();
    }

    public Long getTplaId() {
        return tplaId;
    }

    public void setTplaId(Long tplaId) {
        this.tplaId = tplaId;
    }

    public String getTplaCodigo() {
        return tplaCodigo;
    }

    public void setTplaCodigo(String tplaCodigo) {
        this.tplaCodigo = tplaCodigo;
    }

    public String getTplaDescripcion() {
        return tplaDescripcion;
    }

    public void setTplaDescripcion(String tplaDescripcion) {
        this.tplaDescripcion = tplaDescripcion;
    }

    public Integer getTplaPlaxmes() {
        return tplaPlaxmes;
    }

    public void setTplaPlaxmes(Integer tplaPlaxmes) {
        this.tplaPlaxmes = tplaPlaxmes;
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
        return tplaEstado;
    }

    public void setTplaEstado(String tplaEstado) {
        this.tplaEstado = tplaEstado;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<EmpleadoDto> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDto> empleados) {
        this.empleados = empleados;
    }

    public List<EmpleadoDto> getEmpleadosEliminados() {
        return empleadosEliminados;
    }

    public void setEmpleadosEliminados(List<EmpleadoDto> empleadosEliminados) {
        this.empleadosEliminados = empleadosEliminados;
    }

    @Override
    public String toString() {
        return "TipoPlanillaDto{" + "tplaId=" + tplaId + ", tplaCodigo=" + tplaCodigo + '}';
    }

}

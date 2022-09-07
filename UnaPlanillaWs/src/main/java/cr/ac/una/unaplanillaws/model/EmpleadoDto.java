/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanillaws.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Carlos
 */

public class EmpleadoDto {

    private Long empId;
    private String empNombre;
    private String empPapellido;
    private String empSapellido;
    private String empCedula;
    private String empGenero;
    private String empCorreo;
    private String empAdministrador;
    private String empUsuario;
    private String empClave;
    private LocalDate empFingreso;
    private LocalDate empFsalida;
    private String empEstado;
    private Boolean modificado;
    private String token;
    private LocalDateTime fecha;

    public EmpleadoDto() {
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public EmpleadoDto(Empleado empleado) {
        this();
        this.empId = empleado.getEmpId();
        this.empNombre = empleado.getEmpNombre();
        this.empPapellido = empleado.getEmpPapellido();
        this.empSapellido = empleado.getEmpSapellido();
        this.empCedula = empleado.getEmpCedula();
        this.empGenero = empleado.getEmpGenero();
        this.empCorreo = empleado.getEmpCorreo();
        this.empAdministrador = empleado.getEmpAdministrador();
        this.empUsuario = empleado.getEmpUsuario();
        this.empClave = empleado.getEmpClave();
        this.empFingreso = empleado.getEmpFingreso();
        if (empleado.getEmpFsalida() != null) {
            this.empFsalida = empleado.getEmpFsalida();
        } else {
            this.empFsalida = null;
        }
        this.empEstado = empleado.getEmpEstado();
        this.fecha = LocalDateTime.now();
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpPapellido() {
        return empPapellido;
    }

    public void setEmpPapellido(String empPapellido) {
        this.empPapellido = empPapellido;
    }

    public String getEmpSapellido() {
        return empSapellido;
    }

    public void setEmpSapellido(String empSapellido) {
        this.empSapellido = empSapellido;
    }

    public String getEmpCedula() {
        return empCedula;
    }

    public void setEmpCedula(String empCedula) {
        this.empCedula = empCedula;
    }

    public String getEmpGenero() {
        return empGenero;
    }

    public void setEmpGenero(String empGenero) {
        this.empGenero = empGenero;
    }

    public String getEmpCorreo() {
        return empCorreo;
    }

    public void setEmpCorreo(String empCorreo) {
        this.empCorreo = empCorreo;
    }

    public String getEmpAdministrador() {
        return empAdministrador;
    }

    public void setEmpAdministrador(String empAdministrador) {
        this.empAdministrador = empAdministrador;
    }

    public String getEmpUsuario() {
        return empUsuario;
    }

    public void setEmpUsuario(String empUsuario) {
        this.empUsuario = empUsuario;
    }

    public String getEmpClave() {
        return empClave;
    }

    public void setEmpClave(String empClave) {
        this.empClave = empClave;
    }

    public LocalDate getEmpFingreso() {
        return empFingreso;
    }

    public void setEmpFingreso(LocalDate empFingreso) {
        this.empFingreso = empFingreso;
    }

    public LocalDate getEmpFsalida() {
        return empFsalida;
    }

    public void setEmpFsalida(LocalDate empFsalida) {
        this.empFsalida = empFsalida;
    }

    public String getEmpEstado() {
        return empEstado;
    }

    public void setEmpEstado(String empEstado) {
        this.empEstado = empEstado;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    

    @Override
    public String toString() {
        return "EmpleadoDto{" + "empId=" + empId + ", empNombre=" + empNombre + ", empPapellido=" + empPapellido + ", empSapellido=" + empSapellido + ", empCedula=" + empCedula + ", empGenero=" + empGenero + ", empCorreo=" + empCorreo + ", empAdministrador=" + empAdministrador + ", empUsuario=" + empUsuario + ", empClave=" + empClave + ", empFingreso=" + empFingreso + ", empFsalida=" + empFsalida + ", empEstado=" + empEstado + '}';
    }

}

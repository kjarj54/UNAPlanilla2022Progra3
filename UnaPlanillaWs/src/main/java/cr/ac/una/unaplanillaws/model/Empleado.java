/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanillaws.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "PLAM_EMPLEADOS", schema = "UNA")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByEmpId", query = "SELECT e FROM Empleado e WHERE e.empId = :empId"),
    @NamedQuery(name = "Empleado.findByCedulaNombrePapellido", query = "SELECT e FROM Empleado e WHERE UPPER(e.empNombre) like :nombre and UPPER(e.empCedula) like :cedula and UPPER(e.empPapellido) like :pApellido", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "Empleado.findByUsuClave", query = "SELECT e FROM Empleado e WHERE e.empUsuario = :empUsuario and e.empClave = :empClave", hints = @QueryHint(name = "eclipselink.refresh", value = "true"))
})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PLAM_EMPLEADOS_EMP_ID_GENERATOR", sequenceName = "UNA.PLAM_EMPLEADOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_EMPLEADOS_EMP_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "EMP_ID")
    private Long empId;
    @Basic(optional = false)
    @Column(name = "EMP_NOMBRE")
    private String empNombre;
    @Basic(optional = false)
    @Column(name = "EMP_PAPELLIDO")
    private String empPapellido;
    @Basic(optional = false)
    @Column(name = "EMP_SAPELLIDO")
    private String empSapellido;
    @Basic(optional = false)
    @Column(name = "EMP_CEDULA")
    private String empCedula;
    @Basic(optional = false)
    @Column(name = "EMP_GENERO")
    private String empGenero;
    @Column(name = "EMP_CORREO")
    private String empCorreo;
    @Basic(optional = false)
    @Column(name = "EMP_ADMINISTRADOR")
    private String empAdministrador;
    @Column(name = "EMP_USUARIO")
    private String empUsuario;
    @Column(name = "EMP_CLAVE")
    private String empClave;
    @Basic(optional = false)
    @Column(name = "EMP_FINGRESO")
    private LocalDate empFingreso;
    @Column(name = "EMP_FSALIDA")
    private LocalDate empFsalida;
    @Basic(optional = false)
    @Column(name = "EMP_ESTADO")
    private String empEstado;
    @Version
    @Column(name = "EMP_VERSION")
    private Long empVersion;
    @ManyToMany(mappedBy = "empleados", fetch = FetchType.LAZY)
    private List<TipoPlanilla> tiposPlanillas;

    public Empleado() {
    }

    public Empleado(Long empId) {
        this.empId = empId;
    }

    public Empleado(Long empId, String empNombre, String empPapellido, String empSapellido, String empCedula, String empGenero, String empAdministrador, String empUsuario, String empIdiomasis, LocalDate empFingreso, String empEstado) {
        this.empId = empId;
        this.empNombre = empNombre;
        this.empPapellido = empPapellido;
        this.empSapellido = empSapellido;
        this.empCedula = empCedula;
        this.empGenero = empGenero;
        this.empAdministrador = empAdministrador;
        this.empUsuario = empUsuario;
        this.empFingreso = empFingreso;
        this.empEstado = empEstado;
    }

    public Empleado(EmpleadoDto empleado) {
        this.empId = empleado.getEmpId();
        actualizarEmpleado(empleado);
    }
    
    public void actualizarEmpleado(EmpleadoDto empleado){
        this.empNombre = empleado.getEmpNombre();
        this.empPapellido = empleado.getEmpPapellido();
        this.empSapellido = empleado.getEmpSapellido();
        this.empCedula = empleado.getEmpCedula();
        this.empGenero = empleado.getEmpGenero();
        this.empAdministrador = empleado.getEmpAdministrador();
        this.empUsuario = empleado.getEmpUsuario();
        this.empClave = empleado.getEmpClave();
        this.empCorreo = empleado.getEmpCorreo();
        this.empFingreso = empleado.getEmpFingreso();
        this.empFsalida = empleado.getEmpFsalida();
        this.empEstado = empleado.getEmpEstado();
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

    public Long getEmpVersion() {
        return empVersion;
    }

    public void setEmpVersion(Long empVersion) {
        this.empVersion = empVersion;
    }
    
    public List<TipoPlanilla> getTiposPlanillas() {
        return tiposPlanillas;
    }

    public void setTiposPlanillas(List<TipoPlanilla> tiposPlanillas) {
        this.tiposPlanillas = tiposPlanillas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.co.una.unaplanillaws.model.Empleado[ empId=" + empId + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanillaws.service;

import cr.ac.una.unaplanillaws.model.Empleado;
import cr.ac.una.unaplanillaws.model.EmpleadoDto;
import cr.ac.una.unaplanillaws.model.TipoPlanilla;
import cr.ac.una.unaplanillaws.model.TipoPlanillaDto;
import cr.ac.una.unaplanillaws.util.CodigoRespuesta;
import cr.ac.una.unaplanillaws.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
@Stateless
@LocalBean
public class TipoPlanillaService {

    private static final Logger LOG = Logger.getLogger(TipoPlanillaService.class.getName());

    //TODO
    @PersistenceContext(unitName = "UnaPlanillaWsPU")
    private EntityManager em;

    public Respuesta getTipoPlanilla(Long id) {
        try {
            Query qryTipoPlanilla = em.createNamedQuery("TipoPlanilla.findByTplaId", TipoPlanilla.class);
            qryTipoPlanilla.setParameter("tplaId", id);

            TipoPlanilla tipoPlanilla = (TipoPlanilla) qryTipoPlanilla.getSingleResult();
            TipoPlanillaDto tipoPlanillaDto = new TipoPlanillaDto(tipoPlanilla);
            for (Empleado emp : tipoPlanilla.getEmpleados()) {
                tipoPlanillaDto.getEmpleados().add(new EmpleadoDto(emp));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TipoPlanilla", tipoPlanillaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de planilla con el código ingresado.", "getTipoPlanilla NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de planilla.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta guardarTipoPlanilla(TipoPlanillaDto tipoPlanillaDto) {
        try {
            TipoPlanilla tipoPlanilla;
            if (tipoPlanillaDto.getTplaId() != null && tipoPlanillaDto.getTplaId() > 0) {
                tipoPlanilla = em.find(TipoPlanilla.class, tipoPlanillaDto.getTplaId());
                if (tipoPlanilla == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el tipo de planilla a modificar.", "guardarTipoPlanilla NoResultException");
                }
                tipoPlanilla.actualizarTipoPlanilla(tipoPlanillaDto);
                for (EmpleadoDto emp : tipoPlanillaDto.getEmpleadosEliminados()) {
                    tipoPlanilla.getEmpleados().remove(new Empleado(emp.getEmpId()));
                }
                if (!tipoPlanillaDto.getEmpleados().isEmpty()) {
                    for (EmpleadoDto emp : tipoPlanillaDto.getEmpleados()) {
                        if (emp.getModificado()) {
                            Empleado empleado = em.find(Empleado.class, emp.getEmpId());
                            empleado.getTiposPlanillas().add(tipoPlanilla);
                            tipoPlanilla.getEmpleados().add(empleado);
                        }
                    }
                }
                tipoPlanilla = em.merge(tipoPlanilla);
            } else {
                tipoPlanilla = new TipoPlanilla(tipoPlanillaDto);
                em.persist(tipoPlanilla);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TipoPlanilla", new TipoPlanillaDto(tipoPlanilla));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el tipo de planilla.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el tipo de planilla.", "guardarTipoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta eliminarTipoPlanilla(Long id) {
        try {
            TipoPlanilla tipoPlanilla;
            if (id != null && id > 0) {
                tipoPlanilla = em.find(TipoPlanilla.class, id);
                if (tipoPlanilla == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el tipo de planilla a eliminar.", "eliminarTipoPlanilla NoResultException");
                }
                em.remove(tipoPlanilla);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el tipo de planilla a eliminar.", "eliminarTipoPlanilla NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el tipo de planilla porque tiene relaciones con otros registros.", "eliminarTipoPlanilla " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el tipo de planilla.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el tipo de planilla.", "eliminarTipoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta getPlanillas(String codigo, String descripcion, String planillasPorMes) {
        try {
            Query qryPlanilla = em.createNamedQuery("TipoPlanilla.findAll", TipoPlanilla.class);

            List<TipoPlanilla> planillas = qryPlanilla.getResultList();

            if (!codigo.equals("%")) {
                planillas = planillas.stream().filter((t) -> t.getTplaCodigo().contains(codigo.toLowerCase()) || t.getTplaCodigo().contains(codigo.toUpperCase())).collect(Collectors.toList());
            }
            if (!descripcion.equals("%")) {
                planillas = planillas.stream().filter((t) -> t.getTplaDescripcion().contains(descripcion.toLowerCase()) || t.getTplaDescripcion().contains(descripcion.toUpperCase())).collect(Collectors.toList());

            }
            if (!planillasPorMes.equals("%")) {
                planillas = planillas.stream().filter((t) -> t.getTplaPlaxmes().equals(Integer.parseInt(planillasPorMes))).collect(Collectors.toList());

            }
            //List<TipoPlanillaDto> planillasDto2 = planillasDto.stream().filter((t) -> codigo.equals("%") || t.getTplaCodigo().contains(codigo)).filter((t) -> descripcion.equals("%") || t.getTplaDescripcion().contains(descripcion)).filter((t)-> planillasPorMes.equals("%") || t.getTplaPlaxmes().toString().contains(planillasPorMes)).collect(Collectors.toList());
            List<TipoPlanillaDto> planillasDto = new ArrayList<>();
            for (TipoPlanilla tipoPlanilla : planillas) {
                planillasDto.add(new TipoPlanillaDto(tipoPlanilla));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Planillas", planillasDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen planillas con los criterios ingresados.", "getPlanillas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las planillas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las planillas.", "getPlanillas " + ex.getMessage());
        }
    }
    /*
    private List<TipoPlanillaDto> filter(String drescipcion, List<TipoPlanillaDto> lista){
        return
        
    }
     */
}
